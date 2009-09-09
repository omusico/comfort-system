package comfort.ui.core.unittest;

import comfort.ui.IController;
import comfort.ui.IElement;
import comfort.ui.IPage;

import java.util.ArrayList;
import java.util.List;

public class TestController implements IController {
    public static class History {
        private static class WidgetRecord {
            String page;
            String area;
            String widget;
            String widgetCode;
            String status;

            public WidgetRecord(String page, String area, String widget, String status, String widgetCode) {
                this.page = page;
                this.area = area;
                this.widget = widget;
                this.widgetCode = widgetCode;
                this.status = status;
            }
            public boolean equals(Object o) {
                if (!(o instanceof WidgetRecord))
                    return false;

                WidgetRecord r = (WidgetRecord) o;
                if (!page.equals(r.page))
                    return false;
                if (!area.equals(r.area))
                    return false;
                if (!widget.equals(r.widget))
                    return false;
                if (!widgetCode.equals(r.widgetCode))
                    return false;
                if (!status.equals(r.status))
                    return false;

                return true;
            }
        }

        private static class Record {
            String page;
            
            List<WidgetRecord> widgets = new ArrayList<WidgetRecord>();

            private boolean widgetExists(WidgetRecord r) {
                for (WidgetRecord item : widgets) {
                    if (item.equals(r))
                        return true;
                }
                return false;
            }

            public boolean equals(Object o) {
                if (!(o instanceof Record))
                    return false;

                Record r = (Record) o;

                if (r.widgets.size() != widgets.size())
                    return false;

                for (WidgetRecord wr : r.widgets) {
                    if (!widgetExists(wr))
                        return false;
                }


                return true;
            }
        }

        private List<Record> records = new ArrayList<Record>();
        private Record current = null;

        private void prepare(IPage page) {
            if (current == null) {
                current = new Record();
                records.add(current);
            }
            
            if (current.page != null)
                if (!current.page.equals(page.getName())) {
                    current = new Record();
                    records.add(current);
                }

            current.page = page.getName();
        }

        public void activate(IPage page, String areaName, String widgetName, IElement widget) {
            prepare(page);

            WidgetRecord r = new WidgetRecord(page.getName(), areaName, widgetName, "activated",
                    TestBridge.getTestElement(widget).code);

            current.widgets.add(r);
        }

        public void deactivate(IPage page, String areaName, String widgetName, IElement widget) {
            prepare(page);

            WidgetRecord r = new WidgetRecord(page.getName(), areaName, widgetName, "deactivated",
                    TestBridge.getTestElement(widget).code);

            current.widgets.add(r);
        }

        public void clear() {
            records.clear();            
        }

        public String toString() {
            String s = "\nРЅР°С‡Р°Р»Рѕ РёСЃС‚РѕСЂРёРё\n";

            for (Record r : records) {
                s += "\nСЃС‚СЂР°РЅРёС†Р°: " + r.page + "\n";
                for (WidgetRecord wr : r.widgets) {
                    s += String.format("РІРёРґР¶РёС‚ %s %s, РѕР±Р»Р°СЃС‚СЊ %s\n", wr.widget, wr.status, wr.area);
                }
            }

            s += "\nРєРѕРЅРµС† РёСЃС‚РѕСЂРёРё\n";
            return s;
        }

        public boolean equals(Object o) {
            if (!(o instanceof History))
                return false;

            History h = (History) o;
            
            if (records.size() != h.records.size())
                return false;

            for (int i = 0; i < records.size(); i ++) {
                Record r = records.get(i);
                Record r2 = h.records.get(i);

                if (!r.equals(r2))
                    return false;
            }

            return true;
        }
    }

    public void activate(IPage page, String areaName, String widgetName, IElement widget) {
        history.activate(page, areaName, widgetName, widget);
    }

    public void deactivate(IPage page, String areaName, String widgetName, IElement widget) {
        history.deactivate(page, areaName, widgetName, widget);
    }

    public History history = new History();
}
