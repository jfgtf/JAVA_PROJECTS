import java.util.HashMap;
import java.util.Random;

public class DaneUzytkownika extends Aktor {

    private String idUzytkownika;
    private String imie;
    private String nazwisko;
    private String adres;
    private String kodPocztowy;
    private String telefon;

    public DaneUzytkownika(String idUzytkownika, String email, String imie, String nazwisko, String adres, String kodPocztowy, String haslo, String telefon) {
        super(email, haslo);
        this.idUzytkownika = idUzytkownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.kodPocztowy = kodPocztowy;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return  idUzytkownika + " " + getEmail() + " " + imie + " " + nazwisko + " " + telefon + " " + adres + " " + kodPocztowy ;
    }

    public String getIdUzytkownika() {
        return idUzytkownika;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getTelefon() {
        return telefon;
    }

    public String generujIDUzytkownika(HashMap<String, DaneUzytkownika> uzytkownicy, DataBaseUzytkownicy dataBaseUzytkownicy){
        Boolean aux = false;
        String noweIDString = new String();
        int noweID;
        while(!aux) {
            Random rand = new Random();
            noweID = rand.nextInt(16000);
            noweIDString = Integer.toString(noweID);
            aux = dataBaseUzytkownicy.sprawdzNoweID(uzytkownicy, noweIDString);
        }
        return noweIDString;
    }

    public String wszystkoDoStringa() {
        return idUzytkownika + ";" + getEmail() + ";" + imie + ";" + nazwisko + ";" + adres + ";" + kodPocztowy + ";" + getHaslo() + ";" + telefon + ";";
    }
}


