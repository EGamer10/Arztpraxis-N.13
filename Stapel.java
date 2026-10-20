class Stapel{
    private Liste liste;
    
    public Stapel() {
        this.liste = new Liste();
    }

    public void vorneEinfügen(Datenelement d) {
        this.liste.vorneEinfügen(d);
    }

    public void anfangEntfernen() {
        this.liste.anfangEntfernen();
    }

    public void ausgeben() {
        this.liste.ausgeben();
    }

    public void vorneAusgeben() {
        this.liste.vorneAusgeben();
    }

}
