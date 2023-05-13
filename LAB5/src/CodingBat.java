import java.util.*;

public class CodingBat{

    public Map<String, Integer> word0(String[] strings) {
        Map<String, Integer> mapa = new HashMap<>();
        for (String XD : strings) {
            mapa.put(XD, 0);
        }
        return mapa;
    }

    public Map<String, Integer> wordLen(String[] strings) {
        Map<String, Integer> mapa = new HashMap<>();
        for(String XD : strings){
            mapa.put(XD, XD.length());
        }
        return mapa;
    }

    public Map<String, String> pairs(String[] strings) {
        Map<String, String> mapa = new HashMap<>();
        for (String string : strings) {
            String pierwsza = string.substring(0,1);
            String ostatnia = string.substring(string.length() - 1);
            mapa.put(pierwsza, ostatnia);
        }
        return mapa;
    }
}
