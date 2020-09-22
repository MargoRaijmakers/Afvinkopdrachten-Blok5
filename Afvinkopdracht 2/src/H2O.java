//Margo Raijmakers
//08-09-20

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class H2O extends JFrame implements ActionListener {
    private JTextField textFieldx, textFieldy;
    private JLabel labelx, labely;
    private JButton button;
    private JPanel panel;

    public static void main(String[] args) {
        H2O frame = new H2O();
        frame.setSize(500, 600);
        frame.setTitle("Watermolecuul Margo Raijmakers");
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * Maakt de GUI aan (maakt labels en textfields voor x en y,
     * maakt een button en maakt een panel voor het weergeven van het watermolecuul).
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        labelx = new JLabel("x:");
        labely = new JLabel("y:");
        textFieldx = new JTextField("Voer hier x in");
        textFieldy = new JTextField("Voer hier y in");
        button = new JButton("Teken watermolecuul");
        panel = new JPanel();
        labelx.setForeground(Color.white);
        labely.setForeground(Color.white);
        window.setBackground(Color.black);
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(500, 500));
        button.addActionListener(this);
        window.add(panel);
        window.add(labelx);
        window.add(textFieldx);
        window.add(labely);
        window.add(textFieldy);
        window.add(button);
    }

    /**
     * Tekent het watermolecuul op de aangegeven plaats (x en y voor het bovenste waterstofatoom)
     *
     * @param event dit tekent het watermolecuul als er op de button wordt geklikt.
     */
    public void actionPerformed(ActionEvent event) {
        Graphics paper = panel.getGraphics();
        // oude watermolecuul weghalen
        paper.setColor(Color.white);
        paper.fillRect(0, 0, 500, 500);
        try {
            int x = Integer.parseInt(textFieldx.getText());
            int y = Integer.parseInt(textFieldy.getText());
            // tekenen van het watermolecuul
            paper.setColor(Color.black);
            paper.drawLine(x + 40, y + 40, x + 200, y + 140);
            paper.drawLine(x + 40, y + 240, x + 200, y + 140);
            paper.setColor(Color.blue);
            paper.fillOval(x, y, 80, 80);
            paper.fillOval(x, y + 190, 80, 80);
            paper.setColor(Color.red);
            paper.fillOval(x + 140,y + 80, 120, 120);
        } catch(Exception e){
            System.out.println("Geef voor x en y een getal");
        }

    }
}