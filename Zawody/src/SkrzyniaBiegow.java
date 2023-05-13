public class SkrzyniaBiegow extends Komponent {
    private int aktaulnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;
    private double zmiennaAktPrzel;
    private Sprzeglo sprzeglo;
    private boolean skrzyniaWlaczona;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, int iloscBiegow, Sprzeglo sprzeglo){
        super(nazwa, waga, cena);
        this.iloscBiegow = iloscBiegow;
        this.aktaulnyBieg = 1;
        this.zmiennaAktPrzel = 0.007;
        this.sprzeglo = sprzeglo;
        this.skrzyniaWlaczona = false;
    }

    public void uruchom(){
        skrzyniaWlaczona = true;
    }

    public void zatrzymaj(){
        skrzyniaWlaczona = true;
    }

    public void zwiekszBieg(){
        if(skrzyniaWlaczona) {
            if (aktaulnyBieg < iloscBiegow) {
                if (getSprzeglo().getStanSprzegla()) {
                    aktaulnyBieg += 1;
                } else {
                    System.out.println("wcisnij sprezglo!");
                }
            } else {
                System.out.println("to juz maksymalny bieg");
            }
        }
        else
            System.out.println("wlacz samochod");

    }

    public void zmniejszBieg(){
        if(skrzyniaWlaczona) {
            if (aktaulnyBieg > 1) {
                if (getSprzeglo().getStanSprzegla()) {
                    aktaulnyBieg -= 1;
                } else {
                    System.out.println("wcisnij sprezglo!");
                }
            } else {
                System.out.println("to juz minimalny bieg");
            }
        }
        else
            System.out.println("wlacz samochod");
    }

    public int getAktaulnyBieg() {
        return aktaulnyBieg;
    }

    public double getAktaulnePrzelozenie() {
        return zmiennaAktPrzel * aktaulnyBieg;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }
}
