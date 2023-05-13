import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class DataBaseWypozyczoneKsiazki {
    KomunikatyBledow komunikatyBledow;

    public WypozyczoneKsiazki szukajPoID(HashMap<String, WypozyczoneKsiazki> wypozyczoneKsiazkiHashMap, String ID) {
        //String aux = new String();
        WypozyczoneKsiazki wypozyczoneKsiazki = new WypozyczoneKsiazki("","","","","","","","", "");
        for (Map.Entry<String, WypozyczoneKsiazki> entry : wypozyczoneKsiazkiHashMap.entrySet()) {
            if (entry.getValue().getIdKsiazki().equals(ID)) {
                //aux = entry.getKey();
                wypozyczoneKsiazki = wypozyczoneKsiazkiHashMap.get(entry.getKey());
            }
        }
        return wypozyczoneKsiazki;
    }

    public ArrayList<WypozyczoneKsiazki> szukajWypoPoIdUzytkownika (HashMap<String, WypozyczoneKsiazki> wypozyczoneKsiazkiHashMap, String uzytkownikID){
        ArrayList<WypozyczoneKsiazki> lista = new ArrayList<WypozyczoneKsiazki>();
        //Ksiazki ksiazka = new Ksiazki("","" ,"", "" ,"", "");
        for (Map.Entry<String, WypozyczoneKsiazki> entry : wypozyczoneKsiazkiHashMap.entrySet()) {
            if(entry.getValue().getStan().equals("wypozyczona")) {
                if (entry.getValue().getIdUzytkownika().equals(uzytkownikID)) {
                    //aux = entry.getKey();
                    WypozyczoneKsiazki ksiazka = wypozyczoneKsiazkiHashMap.get(entry.getKey());
                    lista.add(ksiazka);
                }
            }
        }
        return lista;
    }

    public ArrayList<WypozyczoneKsiazki> szukajZarePoIdUzytkownika (HashMap<String, WypozyczoneKsiazki> wypozyczoneKsiazkiHashMap, String uzytkownikID){
        ArrayList<WypozyczoneKsiazki> lista = new ArrayList<WypozyczoneKsiazki>();
        //Ksiazki ksiazka = new Ksiazki("","" ,"", "" ,"", "");
        for (Map.Entry<String, WypozyczoneKsiazki> entry : wypozyczoneKsiazkiHashMap.entrySet()) {
            if(entry.getValue().getStan().equals("zarezerwowana")) {
                if (entry.getValue().getIdUzytkownika().equals(uzytkownikID)) {
                    //aux = entry.getKey();
                    WypozyczoneKsiazki ksiazka = wypozyczoneKsiazkiHashMap.get(entry.getKey());
                    lista.add(ksiazka);
                }
            }
        }
        return lista;
    }

    public void dodajDoBazyWypozyczonychKsiazektxt(String s) throws IOException {
        /* //DODAJEMY W NOTACJI 141434;imie;naziwsko;pesel;*/
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/DataBase/DataBaseWypozyczone.txt", true);
        fw.write("\n" + s);
        fw.close();
    }

    public void usunZWypozyczonych(String id) {
        String buforzwracanej = new String();
        try
        {
            Scanner scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "/DataBase/DataBaseWypozyczone.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            String linijka;
            String doZapisu;
            String buforZwracanej;
            String identyfikator;
            while (scan.hasNextLine()) {
                linijka = scan.nextLine();
                identyfikator = linijka;
                //System.out.println(linijka);
                System.out.println(identyfikator.substring(0, identyfikator.indexOf(";")));
                if (identyfikator.substring(0, identyfikator.indexOf(";")).equals(id)) {
                    int poczatek_stanu = StringUtils.ordinalIndexOf(linijka, ";", 5);
                    int koniec_stanu = StringUtils.ordinalIndexOf(linijka, ";", 6);
                    sb.append(linijka);
                    sb.replace(poczatek_stanu + 1, koniec_stanu, "dostepna");
                    int poczatek_stopki = StringUtils.ordinalIndexOf(linijka, ";", 6);
                    int koniec_stopki = StringUtils.ordinalIndexOf(linijka, ";", 9);
                    sb.delete(poczatek_stopki - 2, koniec_stopki);
                    buforZwracanej = String.valueOf(sb);
                    buforzwracanej = buforZwracanej;
                    System.out.println(sb);
                    sb.setLength(0);
                } else {
                    inputBuffer.append(linijka);
                    inputBuffer.append("\n");
                }
            }
            scan.close();
            doZapisu = inputBuffer.toString();
            FileOutputStream zapis = new FileOutputStream(System.getProperty("user.dir") + "/DataBase/DataBaseWypozyczone.txt");
            zapis.write(doZapisu.getBytes());
            zapis.close();
        }
        catch(Exception blad) {
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }
    }

    public void fixPusteLinie(){
        System.out.println("USUWAMY PUSTE LINIE");
        try {
            String name = System.getProperty("user.dir") +  "/DataBase/DataBaseWypozyczone.txt";
            List<String> lines = FileUtils.readLines(new File(name));

            Iterator<String> i = ((List<String>) lines).iterator();
            while (i.hasNext()) {
                String line = i.next();
                if (line.trim().isEmpty())
                    i.remove();
            }

            FileUtils.writeLines(new File(name), lines);
        } catch (IOException blad) {
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }
    }

    public HashMap<String, WypozyczoneKsiazki> wczytajWypozyczoneKsiazki(){
        StringBuilder budulec = new StringBuilder();
        Scanner scan = null;
        int g = 97;
        char c;
        String v;
        String v1;
        HashMap<String, String> hashmapa_wypozyczona_biezaca = new HashMap<String, String>();//pomoc dla konstruktora
        HashMap<String, WypozyczoneKsiazki> hashmapa_wypozyczone = new HashMap<String, WypozyczoneKsiazki>();

        try {
            scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "/DataBase/DataBaseWypozyczone.txt"));
        }
        catch (Exception blad) {
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }
        while (scan.hasNextLine()) {

            if (!hashmapa_wypozyczona_biezaca.isEmpty()) { //warunek de facto nie bedzie spelniany tylko za 1szym razem
                hashmapa_wypozyczone.put(hashmapa_wypozyczona_biezaca.get("a"), new WypozyczoneKsiazki(hashmapa_wypozyczona_biezaca.get("a"), hashmapa_wypozyczona_biezaca.get("b"),
                        hashmapa_wypozyczona_biezaca.get("c"), hashmapa_wypozyczona_biezaca.get("d"), hashmapa_wypozyczona_biezaca.get("e"), hashmapa_wypozyczona_biezaca.get("f"),
                        hashmapa_wypozyczona_biezaca.get("g"), hashmapa_wypozyczona_biezaca.get("h"), hashmapa_wypozyczona_biezaca.get("i")));
            }
            g = 97;//znak ascii 97 to a
            String linijka = scan.nextLine();// + " " było z i nasnext

            for (int i = 0; i < linijka.length(); i++) {
                c = linijka.charAt(i);

                if (c == ';') {
                    char ch = (char) g; //zamiana liczby g na znak ascii
                    v = String.valueOf(ch); //klucz ,,a" to id ,,b" to imię itd.
                    v1 = String.valueOf(budulec); //konwersja wartosci id do stringa itd.
                    hashmapa_wypozyczona_biezaca.put(v, v1);
                    g++;//zwiekszamy liczbe
                    budulec.setLength(0);
                } else {
                    budulec.append(c);
                }
            }
            //v = str.substring(0,str.indexOf(";"));
            // System.out.println(str.substring(0,str.indexOf(";")));
        }
        hashmapa_wypozyczone.put(hashmapa_wypozyczona_biezaca.get("a"), new WypozyczoneKsiazki(hashmapa_wypozyczona_biezaca.get("a"), hashmapa_wypozyczona_biezaca.get("b"),
                hashmapa_wypozyczona_biezaca.get("c"), hashmapa_wypozyczona_biezaca.get("d"), hashmapa_wypozyczona_biezaca.get("e"), hashmapa_wypozyczona_biezaca.get("f"),
                hashmapa_wypozyczona_biezaca.get("g"), hashmapa_wypozyczona_biezaca.get("h"), hashmapa_wypozyczona_biezaca.get("i")));//TYLKO RAZ BEDZIE NA KONCU WYWOLANE
        scan.close();

        return hashmapa_wypozyczone;
    }
}
