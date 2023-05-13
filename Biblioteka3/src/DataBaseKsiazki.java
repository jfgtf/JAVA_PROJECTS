import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataBaseKsiazki {
    private HashMap<String, Ksiazki> hashmapa_ksiazki = new HashMap<String, Ksiazki>();
    KomunikatyBledow komunikatyBledow;

    public boolean sprawdzNoweID(HashMap<String, Ksiazki> ksiazki, String noweID){
        Boolean aux = false;
        for (Map.Entry<String, Ksiazki> entry : ksiazki.entrySet()) {
            if (!entry.getValue().getIdKsiazki().equals(noweID)) {
                //aux = entry.getKey();
                aux  = true;
            }
        }
        return aux;
    }

    public void dodajDoBazyKsiazektxt(String s) throws IOException {
        /* //DODAJEMY W NOTACJI 141434;imie;naziwsko;pesel;*/
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/DataBase/DataBaseKsiazki.txt", true);
        fw.write(s + "\n" );
        fw.close();
    }

    /*public Ksiazki szukajPoNazwie(HashMap<String, Ksiazki> ksiegi, String nazwa_ksiazki){
        nazwa_ksiazki = nazwa_ksiazki.toLowerCase();
        Ksiazki ksiazka = new Ksiazki("","" ,"", "" ,"", "");
        for (Map.Entry<String, Ksiazki> entry : ksiegi.entrySet()) {
            if (entry.getValue().getNazwaKs().contains(nazwa_ksiazki)){
                ksiazka = ksiegi.get(entry.getKey());
            }
        }
        return ksiazka;
    }

     */



    public ArrayList<Ksiazki> szukajListyKsiazekPoNazwie(HashMap<String, Ksiazki> ksiegi, String nazwa){
        ArrayList<Ksiazki> lista = new ArrayList<Ksiazki>();
        nazwa = nazwa.toLowerCase();
        //Ksiazki ksiazka = new Ksiazki("","" ,"", "" ,"", "");
        for (Map.Entry<String, Ksiazki> entry : ksiegi.entrySet()) {
            if (entry.getValue().getNazwaKs().toLowerCase().contains(nazwa)) {
                //aux = entry.getKey();
                Ksiazki ksiazka = ksiegi.get(entry.getKey());
                lista.add(ksiazka);
            }
        }
        return lista;
    }

    public Ksiazki szukajPoID (HashMap<String, Ksiazki> ksiegi, String ksiazkaID){
        Ksiazki ksiazka = new Ksiazki("","" ,"", "" ,"", "");
        for (Map.Entry<String, Ksiazki> entry : ksiegi.entrySet()) {
            if (entry.getValue().getIdKsiazki().equals(ksiazkaID)){
                ksiazka = ksiegi.get(entry.getKey());
            }
        }
        return ksiazka;
    }



    public HashMap wczytajKsiazki() {
        StringBuilder budulec = new StringBuilder();
        Scanner scan = null;
        int g = 97;
        char c;
        String v;
        String v1;
        HashMap<String, String> hashmapa_ksiazka_biezaca = new HashMap<String, String>();//pomoc dla konstruktora
        //HashMap<String, Ksiazki> hashmapa_ksiazki = new HashMap<String, Ksiazki>();

        try {
            scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "/DataBase/DataBaseKsiazki.txt"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {

            if (!hashmapa_ksiazka_biezaca.isEmpty()) { //warunek de facto nie bedzie spelniany tylko za 1szym razem
                hashmapa_ksiazki.put(hashmapa_ksiazka_biezaca.get("a"), new Ksiazki(hashmapa_ksiazka_biezaca.get("a"), hashmapa_ksiazka_biezaca.get("b"),
                        hashmapa_ksiazka_biezaca.get("c"), hashmapa_ksiazka_biezaca.get("d"), hashmapa_ksiazka_biezaca.get("e"), hashmapa_ksiazka_biezaca.get("f")));
            }
            g = 97;//znak ascii 97 to a
            String linijka = scan.nextLine();// + " " było z i nasnext

            for (int i = 0; i < linijka.length(); i++) {
                c = linijka.charAt(i);

                if (c == ';') {
                    char ch = (char) g; //zamiana liczby g na znak ascii
                    v = String.valueOf(ch); //klucz ,,a" to id ,,b" to imię itd.
                    v1 = String.valueOf(budulec); //konwersja wartosci id do stringa itd.
                    hashmapa_ksiazka_biezaca.put(v, v1);
                    g++;//zwiekszamy liczbe
                    budulec.setLength(0);
                }
                else {
                    budulec.append(c);
                }
            }
            //v = str.substring(0,str.indexOf(";"));
            // System.out.println(str.substring(0,str.indexOf(";")));
        }
        hashmapa_ksiazki.put(hashmapa_ksiazka_biezaca.get("a"), new Ksiazki(hashmapa_ksiazka_biezaca.get("a"), hashmapa_ksiazka_biezaca.get("b"),
                hashmapa_ksiazka_biezaca.get("c"), hashmapa_ksiazka_biezaca.get("d"), hashmapa_ksiazka_biezaca.get("e"), hashmapa_ksiazka_biezaca.get("f")));
        //TYLKO RAZ BEDZIE NA KONCU WYWOLANE
        scan.close();

        return hashmapa_ksiazki;
    }

    public void zmianaStanuKsiazki(String id_szukanej_ksiazki, String oczekiwany_stan){
        try {
            Scanner scan = new Scanner(new FileInputStream(System.getProperty("user.dir") + "/DataBase/DataBaseKsiazki.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            String id;
            String linijka;
            String doZapisu;
            while (scan.hasNextLine()) {
                linijka = scan.nextLine();
                id = linijka;
                System.out.println(linijka);
                if (id.substring(0 , id.indexOf(";")).equals(id_szukanej_ksiazki) ){
                    int poczatek_stanu = StringUtils.ordinalIndexOf(linijka, ";", 5);
                    int koniec_stanu = StringUtils.ordinalIndexOf(linijka, ";" , 6);
                    sb.append(linijka);
                    sb.replace(poczatek_stanu+1, koniec_stanu, oczekiwany_stan);
                    inputBuffer.append(sb);
                    inputBuffer.append("\n");
                    sb.setLength(0);
                }
                else {
                    inputBuffer.append(linijka);
                    inputBuffer.append("\n");
                }

                /*String inputStr = inputBuffer.toString();
                FileOutputStream zapis = new FileOutputStream(System.getProperty("user.dir") + "/nowyplik.txt");
                zapis.write(inputStr.getBytes());
                zapis.close();*/
            }
            scan.close();
            doZapisu = inputBuffer.toString();
            FileOutputStream zapis = new FileOutputStream(System.getProperty("user.dir") + "/DataBase/DataBaseKsiazki.txt");
            zapis.write(doZapisu.getBytes());
            zapis.close();
        }
        catch (Exception blad){
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }
    }
}
