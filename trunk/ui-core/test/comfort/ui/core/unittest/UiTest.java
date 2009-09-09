package comfort.ui.core.unittest;

import comfort.exceptions.ProgrammerError;
import comfort.ui.*;
import comfort.ui.core.PageViewer;
import comfort.unittest.util.Random;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

public class UiTest {

    private static Area area;

    private static final Object borderLayout = new Object();
    private static final Object gridLayout = new Object();
    private static final Object centerAlignment = new Object();
    private static final Object eastAlignment = new Object();
    private static final Object northAlignment = new Object();

    private static Widget[] widgets;

    private static final String selectUserDialogCode = Random.randomString();
    private static final String infoPanelCode = Random.randomString();
    private static final String paroleDialogCode = Random.randomString();
    private static final String mainMenuCode = Random.randomString();
    private static final String billCode = Random.randomString();
    private static final String favoritesCode = Random.randomString();
    private static final String paymentCode = Random.randomString();
    private static final String paymentInfoCode = Random.randomString();
    private static final String billInfoCode = Random.randomString();
    private static final String buttonsCode = Random.randomString();

    private static IPageViewer viewer;
    private static IElement parent;
    private static final IWidgetResolver resolver = new WidgetResolver();

    static class WidgetResolver implements IWidgetResolver {
        public Widget resolve(String name) throws ProgrammerError {
            for (Widget w : widgets) {
                if (w.getName().equals(name))
                    return w;
            }
            throw ProgrammerError.general("The widget '%s' is not found", name);  
        }
    }

    @BeforeClass
    public static void setup() {
        area = Area.createRootArea(null, borderLayout,
                Area.createArea("EntireRightPanel", borderLayout, eastAlignment, 200, 0,
                        Area.createEndArea("RightPanel", centerAlignment, 0, 0),
                        Area.createEndArea("ButtonPanel", eastAlignment, 40, 0)
                ),
                Area.createEndArea("MainPanel", centerAlignment, 0, 0),
                Area.createArea(null, gridLayout, northAlignment, 0, 50,
                        Area.createEndArea("ContextPanel", null, 0, 0),
                        Area.createEndArea("TopPanel", null, 200, 0)
                )
        );

        widgets = new Widget[]{
                new Widget(
                        "SelectUserDialog",
                        new StringReader(selectUserDialogCode)
                ),
                new Widget(
                        "ParoleDialog",
                        new StringReader(paroleDialogCode)
                ),
                new Widget(
                        "InfoPanel",
                        infoPanelCode
                ),
                new Widget(
                        "MainMenu",
                        mainMenuCode
                ),
                new Widget(
                        "Bill",
                        new StringReader(billCode)
                ),
                new Widget(
                        "Favorites",
                        new StringReader(favoritesCode)
                ),
                new Widget(
                        "Payment",
                        paymentCode
                ),
                new Widget(
                        "PaymentInfo",
                        new StringReader(paymentInfoCode)
                ),
                new Widget(
                        "BillInfo",
                        new StringReader(billInfoCode)
                ),
                new Widget(
                        "Buttons",
                        buttonsCode
                )
        };

        IBridge bridge = new TestBridge();
        parent = bridge.createPane();
        viewer = new PageViewer(parent, bridge);
    }

    private IElement testPanelCreation(String pageName, String panelName, IElement parent, int index,
                                       Object layout, Object alignment, int width, int height, int childrenCount) {
        IElement panel = TestBridge.getList(parent).get(index);

        String method;
        if (pageName == null)
            method = "defineAreas";
        else
            method = String.format("show(%s)", pageName);

        assertEquals(String.format("Ошибка в методе %s: layout панели " + panelName, method), layout, TestBridge.getTestElement(panel).layout);
        assertEquals(String.format("Ошибка в методе %s: alignment панели " + panelName, method), alignment, TestBridge.getTestElement(panel).alignment);
        assertEquals(String.format("Ошибка в методе %s: width панели " + panelName, method), width, TestBridge.getTestElement(panel).width);
        assertEquals(String.format("Ошибка в методе %s: height панели " + panelName, method), height, TestBridge.getTestElement(panel).height);

        List<IElement> list = TestBridge.getList(panel);

        assertEquals(String.format("Ошибка в методе %s: Количество панелей в панели " + panelName, method), childrenCount, list.size());

        return panel;
    }


