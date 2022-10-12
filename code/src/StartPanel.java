
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartPanel extends JFrame //implements ActionListener
{
    StartPanel() {
        setTitle("防疫模拟器");
        setSize(1600, 900);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GamePanel a = new GamePanel();
        add(a);
        setContentPane(a);

    }

}

