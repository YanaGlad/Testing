import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BooksFactory.class).to(FileBookFactory.class);
    }
}
