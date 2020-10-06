//Margo Raijmakers
//28-09-20

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class One2Three extends JFrame implements ActionListener {
    private JTextField aa1, aa3;

    public static void main(String[] args) {
        One2Three frame = new One2Three();
        frame.setSize(500, 100);
        frame.setTitle("One2Three Margo Raijmakers");
        frame.createGUI();
        frame.setVisible(true);
    }

    /** Deze methode maakt de GUI aan
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        aa1 = new JTextField("1-lettercode");
        aa3 = new JTextField("3-lettercode");
        JButton button = new JButton("Translate");
        window.setBackground(Color.black);
        button.addActionListener(this);
        window.add(aa1);
        window.add(button);
        window.add(aa3);
    }

    /** Deze methode vertaalt de 1-lettercode naar de 3-lettercode van een aminozuur
     *
     * @param event vertaalt de 1-lettercode naar de 3-lettercode als er op de button wordt geklikt
     */
    public void actionPerformed(ActionEvent event) {
        StringBuilder result = new StringBuilder();
        if (!aa1.getText().equals("1-lettercode") & !aa1.getText().equals("")) {
            try {
                for (int i = 0; i < aa1.getText().length(); i++) {
                    result.append(Translator.one2three(String.valueOf(aa1.getText().toUpperCase().charAt(i))));
                    result.append("-"); }
                result = result.deleteCharAt(result.length() - 1);
                aa3.setText(String.valueOf(result));
            } catch (NotAnAA notAnAA) { System.out.println(notAnAA); }
        } else {
            System.out.println("Geef voor de 1-lettercode een waarde op");
        }
    }
}