    private void errorTest(IPage page) {
        boolean error = false;
        try {
            viewer.show(page);
        } catch (Throwable e) {
            assertEquals("IPageViewer.show должен вызывать ProgrammerError, если области или виджеты не заданы",
                    ProgrammerError.class, e.getClass());

            error = true;
        }

        if (!error)
            fail("IPageViewer.show должен вызывать ошибку, если области или виджеты не заданы");
    }
  
    private void testWidget(IElement area, String pageName, String areaName, String widgetName,
                            String widgetCode) {
        assertEquals(
                String.format(
                        "Ошибка в IPageViewer.show(%s): область %s должна содержать один элемент - виджет %s.",
                        pageName, areaName, widgetName),
                1, TestBridge.getList(area).size());

        IElement widget = TestBridge.getList(area).get(0);

        assertEquals(
                String.format(
                        "Ошибка в IPageViewer.show(%s): виджет %s должен размещаться по всему контенту области %s, т. е. иметь alignment = null.",
                        pageName, widgetName, areaName),
                null, TestBridge.getTestElement(widget).alignment);

        assertEquals(
                String.format(
                        "Ошибка в IPageViewer.show(%s): неверный код у виджета %s.",
                        pageName, widgetName),
                widgetCode, TestBridge.getTestElement(widget).code);

    }

    private static class Areas {
        IElement entireRightPanel;
        IElement rightPanel;
        IElement buttonPanel;
        IElement mainPanel;
        IElement entireTopPanel;
        IElement contextPanel;
        IElement topPanel;
    }

    private static String getAreaName(Areas areas, IElement area) {
        if (area == areas.entireRightPanel)
            return "EntireRightPanel";

        if (area == areas.rightPanel)
            return "RightPanel";

        if (area == areas.buttonPanel)
            return "ButtonPanel";

        if (area == areas.mainPanel)
            return "MainPanel";

        if (area == areas.contextPanel)
            return "ContextPanel";

        if (area == areas.topPanel)
            return "TopPanel";

        throw new IllegalArgumentException("Ошибка в тесте");
    }

    private Areas testAreas(String pageName,
                           boolean entireRightPanelUsed,
                           boolean rightPanelUsed,
                           boolean buttonPanelUsed,
                           boolean mainPanelUsed,
                           boolean contextPanelUsed,
                           boolean topPanelUsed
                           ) {
        Areas result = new Areas();

        List<IElement> parentList = TestBridge.getList(parent);

        assertEquals(String.format("Ошибка в show(%s): Количество панелей в parent", pageName),
                1, parentList.size());

        IElement root = testPanelCreation(pageName, "root", parent, 0, borderLayout, null, 0, 0, 3);

        result.entireRightPanel = testPanelCreation(
                pageName, "EntireRightPanel", root, 0, borderLayout, eastAlignment, 200, 0,
                entireRightPanelUsed? 1 : rightPanelUsed || buttonPanelUsed? 2 : 0);

        if (rightPanelUsed) {
            assertEquals("Ошибка в тесте: если на RightPanel есть виджет, то EntireRightPanel не должна содержать виджет",
                    false, entireRightPanelUsed);

            result.rightPanel = testPanelCreation(
                    pageName, "RightPanel", result.entireRightPanel, 0, null, centerAlignment, 0, 0, 1);
        }
        if (buttonPanelUsed) {
            assertEquals("Ошибка в тесте: если на ButtonPanel есть виджет, то EntireRightPanel не должна содержать виджет",
                    false, entireRightPanelUsed);

            result.buttonPanel = testPanelCreation(
                    pageName, "ButtonPanel", result.entireRightPanel, 1, null, eastAlignment, 40, 0, 1);
        }
        
        result.mainPanel = testPanelCreation(pageName, "MainPanel", root, 1, null, centerAlignment, 0, 0,
                mainPanelUsed? 1 : 0);

        result.entireTopPanel = testPanelCreation(
                pageName, "entire top panel", root, 2, gridLayout, northAlignment, 0, 50,
                contextPanelUsed || topPanelUsed? 2 : 0);

        result.contextPanel = testPanelCreation(
                pageName, "ContextPanel", result.entireTopPanel, 0, null, null, 0, 0, contextPanelUsed? 1 : 0);
        result.topPanel = testPanelCreation(
                pageName, "TopPanel", result.entireTopPanel, 1, null, null, 200, 0, topPanelUsed? 1 : 0);

        return result;
    }

