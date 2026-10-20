
public class Abschluss extends Listenelement
{
//Attribute

//Konstruktor
    public Abschluss()
    {

    }

    //Methoden
    @Override 
    public int restlängeGeben()
    {
        return 0;
    }
    
    @Override
    public Patient suchen(String name)
    {
        return null;
    }
    
    public Listenelement löschen (String name)
    {
        return this;
    }
    
    @Override 
    public Listenelement nachfolgerGeben()
    {
        return null;
    }
   
    @Override 
    public Datenelement datenelementGeben()
    {
        return null;
    }
    public Listenelement hintenEinfügen(Patient neu)
    {
        return new Knoten(neu);
    }
    
    @Override
    public Listenelement StelleXeinfügen(int x, int aktuelleStelle, Patient pat)
    {
        Knoten a = new Knoten (pat);
        a.nachfolgerSetzen(this);
        return a;
    }
    
    @Override
    public Listenelement vorEinemElementEinfügen(Patient patNeu, Patient patInListe)
    {
        return this;
    }
    @Override
    public Listenelement sortiertEinfügen(Patient p){
        Knoten a = new Knoten(p);
        a.nachfolger = this;
        return a;
    }
    @Override
    public Patient nächsterPatientSuchen(String wunschArzt){
        return null;
    }
}
