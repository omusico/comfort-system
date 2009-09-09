package comfort.ui.swing.javafx;

import comfort.exceptions.ProgrammerError;
import comfort.exceptions.UnexpectedException;
import comfort.ui.swing.IElementCreator;

import java.io.Reader;

/**
 * Реализует интерфейс {@link comfort.ui.swing.IElementCreator} с помощью технологии JavaFX
 */
public class JavaFxElementCreator implements IElementCreator {
    /**
     * Создает элемент, используя код на JavaFx.
     *
     * @param className полное имя класса JavaFx елемента.
     * @return созданный элемент.
     * @throws ProgrammerError если класс с указанным именем не найден.
     */
    public Object create(String className) throws ProgrammerError {
        Class cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw ProgrammerError.classNotFound(className);
        }
        Object res;
        try {
            res = cls.newInstance();
        } catch (InstantiationException ex) {
            throw UnexpectedException.exception(ex);
        } catch (IllegalAccessException ex) {
            throw UnexpectedException.exception(ex);
        }

        return res;
    }

    @Deprecated
    public Object create(Reader code) throws ProgrammerError {
        throw ProgrammerError.notImplemented(getClass(), "create");
    }
}
