package comfort.widgets.login;

import javafx.ui.*;
import comfort.widgets.WidgetPanel;

public class UserSelectionDialog extends WidgetPanel {
    override attribute center = getContent();

    public function getContent(): Widget {
        return BorderPanel {
            top:
                
                    Label {
                        text: ##"Select a user, please"

                    }
                
        }
    };
};

WidgetPanel.run(UserSelectionDialog {});
