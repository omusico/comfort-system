package comfort.books.general;

import comfort.books.IBookworm;
import comfort.bpm.jbpm.StateMachineFactory;

public class BookwormFactory {
    /**
     *
     * @param bookViewer Используется для отображения книги.
     * @param loader Загрузчик книг.
     * @return {@link comfort.books.IBookworm}
     */
    public static IBookworm createBookworm(IBookViewer bookViewer, IBookLoader loader) {
		return new Bookworm(StateMachineFactory.createStateMachine(), new UI(bookViewer), new Library(loader));
	}
}