    private static class PageTestResult {
        IPage page;
        Areas areas;

        public PageTestResult(IPage page, Areas areas) {
            this.page = page;
            this.areas = areas;
        }
    }

    private static class SelectUserPage implements IPage {
        public String getName() {
            return "SelectUser";
        }

        public String getWidget(String area) {
            if ("EntireRightPanel".equals(area))
                return "SelectUserDialog";

            if ("TopPanel".equals(area))
                return "InfoPanel";

            return null;
        }
    }

    private PageTestResult selectUserPageTest() {
        final String pageName = "SelectUserPage";
        System.out.println("show page " + pageName);
        IPage page = new SelectUserPage();
        viewer.show(page);

        // тестим расположение панелей

        Areas areas = testAreas(pageName, true, false, false, false, false, true);

        // тестим расположение виджетов

        testWidget(areas.entireRightPanel, pageName, "EntireRightPanel", "SelectUserDialog", selectUserDialogCode);
        testWidget(areas.topPanel, pageName, "TopPanel", "InfoPanel", infoPanelCode);

        return new PageTestResult(page, areas);
    }

    private static class ParolePage implements IPage {
        public String getName() {
            return "Parole";
        }

        public String getWidget(String area) {
            if ("EntireRightPanel".equals(area))
                return "ParoleDialog";

            if ("TopPanel".equals(area))
                return "InfoPanel";

            return null;
        }
    }

    private PageTestResult parolePageTest() {
        final String pageName = "ParolePage";
        System.out.println("show page " + pageName);
        IPage page = new ParolePage();
        viewer.show(page);

        Areas areas = testAreas(pageName, true, false, false, false, false, true);

        testWidget(areas.entireRightPanel, pageName, "EntireRightPanel", "ParoleDialog", paroleDialogCode);

        testWidget(areas.topPanel, pageName, "TopPanel", "InfoPanel", infoPanelCode);

        return new PageTestResult(page, areas);
    }

    private static class MainMenuPage implements IPage {
        public String getName() {
            return "MainMenu";
        }

        public String getWidget(String area) {
            if ("EntireRightPanel".equals(area))
                return "MainMenu";

            if ("TopPanel".equals(area))
                return "InfoPanel";

            return null;
        }
    }

    private PageTestResult mainMenuPageTest() {
        final String pageName = "MainMenuPage";
        System.out.println("show page " + pageName);
        IPage page = new MainMenuPage();
        viewer.show(page);

        Areas areas = testAreas(pageName, true, false, false, false, false, true);

        testWidget(areas.entireRightPanel, pageName, "EntireRightPanel", "MainMenu", mainMenuCode);
        testWidget(areas.topPanel, pageName, "TopPanel", "InfoPanel", infoPanelCode);

        return new PageTestResult(page, areas);
    }

    private static class BillEditingPage implements IPage {
        public String getName() {
            return "BillEditing";
        }

        public String getWidget(String area) {
            if ("MainPanel".equals(area))
                return "Bill";

            if ("RightPanel".equals(area))
                return "Favorites";

            if ("ButtonPanel".equals(area))
                return "Buttons";

            if ("TopPanel".equals(area))
                return "InfoPanel";

            if ("ContextPanel".equals(area))
                return "BillInfo";

            return null;
        }
    }

