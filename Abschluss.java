
public class Abschluss extends Listenelement
{
//Attribute

//Konstruktor
    public Abschluss()
    {

    }

    //Methoden
    public int restlängeGeben()
    {
        return 0;
    }
    
    public Patient suchen(String name)
    {
        return null;
    }
    
    public Listenelement löschen (String name)
    {
        return this;
    }
    
    public Patient endeGeben(Patient ende)
    {
        return ende;
    }
    
    public Listenelement nachfolgerGeben()
    {
        return null;
    }
   
    public Patient datenelementGeben()
    {
        return null;
    }
    public Listenelement hintenEinfügen(Patient neu)
    {
        return new Knoten(neu);
    }
    
    public Listenelement StelleXeinfügen(int x, int aktuelleStelle, Patient pat)
    {
        Knoten a = new Knoten (pat);
        a.nachfolgerSetzen(this);
        return a;
    }
    
    public Listenelement vorEinemElementEinfügen(Patient patNeu, Patient patInListe)
    {
        return this;
    }
    public Listenelement sortiertEinfügen(Patient p){
        Knoten a = new Knoten(p);
        a.nachfolger = this;
        return a;
    }
    public Patient nächsterPatientSuchen(String wunschArzt){
        return null;
    }
}
