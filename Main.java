public class Main {
    public static void main(String[] args) {
        Patient patient1 = new Patient("Max Mustermann", "Dr. Müller");
        Patient patient2 = new Patient("Erika Mustermann", "Dr. Schmidt");  
        
        Liste liste = new Liste();
        liste.vorneEinfügen(patient1);
        liste.vorneEinfügen(patient2);

        liste.ausgeben();
        println();

        patient1.informationAusgebenMitStapel();
        println();
        patient1.BehandlungHinzufügen(new Behandlung("2023-01-15", "Grippe", "Dr. Müller"));
        patient1.neusteBehandlungAusgeben();
        println();
        patient1.BehandlungHinzufügen(new Behandlung("2023-02-10", "Fieber", "Dr. Müller"));
        patient1.neusteBehandlungAusgeben();
        println();
        patient1.alleBehandlungenAusgegeben();
        println();

    }
    
    static void println(){
        System.out.println("");
    }
}