package comfort.books.general.bookloader;

import comfort.books.general.IGeneralBook;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

class BindedBooks {
    @XmlElement(name = "book")
    private List<IGeneralBook> books;
    public List<IGeneralBook> getBooks() {
        return books;
    }    
}