    private PageTestResult billEditingPageTest() {
        final String pageName = "BillEditingPage";
        System.out.println("show page " + pageName);
        IPage page = new BillEditingPage();
        viewer.show(page);

        Areas areas = testAreas(pageName, false, true, true, true, true, true);

        testWidget(areas.mainPanel, pageName, "MainPanel", "Bill", billCode);
        testWidget(areas.rightPanel, pageName, "RightPanel", "Favorites", favoritesCode);
        testWidget(areas.buttonPanel, pageName, "ButtonPanel", "Buttons", buttonsCode);
        testWidget(areas.contextPanel, pageName, "ContextPanel", "BillInfo", billInfoCode);
        testWidget(areas.topPanel, pageName, "TopPanel", "InfoPanel", infoPanelCode);

        return new PageTestResult(page, areas);
    }

    private static class PayPage implements IPage {
        public String getName() {
            return "Pay";
        }

        public String getWidget(String area) {
            if ("MainPanel".equals(area))
                return "Bill";

            if ("RightPanel".equals(area))
                return "Payment";

            if ("TopPanel".equals(area))
                return "InfoPanel";

            if ("ContextPanel".equals(area))
                return "PaymentInfo";

            return null;
        }
    }

    private PageTestResult payPageTest() {
        final String pageName = "PayPage";
        System.out.println("show page " + pageName);

        IPage page = new PayPage();
        viewer.show(page);

        Areas areas = testAreas(pageName, false, true, false, true, true, true);

        testWidget(areas.rightPanel, pageName, "RightPanel", "Payment", paymentCode);
        testWidget(areas.mainPanel, pageName, "MainPanel", "Bill", billCode);
        testWidget(areas.contextPanel, pageName, "ContextPanel", "PaymentInfo", paymentInfoCode);
        testWidget(areas.topPanel, pageName, "TopPanel", "InfoPanel", infoPanelCode);

        return new PageTestResult(page, areas);
    }

    private void writeActivate(TestController.History history, PageTestResult res, IElement area,
                                   String widgetName) {
        IElement el = TestBridge.getListElement(area, 0);
        String areaName = getAreaName(res.areas, area);
        history.activate(res.page, areaName, widgetName, el);
    }

    private void writeDeactivate(TestController.History history, PageTestResult res, IElement area,
                                   String widgetName) {
        IElement el = TestBridge.getListElement(area, 0);
        String areaName = getAreaName(res.areas, area);
        history.deactivate(res.page, areaName, widgetName, el);
    }

    private static class HistoryParam {
        IElement area;
        String widget;
        boolean activate;
        boolean deactivate;

        private HistoryParam(IElement area, String widget, boolean activate, boolean deactivate) {
            this.area = area;
            this.widget = widget;
            this.activate = activate;
            this.deactivate = deactivate;
        }

        public static HistoryParam activateOnly(IElement area, String widget) {
            return new HistoryParam(area, widget, true, false);
        }
        public static HistoryParam deactivateOnly(IElement area, String widget) {
            return new HistoryParam(area, widget, false, true);               
        }
        public static HistoryParam activateAndDeactivate(IElement area, String widget) {
            return new HistoryParam(area, widget, true, true);
        }
    }
    private void writeHistory(TestController.History history, PageTestResult res, HistoryParam... params) {
        for (HistoryParam p : params) {
            if (p.activate)
                writeActivate(history, res, p.area, p.widget);
        }

        for (HistoryParam p : params) {
            if (p.deactivate)
                writeDeactivate(history, res, p.area, p.widget);
        }
    }

    private TestController.History goThroughPages() {
        TestController.History history = new TestController.History();

        // Проходим по страницам от выбора пользователя до оплаты
        PageTestResult res = selectUserPageTest();

        writeHistory(history, res,
                // деактивируем только SelectUserDialog, т. к. InfoPanel также присутствует на следующей странице
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "SelectUserDialog"),
                HistoryParam.activateOnly(res.areas.topPanel, "InfoPanel")
                );


