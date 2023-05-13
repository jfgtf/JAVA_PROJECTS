public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;
    private boolean silnikWlaczony;

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
        this.silnikWlaczony = false;
    }

    public void uruchom(){
        silnikWlaczony = true;
    }

    public void zatrzymaj(){
        silnikWlaczony = true;
    }

    public void zwiekszObroty(int obroty){
        if(silnikWlaczony){
            if(this.obroty + obroty <= maxObroty) {
                this.obroty += obroty;
            }
        }
    }

    public void zmniejszObroty(int obroty){
        if(silnikWlaczony){
            if(this.obroty - obroty >= 0) {
                this.obroty -= obroty;
            }
        }
    }

    public int getObroty() {
        return obroty;
    }
}
