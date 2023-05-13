public class WypozyczoneKsiazki extends Ksiazki {

    private String dataBiezaca;
    private String dataOddania;
    private String idUzytkownika;

    public WypozyczoneKsiazki(String idKsiazki, String nazwaKs, String imieAutora, String nazwAutora, String dataWydania,
                              String stan, String dataBiezaca, String dataOddania, String idUzytkownika) {
        super(idKsiazki, nazwaKs, imieAutora, nazwAutora, dataWydania, stan);
        this.dataBiezaca = dataBiezaca;
        this.dataOddania = dataOddania;
        this.idUzytkownika = idUzytkownika;
    }

    public String getDataBiezaca() {
        return dataBiezaca;
    }

    public String getDataOddania() {
        return dataOddania;
    }

    public String getIdUzytkownika() {
        return idUzytkownika;
    }

    @Override
    public String toString() {
        return getIdKsiazki() + " " + getNazwaKs() + " " + getImieAutora() + " " + getNazwAutora() + " " + getDataWydania() + " " + getStan() + " " + dataBiezaca
                + " " + dataOddania + " " + idUzytkownika;
    }

    public String wszystkoDoStringa(){
        return getIdKsiazki() + ";" + getNazwaKs() + ";" + getImieAutora() + ";" + getNazwAutora() + ";" + getDataWydania() + ";" + getStan() + ";" + dataBiezaca
                + ";" + dataOddania + ";" + idUzytkownika + ";";
    }
}
