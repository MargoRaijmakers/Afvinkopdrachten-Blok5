// Margo Raijmakers
// 05-10-20

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class YANARA extends JFrame implements ActionListener {
    private JButton bladerButton, analyseerButton;
    private JLabel bestandLabel, infoLabel, percLabel;
    private JTextField bestandTextField;
    private JTextArea infoTextArea;
    private JPanel percPanel;
    private JFileChooser fileChooser;
    private BufferedReader inFile;
    private final Font font = new Font("Arial", Font.PLAIN, 20);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        YANARA frame = new YANARA();
        frame.setTitle("YANARA Margo Raijmakers");
        frame.setSize(600, 350);
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * Deze functie maakt de GUI aan
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.BLACK);

        // Het label: 'Bestand:'
        bestandLabel = new JLabel("Bestand:");
        bestandLabel.setForeground(Color.WHITE);
        window.add(bestandLabel);

        // Het textfield waar het gekozen bestand komt te staan
        bestandTextField = new JTextField(25);
        window.add(bestandTextField);

        // De button waar op geklikt wordt om een bestand te kiezen
        bladerButton = new JButton("Blader");
        window.add(bladerButton);
        bladerButton.addActionListener(this);

        //De button waar op geklikt wordt om de sequentie te analyseren
        analyseerButton = new JButton("Analyseer");
        window.add(analyseerButton);
        analyseerButton.addActionListener(this);

        // Het label: 'Informatie:'
        infoLabel = new JLabel("Informatie:");
        infoLabel.setForeground(Color.WHITE);
        window.add(infoLabel);

        // De text area waar informatie over de sequentie komt te staan
        infoTextArea = new JTextArea();
        infoTextArea.setPreferredSize(new Dimension(600, 75));
        infoTextArea.setFont(font);
        window.add(infoTextArea);

        // Het label: 'Percentage:'
        percLabel = new JLabel("Percentage:");
        percLabel.setForeground(Color.WHITE);
        window.add(percLabel);

        // Het panel waar de hoeveelheid polair/apolair op wordt weergegeven
        percPanel = new JPanel();
        percPanel.setPreferredSize(new Dimension(600, 130));
        percPanel.setBackground(Color.cyan);
        window.add(percPanel);
    }

    /**
     * Deze functie leest het bestand in
     */
    public String readFile() {
        StringBuilder sequence = new StringBuilder();
        System.out.println(bestandTextField.getText());
        if (!bestandTextField.getText().equals("")) {
            try {
                // Haalt het bestand op in het text field
                inFile = new BufferedReader(new FileReader(bestandTextField.getText()));
                infoTextArea.setText("");
                String line;
                // Leest het bestand in
                while ((line = inFile.readLine()) != null) {
                    // Voegt de regels in het bestand toe aan sequence
                    sequence.append(line);
                }
                inFile.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "File Error: " + e.toString());
            }
        }
        return sequence.toString();
    }

    /**
     * Deze functie bepaalt of een aminozuur polair of apolair is
     *
     * @param aa een aminozuur in de aminozuursequentie
     * @return true of false, true als het aminozuur polair is en false als het aminozuur apolair is
     */
    private boolean polairOrApolair(String aa) {
        final String polair = "RNDCQEGHKSTY";
        return polair.contains(aa);
    }

    /**
     * Deze functie laat de gebruiker een bestand kiezen en geeft de informatie over de
     * aminozuursequentie in het bestand weer als er op de buttons wordt geklikt
     *
     * @param event het klikken van een button
     */
    public void actionPerformed(ActionEvent event) {
        File selectedFile;
        int reply;
        String sequence = readFile();

        // Als er op de blader button wordt geklikt
        if (event.getSource() == bladerButton) {
            // Laat de gebruiker een bestand kiezen
            fileChooser = new JFileChooser();
            reply = fileChooser.showOpenDialog(this);
            // Voegt het bestand aan het text field toe
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                bestandTextField.setText(selectedFile.getAbsolutePath());
            }

        // Als er op de analyseer button wordt geklikt
        } else if (event.getSource() == analyseerButton) {
            float countpol = 0;
            float countapol = 0;
            int exception = 0;
            for (int i = 0; i < sequence.length(); i++) {
                try {
                    AAchecker.aachecker(Character.toString(sequence.charAt(i)));
                    // Telt het aantal polaire en apolaire aminozuren
                    if (polairOrApolair(Character.toString(sequence.charAt(i)))) {
                        countpol++;
                    } else { countapol++; }
                } catch (notanaa notanaa) {
                    if (exception == 0){
                        infoTextArea.setText(notanaa.toString());
                        exception++;
                        JOptionPane.showMessageDialog(null, "Dit is een corrupt bestand");
                    }
                }
            }
            if (exception == 0) {
                // Geef de informatie over het bestand weer in de text area
                infoTextArea.setText("Alle aminozuren zijn juist \n");
                infoTextArea.append("Het totaal aantal aminozuren: " + sequence.length() + "\n");
                infoTextArea.append((int) (countpol / sequence.length() * 100) + "% van de aminozuren is polair en " +
                        (int) ((countapol / sequence.length()) * 100) + "% is apolair");

                // Teken de rechthoeken met de hoeveelheid polair/apolair
                Graphics paper = percPanel.getGraphics();
                paper.setColor(Color.cyan);
                paper.fillRect(0, 0, 600, 130);
                paper.setColor(Color.blue);
                paper.fillRect(10, 10, (int) (590 * (countpol / sequence.length())), 50);
                paper.setColor(Color.yellow);
                paper.fillRect(10, 70, (int) (590 * (countapol / sequence.length())), 50);
                paper.setColor(Color.red);
                paper.setFont(font);
                paper.drawString("Polair (" + (int) (countpol / sequence.length() * 100) + "%)", 20, 40);
                paper.drawString("Apolair (" + (int) (countapol / sequence.length() * 100) + "%)", 20, 100);
            }
        }
    }
}
