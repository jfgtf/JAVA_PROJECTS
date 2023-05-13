import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LOTTO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList wylosowane_liczby = new ArrayList<>(6);
        ArrayList trafione_liczby = new ArrayList<>();
        int liczba;
        int i = 0;
        int trafienia = 0;
        Random r = new Random();

        while(i < 6){
            liczba = (r.nextInt(50));

            if ( !wylosowane_liczby.contains( liczba ) ){
                wylosowane_liczby.add(liczba);
                i++;
            }

        }

        System.out.println("wpisz liczby:");
        i = 0;

        while(i<6){
            int x = scanner.nextInt();

            if ( wylosowane_liczby.contains( x ) ){
                trafienia++;
                trafione_liczby.add(x);

            }
            i++;
        }
        System.out.println("ilosc trafien:" + trafienia);

        System.out.println("trafione liczby:" + trafione_liczby);

        System.out.println("wylosowane liczby:" + wylosowane_liczby);
    }
}
