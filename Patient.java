public class Patient {
    
    private String name;
    private String arztWunsch;

    public Patient(String name, String arztWunsch){
        this.name = name;
        this.arztWunsch = arztWunsch;
    }

    public Patient(String name){
        this.name = name;
        arztWunsch = null;
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
            System.out.println("Gewünschter Arzt:"+arztWunsch);
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
        if (arztWunsch == this.arztWunsch || this.arztWunsch ==  "egal"){
            return true;
        }
        return false;
    }
}
