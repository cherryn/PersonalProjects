import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;



public class makePic {
	public static void main(String[] args) throws IOException {
		Scanner stdin = new Scanner(System.in);

		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage img = new BufferedImage(
				(int)screen.getWidth(),
				(int)screen.getHeight(),
				BufferedImage.TYPE_INT_RGB
				);

		// Blocks until tmp.jpg written.
		String title = stdin.nextLine().trim();
		String url = stdin.nextLine().trim();
		String alt1= stdin.nextLine().trim();
		String alt2= stdin.nextLine().trim();
		String alt3= stdin.nextLine().trim();
		String alt4= stdin.nextLine().trim();

		BufferedImage pic = ImageIO.read(new File("tmp.jpg"));
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.drawImage(
				pic,
				img.getWidth()/2-pic.getWidth()/2,
				img.getHeight()/2-pic.getHeight()/2,
				null
				);


		String[] draw = { url, title, alt1,alt2,alt3,alt4 };
		float[] pos = {-0.8f,-0.8f,1.0f,1.3f,1.6f,1.9f};
		int[] size = {30,65,50,50,50,50};
		float offsetP = img.getHeight()/2+pic.getHeight()/2;
		float offsetN=	img.getHeight()/2-pic.getHeight()/2;
		for(int i=0; i<6; i++) {
			if(draw[i].length()==0) continue;
			FontRenderContext frc = g.getFontRenderContext();
			Font font = new Font("Ariel",Font.PLAIN,size[i]);
			TextLayout layout = new TextLayout(draw[i],font,frc);
			Rectangle2D bounds = layout.getBounds();
			float y=(float)bounds.getHeight()*pos[i];
			if( y>0 ) {
				offsetP+=y;
				y=offsetP;
			} else {
				offsetN+=y;
				y=offsetN;
			}
			layout.draw(
					g,
					(float)(img.getWidth()/2-bounds.getCenterX()),
					y
					);
		}
		File f = new File("desktop.jpg");
		ImageIO.write(img,"jpg",f);
	}
}
