import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryTest {

    //В файле books.txt 100 книг. Значения подобраны, исходя из данного значения

    @Test(expected = IndexOutOfBoundsException.class)
    public void checkIfCapacityIsLessThanBooksCount() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(90);
    }

    @Test
    public void checkIfBooksStayInOrder() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(120);

        int expected = 100;
        int actual = 0;
        for (int i = 0; i < factory.controller.getBookDots().length; i++) {
            if (factory.controller.getBookDots()[i] != null) actual++;
            else break;
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFileBookFactoryReturnsListOfBooks() throws IOException {
        FileBookFactory fileBookFactory = Mockito.mock(FileBookFactory.class);
        Mockito.when(fileBookFactory.books()).thenReturn(new ArrayList<Book>());
    }

    @Test
    public void checkTakeBook() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = Mockito.spy(injector.getInstance(LibraryFactory.class));
        factory.library(200);

        Mockito.doAnswer(invocation -> new Book(Mockito.anyString(), new Author(Mockito.anyString()))).when(factory).controller.takeBook(Mockito.anyInt());
    }

    @Test(expected = NullPointerException.class)
    public void checkExeptIfDotIsNull() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(120);
        factory.controller.takeBook(20);
        factory.controller.takeBook(20);
    }

    @Test
    public void returnsCorrectBook() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(120);

        Book expected = factory.controller.getBookDots()[20], actual;
        actual = factory.controller.takeBook(20);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkIfPutIntoFirstEmpty() throws Exception {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(120);

        Book expected = new Book("Name", new Author("Author101")), actual;
        factory.controller.addBook(expected);
        actual = factory.controller.getBookDots()[100];

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void checkExceptIfNoEmptyDots() throws Exception {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(100);
        factory.controller.addBook(new Book("Name", new Author("Author101")));

    }

    @Test
    public void checkPrinting() throws IOException {
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory factory = injector.getInstance(LibraryFactory.class);
        factory.library(120);
        factory.controller.printInfoToConsole();
    }
}
