import model.Book;
import model.Chapter;
import model.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public Connection connection = null;

    public void insertBook(Book book) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");

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

    //ESERCIZIO 1
    public Book select(String isbn) {
        Book book = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM BOOK, PUBLISHER WHERE BOOK.PUBLISHER_CODE = PUBLISHER.CODE AND BOOK.ISBN = ?");
            stmt.setString(1, isbn);

            ResultSet rs = stmt.executeQuery(); // Il ResultSet rs riceve la risposta del database.

            book = new Book();
            if (rs.next()) {
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("BOOK_NAME"));

                Publisher publisher = new Publisher();
                publisher.setCode(rs.getString("CODE"));
                publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
                book.setPublisher(publisher);
            }

            rs.close();
            stmt.close();

            List<Chapter> chapters = new ArrayList<>();
            stmt = connection.prepareStatement("SELECT * FROM CHAPTER WHERE BOOK_ISBN = ?");
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(rs.getString("TITLE"));
                chapters.add(chapter);
            }
            book.setChapterList(chapters);

            rs.close();
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    //ESERCIZIO 2
    public Book update(Book book) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");

            PreparedStatement stmt = connection.prepareStatement("UPDATE BOOK SET NAME = ? WHERE BOOK.ISBN = ?");
            stmt.setString(1, book.getBookName());
            stmt.setString(2, book.getIsbn());
            stmt.executeUpdate();

            stmt.close();

            stmt = connection.prepareStatement("UPDATE PUBLISHER SET PUBLISHER_NAME = ? WHERE PUBLISHER.ISBN = ?");
            stmt.setString(1, book.getPublisher().getCode());
            stmt.setString(2, book.getPublisher().getPublisherName());
            stmt.executeUpdate();

            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    //ESERCIZIO 3
    public Book delete(Book book) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");

            PreparedStatement stmt = connection.prepareStatement("DELETE BOOK WHERE BOOK.ISBN = ?");
            stmt.setString(1, book.getIsbn());
            stmt.executeUpdate();

            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return book;
    }
}