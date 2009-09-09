package comfort.books.general.bookloader;

import comfort.books.IBookGroup;
import comfort.books.general.IGeneralBook;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlRootElement(name = "library")
class BindedLibrary {
    @XmlElement(name = "books-path")
    private String booksPath;
    @XmlElement(name = "book-source-extension")
    private String bookSourceExtension;
    @XmlElement(name = "initial-book")
    private String initialBook;

    @XmlElement(name = "groups")
    private BindedGroups rootGroup;
    public List<IBookGroup> getGroups() {
        return rootGroup.getGroups();
    }

    public String getBooksPath() {
        return booksPath;
    }

    public String getBookSourceExtension() {
        return bookSourceExtension;
    }

    public String getInitialBook() {
        return initialBook;
    }

    @XmlElement(name = "books")
    private BindedBooks books;
    public List<IGeneralBook> getBooks() {
        return books.getBooks();
    }
}