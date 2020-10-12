import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;

public class LibraryFactory {
    private final BooksFactory booksFactory;
    public LibraryController controller;

    @Inject
    public LibraryFactory(@NotNull BooksFactory booksFactory) {
        this.booksFactory = booksFactory;
    }

    public void library(int capacity) throws IOException {
        Library library = new Library(booksFactory.books());
        controller = new LibraryController(capacity, library);
        controller.printInfoToConsole();

    }

}
