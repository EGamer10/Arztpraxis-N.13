public class Knoten extends Listenelement   
{
    //Attribute
    Listenelement nachfolger;
    Datenelement daten;
    
    //Konstruktor
    public Knoten(Datenelement d)
    {
        daten = d;
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
    
    public Datenelement datenelementGeben()
    {
        return daten;
    }
    
    public int restlängeGeben()
    {
        return 1 + nachfolger.restlängeGeben();
    }
    
    public Patient suchen(String name)
    {
        if (daten instanceof Patient pat){
            if(pat.namenGeben().equals(name)){
                return pat;
            }
            return nachfolger.suchen(name);
        }
        return null;
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
        if (daten instanceof Patient pat){
            if(pat.istKleinerAls(p)){
                nachfolger = nachfolger.sortiertEinfügen(p);
                return this;
            }
            else {
                Knoten a = new Knoten(p);
                a.nachfolger = this;
                return a;
            }
        }
        else{
            Knoten a = new Knoten(p);
            a.nachfolger = this;
            return a;}
    }

    public Patient nächsterPatientSuchen(String wunschArzt){
        if (daten instanceof Patient pat){
            if (pat.wunschArztCheck(wunschArzt)){
                return pat;
            }
            return nachfolger.nächsterPatientSuchen(wunschArzt);
        }
        return null;
    }
}
