package BookStore;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class BookApp extends JFrame{
	
		private JPanel upper = new JPanel();
		private LogoPanel logoPanel = new LogoPanel("C:\\Users\\X_X\\eclipse-workspace\\dovyB00108417AssignJava\\src\\Books.png");
		private JPanel langPanel = new JPanel();
		private JButton selectLang = new JButton("Change Language (LT)");
		private ResourceBundle bun;
		private BooksPanel booksTranslate = new BooksPanel();
		private JTabbedPane tabs = new JTabbedPane();
		private static Bookshopz bn026Y2 = new Bookshopz();	
		
		JLabel programTitle = new JLabel("progTitle");
		
		public static Bookshopz getBN026() {
			return bn026Y2;
		}
		
	public BookApp() {
		
		super("BookStore 0.3x");
		this.setSize(1200, 550);
		this.setMinimumSize(new Dimension(1300, 550));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(upper, BorderLayout.NORTH);
		upper.setLayout(new FlowLayout());
		upper.add(logoPanel);
		upper.add(programTitle = new JLabel("Welcome to Bookshop version: 0.3x"));
		this.add(langPanel, BorderLayout.SOUTH);
		langPanel.setLayout(new FlowLayout());
		langPanel.add(selectLang);
		
		selectLang.addActionListener((ev) -> changeLocale());

		tabs.addTab("Bookshelf", booksTranslate);	
		this.add(tabs, BorderLayout.CENTER);
		this.repaint();	
	}
	
	private void changeLocale() {
		
		Locale locale = Locale.getDefault();

		if (selectLang.getText().contains("Change")) {
			locale = new Locale ("LT");
			
		    } else {
		    	locale = Locale.getDefault();
		    }
		        bun = ResourceBundle.getBundle("res", locale);
				//student panel language change
		        programTitle.setText(bun.getString("progTitle"));
		        booksTranslate.jISBN.setText(bun.getString("langISBN"));
		        booksTranslate.jTitle.setText(bun.getString("langTitle"));
		        booksTranslate.jAuthor.setText(bun.getString("langAuthor"));
		        booksTranslate.jPrice.setText(bun.getString("langPrice"));
		        booksTranslate.jStock.setText(bun.getString("langStock"));
		        //titles language change
		        booksTranslate.borderBooks.setTitle(bun.getString("brdBooks"));
		        booksTranslate.borderDelBooks.setTitle(bun.getString("brdDelBooks"));
		        booksTranslate.borderSubBooks.setTitle(bun.getString("brdSubBooks"));
		        booksTranslate.borderPriceBooks.setTitle(bun.getString("brdPriceBooks"));
		        //Buttons Language
		        booksTranslate.addBook.setText(bun.getString("submit"));
		        booksTranslate.removeBook.setText(bun.getString("delBook"));
		        booksTranslate.updatePrice.setText(bun.getString("upPrice"));
		        this.selectLang.setText(bun.getString("change"));
	}
}


