package comfort.widgets.main;

import javafx.ui.*;
import comfort.widgets.WidgetPanel;

public class SystemPanel extends WidgetPanel {
    private attribute clock = Clock {};

    public function activate() {
        clock.activate();
    }

    public function deactivate() {
        clock.deactivate();
    }

    override attribute center = bind getContent();

    bound public function getContent(): Widget {
        let logoff = Button {
            text: "Log off"
            action: function() {
                executeAction("logoff", []);
            }
        }
        return GridPanel {
            rows: 1
            columns: 2
            cells: [
            Label {
                        text: ##"System panel"

                    },
            clock
            ]
        }
    };
};

WidgetPanel.run(SystemPanel {});
