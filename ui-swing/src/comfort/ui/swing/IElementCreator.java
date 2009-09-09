package comfort.ui.swing;

import comfort.exceptions.ProgrammerError;

import java.io.Reader;

/**
 * Создает элемент, используя предоставленный код.
 */
public interface IElementCreator {
    /**
     * Создает елемент по коду.
     * @param code код, определяющий, какой компонент должен быть создан.
     * @return созданный елемент.
     * @throws ProgrammerError если код содержит ошибки.
     */
    Object create(String code) throws ProgrammerError;

    /**
     * Создает елемент по коду с помощью {@link Reader}.
     */
    Object create(Reader code) throws ProgrammerError;
}
