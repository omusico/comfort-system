package comfort.books.general;

import comfort.books.IBook;
import comfort.books.IBookGroup;
import comfort.books.ILibrary;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

class Library implements ILibrary {

	Library(IBookLoader loader) {
        this.loader = loader;
    }

    private IBookLoader loader;
    private Map<String, IGeneralBook> books = new Hashtable<String, IGeneralBook>();

    public List<IBookGroup> getGroups() {
		return loader.getGroups();
	}

    /**
     * Возвращает книгу по имени.
     * @return книгу или null, если книги с таким именем нет в библиотеке.
     */
	public IGeneralBook getBook(String name) {
		IGeneralBook book = books.get(name);
		if (book == null) {
            book = loader.loadBook(name);
            if (book != null)
                books.put(name, book);
        }
		
        return book;
	}

    public String getInitialBook() {
        return loader.getInitialBook();   
    }

    public String getProcessDefinitionResolver() {
		return ProcessDefinitionResolver.class.getName();
	}

	public String getProcessDefinitionResolverData() {
		return loader.getBooksPath() + "," + loader.getBookSourceExtension();
	}

}