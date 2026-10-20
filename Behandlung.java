public class Behandlung extends Datenelement {
    private String datum;
    private String behandlung;
    private String arzt;
    
    public Behandlung(String datum, String behandlung, String arzt) {
        this.datum = datum;
        this.behandlung = behandlung;
        this.arzt = arzt;
    }
    
    @Override
    public String namenGeben() {
        return datum; 
    }
    
    @Override
    public void informationAusgeben() {
        System.out.println("Datum: " + datum + ", Behandlung: " + behandlung + ", Arzt: " + arzt);
    }
    
}
