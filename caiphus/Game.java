import javax.swing.*;

public class Game {

    public static void main(String[] args) {

        JFrame Windows_F = new JFrame("Game one");
        Windows_F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Windows_F.setContentPane(new GamePanel());

        Windows_F.pack();
        Windows_F.setVisible(true);



    }
}