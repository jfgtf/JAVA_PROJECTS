import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CodingBatTest {

    private final CodingBat obj = new CodingBat();

    @Test
    public void word0() {
        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("a", 0);
        mapa.put("b", 0);
        mapa.put("c", 0);
        assertEquals(mapa, obj.word0(new String[] {"a", "b", "c"}));
        Map<String, Integer> mapaa = new HashMap<>();
        mapaa.put("cc", 0);
        mapaa.put("b", 0);
        mapaa.put("c", 0);
        assertEquals(mapaa, obj.word0(new String[] {"cc", "b", "c"}));
        Map<String, Integer> mapaaa = new HashMap<>();
        mapaaa.put("aa", 0);
        mapaaa.put("b", 0);
        mapaaa.put("c", 0);
        assertEquals(mapaaa, obj.word0(new String[] {"aa", "b", "c"}));
    }

    @Test
    public void wordLen() {
        Map<String, Integer> mapa2 = new HashMap<>();
        mapa2.put("a", 1);
        mapa2.put("xd", 2);
        mapa2.put("sss", 3);
        assertEquals(mapa2, obj.wordLen(new String[] {"a", "xd", "sss"}));
        Map<String, Integer> mapa3 = new HashMap<>();
        mapa3.put("aqbc", 4);
        mapa3.put("xddddddddd", 10);
        mapa3.put("c", 1);
        assertEquals(mapa3, obj.wordLen(new String[] {"aqbc", "xddddddddd", "c"}));
        Map<String, Integer> mapa4 = new HashMap<>();
        mapa4.put("aa", 2);
        mapa4.put("b", 1);
        mapa4.put("c", 1);
        assertEquals(mapa4, obj.wordLen(new String[] {"aa", "b", "c"}));
    }

}