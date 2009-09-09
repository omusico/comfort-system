package comfort.ui.core;

import comfort.exceptions.ProgrammerError;
import comfort.ui.*;

import java.io.Reader;
import java.util.*;

/**
 * Реализация интерфейса {@link comfort.ui.IPageViewer}
 */

public class PageViewer implements IPageViewer {
    private IPage currentPage;
    private IElement root;
    private static final String pathDelimeter = ".";
    private final IBridge bridge;

    private final Map<String, IElement> widgetsMap;
    private final Map<String, InternalArea> areas;
    private final Map<String, IElement> areasPresentationsMap;
    private IController controller;

    /**
     * Cоздает ui элементы областей, сохраняя их для последущего использования.
     * А также создает уникальное имя-путь, который используется для идентификации
     * в Map областей.
     *
     * @param area   текущая область
     * @param parent область предок
     */
    private void recurDefineArea(InternalArea area, InternalArea parent) {

        if (parent != null) {
            final String pathName = area.getName() == null ?
                    "Panel" + String.valueOf(areas.size()) :
                    area.getName();
            area.setAreaPath(new StringBuilder(parent.getAreaPath()).
                    append(pathDelimeter).
                    append(pathName).
                    toString());
        } else {
            area.setAreaPath(area.getName() == null ? "ROOT" : area.getName());
        }
        IElement element = bridge.createPane();
        areas.put(area.getAreaPath(), area);
        areasPresentationsMap.put(area.getAreaPath(), element);
        bridge.setLayout(element, area.getLayout());
        bridge.setSize(element, area.getSize().width, area.getSize().height);
        if (parent != null)
            bridge.place(
                    element,
                    areasPresentationsMap.get(parent.getAreaPath()), area.getAlignment()
            );
        else
            bridge.place(
                    element,
                    root,
                    null
            );

        // Обрабатываем детей области
        for (InternalArea child : area.getChildren()) {
            recurDefineArea(child, area);
        }
    }

    public PageViewer(IElement root, IBridge bridge) {
        this.bridge = bridge;
        this.root = root;
        this.widgetsMap = new Hashtable<String, IElement>();
        this.areas = new Hashtable<String, InternalArea>();
        this.areasPresentationsMap = new Hashtable<String, IElement>();
    }

    public void defineAreas(Area rootArea) {
        areas.clear();
        areasPresentationsMap.clear();

        InternalArea ia = InternalArea.assign(rootArea);
        recurDefineArea(ia, null);
    }

    /**
     * Проверяет области на возможные программерские логические ошибки при назначначении виджетов.
     * Считается ошибкой при назначении виджет одного области, а второй
     * виджет лежит на одной из дочерних областей относительно первой области.
     * Т.е. второй виджет будет фактически скрыт на этой странице.
     * Чтобы не было недоразуменний поэтому поводу и вопросов,  почему не показывается мой виджет
     *
     * @throws ProgrammerError в каком случае?
     */
    private void checkAreasForErrors() throws ProgrammerError {
        Collection<InternalArea> usedAreas = areas.values();
        for (InternalArea source_area : usedAreas)
            for (InternalArea target_area : usedAreas) {
                if (target_area != source_area &&
                        target_area.isUsed() && source_area.isUsed() &&
                        target_area.getAreaPath().startsWith(source_area.getAreaPath()))
                    throw ProgrammerError.general("Found areas that would be hidden because widgets assigning is wrong. Page showing is stopped");
            }
    }

    /**
     * Парсит путь области в список областей - предков
     *
     * @param area область из которой извлекается список предков
     * @return список областей - предков
     */
    private List<InternalArea> getParentsFromPath(InternalArea area) {
        String[] areasNames = area.getAreaPath().split("\\" + pathDelimeter);
        final List<InternalArea> result = new ArrayList<InternalArea>();
        final StringBuilder sb = new StringBuilder();

        for (String pathName : areasNames) {
            sb.append(pathName);
            if (this.areas.get(sb.toString()) == null)
                throw ProgrammerError.general("Area <<%s>> not found", sb.toString());
            result.add(this.areas.get(sb.toString()));
            sb.append(pathDelimeter);
        }
        result.remove(area);
        return result;
    }

