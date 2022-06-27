import java.sql.*;

public class Main {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

  public static void main(String[] args) throws ClassNotFoundException {
    Class.forName(DRIVER);
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
      try (Statement deleteStatement = connection.createStatement()) {
        deleteStatement.execute("DROP TABLE IF EXISTS MOVIES");
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //create table
      try (Statement createTableStatement = connection.createStatement()) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS MOVIES (id INTEGER AUTO_INCREMENT, " +
            "title VARCHAR(255), " +
            "genre VARCHAR(255), " +
            "yearOfRelease INTEGER, " +
            "PRIMARY KEY (id))";
        createTableStatement.execute(createTableQuery);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //insert record
      try (Statement insertItemStatement = connection.createStatement();) {
        String insertStarWarsQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Star Wars', 'Action', 2015)";
        String insertHarryPotterQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Harry Potter', 'Fantasy', 2001)";
        String insertRockyQuery = "INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES ('Rocky', 'Sports', 1979)";
        insertItemStatement.execute(insertStarWarsQuery);
        insertItemStatement.execute(insertHarryPotterQuery);
        insertItemStatement.execute(insertRockyQuery);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //update records
      try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET title = ? WHERE id = ?")) {
        updateStatement.setString(1, "Star Wars Force Awakens");
        updateStatement.setInt(2, 1);
        updateStatement.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //delete records
      try (Statement deleteItemStatement = connection.createStatement()) {
        String deleteItemQuery = "DELETE FROM MOVIES WHERE id = 2";
        deleteItemStatement.execute(deleteItemQuery);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //read record
      try (Statement readItemsStatement = connection.createStatement()) {
        String readItemsQuery = "SELECT * FROM MOVIES";
        ResultSet rs = readItemsStatement.executeQuery(readItemsQuery);

        while (rs.next()) {
          int id = rs.getInt("id");
          String title = rs.getString("title");
          String genre = rs.getString("genre");
          int yearOfRelease = rs.getInt("yearOfRelease");

          System.out.println("#####################");
          System.out.println("Id: " + id);
          System.out.println("Title: " + title);
          System.out.println("Genre: " + genre);
          System.out.println("Year of release: " + yearOfRelease);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}