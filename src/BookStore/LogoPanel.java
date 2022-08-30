package BookStore;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage; 
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class LogoPanel extends JPanel {
	
	private BufferedImage logo;
	
	public LogoPanel(String imagePath) {
		this.setLayout(new FlowLayout());
		
		try {
			logo = ImageIO.read(new File(imagePath));
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			logo.flush();
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		if(logo == null)
			return new Dimension(100, 100);
		else 
			return new Dimension(logo.getWidth(), logo.getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(logo, 5, 5, null);
	}	
}