import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

//telegram @YanaGlad12 Яна Гладких
public class Application {
    public static void main(String[] args) throws Exception {
        //Пример работы приложения
        final Injector injector = Guice.createInjector(new MainModule());
        LibraryFactory lib = injector.getInstance(LibraryFactory.class);
        lib.library(103);
        lib.controller.takeBook(1);
        lib.controller.printInfoToConsole();
        lib.controller.addBook(new Book("Kniga", new Author("Avtor")));
        lib.controller.printInfoToConsole();
    }
}
