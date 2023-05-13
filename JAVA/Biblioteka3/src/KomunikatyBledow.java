import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KomunikatyBledow {

    public void loggerIn(String s) {
        try {
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/KomunikatyBledow/logger.txt", true);
            Date data = new Date();
            System.out.println(data);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            fw.write(formatter.format(data) + "\n" + s + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
