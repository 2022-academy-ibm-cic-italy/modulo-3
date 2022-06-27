import model.Book;
import model.Chapter;
import model.Publisher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Service service = new Service();

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Mondadori");
        publisher.setCode("1");

        Book book = new Book();
        book.setBookName("Harry Potter");
        book.setIsbn("12345");
        book.setPublisher(publisher);

        List<Chapter> chapters = new ArrayList<>();
        Chapter chapter1 = new Chapter();
        chapter1.setId("1");
        chapter1.setTitle("Inizio");
        Chapter chapter2 = new Chapter();
        chapter2.setId("2");
        chapter2.setTitle("A metà del viaggio");

        chapters.add(chapter1);
        chapters.add(chapter2);

        book.setChapterList(chapters);

        System.out.println(book);
        service.insertBook(book);
    }
}
