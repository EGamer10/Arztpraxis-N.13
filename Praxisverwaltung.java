import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Praxisverwaltung extends JFrame {
    private JTextField tfName = new JTextField();
    private JLabel lblNaechsterLinde = new JLabel();
    private JLabel lblNaechsterEiche = new JLabel();
    private JLabel lblNaechsterBuche = new JLabel();

    // Neue UI-Elemente für das Behandlungszimmer & die Krankenakte
    private JLabel lblAktuellerPatient = new JLabel("Aktuell in Behandlung: Niemand");
    private JTextField tfDatum = new JTextField();
    private JTextField tfBehandlung = new JTextField();
    private JTextArea taAkte = new JTextArea();
    private JButton btnBehandlungSpeichern = new JButton("Behandlung speichern");
    private JButton btnNeusteAusgeben = new JButton("Nur letzte zeigen");
    private JButton btnAlleAusgeben = new JButton("Komplette Akte");
    private JButton btnNeusteEntfernen = new JButton("Letzte löschen");

    private Liste warteschlange = new Liste();
    private Liste patientenkartei = new Liste(); // Dauerhafte Liste für alle Patienten

    private Patient aktuellerPatient = null;
    private String aktuellerArzt = null;

    public Praxisverwaltung() {
        super("Praxisverwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 420);
        setLayout(null);

        // --- LINKER BEREICH: Warteschlangenverwaltung ---
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

        // --- RECHTER BEREICH: Behandlungszimmer & Patientenakte ---
        JLabel lblAkteTitel = new JLabel("Elektronische Patientenakte");
        lblAkteTitel.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblAkteTitel.setBounds(380, 10, 300, 30);
        add(lblAkteTitel);

        lblAktuellerPatient.setBounds(380, 40, 360, 20);
        add(lblAktuellerPatient);

        JLabel lblDatum = new JLabel("Datum:");
        lblDatum.setBounds(380, 80, 80, 25);
        add(lblDatum);

        tfDatum.setBounds(470, 80, 120, 25);
        tfDatum.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        add(tfDatum);

        JLabel lblBehandlung = new JLabel("Behandlung:");
        lblBehandlung.setBounds(380, 115, 80, 25);
        add(lblBehandlung);

        tfBehandlung.setBounds(470, 115, 270, 25);
        add(tfBehandlung);

        btnBehandlungSpeichern.setBounds(470, 150, 270, 30);
        btnBehandlungSpeichern.addActionListener(e -> behandlungHinzufuegen());
        add(btnBehandlungSpeichern);

        taAkte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taAkte);
        scrollPane.setBounds(380, 195, 360, 130);
        add(scrollPane);

        btnAlleAusgeben.setBounds(380, 335, 115, 30);
        btnAlleAusgeben.addActionListener(e -> konsoleUndUiAusgeben(() -> aktuellerPatient.alleBehandlungenAusgegeben()));
        add(btnAlleAusgeben);

        btnNeusteAusgeben.setBounds(502, 335, 115, 30);
        btnNeusteAusgeben.addActionListener(e -> konsoleUndUiAusgeben(() -> aktuellerPatient.neusteBehandlungAusgeben()));
        add(btnNeusteAusgeben);

        btnNeusteEntfernen.setBounds(625, 335, 115, 30);
        btnNeusteEntfernen.addActionListener(e -> neusteBehandlungLoeschen());
        add(btnNeusteEntfernen);

        behandlungsBereichAktivieren(false);
        labelsAktualisieren();
        setVisible(true);
    }

    private void patientAufnehmen(String wunschArzt) {
        String name = tfName.getText().trim();
        if (!name.isEmpty()) {
            // Prüfen, ob der Patient bereits in der dauerhaften Kartei existiert
            Patient pat = patientenkartei.suchen(new Patient(name, wunschArzt));
            if (pat == null) {
                pat = new Patient(name, wunschArzt);
                patientenkartei.hintenEinfügen(pat);
            } else {
                pat.arztWunschSetzen(wunschArzt);
            }

            warteschlange.hintenEinfügen(pat);
            tfName.setText("");
            labelsAktualisieren();
        }
    }

    private void patientAbrufen(String arzt) {
        Patient naechster = warteschlange.nächsterPatientSuchen(arzt);
        if (naechster != null) {
            warteschlange.patientLöschen(naechster.namenGeben());
            labelsAktualisieren();

            aktuellerPatient = naechster;
            aktuellerArzt = arzt;
            lblAktuellerPatient.setText("Aktuell in Behandlung: " + naechster.namenGeben() + " (Dr. " + arzt + ")");
            behandlungsBereichAktivieren(true);

            // Krankenakte beim Aufrufen direkt in Konsole & UI ausgeben
            konsoleUndUiAusgeben(() -> aktuellerPatient.alleBehandlungenAusgegeben());
        }
    }

    private void behandlungHinzufuegen() {
        String datum = tfDatum.getText().trim();
        String eintrag = tfBehandlung.getText().trim();

        if (aktuellerPatient != null && !datum.isEmpty() && !eintrag.isEmpty()) {
            Behandlung neu = new Behandlung(datum, eintrag, "Dr. " + aktuellerArzt);
            aktuellerPatient.BehandlungHinzufügen(neu);
            tfBehandlung.setText("");

            // Aktualisierte Akte sofort in Konsole & UI zeigen
            konsoleUndUiAusgeben(() -> aktuellerPatient.alleBehandlungenAusgegeben());
        }
    }

    private void neusteBehandlungLoeschen() {
        if (aktuellerPatient != null) {
            aktuellerPatient.neusteBehandlungEntfernen();
            konsoleUndUiAusgeben(() -> aktuellerPatient.alleBehandlungenAusgegeben());
        }
    }

    private void konsoleUndUiAusgeben(Runnable ausgabeAktion) {
        if (aktuellerPatient == null) return;

        PrintStream originalKonsole = System.out;
        ByteArrayOutputStream speicher = new ByteArrayOutputStream();
        System.setOut(new PrintStream(speicher));

        ausgabeAktion.run();

        System.setOut(originalKonsole);
        String text = speicher.toString();
        System.out.print(text);
        taAkte.setText(text);
    }

    private void behandlungsBereichAktivieren(boolean aktiv) {
        tfDatum.setEnabled(aktiv);
        tfBehandlung.setEnabled(aktiv);
        btnBehandlungSpeichern.setEnabled(aktiv);
        btnAlleAusgeben.setEnabled(aktiv);
        btnNeusteAusgeben.setEnabled(aktiv);
        btnNeusteEntfernen.setEnabled(aktiv);
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