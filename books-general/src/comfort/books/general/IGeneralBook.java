package comfort.books.general;

import comfort.books.IBook;

public interface IGeneralBook extends IBook {

    /**
     * Путь к папке или jar архиву, где находятся бинарные файлы классов книги.
     * @return путь, относительно источника книги (см. {@link IBookLoader#getBookSourceExtension()}).
     * Если путь оканчивается символом '/' - это папка, иначе - jar.
     * Может быть пустой строкой, в этом случае, классы будут искаться в <tt>CLASSPATH</tt>.
     */
	String getClassPath();
	
    /**
     * Полное имя класса обработчика действий.
     * Указанный класс должен иметь публичный конструктор по-умолчанию.
     * Для каждого действия должен быть предоставлен публичный метод, имя которого совпадает с именем действия.
     * Метод должен иметь следующий параметр: {@code Map&lt;String, Object&gt; data}.
     * Метод должен вернуть имя перехода.
     * Бинарный файл класса должен лежать в {@link #getClassPath() ClassPath}.
     * @see comfort.bpm.IActionHandler#execute
     * @return полное имя класса обработчика действий.
     */
	String getActionHandler();

    /**
     * Начальный процесса книги.
     * @return имя (идентификатор) начального процесса книги.
     */
    String getInitialProcess();
}