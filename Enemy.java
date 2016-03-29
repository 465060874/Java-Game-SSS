package Game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy {

    private Image image;
    private int x, y;
    private int width, height;
    private boolean isVisible;

    private static final int SCREEN_WIDTH = 800;
    private static final int SPEED = 1;

    private static int counter = 0;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon reference;

        if (counter++ % 3 == 0) {

            reference = new ImageIcon("C:\\Users\\Igor\\Documents\\NetBeansProjects\\GameTutorial\\src\\res\\bomb.gif");
        } else {
            reference = new ImageIcon("C:\\Users\\Igor\\Documents\\NetBeansProjects\\GameTutorial\\src\\res\\bat.gif");
        }

        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        isVisible = true;

    }

    public void move() {

        if (this.x < 0) {
            this.x = SCREEN_WIDTH;
        } else {
            this.x -= SPEED;
        }

    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, width, height);

    }

}
