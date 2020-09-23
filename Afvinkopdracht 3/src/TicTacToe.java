//Margo Raijmakers
// 17-09-2020

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TicTacToe extends JFrame implements ActionListener{
    private JButton button[] = new JButton[9];
    private JLabel labelPlayer = new JLabel("      X is aan zet"),
            label2 = new JLabel(""), label3 = new JLabel("");
    int player = 1;
    int winMessage = 0;

    public static void main(String[] args) {
        TicTacToe frame = new TicTacToe();
        frame.setSize(400, 550);
        frame.setTitle("TiTacToe");
        frame.createGUI();
        frame.setVisible(true);
    }

    /** Deze methode maakt de GUI aan
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new GridLayout(4, 3, 5, 5));
        window.setBackground(Color.black);
        window.add(label2);
        window.add(labelPlayer);
        labelPlayer.setForeground(Color.white);
        window.add(label3);

        // de buttons aanmaken en toevoegen aan de window
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setText("");
            button[i].setPreferredSize(new Dimension(100, 100));
            button[i].setEnabled(true);
            button[i].addActionListener(this);
            window.add(button[i]);
        }
    }

    /** Deze methode vult en X of O in voor de button die is aangeklikt en geeft een
     * bericht als iemand gewonnen heeft.
     *
     * @param event als er op een button wordt geklikt
     */
    public void actionPerformed(ActionEvent event) {
        // Vult X of O in voor de button waar op geklikt is en verandert het label naar de andere speler
        if (player % 2 == 0) {
            button[Arrays.asList(button).indexOf(event.getSource())].setText("O");
            labelPlayer.setText("      X is aan zet");
        } else {
            button[Arrays.asList(button).indexOf(event.getSource())].setText("X");
            labelPlayer.setText("      O is aan zet");
        }
        button[Arrays.asList(button).indexOf(event.getSource())].setEnabled(false);

        // Telt hoeveel buttons pressed zijn
        int buttonsPressed = 0;
        for (int i = 0; i < 9; i++) {
            if (!button[i].getText().equals("")) {
                ++buttonsPressed;
            }
        }

        // Laat de goede message zien
        if (winMessage == 0) {
            if (winHor() | winVer() | winDia()) {
                if (player % 2 == 0) {
                    JOptionPane.showMessageDialog(this, "O heeft gewonnen!");
                } else {
                    JOptionPane.showMessageDialog(this, "X heeft gewonnen!");
                }
                ++winMessage;
                if (buttonsPressed == 9) {
                    labelPlayer.setText("Het spel is voorbij");
                }
            } else if (buttonsPressed == 9) {
                JOptionPane.showMessageDialog(this, "Niemand heeft gewonnen!");
                labelPlayer.setText("Het spel is voorbij");
                ++winMessage;
            }
        } else {
            if (buttonsPressed == 9) {
                labelPlayer.setText("Het spel is voorbij");
            }
        }
        player++;
    }

    /** Deze methode controleert of iemand horizontaal gewonnen heeft.
     *
     * @return true als iemand horizontaal gewonnen heeft, false als dat niet zo is
     */
    public boolean winHor() {
        int win = 0;

        // zorgt ervoor dat i, j en k respectievelijk 0, 1, 2 en 3, 4, 5, en 6, 7, 8 zijn
        for (int i = 0; i < 9; i += 3) {
            for (int j = 1; j < 9; j += 3) {
                for (int k = 2; k < 9; k += 3) {
                    if (k == i + 2 && k == j + 1) {
                        if (button[i].getText().equals(button[j].getText()) &&
                                button[j].getText().equals(button[k].getText()) && !button[i].getText().equals("")){
                            ++win;
                        }
                    }
                }
            }
        }
        if (win == 1) {
            return true;
        } else {
            return false;
        }
    }

    /** Deze methode controleert of iemand verticaal gewonnen heeft.
     *
     * @return true als iemand verticaal gewonnen heeft, false als dat niet zo is
     */
    public boolean winVer() {
        int win = 0;

        // zorgt ervoor dat i, j en k respectievelijk 0, 3, 6 en 1, 4, 7, en 2, 5, 8 zijn
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                for (int k = 6; k < 9; k++) {
                    if (k == j + 3 && k == i + 6) {
                        if (button[i].getText().equals(button[j].getText()) &&
                                button[j].getText().equals(button[k].getText()) && !button[i].getText().equals("")) {
                            win++;
                        }
                    }
                }
            }
        }
        if (win == 1) {
            return true;
       } else {
            return false;
       }
    }

    /** Deze methode controleert of iemand diagonaal gewonnen heeft.
     *
     * @return true als iemand diagonaal gewonnen heeft, false als dat niet zo is
     */
    public boolean winDia() {
        if (button[0].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[8].getText()) && !button[0].getText().equals("")) {
            return true;
        }
        else if (button[2].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[6].getText()) && !button[2].getText().equals("")) {
            return true;
        }
        else {
            return false;
        }
    }
}
