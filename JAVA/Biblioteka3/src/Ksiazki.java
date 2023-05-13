import java.util.HashMap;
import java.util.Random;

public class Ksiazki {

    private String nazwaKs;
    private String imieAutora;
    private String nazwAutora;
    private String dataWydania;
    private String idKsiazki;
    //private boolean status;
    private String stan;



    public Ksiazki(String idKsiazki, String nazwaKs, String imieAutora, String nazwAutora, String dataWydania, String stan) {
        this.nazwaKs = nazwaKs;
        this.imieAutora = imieAutora;
        this.nazwAutora = nazwAutora;
        this.dataWydania = dataWydania;
        this.idKsiazki = idKsiazki;
        this.stan = stan;
        //this.status = true;
    }

    public String getIdKsiazki() {
        return idKsiazki;
    }

    public String getImieAutora() {
        return imieAutora;
    }

    public String getNazwAutora() {
        return nazwAutora;
    }

    public String getDataWydania() {
        return dataWydania;
    }

    public String getStan() {
        return stan;
    }

    @Override
    public String toString() {
        return idKsiazki + " " + stan + " " + nazwaKs + " " + imieAutora + " " + nazwAutora + " " + dataWydania;
    }

    public String wszystkoDoStringa(){
        return idKsiazki + ";" + nazwaKs + ";" + imieAutora + ";" + nazwAutora + ";" + dataWydania + ";" + stan + ";";
    }

    public String getNazwaKs() {
        return nazwaKs;
    }

    public String generujIDKsiazki(HashMap<String, Ksiazki> ksiazki, DataBaseKsiazki dataBaseKsiazki){
        Boolean aux = false;
        String noweIDString = new String();
        int noweID;
        while(!aux) {
            Random rand = new Random();
            noweID = rand.nextInt(16000);
            noweIDString = Integer.toString(noweID);
            aux = dataBaseKsiazki.sprawdzNoweID(ksiazki, noweIDString);
        }
        return noweIDString;
    }

    public Ksiazki() {
    }
    /*public boolean isStatus() {
        return status;
    }*/

    /*public String getStatusString(){
        if (this.status)
            return "Dostępne";
        else
            return "Wypożyczone";
    }*/
}
