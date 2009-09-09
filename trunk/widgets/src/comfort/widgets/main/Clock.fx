package comfort.widgets.main;

import comfort.widgets.*;
import javafx.ui.*;
import javafx.ui.canvas.*;
import java.util.Calendar;
import java.lang.Math;
import java.lang.System;
import java.util.TimerTask;
import java.util.Timer;

public class Clock extends WidgetPanel {
    override attribute center = bind getContent();

    bound public function getContent(): Widget {
        return Canvas {
            content:
            [
                node
            ]
        }
    };

    private attribute node = ClockNode {};

    public function activate() {
        node.running = true;
    }

    public function deactivate() {
        node.running = false;
    }

}

class Task extends TimerTask {
    public attribute minutes: Integer;
    public attribute seconds: Integer;
    public attribute hours: Integer;

    public function run() {
        var now: Calendar = Calendar.getInstance();
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
        hours = now.get(Calendar.HOUR_OF_DAY);
        System.out.println("Time is {hours}:{%02d minutes}:{%02d seconds}");
    }
}

class ClockNode extends CompositeNode {
    private attribute timer: Timer;
    private attribute task = Task {};

    public attribute running: Boolean on replace oldValue = newValue {
        if (newValue) {
            if (timer == null) {
                timer = new Timer();
                timer.schedule(task, 0, 1000);
            }
        } else {
            if (timer <> null) {
                timer.cancel();
                timer = null;
            }
        }
    }

    public function composeNode(): Node {
        return Group {
            transform: [ Transform.translate(5, 5), Transform.scale(0.25, 0.25) ]
            var secs: Number = bind task.seconds
            var mins: Number = bind task.minutes + secs/60
            var hrs: Number = bind task.hours + mins/60

            content:
            [
            ImageView {
                transform: []
                image: Image {url: "comfort/widgets/main/images/clock_face.png"}
            },
            Group {
                var hourHand =
                ImageView {
                    transform: bind Transform.rotate(hrs * 30, 255, 245)
                    image: Image {url: "comfort/widgets/main/images/hour_hand.png"}
                }

                var minuteHand =
                ImageView {
                    transform: bind Transform.rotate(mins * 6, 255, 245)
                    image: Image {url: "comfort/widgets/main/images/minute_hand.png"}
                }

                var secondHand =
                ImageView {
                    transform: bind Transform.rotate(secs * 6, 255, 245)
                    image: Image {url: "comfort/widgets/main/images/second_hand.png"}
                }

                content: [hourHand, minuteHand, secondHand]
            },
            ImageView {
                transform: []
                image: Image {url: "comfort/widgets/main/images/pin.png"}
            }]
        };
    }
}

WidgetPanel.run(Clock { });