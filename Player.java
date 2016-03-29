package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player { //our space ship!

    private int x, y;
    private int dx, dy;
    private int height, width;
    private boolean isVisible;

    private Image image;

    private List<Missile> missiles;

    public Player() {

        ImageIcon reference = new ImageIcon("C:\\Users\\Igor\\Documents\\NetBeansProjects\\GameTutorial\\src\\res\\supersonic.gif");
        image = reference.getImage();

        height = image.getHeight(null);
        width = image.getWidth(null);

        missiles = new ArrayList<Missile>();

        this.x = 100;
        this.y = 100;
    }

    public void move() {

        System.out.println(x + " " + y);
        x = x + dx; //1 and 735
        y = y + dy; //1 and 340

        if (this.x < 1) {
            x = 1;
        }

        if (this.x > 735) {
            x = 735;
        }

        if (this.y < 1) {
            y = 1;
        }
        if (this.y > 340) {
            y = 340;
        }

    }

    public List<Missile> getMissile() {
        return missiles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void shoot() {
        this.missiles.add(new Missile(x + width, y + height / 2)); // where the missile pop up when fired

    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, height, width);

    }

    public void keyPressed(KeyEvent k) {

        int code = k.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            shoot();
        }

        if (code == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (code == KeyEvent.VK_DOWN) {
            dy = 1;
        }

        if (code == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (code == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

    }

    public void keyReleased(KeyEvent k) {

        int code = k.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (code == KeyEvent.VK_DOWN) {
            dy = 0;
        }

        if (code == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (code == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }
}
