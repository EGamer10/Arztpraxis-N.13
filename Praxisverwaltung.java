import javax.swing.*;
import java.awt.*;

public class Praxisverwaltung extends JFrame {
    private JTextField tfName = new JTextField();
    private JLabel lblNaechsterLinde = new JLabel();
    private JLabel lblNaechsterEiche = new JLabel();
    private JLabel lblNaechsterBuche = new JLabel();

    private Liste warteschlange = new Liste();

    public Praxisverwaltung() {
        super("Praxisverwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 390);
        setLayout(null);

        JLabel lblTitel = new JLabel("Gemeinschaftspraxis");
        lblTitel.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblTitel.setBounds(15, 10, 250, 30);
        add(lblTitel);

        JLabel lblUntertitel = new JLabel("Warteschlangenverwaltung");
        lblUntertitel.setBounds(15, 40, 200, 20);
        add(lblUntertitel);

        JLabel lblName = new JLabel("Name Patient:");
        lblName.setBounds(15, 80, 90, 25);
        add(lblName);

        tfName.setBounds(105, 80, 200, 25);
        add(tfName);

        JLabel lblAufnehmen = new JLabel("Aufnehmen in Warteschlange für Dr.:");
        lblAufnehmen.setBounds(15, 120, 250, 20);
        add(lblAufnehmen);

        JButton btnAufLinde = new JButton("Linde");
        btnAufLinde.setBounds(15, 150, 75, 30);
        btnAufLinde.addActionListener(e -> patientAufnehmen("Linde"));
        add(btnAufLinde);

        JButton btnAufEiche = new JButton("Eiche");
        btnAufEiche.setBounds(100, 150, 75, 30);
        btnAufEiche.addActionListener(e -> patientAufnehmen("Eiche"));
        add(btnAufEiche);

        JButton btnAufBuche = new JButton("Buche");
        btnAufBuche.setBounds(185, 150, 75, 30);
        btnAufBuche.addActionListener(e -> patientAufnehmen("Buche"));
        add(btnAufBuche);

        JButton btnAufEgal = new JButton("egal");
        btnAufEgal.setBounds(270, 150, 75, 30);
        btnAufEgal.addActionListener(e -> patientAufnehmen("egal"));
        add(btnAufEgal);

        JLabel lblNaechster = new JLabel("Nächster Patient für Dr.");
        lblNaechster.setBounds(15, 195, 200, 20);
        add(lblNaechster);

        JButton btnAbrufLinde = new JButton("Linde");
        btnAbrufLinde.setBounds(15, 225, 75, 30);
        btnAbrufLinde.addActionListener(e -> patientAbrufen("Linde"));
        add(btnAbrufLinde);

        lblNaechsterLinde.setBounds(110, 225, 200, 30);
        add(lblNaechsterLinde);

        JButton btnAbrufEiche = new JButton("Eiche");
        btnAbrufEiche.setBounds(15, 265, 75, 30);
        btnAbrufEiche.addActionListener(e -> patientAbrufen("Eiche"));
        add(btnAbrufEiche);

        lblNaechsterEiche.setBounds(110, 265, 200, 30);
        add(lblNaechsterEiche);

        JButton btnAbrufBuche = new JButton("Buche");
        btnAbrufBuche.setBounds(15, 305, 75, 30);
        btnAbrufBuche.addActionListener(e -> patientAbrufen("Buche"));
        add(btnAbrufBuche);

        lblNaechsterBuche.setBounds(110, 305, 200, 30);
        add(lblNaechsterBuche);

        labelsAktualisieren();
        setVisible(true);
    }

    private void patientAufnehmen(String wunschArzt) {
        String name = tfName.getText().trim();
        if (!name.isEmpty()) {
            warteschlange.hintenEinfügen(new Patient(name, wunschArzt));
            tfName.setText("");
            labelsAktualisieren();
        }
    }

    private void patientAbrufen(String arzt) {
        Patient naechster = warteschlange.nächsterPatientSuchen(arzt);
        if (naechster != null) {
            warteschlange.patientLöschen(naechster.namenGeben());
            labelsAktualisieren();
        }
    }

    private void labelsAktualisieren() {
        einzelnesLabelSetzen("Linde", lblNaechsterLinde);
        einzelnesLabelSetzen("Eiche", lblNaechsterEiche);
        einzelnesLabelSetzen("Buche", lblNaechsterBuche);
    }

    private void einzelnesLabelSetzen(String arzt, JLabel zielLabel) {
        Patient naechster = warteschlange.nächsterPatientSuchen(arzt);
        if (naechster != null) {
            zielLabel.setText(naechster.namenGeben());
        } else {
            zielLabel.setText("Kein Patient");
        }
    }

    public static void main(String[] args) {
        new Praxisverwaltung();
    }
}