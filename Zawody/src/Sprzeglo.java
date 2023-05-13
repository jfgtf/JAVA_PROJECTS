public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

    public Sprzeglo(String nazwa, float cena, float waga) {
        super(nazwa, cena, waga);
    }

    public void wcisnij() {
        if(!stanSprzegla) {
            stanSprzegla = true;
        }
        else{
            System.out.println("sprzeglo juz wcisniete");
        }
    }

    public void zwolnij() {
        if (stanSprzegla) {
            stanSprzegla = false;
        }
        else {
            System.out.println("sprzeglo juz zwolnione");
        }
    }

    public boolean getStanSprzegla() {
        return stanSprzegla;
    }

    public String getStanSprzeglaWyraz(){
        if(stanSprzegla){
            return "Wciśnięte";
        }else{
            return "Nie wciśnięte";
        }

    }
}
