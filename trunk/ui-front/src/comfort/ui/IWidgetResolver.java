package comfort.ui;

import comfort.exceptions.ProgrammerError;

public interface IWidgetResolver {
    /**
     * Создает элемент по имени.
     * @param name имя элемента.
     * @return созданный элемент.
     * @throws ProgrammerError если нет элемента с указанным именем.
     */
    Widget resolve(String name) throws ProgrammerError;
}