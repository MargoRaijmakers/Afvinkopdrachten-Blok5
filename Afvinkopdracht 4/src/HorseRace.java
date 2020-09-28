// Margo Raijmakers
// 24-09-2020

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseRace extends JFrame implements ActionListener {
    Paard p1 = new Paard("Rainbow Dash");
    Paard p2 = new Paard("Pinkie Pie");
    Paard p3 = new Paard("Applejack");
    private JLabel winMessage;
    private JButton button;
    private JPanel panel;

    public static void main(String[] args) {
        HorseRace frame = new HorseRace();
        frame.setSize(1000, 600);
        frame.setTitle("HorseRace Margo Raijmakers");
        frame.createGUI();
        frame.setVisible(true);
    }

    /** Deze methode maakt de GUI aan
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        winMessage = new JLabel("");
        button = new JButton("Start paardenrace");
        panel = new JPanel();
        winMessage.setForeground(Color.white);
        window.setBackground(Color.black);
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(1000, 500));
        button.setEnabled(true);
        button.addActionListener(this);
        window.add(button);
        window.add(winMessage);
        window.add(panel);
    }

    /**Deze methode laat de paarden racen
     *
     * @param event dit start de paardenrace als er op de button wordt geklikt
     */
    public void actionPerformed(ActionEvent event) {
        button.setEnabled(false);
        Graphics paper = panel.getGraphics();
        paper.setColor(Color.black);
        paper.fillRect(900, 50, 10, 400);
        while (p1.getAfstand() < 800 & p2.getAfstand() < 800 & p3.getAfstand() < 800) {
            try {
                Thread.sleep(5);
                p1.loop();
                p2.loop();
                p3.loop();
                paper.setColor(Color.cyan);
                paper.fillRect(100 + p1.getAfstand(), 100, 50, 50);
                paper.setColor(Color.magenta);
                paper.fillRect(100 + p2.getAfstand(), 225, 50, 50);
                paper.setColor(Color.orange);
                paper.fillRect(100 + p3.getAfstand(), 350, 50, 50);

            } catch (InterruptedException e) {
                System.out.println("Pauze interruptie");
            }
        }
        int winnaar = Math.max(p1.getAfstand(), Math.max(p2.getAfstand(), p3.getAfstand()));
        if (winnaar == p1.getAfstand()) {
            winMessage.setText(p1.getNaam() + " (pony " + p1.getPaardNummer() + ") heeft gewonnen");
        } else if (winnaar == p2.getAfstand()) {
            winMessage.setText(p2.getNaam() + " (pony " + p2.getPaardNummer() + ") heeft gewonnen");
        } else {
            winMessage.setText(p3.getNaam() + " (pony " + p3.getPaardNummer() + ") heeft gewonnen");
        }
    }
}