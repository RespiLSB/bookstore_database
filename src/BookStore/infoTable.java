package BookStore;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class infoTable  extends JFrame{

  //CONSTRUCT
    public infoTable()
    {

    //TITLE,CLOSE
        setDefaultCloseOperation(EXIT_ON_CLOSE);

      //SET DATA
        Object [][] data = 
				{{"some ISBN", "Some title", "some author", 34.5, 3}, 
				{"some ISBN2", "Some title2", "some author2", 34.0, 7}};

      //COLUMN HEADERS
        String [] colNames = {"ISBN", "Title", "Author", "Price", "In Stock"};;

        //CREATE TABLE
        JTable table = new JTable(data,colNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,80));

        //CUSTOM WIDTH
        TableColumn col0 = table.getColumnModel().getColumn(0);
        TableColumn col1 = table.getColumnModel().getColumn(1);
        TableColumn col2 = table.getColumnModel().getColumn(2);

        col0.setPreferredWidth(5);
        col1.setPreferredWidth(300);
        col2.setPreferredWidth(150);

        //SCROLLPNAE
        JScrollPane pane = new JScrollPane(table);
        getContentPane().add(pane);

    }

    //MAIN METHOD
    public static void main(String[] args) {
    	infoTable c = new infoTable();
      c.setVisible(true);
    }
}