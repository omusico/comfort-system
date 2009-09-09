package comfort.books;

import java.util.List;

/**
 * Библиотека. 
 */
public interface ILibrary {
	/**
	 * Список книжных групп.
	 */
	List<IBookGroup> getGroups();
}