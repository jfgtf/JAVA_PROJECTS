import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataBaseUzytkownicy {
    KomunikatyBledow komunikatyBledow = new KomunikatyBledow();
    //private HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();


    /*public HashMap<String, DaneUzytkownika> getHashmapa_uzytkownicy() {
        return hashmapa_uzytkownicy;
    }

     */

    public boolean sprawdzNoweID(HashMap<String, DaneUzytkownika> uzytkownicy, String noweID){
        Boolean aux = false;
        for (Map.Entry<String, DaneUzytkownika> entry : uzytkownicy.entrySet()) {
            if (!entry.getValue().getIdUzytkownika().contains(noweID)) {
                //aux = entry.getKey();
                aux  = true;
            }
        }
        return aux;
    }

    public void dodajDoBazyUzytkownikowtxt(String s) throws IOException {
        /* //DODAJEMY W NOTACJI 141434;imie;naziwsko;pesel;*/
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/DataBase/DataBaseUzytkownicy.txt", true);
        fw.write("\n" + s);
        fw.close();
    }

    public DaneUzytkownika szukajPoEmailu(HashMap<String, DaneUzytkownika> uzytkownicy, String email) {
        //String aux = new String();
        DaneUzytkownika uzytkownik = new DaneUzytkownika("","","","","","","","");
        for (Map.Entry<String, DaneUzytkownika> entry : uzytkownicy.entrySet()) {
            if (entry.getValue().getEmail().equals(email)) {
                //aux = entry.getKey();
                uzytkownik = uzytkownicy.get(entry.getKey());
            }
        }
        return uzytkownik;
    }

    public DaneUzytkownika szukajPoID(HashMap<String, DaneUzytkownika> uzytkownicy, String ID) {
        //String aux = new String();
        DaneUzytkownika uzytkownik = new DaneUzytkownika("","","","","","","","");
        for (Map.Entry<String, DaneUzytkownika> entry : uzytkownicy.entrySet()) {
            if (entry.getValue().getIdUzytkownika().equals(ID)) {
                //aux = entry.getKey();
                uzytkownik = uzytkownicy.get(entry.getKey());
            }
        }
        return uzytkownik;
    }

    public HashMap wczytajUzytkownikow() {
        StringBuilder budulec = new StringBuilder();
        Scanner scan = null;
        int g = 97;
        char c;
        String v;
        String v1;
        HashMap<String, String> hashmapa_uzytkownik_biezacy = new HashMap<String, String>();//pomoc dla konstruktora
        HashMap<String, DaneUzytkownika> hashmapa_uzytkownicy = new HashMap<String, DaneUzytkownika>();

        try {
            scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "/DataBase/DataBaseUzytkownicy.txt"));
        }
        catch (Exception blad) {
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }

        while (scan.hasNextLine()) {

            if (!hashmapa_uzytkownik_biezacy.isEmpty()) { //warunek de facto nie bedzie spelniany tylko za 1szym razem
                hashmapa_uzytkownicy.put(hashmapa_uzytkownik_biezacy.get("a"), new DaneUzytkownika(hashmapa_uzytkownik_biezacy.get("a"), hashmapa_uzytkownik_biezacy.get("b"),
                        hashmapa_uzytkownik_biezacy.get("c"), hashmapa_uzytkownik_biezacy.get("d"), hashmapa_uzytkownik_biezacy.get("e"), hashmapa_uzytkownik_biezacy.get("f"),
                        hashmapa_uzytkownik_biezacy.get("g"), hashmapa_uzytkownik_biezacy.get("h")));
            }
            g = 97;//znak ascii 97 to a
            String linijka = scan.nextLine();// + " " było z i nasnext

            for (int i = 0; i < linijka.length(); i++) {
                c = linijka.charAt(i);

                if (c == ';') {
                    char ch = (char) g; //zamiana liczby g na znak ascii
                    v = String.valueOf(ch); //klucz ,,a" to id ,,b" to imię itd.
                    v1 = String.valueOf(budulec); //konwersja wartosci id do stringa itd.
                    hashmapa_uzytkownik_biezacy.put(v, v1);
                    g++;//zwiekszamy liczbe
                    budulec.setLength(0);
                } else {
                    budulec.append(c);
                }
            }
            //v = str.substring(0,str.indexOf(";"));
            // System.out.println(str.substring(0,str.indexOf(";")));
        }
        hashmapa_uzytkownicy.put(hashmapa_uzytkownik_biezacy.get("a"), new DaneUzytkownika(hashmapa_uzytkownik_biezacy.get("a"), hashmapa_uzytkownik_biezacy.get("b"),
                hashmapa_uzytkownik_biezacy.get("c"), hashmapa_uzytkownik_biezacy.get("d"), hashmapa_uzytkownik_biezacy.get("e"), hashmapa_uzytkownik_biezacy.get("f"),
                hashmapa_uzytkownik_biezacy.get("g"), hashmapa_uzytkownik_biezacy.get("h")));//TYLKO RAZ BEDZIE NA KONCU WYWOLANE
        scan.close();

        return hashmapa_uzytkownicy;
    }
}
