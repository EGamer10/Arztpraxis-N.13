
public abstract class Listenelement
{
//Attribute

//Methoden
public abstract int restlängeGeben();
public abstract Patient suchen(String name);
public abstract Listenelement löschen(String name);
public abstract Listenelement nachfolgerGeben();
public abstract Datenelement datenelementGeben();
public abstract Listenelement hintenEinfügen(Patient neu);
public abstract Listenelement StelleXeinfügen(int x, int aktuelleStelle, Patient pat);
public abstract Listenelement vorEinemElementEinfügen(Patient patNeu, Patient patInListe);
public abstract Listenelement sortiertEinfügen(Patient p);
public abstract Patient nächsterPatientSuchen(String wunschArzt);
}
