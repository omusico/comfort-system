package comfort.widgets;

import javafx.ui.*;

import java.lang.System;
import java.lang.Object;

public abstract class WidgetPanel extends BorderPanel, IJavaFxWidget {
    public function activate() {
    }

    public function deactivate() {
    }

    public function executeAction(action: String, data: Object[]) {
        var params = "";
        var i = 0;
        while (i < sizeof data) {
            if (i > 0) {
                params = "{params}, ";
            }

            params = "{params}{data[i]}={data[i + 1]}";
            i += 2;
        }

        System.out.println(
            "{if (action <> null and action <> "") then "action: {action}" else "default action"}; ".concat(
                "{if (params <> "") then "params: {params}" else "no params"}"
            )
        );
    }
    
    public static function run(widget: WidgetPanel): Frame {
        widget.activate();
        return Frame {
            onClose: function(): Void {
                try {
                    widget.deactivate();
                } finally {
                    java.lang.System.exit(0);
                }
            }
            height: 600, width: 800;
            content: widget;
            background: Color.LAVENDER;
            visible: true            
        }
    }

}
