import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class Library {
    public ArrayList<Book> books;
    public Gson gson;

    public Library(ArrayList<Book>books) {
        this.books = books;
    }


    public void printBooksByAuthor(Author author) {
        for (Book book : books)
            if (book.getAuthor().getName().equals(author.getName()))
                System.out.println(book.getName());
    }

    public void printJsonSerializedClass(Author author) {
        for (Book book : books)
            if (book.getAuthor().getName().equals(author.getName()))
                System.out.println(gson.toJson(book));
    }
}
