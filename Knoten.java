public class Knoten extends Listenelement   
{
    //Attribute
    Listenelement nachfolger;
    Patient daten;
    
    //Konstruktor
    public Knoten(Patient pat)
    {
        daten = pat;
        nachfolger = new Abschluss();
    }
        
    void nachfolgerSetzen(Listenelement nf)
    {
        nachfolger = nf;
    }
    
    public Listenelement nachfolgerGeben()
    {
        return nachfolger;
    }

    void informationAusgeben()
    {
        daten.informationAusgeben();
    }
    
    public Patient datenelementGeben()
    {
        return daten;
    }
    
    public int restlängeGeben()
    {
        return 1 + nachfolger.restlängeGeben();
    }
    
    public Patient suchen(String name)
    {
        if(daten.namenGeben().equals(name))
        {
            return daten;
        }
        else return nachfolger.suchen(name);
    }
    
    public Listenelement löschen (String name)
    {
     if (daten.namenGeben().equals(name))
     {
         return nachfolger;
     }
    else 
    {
        nachfolger = nachfolger.löschen(name);
        return this;
    }
    }
    
    public Patient endeGeben(Patient ende)
    {
        return nachfolger.endeGeben(daten);
    }
    
    public Listenelement hintenEinfügen(Patient neu)
    {
        nachfolger = nachfolger.hintenEinfügen(neu);
        return this;
    }
    
    public Listenelement StelleXeinfügen(int x, int aktuelleStelle, Patient pat)
    {
        if (aktuelleStelle + 1 == x)
        {
            Knoten a = new Knoten(pat);
            a.nachfolgerSetzen(nachfolger);
            nachfolger = a;
            return this;
        }
        else {
            nachfolger = nachfolger.StelleXeinfügen(x, 1+aktuelleStelle, pat);
            return this;
        }
    }
    
    public Listenelement vorEinemElementEinfügen(Patient patNeu, Patient patInListe)
    {
        if(daten.namenGeben().equals(patInListe.namenGeben()))
        {
            Knoten a = new Knoten(patNeu);
            a.nachfolgerSetzen(this);
            return a;
        }
        else
        {
            return nachfolger.vorEinemElementEinfügen(patNeu, patInListe);
        }
    }
    public Listenelement sortiertEinfügen(Patient p){
        //Zuerst wird der neue Patient pat mit dem Datenelement verglichen
        if(daten.istKleinerAls(p)){
            nachfolger = nachfolger.sortiertEinfügen(p);
            return this;
        }
        else {
            Knoten a = new Knoten(p);
            a.nachfolger = this;
            return a;
        }
    }

    public Patient nächsterPatientSuchen(String wunschAzt){
        if (daten.wunschArztCheck(wunschAzt)){
            return daten;
        }
        else{
            return nachfolger.nächsterPatientSuchen(wunschAzt);
        }
    }
}
