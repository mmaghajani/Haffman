package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Created by mma on 6/5/2016.
 */
public class MyLabel extends JLabel {


    public MyLabel(String s, ImageIcon image, int x) {
        super(s, image, x);// TODO Auto-generated constructor stub
    }

    public MyLabel(String s, int x) {
        // TODO Auto-generated constructor stub
        super(s, x);
    }

    @Override
    public void paint(Graphics g) {

        Image image = new ImageIcon(getClass().getResource("/images/label.jpg")).getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        super.paint(g);
    }
}

