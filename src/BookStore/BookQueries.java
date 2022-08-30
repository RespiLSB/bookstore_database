package BookStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class BookQueries implements AutoCloseable{
	
	private String url;
	private String user;
	private String password;
	private Connection conn;	
		
	public BookQueries() throws SQLException, IOException{
		conn = getConnection();
	}
	
	private void loadProps() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(".\\src\\bookdb.properties"));
		Properties BookshopDB = new Properties();
		BookshopDB.load(reader);
		this.url = BookshopDB.getProperty("url");
		this.user = BookshopDB.getProperty("user");
		this.password = BookshopDB.getProperty("password");
	}
	
	private Connection getConnection() throws SQLException, IOException{
		loadProps();
		return DriverManager.getConnection(url, user, password);
	}
	
		private String ISBN;
		private String title;
		private String firstAuthor;
		private Double price;
		private int quantityInStock;
		
		
	public ArrayList<Book> getAllBooks() throws SQLException{
		PreparedStatement getAllBooks = conn.prepareStatement("SELECT * FROM book");
		ArrayList<Book> books = new ArrayList<>();
		ResultSet result = getAllBooks.executeQuery();
		while(result.next()) {
			String ISBN = result.getString(1);
			String title = result.getString(2);
			String firstAuthor = result.getString(3);
			int quantityInStock = result.getInt(5);
			Double price = result.getDouble(4);
			books.add(new Book(ISBN, title, firstAuthor, quantityInStock, price));
		}
		return books;
	}
	
	public int insertBook(String ISBN, String title, String firstAuthor, double price, int quantityInStock) throws SQLException {
		PreparedStatement insertBook = conn.prepareStatement
				("INSERT INTO book(ISBN, title, firstAuthor, price, quantityInStock)" + " VALUES (?, ?, ?, ?, ?)");
		insertBook.setString(1, ISBN);
		insertBook.setString(2, title);
		insertBook.setString(3, firstAuthor);
		insertBook.setDouble(4, price);
		insertBook.setInt(5, quantityInStock);
		return insertBook.executeUpdate();
		
	}
	
	public int deleteBook(String ISBN) throws SQLException {
		PreparedStatement deleteBook = conn.prepareStatement("DELETE FROM book WHERE ISBN = ?");
		deleteBook.setString(1, ISBN);
		return deleteBook.executeUpdate();
	}
	
	public int updateBookPrice(double price, String ISBN) throws SQLException {
		PreparedStatement updateBookPrice = conn.prepareStatement("UPDATE book SET PRICE = ? where ISBN = ?");
		updateBookPrice.setDouble(1, price);
		updateBookPrice.setString(2, ISBN);
		return updateBookPrice.executeUpdate();
		
	}
	
	public int updateBookStock(int quantityInStock, String ISBN) throws SQLException {
		PreparedStatement updateBookStock = conn.prepareStatement("UPDATE book SET QUANTITYINSTOCK = ? where ISBN = ?");
		updateBookStock.setInt(1, quantityInStock);
		updateBookStock.setString(2, ISBN);
		return updateBookStock.executeUpdate();
	}
	
	public void saveBook(Book newBook) {
		
	}
	
	@Override
	public void close() throws SQLException {
		conn.close();
	}
}

