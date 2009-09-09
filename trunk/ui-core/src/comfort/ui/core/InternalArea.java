package comfort.ui.core;

import comfort.ui.Area;

class InternalArea extends Area {
    private String assignedWidget;
    private String previosWidget;

    public static InternalArea assign(Area area) {
        InternalArea[] children = new InternalArea[area.getChildren().length];
        Area[] sourceChildren = area.getChildren();
        for (int i = 0; i < sourceChildren.length; i++) {
            Area a = sourceChildren[i];
            children[i] = InternalArea.assign(a);
        }
        return new InternalArea(area, children);
    }

    InternalArea(Area area, Area[] children) {
        super(area.getName(), area.getLayout(), area.getAlignment(),
                area.getSize().width, area.getSize().height, children);
    }

    private String areaPath;

    /**
     * Возвращает свойство признака занятости области.
     * Это означает, что если свойтсво выставленно в true, то область занята виджетом
     * @return значение занятости, если область занята, то true, иначе false
     */
    public boolean isUsed() {
        return this.assignedWidget != null;
    }

    public String getAreaPath() {
        return areaPath;
    }

    public void setAreaPath(String areaPath) {
        this.areaPath = areaPath;
    }

    public InternalArea[] getChildren() {
        return (InternalArea[]) super.getChildren();
    }

    public void setAssignedWidget(String widgetName){
        previosWidget = assignedWidget;
        assignedWidget = widgetName;
    }

    public String getAssignedWidget(){
        return assignedWidget;
    }

    public String getPreviousWidget(){
        return previosWidget;
    }
}