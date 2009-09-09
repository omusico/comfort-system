package comfort.books;

/**
 * Читатель.
 */
public interface IBookworm {
    /**
     * Открывает книгу по-умолчанию и запускает ее начальный бизнесс-процесс.
     * @return результат (код) выполнения процесса книги.
     * @see #readBook(String, String)
     */
    String readBook();
	/**
	 * Открывает указанную книгу и запускает ее начальный бизнесс-процесс.
	 * @param book Имя книги.
	 * @return результат (код) выполнения процесса книги.
     * @see #readBook(String, String)
	 */
	String readBook(String book);
	/**
	 * Открывает указанную книгу и запускает указанный бизнесс-процесс.
     * Метод возвращает управление после закрытия книги, т. е. завершения ее начального бизнесс-процесса.
	 * @param book Имя книги. Если null, то открывается книга по-умолчанию.
	 * @param processName Имя процесса. Если null, то запускается начальный бизнесс-процесс.
     * @return результат (код) выполнения процесса книги.
	 */
	String readBook(String book, String processName);

    ILibrary getLibrary();
}