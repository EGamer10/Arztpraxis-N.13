public class Patient extends Datenelement{
    
    private String name;
    private String arztWunsch;
    private Stapel krankenakte;

    public Patient(String name, String arztWunsch){
        this.name = name;
        this.arztWunsch = arztWunsch;
        krankenakte = new Stapel();
    }

    public Patient(String name){
        this.name = name;
        arztWunsch = null;
        krankenakte = new Stapel();
    }
    
    //Methoden
    String namenGeben()
    {
        return name;
    }
    
    public void informationAusgeben()
    {
        System.out.println("Name : "+name);
        if (arztWunsch.equals(null)){
            System.out.println("Gewünschter Arzt: 'nicht gewählt'");
        }
        else{
            System.out.println("Gewünschter Arzt: "+arztWunsch);
        }
    }
    
    public boolean istKleinerAls(Patient pat)
    {
        //Wenn name vor pat.name kommt, wird true zurück gegeben
        //Wenn name nach pat.name kommt, wird false zurück gegeben
        if(name.compareTo(pat.namenGeben()) < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean wunschArztCheck(String arztWunsch){
        return arztWunsch.equals(this.arztWunsch) || this.arztWunsch.equals("egal" ) || this.arztWunsch == null;
    }

    public void informationAusgebenMitStapel()
    {
        System.out.println("Name : "+name);
        if (arztWunsch == null || arztWunsch.equals("")){
            System.out.println("Gewünschter Arzt: 'nicht gewählt'");
        }
        else{
            System.out.println("Gewünschter Arzt: "+arztWunsch);
        }
        krankenakte.ausgeben();
    }

    public void BehandlungHinzufügen(Behandlung behandlung){
        krankenakte.vorneEinfügen(behandlung);
    }

    public void neusteBehandlungAusgeben(){
        System.out.println("Letzte Behandlung von "+name+":");
        krankenakte.vorneAusgeben();
    }

    public void neusteBehandlungEntfernen(){
        krankenakte.anfangEntfernen();
    }

    public void alleBehandlungenAusgegeben(){
        System.out.println("Krankenakte von "+name+":");
        krankenakte.ausgeben();
    }

    public String arztWunschGeben(){
        return arztWunsch;
    }

    public void arztWunschEntfernen(){
        this.arztWunsch = null;
    }

    public void arztWunschSetzen(String arztWunschNeu){
        this.arztWunsch = arztWunschNeu;
    }

}
