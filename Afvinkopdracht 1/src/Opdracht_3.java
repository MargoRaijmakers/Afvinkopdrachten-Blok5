import javax.swing.*;
import java.awt.*;

public class Opdracht_3 extends JFrame {

    private JTextField textField;
    private JLabel label;

    /**
     * Maakt het frame aan
     */
    public static void main(String[] args) {
        Opdracht_3 frame = new Opdracht_3();
        frame.setSize(500, 500);
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * Vult het frame met een label en textfield
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        // label toevoegen
        label = new JLabel("Input:");
        window.add(label);
        // textfield toevoegen
        textField = new JTextField("Enter input here");
        window.add(textField);

    }
}