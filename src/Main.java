import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame{

    public Main() {
        initUI();
    }

    private void initUI() {
        add(new board());

        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main x = new Main();
            x.setVisible(true);
        });
        
    }
}