    /**
     * Кладет виджет в Map и возвращает ui элемент
     */
    private IElement putWidget(Widget widget) throws ProgrammerError {
        Reader r = widget.getCodeReader();
        IElement el;
        if (r != null)
            el = bridge.createElement(r);
        else
            el = bridge.createElement(widget.getCode());

        if (el == null)
            throw ProgrammerError.general("Can't create widget '%s'", widget.getName());

        widgetsMap.put(widget.getName(), el);

        return el;
    }

    private IWidgetResolver widgetResolver;

    public void setWidgetResolver(IWidgetResolver widgetResolver) {
        this.widgetResolver = widgetResolver;
    }


    public void show(IPage page) throws ProgrammerError {
        if (page == null)
            throw ProgrammerError.nullArgument("page");

        if (currentPage != null && currentPage == page) return;

        if (controller == null)
            throw ProgrammerError.general("Controller must be set at first");

        if (areas.isEmpty())
            throw ProgrammerError.general("Named areas must be defined at first");

        if (widgetResolver == null)
            throw ProgrammerError.general("WidgetResolver must be defined at first");

        // Выставляем желаемый результат в виде назначенных виджетов
        for (InternalArea area : areas.values()) {
            String widgetName = page.getWidget(area.getName());
            area.setAssignedWidget(widgetName);
        }

        // Проверяем устраивает ли нас то что мы хотим получить
        checkAreasForErrors();

        //Деактивация старых виджетов и востановление структуры
        if (currentPage != null)
            for (InternalArea area : areas.values()) {
                if ((area.getPreviousWidget() != null && !area.getPreviousWidget().equals(area.getAssignedWidget()))) {
                    bridge.clear(areasPresentationsMap.get(area.getAreaPath()));
                    controller.deactivate(
                            currentPage,
                            area.getName(),
                            area.getPreviousWidget(),
                            widgetsMap.get(area.getPreviousWidget()));
                }

                if (area.getAssignedWidget() == null) {
                    // востанавливаем структуру
                    IElement areaEl = areasPresentationsMap.get(area.getAreaPath());
                    bridge.clear(areaEl);
                    for (InternalArea child : area.getChildren()) {
                        IElement childEl = areasPresentationsMap.get(child.getAreaPath());
                        bridge.setSize(childEl, child.getSize().width, child.getSize().height);
                        bridge.place(childEl, areaEl, child.getAlignment());
                    }
                }
            }

        //Активация новых виджетов
        for (InternalArea area : areas.values()) {
            if (area.getAssignedWidget() != null && !area.getAssignedWidget().equals(area.getPreviousWidget())) {
                IElement areaEl = areasPresentationsMap.get(area.getAreaPath());
                bridge.clear(areaEl);

                bridge.setLayout(areaEl, area.getLayout());
                bridge.setSize(areaEl, area.getSize().width, area.getSize().height);

                //назначаем виджеты
                String widgetName = area.getAssignedWidget();
                IElement widgetEl = widgetsMap.get(widgetName);
                if (widgetEl == null)
                    widgetEl = putWidget(widgetResolver.resolve(widgetName));

                controller.activate(page, area.getName(), widgetName, widgetEl);
                bridge.place(widgetEl, areaEl, null);
            }
        }

        currentPage = page;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    public void hide() throws ProgrammerError {
        //Деактивация и очистка
        if (currentPage != null)
            for (InternalArea area : areas.values()) {
                area.setAssignedWidget(null);
                if ((area.getPreviousWidget() != null && !area.getPreviousWidget().equals(area.getAssignedWidget()))) {
                    controller.deactivate(
                            currentPage,
                            area.getName(),
                            area.getPreviousWidget(),
                            widgetsMap.get(area.getPreviousWidget()));
                }

                bridge.clear(root);

            }

        currentPage = null;

    }
}