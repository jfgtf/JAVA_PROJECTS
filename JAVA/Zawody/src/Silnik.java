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
        obroty = 0;
    }

    public void zatrzymaj(){
        silnikWlaczony = true;
        obroty = 0;
    }

    public void zwiekszObroty(int obroty){
        if(silnikWlaczony){
            if(this.obroty + obroty <= maxObroty) {
                this.obroty += obroty;
            }
            else
                System.out.println("to juz maksymalne obroty");
        }
    }

    public void zmniejszObroty(int obroty){
        if(silnikWlaczony){
            if(this.obroty - obroty >= 0) {
                this.obroty -= obroty;
            }
            else
                System.out.println("to juz minimalne obroty");
        }
    }

    public int getMaxObroty() {
        return maxObroty;
    }

    public boolean isSilnikWlaczony() {
        return silnikWlaczony;
    }

    public int getObroty() {
        return obroty;
    }
}
