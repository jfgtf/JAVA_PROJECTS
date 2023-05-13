public class Samochod extends Thread{
    private String nazwa;
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private double predkoscMaksymalna;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;
    Pozycja obecnaPozycja;
    private Pozycja cel;
    private double waga;
    private double aktualnaPredkosc;
    private double obwodKola;
    private Sprzeglo sprzeglo;

    public Samochod(String nazwa, String nrRejest, String model, double predkoscMaksymalna, double wagaObudowy, double obw, Pozycja pozycja  , Silnik silnik, SkrzyniaBiegow skrzynia) {
        this.nazwa = nazwa;
        this.nrRejest = nrRejest;
        this.model = model;
        this.waga = wagaObudowy;
        this.predkoscMaksymalna = predkoscMaksymalna;
        this.obecnaPozycja = pozycja;
        this.cel = pozycja;
        this.stanWlaczenia = false;
        this.skrzynia = skrzynia;
        this.silnik = silnik;
        this.obwodKola = obw;
        this.start();
    }

    public void run(){
        while (true){
            this.aktualnaPredkosc = getAktualnaPredkosc();
            if (!cel.equals(null)){
                System.out.println(nazwa + " " + model + " " + nrRejest);
            }
            obecnaPozycja.przemiesc(cel, getAktualnaPredkosc() , 6);
            System.out.println("x:" + obecnaPozycja.getX() + "  y:" + obecnaPozycja.getY());

            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                System.out.println("Usunięto samochód: " + nazwa + " " + model + " " + nrRejest);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return nazwa + ' ' + nrRejest + ' ' + model ;
    }

    public void wlacz() {
        skrzynia.uruchom();
        silnik.uruchom();
        stanWlaczenia = true;
    }

    public void wylacz() {
        skrzynia.uruchom();
        silnik.zatrzymaj();
        stanWlaczenia = false;
    }

    public void zwiekszObroty(int obroty){
        if(getSilnik().isSilnikWlaczony()){
            if((silnik.getObroty() + obroty) * skrzynia.getAktaulnePrzelozenie() * obwodKola <= predkoscMaksymalna){
                this.getSilnik().zwiekszObroty(obroty);
            }
            else
                System.out.println("osiagnieto maksymalna predkosc");
        }
        else
            System.out.println("wlacz samochod");
    }

    public void zmniejszObroty(int obroty){
        if(getSilnik().isSilnikWlaczony()){
            this.getSilnik().zmniejszObroty(obroty);
        }
        else
            System.out.println("wlacz samochod");
    }

    public double getAktualnaPredkosc() {
        return silnik.getObroty() * skrzynia.getAktaulnePrzelozenie() * obwodKola;
    }

    public void jedzDo(Pozycja cel) {
        this.cel = cel;
    }

    public Pozycja getObecnaPozycja() {
        return obecnaPozycja;
    }

    public boolean isStanWlaczenia() {
        return stanWlaczenia;
    }

    public String getModel() {
        return model;
    }

    public String getNrRejest() {
        return nrRejest;
    }

    public double getWaga() {
        return waga + getSilnik().getWaga() + getSkrzynia().getWaga() + getSkrzynia().getSprzeglo().getWaga();
    }

    public String getNazwa() {
        return nazwa;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public Sprzeglo getSprzeglo(){
        return getSkrzynia().getSprzeglo();
    }

}
