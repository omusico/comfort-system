package comfort.books;

import java.util.List;

/**
 * Книжная группа.
 */
public interface IBookGroup {
	/**
	 * Название.
     * @return локализованное название.
	 */
	String getCaption();

	/**
	 * Имя книги.
     * @return имя (идентификатор) книги. Может быть пустой строкой, если группа не связана с книгой.
	 */
	String getBookName();

    /**
     * Группа, которая содержит эту группу.
     * @return группу-родителя или null, если это корневая группа.
     */
    IBookGroup getParent();

	List<IBookGroup> getChildren();

}