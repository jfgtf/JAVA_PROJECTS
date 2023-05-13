public class SkrzyniaBiegow extends Komponent {
    private int aktaulnyBieg;
    private int iloscBiegow;
    private double aktaulnePrzelozenie;
    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String nazwa, double waga, double cena, int iloscBiegow){
        super(nazwa, waga, cena);
        this.iloscBiegow = iloscBiegow;
        this.aktaulnyBieg = 1;
        this.aktaulnePrzelozenie = 0;
        this.sprzeglo = new Sprzeglo();
    }

    public void zwiekszBieg(){
        if (aktaulnyBieg < iloscBiegow) {
            this.sprzeglo.wcisnij();
            aktaulnyBieg += 1;
            this.sprzeglo.zwolnij();
        }
        else{
            System.out.println("to juz maksymalny bieg");
        }
    }

    public void zmniejszBieg(){
        if (aktaulnyBieg > 0){
            this.sprzeglo.wcisnij();
            aktaulnyBieg -= 1;
            this.sprzeglo.zwolnij();
        }
        else{
            System.out.println("to juz minimalny bieg");
        }
    }

    public int getAktaulnyBieg() {
        return aktaulnyBieg;
    }

    public double getAktaulnePrzelozenie() {
        return aktaulnePrzelozenie;
    }
}
