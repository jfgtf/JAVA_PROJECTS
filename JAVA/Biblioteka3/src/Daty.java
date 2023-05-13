import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Daty {
    KomunikatyBledow komunikatyBledow;

    private DataBaseKsiazki dataBaseKsiazki;
    private DataBaseWypozyczoneKsiazki dataBaseWypozyczoneKsiazki;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public String getObecnaData(){
        Date data = new Date();
        return formatter.format(data);
    }

    public String getDataKoncaWypozyczenia(){
        Date data = new Date(new Date().getTime() + (14 * 86400000));
        return formatter.format(data);
    }

    public String getDataKoncaRezerwacji(){
        Date data = new Date(new Date().getTime() + (3 * 86400000));
        return formatter.format(data);
    }

    public void weryfikacjaCzasuRezerwacjiKsiazki(HashMap<String, WypozyczoneKsiazki> ksiegi){
        ArrayList<Ksiazki> lista = new ArrayList<Ksiazki>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataDzisiaj1 = new Date();
        Date refDataDzisiaj = new Date();
        try {
            Date dataDzisiaj = formatter.parse(formatter.format(dataDzisiaj1));
            refDataDzisiaj = dataDzisiaj;
        } catch (Exception blad) {
            komunikatyBledow.loggerIn(String.valueOf(blad));
        }
        Date dataOddania = new Date();
        for (Map.Entry<String, WypozyczoneKsiazki> entry : ksiegi.entrySet()) {
            try {
                dataOddania = formatter.parse(entry.getValue().getDataOddania());
            } catch (Exception e) {
                System.out.println();
            }
            if (dataOddania.getTime() - refDataDzisiaj.getTime() < 0) {
                //usunTeksiazkePoId
                dataBaseWypozyczoneKsiazki.fixPusteLinie();
                dataBaseKsiazki.zmianaStanuKsiazki(entry.getValue().getIdKsiazki(), "dostepna");
                dataBaseWypozyczoneKsiazki.usunZWypozyczonych(entry.getValue().getIdKsiazki());
                dataBaseWypozyczoneKsiazki.fixPusteLinie();

            } else {
                ;
            }
        }
    }

}
