package comfort.widgets.login;

import javafx.ui.*;
import comfort.widgets.WidgetPanel;

public class KeyboardLogin extends WidgetPanel {
    override attribute center = getContent();

    public function getContent(): Widget {
        var login: String = "";
        var password: String = "";

        var submit = Button {
            text: ##"Log in"
            action: function() {
                executeAction("", ["login", login, "password", password]);
            }
        }
        var passwordField = TextField {
            value: bind password with inverse
            columns: 25
            action: function() {
                submit.requestFocus();
            }
        }
        return FlowPanel {
            content:
                [
                    Label {
                        text: ##"Input your login, please"

                    },
                    TextField {
                        value: bind login with inverse
                        columns: 25
                        action: function() {
                            passwordField.requestFocus();
                        }
                    },
                    Label {
                        text: ##"and password"
                    },
                    passwordField,
                    submit
                ]
        }
    };
};

WidgetPanel.run(KeyboardLogin {});
