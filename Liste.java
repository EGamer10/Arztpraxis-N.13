public class Liste {
    private Listenelement anfang;


    public Liste()
    {
        anfang = new Abschluss();
    }
    
    void vorneEinfügen(Patient neuerPatient)
    {
        Knoten k = new Knoten(neuerPatient);
        k.nachfolger = anfang;
        anfang = k;
    }

    public void hintenEinfügen(Patient neu)
    {
        anfang = anfang.hintenEinfügen(neu);
    }
    
    public int längeGeben()
    {
        return anfang.restlängeGeben();
    }
      
    public Patient patientLöschen(String name)
    {
        //Zuerst wird der Name gesucht und abgespeichert in speicher
        Patient speicher = anfang.suchen(name);
        //Dann wird der Knoten mit dem Namen gelöscht
        anfang = anfang.löschen(name);
        return speicher;
    }

    public Patient nächsterPatientSuchen(String wunschAzt){
        return anfang.nächsterPatientSuchen(wunschAzt);
    }

    public Patient suchen(Patient pat)
    {
        return anfang.suchen(pat.namenGeben());
    }
    
    public void vorEinemElementEinfügen(Patient patNeu, Patient patInListe)
    {
        if(suchen(patInListe).namenGeben() != null)
        {
            anfang = anfang.vorEinemElementEinfügen(patNeu, patInListe);
        }
        else
        {
            
        }
    }
    public void sortiertEinfügen(Patient p){
        anfang = anfang.sortiertEinfügen(p);
    }

}
