public class Samochod extends Thread{
    private String nazwa;
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkosaMaksymalna;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;
    private Pozycja obecnaPozycja;
    private int waga;
    private double aktualnaPredkosc;
    private double obwodKola;

    public Samochod(String nazwa, String nrRejest, String model, int predkosaMaksymalna, int waga) {
        this.nazwa = nazwa;
        this.nrRejest = nrRejest;
        this.model = model;
        this.waga = waga;
        this.predkosaMaksymalna = predkosaMaksymalna;
        this.stanWlaczenia = false;
        this.skrzynia = new SkrzyniaBiegow("elo",500, 5000, 6);
        this.silnik = new Silnik("Silnik", 500, 20000, 10000);
        start();
    }

    //public void skladanieSamochodu()

    public void run(){
        try {
            System.out.println("Startuje samochód:" + nazwa);
            //jedzDo();
            System.out.println(obecnaPozycja);
            Thread.sleep(200);
        }
        catch (InterruptedException x){
            x.printStackTrace();
        }
    }

    public void wlacz() {
        silnik.uruchom();
        stanWlaczenia = true;
    }

    public void wylacz() {
        silnik.zatrzymaj();
        stanWlaczenia = false;
    }

    public void setObecnaPozycja(Pozycja obecnaPozycja) { //zerowanie przy starcie
        this.obecnaPozycja = obecnaPozycja;
    }

    public double getAktualnaPredkosc() {
        return silnik.getObroty() * skrzynia.getAktaulnePrzelozenie() * obwodKola;
    }

    public void jedzDo(Pozycja cel) {
        obecnaPozycja = cel;
    }

    // public double czasDojechaniaDo (Pozycja cel){ } - czas na podstawie ktorego bedzie okreslany zwyciezca

    public Pozycja getObecnaPozycja() {
        return obecnaPozycja;
    }

    public boolean isStanWlaczenia() {
        return stanWlaczenia;
    }
}
