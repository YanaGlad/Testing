import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public interface BooksFactory {
    @NotNull
    public ArrayList<Book> books();

}
