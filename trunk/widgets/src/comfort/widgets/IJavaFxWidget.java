package comfort.widgets;

import javax.swing.JComponent;

public interface IJavaFxWidget {
    JComponent getComponent();
    void activate();
    void deactivate();
}
