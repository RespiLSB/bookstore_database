package BookStore;

public class Book {
	
	private String ISBN;
	private String title;
	private String firstAuthor;
	private int quantityInStock;
	private double price;
	
	public Book() {
		super();
		quantityInStock = 1;
	}
	
	public Book(String ISBN, String title, String firstAuthor, int quantityInStock, double price) {
		if(ISBN == null || ISBN.equals(""))
			throw new RuntimeException("Book must contain a valid barcode!!");
		else
			this.ISBN = ISBN;
		
		if(title == null || title.equals(""))
		throw new RuntimeException("Book must have a Title");
		else
			this.title = title;
		
		if(firstAuthor == null || firstAuthor.equals(""))
		throw new RuntimeException("Book needs to have an author!");
		else
			this.firstAuthor = firstAuthor;
		
		if(quantityInStock < 0)
			throw new RuntimeException("You need a stock! Quantity in stock: " + quantityInStock);
		else
			this.quantityInStock = quantityInStock;
		
		if(price < 0)
			throw new RuntimeException("Your book requires a price!");
	
		this.price = price;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		
		return title;
	}
	
	public String getAuthor() {
		return firstAuthor;
	}
	
	public int getStock() {
		return quantityInStock;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setISBN(String ISBN) {
		if(ISBN == null || ISBN.equals(""))
			throw new RuntimeException("Book requires a correct barcode!");
		else
			this.ISBN = ISBN;
	}
	
	public void setTitle(String title) {
		if(title == null || title.equals(""))
			throw new RuntimeException("You need a title for the book!");
		this.title = title;
	}
	
	public void setPrice(double price) {
		if(price == 0)
			throw new RuntimeException("You need a price!");
		this.price = price;
	}
	
	public void setAuthor(String firstAuthor) {
		if(firstAuthor == null || firstAuthor.equals(""))
			throw new RuntimeException("The book requires an author!");
		this.firstAuthor = firstAuthor;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
