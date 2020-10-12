public class LibraryController {
    private Book[] bookDots;

    public LibraryController(int numberOfDots, Library library) {
        bookDots = new Book[numberOfDots];

        if (numberOfDots < library.books.size() - 1) {
            throw new IndexOutOfBoundsException("Книг в библиотеке больше, чем свободных ячеек");
        }
        for (int i = 0; i < numberOfDots; i++) {
            if (i < library.books.size()) bookDots[i] = library.books.get(i);
            else bookDots[i] = null;
        }
    }

    public void printInfoToConsole() {
        System.out.println("Количество ячеек в библиотеке: " + bookDots.length);
        int counter = 0;
        for (Book bookDot : bookDots) {
            if (bookDot != null) counter++;
        }
        System.out.println("Количество книг, хранящихся в библиотеке: " + counter);
    }

    public void addBook(Book book) throws Exception {
        for (int i = 0; i < bookDots.length; i++) {
            if (bookDots[i] == null) {
                bookDots[i] = book;
                break;
            }
        }
        throw new Exception("Свободных ячеек нет!");

    }

    public Book takeBook(int num) {
        if (bookDots[num] == null)
            throw new NullPointerException("В ячейке с номером " + num + " нет книги");

        try {
            System.out.println("Была взята книга с названием " + bookDots[num].getName()
                    + ", автором " + bookDots[num].getAuthor().getName()
                    + ", хранящаяся в ячейке с номером " + num);
        } catch (Exception e) {
            System.out.println("Не получилось вывести информацию о книге");
        }

        Book result = bookDots[num];
        bookDots[num] = null;
        return result;
    }

    public Book[] getBookDots() {
        return bookDots;
    }
}