        res = parolePageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "ParoleDialog")
                );


        res = mainMenuPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "MainMenu")
                );


        res = billEditingPageTest();

        writeHistory(history, res,
                HistoryParam.activateOnly(res.areas.mainPanel, "Bill"),
                HistoryParam.activateAndDeactivate(res.areas.rightPanel, "Favorites"),
                HistoryParam.activateAndDeactivate(res.areas.buttonPanel, "Buttons"),
                HistoryParam.activateAndDeactivate(res.areas.contextPanel, "BillInfo")
                );


        res = payPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.rightPanel, "Payment"),
                HistoryParam.activateAndDeactivate(res.areas.contextPanel, "PaymentInfo")
                );

        // Возврат на страницу счета
        res = billEditingPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.rightPanel, "Favorites"),
                HistoryParam.activateAndDeactivate(res.areas.buttonPanel, "Buttons"),
                HistoryParam.activateAndDeactivate(res.areas.contextPanel, "BillInfo")
                );


        // Опять оплата
        res = payPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.rightPanel, "Payment"),
                HistoryParam.activateAndDeactivate(res.areas.contextPanel, "PaymentInfo"),
                // на следующей странице счет пропадет
                HistoryParam.deactivateOnly(res.areas.mainPanel, "Bill")
                );


        // Нажали логин - попали на выбор пользователя, снова заходим в книгу, но теперь сразу на страницу оплаты
        res = selectUserPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "SelectUserDialog")
                );


        res = parolePageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "ParoleDialog")
                );


        res = mainMenuPageTest();

        writeHistory(history, res,
                HistoryParam.activateAndDeactivate(res.areas.entireRightPanel, "MainMenu")
                );


        res = payPageTest();

        writeHistory(history, res,
                // последняя страница - деактивируем все
                HistoryParam.activateAndDeactivate(res.areas.mainPanel, "Bill"),
                HistoryParam.activateAndDeactivate(res.areas.rightPanel, "Payment"),
                HistoryParam.activateAndDeactivate(res.areas.contextPanel, "PaymentInfo"),
                HistoryParam.deactivateOnly(res.areas.topPanel, "InfoPanel")
                );

        viewer.hide();

        return history;
    }

    @Test
    public void test() {
        errorTest(new SelectUserPage());

        viewer.defineAreas(area);

        errorTest(new ParolePage());

        viewer.setWidgetResolver(resolver);

        TestController c = new TestController();
        viewer.setController(c);

        TestController.History h = goThroughPages();

        
        assertEquals("Ошибка в show: неправильные вызовы контроллера", h, c.history);

    }

    @Test
    public void bridgeTest() {
        TestBridge b = new TestBridge();
        String s = Random.randomString();
        IElement el = b.createElement(new StringReader(s));
        assertEquals("Ошибка в TestBridge.createElement(Reader): неверное значение code", s, TestBridge.getTestElement(el).code);
    }

    @Test
    public void controllerTest() {
        TestController c = new TestController();
        TestController c2 = new TestController();
        TestController.History h = new TestController.History();

        c.activate(new SelectUserPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));
        c.activate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));

        c.deactivate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));

        c.activate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));
        c.activate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));

        c.deactivate(new MainMenuPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));
        c.deactivate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));
        c.deactivate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));

        //
        c2.activate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));
        c2.activate(new SelectUserPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));

        c2.deactivate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));

        c2.activate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));
        c2.activate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));

        c2.deactivate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));
        c2.deactivate(new MainMenuPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));
        c2.deactivate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));

        //
        h.activate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));
        h.activate(new SelectUserPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));

        h.deactivate(new SelectUserPage(), "area 2", "widget 2", new TestBridge.TestElement("code 2"));

        h.activate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));
        h.activate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));

        h.deactivate(new MainMenuPage(), "area 1", "widget 1", new TestBridge.TestElement("code 1"));
        h.deactivate(new MainMenuPage(), "area 2", "widget 3", new TestBridge.TestElement("code 3"));
        h.deactivate(new MainMenuPage(), "area 4", "widget 4", new TestBridge.TestElement("code 4"));

        assertEquals("Ошибка в реализации TestController: c2 != c", c.history, c2.history);
        assertEquals("Ошибка в реализации TestController: h != c", c.history, h);
    }
}
