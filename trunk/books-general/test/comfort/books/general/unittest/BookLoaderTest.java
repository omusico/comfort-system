package comfort.books.general.unittest;

import comfort.books.IBookGroup;
import comfort.books.general.IBookLoader;
import comfort.books.general.IGeneralBook;
import comfort.books.general.bookloader.BookLoader;

import static comfort.unittest.util.Random.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class BookLoaderTest {

    private static void testBook(IBookLoader loader, String name, String initialProcess, String actionHandler,
                                 String classPath) {
        IGeneralBook book = loader.loadBook(name);

        Assert.assertNotNull(name, book);
        Assert.assertEquals(name + ": getName() does not match", name, book.getName());
        Assert.assertEquals(name + ": getInitialProcess() does not match", initialProcess, book.getInitialProcess());
        Assert.assertEquals(name + ": getClassPath() does not match", classPath, book.getClassPath());
        Assert.assertEquals(name + ": getActionHandler() does not match", actionHandler, book.getActionHandler());

    }

    private static void testGroup(String scope, IBookGroup group, String name, String caption, IBookGroup parent) {
        Assert.assertEquals(scope + ": BookName does not match", name, group.getBookName());
        Assert.assertEquals(scope + ": Caption does not match", caption, group.getCaption());
        Assert.assertSame(scope + ": Parent does not match", parent, group.getParent());

    }

    private String generateBookNode(String name, String initProc, String actionHandler, String classPath) {
        return String.format(
                "<book name='%s'>" +
                        "<initial-process>%s</initial-process>" +
                        "<action-handler>%s</action-handler>" +
                        "<class-path>%s</class-path>" +
                        "</book>",
                name, initProc, actionHandler, classPath);
    }

    @Test
    public void main() {
        String testBooksPath = randomString();
        String testInitialBook = randomString();
        Object[] options = {"/", ".jar"};
        String testBookSourceExtension = (String) randomOptions(options);

        String bookNames[] = {randomString(), randomString(), randomString()};
        String bookCaptions[] = {randomString(), randomString(), randomString()};
        String initialProcesses[] = {randomString(), randomString(), randomString()};
        String actionHandlers[] = {randomString(), randomString(), randomString()};
        String classPaths[] = {randomString(), randomString(), randomString()};

        String testGroupCaption = randomString();

        String s = "";
        for (int i = 0; i < bookNames.length; i++) {
            s += generateBookNode(
                    bookNames[i],
                    initialProcesses[i],
                    actionHandlers[i],
                    classPaths[i]);
        }

        s = "<?xml version=\"1.0\" encoding=\"windows-1251\" ?>" +
                "<library>" +
                "<initial-book>" + testInitialBook + "</initial-book>" +
                "<books-path>" + testBooksPath + "</books-path>" +
                "<book-source-extension>" + testBookSourceExtension + "</book-source-extension>" +
                "<books>" + s + "</books>" +
                "<groups>" +
                String.format("<group caption='%s' book='%s'/>", bookCaptions[0], bookNames[0]) +
                String.format("<group caption='%s' book='%s'/>", bookCaptions[1], bookNames[1]) +
                String.format("<group caption='%s'>", testGroupCaption) +
                String.format("<group caption='%s' book='%s'/>", bookCaptions[2], bookNames[2]) +
                "</group>" +
                "</groups>" +
                "</library>";
        Reader reader = new StringReader(s);
        IBookLoader loader = new BookLoader(reader);

        Assert.assertEquals("BooksPath does not match", testBooksPath, loader.getBooksPath());
        Assert.assertEquals("InitialBook does not match", testInitialBook, loader.getInitialBook());
        Assert.assertEquals("BookSourceExtension does not match", testBookSourceExtension, loader.getBookSourceExtension());

        for (int i = 0; i < bookNames.length; i++) {
            testBook(loader, bookNames[i], initialProcesses[i], actionHandlers[i], classPaths[i]);
        }

        List<IBookGroup> groups = loader.getGroups();
        Assert.assertNotNull("getGroups", groups);
        Assert.assertEquals("Count of root items", 3, groups.size());

        testGroup("group 1", groups.get(0), bookNames[0], bookCaptions[0], null);
        testGroup("group 2", groups.get(1), bookNames[1], bookCaptions[1], null);
        testGroup("group 3", groups.get(2), "", testGroupCaption, null);

        Assert.assertEquals("Count of items in the group 3", 1, groups.get(2).getChildren().size());

        IBookGroup group = groups.get(2).getChildren().get(0);
        testGroup("group 3/1", group, bookNames[2], bookCaptions[2], groups.get(2));
    }
}