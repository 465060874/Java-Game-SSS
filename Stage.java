package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Stage extends JPanel implements ActionListener {

    private Image background;
    private Player player;
    private Timer timer;

    private boolean inGame;

    private List<Enemy> enemies;

    /*private int[][] coordinates = {{2380, 29}, {2600, 59}, {1380, 89},
    {780, 109}, {580, 139}, {880, 239}, {790, 259},
    {760, 50}, {790, 150}, {1980, 209}, {560, 45}, {510, 70},
    {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30},
    {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220},
    {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30},
    {920, 300}, {856, 328}, {456, 320}};*/
    private int[][] coordinates = new int[30][30]; 

    Random rand = new Random(); // the commented code above is for the exact spawn points for the enemies. I attempted to make it random.

    public Stage() {

        setFocusable(true); // this is very important, otherwise our shop would not move, because the Stage class needs to be in focus to respond to a command (it s like selecting the window you want to interact with, otherwise your clicking or typing would not work)
        setDoubleBuffered(true); //this will prevent the painting glitches, since the next image will always be ready beforehand
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("C:\\Users\\Igor\\Documents\\NetBeansProjects\\GameTutorial\\src\\res\\background.jpg");
        background = referencia.getImage();

        player = new Player();

        inGame = true;

        initializeEnemies();

        timer = new Timer(5, this); //each 5 miliseconds, the timer will call the Stage class and the actionPerformed
        timer.start();

    }

    public void initializeEnemies() {
        enemies = new ArrayList<Enemy>();

        for (int[] c : coordinates) {

            enemies.add(new Enemy(rand.nextInt(2000) + 800, rand.nextInt(350)));

        }
    }

    public void paint(Graphics g) {

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(background, 0, 0, null); //args: what I want to paint (the background), 0, 0 goes because the background will fill the entire window, and null, because I don't want to treat the background image at all (i.e it will be static)

        if (inGame) {
            graficos.drawImage(player.getImage(), player.getX(), player.getY(), this); // 3 first args shows we want to print the ship and will reference it because I want to treat it everytime it moves (i.e it won't be static)

            List<Missile> missiles = player.getMissile();

            for (int i = 0; i < missiles.size(); i++) {

                Missile m = (Missile) missiles.get(i);
                graficos.drawImage(m.getImage(), m.getX(), m.getY(), this);

            }

            for (int i = 0; i < enemies.size(); i++) {

                Enemy en = enemies.get(i);
                graficos.drawImage(en.getImage(), en.getX(), en.getY(), this);

            }

            graficos.setColor(Color.white);
            graficos.drawString("Enemies: " + enemies.size(), 5, 15);

        } else {

            ImageIcon gameOver = new ImageIcon("C:\\Users\\Igor\\Documents\\NetBeansProjects\\GameTutorial\\src\\res\\gameover2.png");
            graficos.drawImage(gameOver.getImage(), 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        player.move();
        checkColision();
        repaint();

        if (enemies.isEmpty()) {
            inGame = false;
        }

        List<Missile> missiles = player.getMissile();

        for (int i = 0; i < missiles.size(); i++) {

            Missile m = (Missile) missiles.get(i);

            if (m.isVisible()) {
                m.mexer();
            } else {
                missiles.remove(i);
            }

        }

        for (int i = 0; i < enemies.size(); i++) {

            Enemy en = enemies.get(i);

            if (en.isVisible()) {
                en.move();
            } else {
                enemies.remove(i);
            }

        }

        player.move();
        repaint();

    }

    public void checkColision() {

        Rectangle playerBound = player.getBounds();
        Rectangle enemyBound;
        Rectangle missileBound;

        for (int i = 0; i < enemies.size(); i++) {

            Enemy tempEnemy = enemies.get(i);
            enemyBound = tempEnemy.getBounds();

            if (playerBound.intersects(enemyBound)) {

                player.setVisible(false);
                tempEnemy.setVisible(false);
                inGame = false;
            }

        }

        List<Missile> missiles = player.getMissile(); //watch tutorial 8.2 for explanation again

        for (int i = 0; i < missiles.size(); i++) {

            Missile tempMissile = missiles.get(i);
            missileBound = tempMissile.getBounds();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy tempEnemy = enemies.get(j);
                enemyBound = tempEnemy.getBounds();

                if (missileBound.intersects(enemyBound)) {
                    tempEnemy.setVisible(false);
                    tempMissile.setVisible(false);
                }
            }

        }

    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                inGame = true;
                player = new Player();
                initializeEnemies();
            }

            player.keyPressed(e); //previously was calling the parent class super.keyPressed. Changed it so it could call the method created in the Player class
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

    }

}
