package comfort.books.general;

import comfort.bpm.ITransition;

public interface IBookViewer {
    /**
     * Отображает страницу книги.
     * @param pageName Имя страницы, которую нужно показать.
     * @param bookName Имя книги.
     * @param transition Обработчик действий пользователя (таких, как нажатие на кнопку).
     * Страница должна использовать метод {@link comfort.bpm.ITransition#execute}.
     */
    void show(String pageName, String bookName, ITransition transition);
}