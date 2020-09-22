//Margo Raijmakers
// 17-09-2020

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TicTacToe extends JFrame implements ActionListener{
    private JButton button[] = new JButton[9];
    int player = 1;

    public static void main(String[] args) {
        TicTacToe frame = new TicTacToe();
        frame.setSize(400, 400);
        frame.setTitle("TiTacToe");
        frame.createGUI();
        frame.setVisible(true);
    }

    /** Deze methode maakt de GUI aan
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new GridLayout(3,3));
        window.setBackground(Color.black);
        // de buttons aanmaken en toevoegen aan de window
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            button[i].setText("");
            button[i].setPreferredSize(new Dimension(100, 100));
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
        if (button[Arrays.asList(button).indexOf(event.getSource())].getText().equals("")) {
            if (player % 2 == 0) {
                button[Arrays.asList(button).indexOf(event.getSource())].setText("X");
            } else {
                button[Arrays.asList(button).indexOf(event.getSource())].setText("O");
            }
        }
        if (winHor() | winVer() | winDia()) {
            if (player % 2 == 0) {
                JOptionPane.showMessageDialog(this, "X heeft gewonnen!");
            } else {
                JOptionPane.showMessageDialog(this, "O heeft gewonnen!");
            }
        }
        player++;
    }

    /** Deze methode controleert of iemand horizontaal gewonnen heeft.
     *
     * @return true als iemand horizontaal gewonnen heeft, false als dat niet zo is
     */
    public boolean winHor(){
        int win = 0;
        for (int i = 0; i < 7; i++) {
            if (button[i].getText().equals(button[++i].getText()) &&
                    button[i].getText().equals(button[++i].getText()) && !button[i].getText().equals("")) {
                ++win;
            }
        }
        if(win == 1){
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
        for(int i = 0; i < 3; i++){
            for(int j = 3; j < 6; j++){
                for(int k = 6; k < 9; k++){
                    if(k == j + 3 && k == i + 6){
                        if (button[i].getText().equals(button[j].getText()) &&
                                button[j].getText().equals(button[k].getText()) && !button[i].getText().equals("")){
                            win++;
                    }
                    }
                }
            }
        }
        if(win == 1){
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
                button[4].getText().equals(button[8].getText()) && !button[0].getText().equals("")){
            return true;
        }
        else if (button[2].getText().equals(button[4].getText()) &&
                button[4].getText().equals(button[6].getText()) && !button[2].getText().equals("")){
            return true;
        }
        else {
            return false;
        }
    }

}


