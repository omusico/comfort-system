package comfort.books.general.bookloader;

import comfort.books.IBookGroup;
import comfort.books.general.IBookLoader;
import comfort.books.general.IGeneralBook;
import comfort.exceptions.UnexpectedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.util.List;

/**
 * Загрузчик книг.
 */
public class BookLoader implements IBookLoader {
    private BindedLibrary library;

    private IGeneralBook lookupBook(String name) {
        IGeneralBook res = null;

        for (IGeneralBook book : library.getBooks()) {
            if (book.getName().equals(name)) {
                res =  book;
                break;
            }
        }

        return res;
    }

    /**
     * Создает загрузчик книг.
     *
     * @param reader Предоставляет информацию о книгах.
     */
    public BookLoader(Reader reader) {
        try {
            JAXBContext jaxbContext
                    = JAXBContext.newInstance
                    (BindedLibrary.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();
            library = (BindedLibrary) um.unmarshal(reader);
        } catch (JAXBException e) {
            throw UnexpectedException.exception(e);
        }
    }

    public IGeneralBook loadBook(String name) {
        BindedBook book = (BindedBook) lookupBook(name);
        return book;
    }

    public String getBooksPath() {
        return library.getBooksPath();
    }

    public String getBookSourceExtension() {
        return library.getBookSourceExtension();
    }

    public String getInitialBook() {
        return library.getInitialBook();
    }

    public List<IBookGroup> getGroups() {
       return library.getGroups();
    }
}