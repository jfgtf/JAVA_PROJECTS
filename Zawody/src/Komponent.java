public class Komponent {
    private String nazwa;
    private double waga;
    private double cena;

    public Komponent() {
        this.nazwa = "";
        this.waga = 0;
        this.cena = 0;
    }

    public Komponent(String nazwa, double waga, double cena){
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    public String getNazwa(){
        return nazwa;
    }

    public double getWaga() {
        return waga;
    }

    public double getCena() {
        return cena;
    }
}
