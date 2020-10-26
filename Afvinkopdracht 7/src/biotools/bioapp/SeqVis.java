// Margo Raijmakers
// 05-10-20

package biotools.bioapp;

import biotools.bioseq.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Het stuk code voor het selecteren en inlezen van bestanden is gekopieerd van GitHub van owe5a van hanbioinformatica

public class SeqVis extends JFrame implements ActionListener {
    private JButton bladerButton, visButton;
    private JLabel bestandLabel, seqLabel, visLabel;
    private JTextField bestandTextField;
    private JTextArea infoTextArea;
    private JPanel colPanel;
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

        SeqVis frame = new SeqVis();
        frame.setTitle("SeqVis Margo Raijmakers");
        frame.setSize(700, 350);
        frame.createGUI();
        frame.setVisible(true);
    }


    /**
     * Deze methode maakt de GUI aan.
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.BLACK);

        // Het label: 'Geef een bestand ...'
        visLabel = new JLabel("Geef een bestand met DNA, RNA of een peptide om te visualiseren");
        visLabel.setForeground(Color.WHITE);
        visLabel.setFont(font);
        window.add(visLabel);

        // Het label: 'Bestand:'
        bestandLabel = new JLabel("Bestand:");
        bestandLabel.setForeground(Color.WHITE);
        bestandLabel.setFont(font);
        window.add(bestandLabel);

        // Het textfield waar het gekozen bestand komt te staan
        bestandTextField = new JTextField(40);
        window.add(bestandTextField);

        // De button waar op geklikt wordt om een bestand te kiezen
        bladerButton = new JButton("Blader");
        window.add(bladerButton);
        bladerButton.addActionListener(this);

        // Het label: 'Sequentie:'
        seqLabel = new JLabel("Sequentie:");
        seqLabel.setForeground(Color.WHITE);
        seqLabel.setFont(font);
        window.add(seqLabel);

        // De text area waar informatie over de sequentie komt te staan
        infoTextArea = new JTextArea();
        infoTextArea.setPreferredSize(new Dimension(600, 100));
        infoTextArea.setFont(font);
        window.add(infoTextArea);

        //De button waar op geklikt wordt om de sequentie te visualiseren
        visButton = new JButton("Visualiseer");
        window.add(visButton);
        visButton.addActionListener(this);

        // Het panel waar de visualisatie van de nucleotiden/aminozuren op wordt weergegeven
        colPanel = new JPanel();
        colPanel.setPreferredSize(new Dimension(600, 50));
        colPanel.setBackground(Color.black);
        window.add(colPanel);
    }


    /**
     * Deze methode leest het bestand in.
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
                    // Voegt de regels in het bestand toe aan de text area
                    infoTextArea.append(line + "\n");
                    // Voegt de regels in het bestand toe aan sequence
                    sequence.append(line);
                }
                inFile.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "File Error: " + e.toString());
            }
        }
        return sequence.toString();
    }


    /**
     * Deze methode checkt of de opgegeven sequentie een DNA-, RNA- of aminozuursequentie is.
     * Als het geen van alle is dan geeft het een NoValidSeq error.
     *
     * @param sequence de sequentie uit het bestand
     * @throws NoValidSeq error voor als de opgegeven sequentie ongeldig is
     */
    public void checkSeq(String sequence) throws NoValidSeq {
        // Als de opgegeven sequentie een DNA-sequentie is
        if (sequence.toUpperCase().matches("^[ATCG]*$")) {
            DNA dna = new DNA();
            dna.setSeq(sequence);
            DNAVis(dna);

        // Als de opgegeven sequentie een RNA-sequentie is
        } else if (sequence.toUpperCase().matches("^[AUCG]*$")) {
            RNA rna = new RNA();
            rna.setSeq(sequence);
            RNAVis(rna);

        // Als de opgegeven sequentie een aminozuursequentie is
        } else if (sequence.toUpperCase().matches("^[ARNDCFQEGHILKMPSTWYV]*$")) {
            Peptide peptide = new Peptide();
            peptide.setSeq(sequence);
            PeptideVis(peptide);

        // Als de opgegeven sequentie geen geldige sequentie is
        } else {
            throw new NoValidSeq("Dit is een ongeldige sequentie");
        }
    }


    /**
     * Deze methode laat de informatie over de DNA-sequentie zien.
     *
     * @param dna het DNA object
     */
    public void DNAVis(DNA dna){
        infoTextArea.append("Dit is een DNA-sequentie\n");
        infoTextArea.append("Lengte: " + dna.getLength() + "\n");
        infoTextArea.append("GC percentage: " + dna.getGCperc() + "%\n");

        // Kijkt per nucleotide welke kleur de rechthoek moet zijn,
        // hoe groot de rechthoek moet zijn en waar de rechthoek geplaatst moet worden
        Graphics paper = colPanel.getGraphics();
        for (int i = 0; i < dna.getLength(); i++) {
            if (dna.getColor(i).toString().equals("red")) {
                paper.setColor(Color.red);
            } else {
                paper.setColor(Color.yellow);
            }
            paper.fillRect(i * 600 / dna.getLength(), 0, 600 / dna.getLength(), 50);
        }
    }


    /**
     * Deze methode laat de informatie over de RNA-sequentie zien.
     *
     * @param rna het RNA object
     */
    public void RNAVis(RNA rna) {
        infoTextArea.append("Dit is een RNA-sequentie\n");
        infoTextArea.append("Lengte: " + rna.getLength() + "\n");

        // Kijkt per nucleotide welke kleur de rechthoek moet zijn,
        // hoe groot de rechthoek moet zijn en waar de rechthoek geplaatst moet worden
        Graphics paper = colPanel.getGraphics();
        for (int i = 0; i < rna.getLength(); i++) {
            if (rna.getColor(i).toString().equals("red")) {
                paper.setColor(Color.red);
            } else if (rna.getColor(i).toString().equals("yellow")){
                paper.setColor(Color.yellow);
            } else {
                paper.setColor(Color.blue);
            }
            paper.fillRect(i * 600 / rna.getLength(), 0, 600 / rna.getLength(), 50);
        }
    }


    /**
     * Deze methode laat de informatie over de aminozuursequentie zien.
     *
     * @param peptide het peptide object
     */
    public void PeptideVis(Peptide peptide) {
        infoTextArea.append("Dit is een aminozuursequentie\n");
        infoTextArea.append("Lengte: " + peptide.getLength() + "\n");

        // Kijkt per aminozuur welke kleur de rechthoek moet zijn,
        // hoe groot de rechthoek moet zijn en waar de rechthoek geplaatst moet worden
        Graphics paper = colPanel.getGraphics();
        for (int i = 0; i < peptide.getLength(); i++) {
            if (peptide.getColor(i).toString().equals("blue")) {
                paper.setColor(Color.blue);
            } else if (peptide.getColor(i).toString().equals("red")){
                paper.setColor(Color.red);
            } else {
                paper.setColor(Color.gray);
            }
            paper.fillRect(i * 600 / peptide.getLength(), 0, 600 / peptide.getLength(), 50);
        }
    }


    /**
     * Deze methode laat de gebruiker een bestand kiezen en geeft de informatie over de
     * sequentie in het bestand weer als er op de buttons wordt geklikt.
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
        } else if (event.getSource() == visButton) {
            try {
                checkSeq(sequence);
            } catch (NoValidSeq noValidSeq) {
                infoTextArea.append(String.valueOf(noValidSeq));
                Graphics paper = colPanel.getGraphics();
                paper.setColor(Color.black);
                paper.fillRect(0, 0, 600, 50);
            }
        }
    }
}


class NoValidSeq extends Exception {

    public NoValidSeq(String error) { super(error); }
}
