package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameWindows extends JFrame {

    public GameWindows() { //our jframes!

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Developed by Igor Shiota", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        file.add(about);
        file.add(exit);

        menuBar.add(file);
        setJMenuBar(menuBar);

        add(new Stage());
        setTitle("Game Tutorial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 470);
        setLocationRelativeTo(null); //makes the window appear in the center
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new GameWindows();
    }

}
