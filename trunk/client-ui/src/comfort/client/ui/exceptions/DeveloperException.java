package comfort.client.ui.exceptions;

/**
 * Author: Michael Morozov
 * Date: 28.12.2007
 * Time: 2:56:48
 */
public class DeveloperException extends Exception {

    public DeveloperException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DeveloperException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DeveloperException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DeveloperException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
