public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;

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
}
