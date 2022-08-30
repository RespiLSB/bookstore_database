package BookStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class BooksPanel extends JPanel{
		
		//LAYOUT PANELS
		
		private JTable tableArea;
		private JButton viewAll;
		private JTextField addISBN;
		private JTextField addTitle;
		private JTextField addAuthor;
		private JTextField addPrice;
		private JTextField addStock;
		private JButton findBook;
		private String ISBN;
		private String title;
		private String author;
		private double price;
		private int quantityInStock;
		private boolean goodInput = true;
		
		String [] colNames = {"ISBN", "Title", "Author", "Price", "In Stock"};
		JButton removeBook = new JButton("Delete Book");
		JButton addBook = new JButton("Submit Book");
		JButton updatePrice = new JButton("Update Price");
		JButton updateStock = new JButton("Update Stock");
		JLabel jISBN = new JLabel("langISBN");
		JLabel jTitle = new JLabel("langTitle");
		JLabel jAuthor = new JLabel("langAuthor");
		JLabel jPrice = new JLabel("langPrice");
		JLabel jStock = new JLabel("langPrice");
		JPanel displayPanel, addPanel, deletePanel, submitPanel, pricePanel;
		
		//the data
		private JTable justTable;
		static DefaultTableModel infTable;
		private BookQueries bq;
		
		//Borders
		TitledBorder borderBooks = new TitledBorder("brdBooks");
		TitledBorder borderDelBooks = new TitledBorder("brdDelBooks");
		TitledBorder borderSubBooks = new TitledBorder("brdSubBooks");
		TitledBorder borderPriceBooks = new TitledBorder("brdPriceBooks");
		
		
		public BooksPanel() {
			
			//Options in the JFrame
			displayPanel = new JPanel();
			displayPanel.setLayout(new FlowLayout());
			this.add(addPanel = new JPanel(), BorderLayout.CENTER);
			addPanel.setLayout(new FlowLayout());
			this.add(submitPanel = new JPanel(), BorderLayout.NORTH);
			submitPanel.setLayout(new FlowLayout());
			this.add(deletePanel = new JPanel(), BorderLayout.SOUTH);
			deletePanel.setLayout(new FlowLayout());
			this.add(pricePanel = new JPanel(), BorderLayout.SOUTH);
			pricePanel.setLayout(new FlowLayout());
			
			infTable = new DefaultTableModel(colNames,0); justTable = new JTable(infTable);
			JScrollPane scrollPane = new JScrollPane(justTable);
			scrollPane.setPreferredSize(new Dimension(850, 200));
			
			theTable();
			
			//TABLE AREA
			displayPanel.add(scrollPane);
			displayPanel.repaint(); 
			displayPanel.revalidate();
			
			this.add(displayPanel, BorderLayout.NORTH);
			
			/////// OPTIONS PANEL ///////
			addPanel.setBorder(borderBooks = new TitledBorder("Bookstore Options"));
			addPanel.add(jISBN = new JLabel("ISBN: "));
			addPanel.add(addISBN = new JTextField(10));
			addPanel.add(jTitle = new JLabel("Title: "));
			addPanel.add(addTitle = new JTextField(10));
			addPanel.add(jAuthor = new JLabel("Author: "));
			addPanel.add(addAuthor = new JTextField(10));
			addPanel.add(jPrice = new JLabel("Price: "));
			addPanel.add(addPrice = new JTextField(5));
			addPanel.add(jStock = new JLabel("Stock: "));
			addPanel.add(addStock = new JTextField(3));
			
			/////// SUBMIT PANEL ///////
			submitPanel.setBorder(borderSubBooks = new TitledBorder("Submit Area"));
			submitPanel.add(addBook = new JButton("Submit Book"));
			addBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String Barcode = addISBN.getText();
					if (!Pattern.matches("[0-9]{3}\\-[0-9]{1}\\-[0-9]{2}\\-[0-9]{6}\\-[0-9]{1}", Barcode)) {
						JOptionPane.showMessageDialog(getRootPane(), "Please enter a valid ISBN! Format: XXX-X-XX-XXXXXX-X");
						System.out.println("DEBUG: Book adding initiated.");
						goodInput = false;
					} else { 
						ISBN = (addISBN.getText());
					} 	
					String BookTitle = addTitle.getText();
					if (BookTitle == null || BookTitle.equals("")) {
						JOptionPane.showMessageDialog(getRootPane(), "Please enter the book title!");
						goodInput = false;
					} else {
						title = (addTitle.getText());
					}
					String FirstAuthor = addAuthor.getText();
					if (FirstAuthor == null || FirstAuthor.equals("")) {
						JOptionPane.showMessageDialog(getRootPane(), "Please enter the name of the book author!");
						goodInput = false;
					} else {
						author = (addAuthor.getText());
					}
					String qty = addStock.getText();
					if (qty == null || qty.equals("")) {
						JOptionPane.showMessageDialog(getRootPane(), "Please enter a quantity!");
						goodInput = false;
					} else {
						price = Double.parseDouble(addPrice.getText());
					}
					String bookPrice = addPrice.getText();
					if (bookPrice == null | bookPrice.equals("")) {
						JOptionPane.showMessageDialog(getRootPane(), "Price cannot be null!");
						System.out.println("DEBUG: Error setting price!");
							
					} else {
						quantityInStock = Integer.parseInt(addStock.getText());
					}
						try(BookQueries add = new BookQueries()) {
							add.insertBook(ISBN, title, author, price, quantityInStock);
							JOptionPane.showMessageDialog(getRootPane(), "Book added to database!");
							System.out.println("DEBUG: Successfully added to database: " + Barcode);
							theTable();
						} catch (SQLException | IOException e) {
							e.printStackTrace();
						}
					}
			});
			
			/////// DELETE PANEL ///////
			submitPanel.add(removeBook = new JButton("Delete Book"));
			removeBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedRow = justTable.getSelectedRow();
					String ISBN = justTable.getModel().getValueAt(selectedRow, 0).toString();
					try(BookQueries add = new BookQueries()){
						add.deleteBook(ISBN);
						JOptionPane.showMessageDialog(getRootPane(), "Book removed!");
						System.out.println("DEBUG: Book removed : " + ISBN);
						theTable();
						} catch (SQLException | IOException e) {
							JOptionPane.showMessageDialog(getRootPane(), "An error has occured trying to delete book!");
							System.out.println("DEBUG: Error when deleting!");
							e.printStackTrace();
					}
				}
			});
			
			/////// UPDATE PANEl ///////
			submitPanel.add(updatePrice = new JButton("Update Price"));
			updatePrice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedRow = justTable.getSelectedRow();
					String prc = JOptionPane.showInputDialog(justTable, "Enter a new price.", null);
					String ISBN = justTable.getModel().getValueAt(selectedRow,  0).toString();
					System.out.println("DEBUG: ISBN To update " + ISBN + " Price set to: " + prc);
					try(BookQueries add = new BookQueries()) {
						//add.updateBookPrice(price);
						double price = Double.parseDouble(prc);
						add.updateBookPrice(price, ISBN);
						JOptionPane.showMessageDialog(getRootPane(), "Price updated!");
						theTable();
						} catch (SQLException | IOException e) {
							JOptionPane.showMessageDialog(getRootPane(), "An error has occured when updating!");
							System.out.println("DEBUG: Error when updating!");
							e.printStackTrace();
					}
				} 
		});
			
			submitPanel.add(updateStock = new JButton("Update Stock"));
			updateStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedRow = justTable.getSelectedRow();
					String stk = JOptionPane.showInputDialog(justTable, "Enter a new ammount in stock.", null);
					String ISBN = justTable.getModel().getValueAt(selectedRow, 0).toString();
					System.out.println("DEBUG: ISBN To update " + ISBN + " Stock set to: " + stk);
					try(BookQueries add = new BookQueries()) {
						int quantityInStock = Integer.parseInt(stk);
						add.updateBookStock(quantityInStock, ISBN);
						JOptionPane.showMessageDialog(getRootPane(), "Stock updated!");
						theTable();
						} catch (SQLException | IOException e) {
							JOptionPane.showMessageDialog(getRootPane(), "And error has occured when updating!");
							System.out.println("DEBUG: Error when updating!");
							e.printStackTrace();
					}
				}
				
			});
			
			
		}
		//Display Table.
		public void theTable() {
			
			for (int i = (justTable.getRowCount() - 1); i >= 0; i--) {
			infTable.removeRow(i); 
		}
			
		Object[] rowData = new Object[5];
		
		try(BookQueries queries = new BookQueries()) {	
		for (Book b : (queries.getAllBooks())) {
			rowData[0] = b.getISBN(); 
			rowData[1] = b.getTitle();
			rowData[2] = b.getAuthor();
			rowData[3] = b.getPrice();
			rowData[4] = b.getStock();
			infTable.addRow(rowData);
			}
		} catch(SQLException | IOException ex) {
			JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred when trying to load records from the database!");
			ex.printStackTrace();
		}
	}
		@Override
		public Dimension getPreferredSize() {
			
			return new Dimension(this.getWidth(), this.getHeight());
		}
}