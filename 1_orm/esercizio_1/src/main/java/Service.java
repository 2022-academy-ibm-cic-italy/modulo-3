import model.Book;
import model.Chapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service {
    public Connection connection = null;

    public void insertBook(Book book) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","root");

            PreparedStatement pstm = connection.prepareStatement("INSERT INTO PUBLISHER (CODE, PUBLISHER_NAME) VALUES  (?, ?)");
            pstm.setString(1, book.getPublisher().getCode());
            pstm.setString(2, book.getPublisher().getPublisherName());
            pstm.executeUpdate();
            pstm.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES (?,?,?)");
            preparedStatement2.setString(1, book.getIsbn());
            preparedStatement2.setString(2, book.getBookName());
            preparedStatement2.setString(3, book.getPublisher().getCode());
            preparedStatement2.executeUpdate();
            preparedStatement2.close();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CHAPTER (ID, TITLE, BOOK_ISBN) VALUES (?, ?, ?)");
            for (Chapter chapter : book.getChapterList()) {
                preparedStatement.setString(1, chapter.getId());
                preparedStatement.setString(2, chapter.getTitle());
                preparedStatement.setString(3, book.getIsbn());
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}
