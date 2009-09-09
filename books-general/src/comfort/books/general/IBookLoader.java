package comfort.books.general;

import comfort.books.IBookGroup;

import java.util.List;

/**
 * Загрузчик книг
 */
public interface IBookLoader {
    /**
     * Загружает книгу по имени.
     * @param name Имя (идентификатор) книги.
     * @return загруженную книгу или null, если нет книги с таким именем.
     */
    IGeneralBook loadBook(String name);

    /**
     * Путь к папке, где находятся книги (источники).
     * @return путь. В качестве разделителя должен использоваться символ '/'.
     */
    String getBooksPath();

    /**
     * Расширение источника книги.
     * Книги могут быть представлены отдельными папкама или jar файлами.
     * @return "/" для папки, ".jar" для jar.
     */
    String getBookSourceExtension();

    /**
     * Имя начальной книги.
     * @return
     */
    String getInitialBook();

    /**
     * Группы книг.
     * @return группы.
     */
    List<IBookGroup> getGroups();
}