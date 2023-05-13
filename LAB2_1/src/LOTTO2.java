import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LOTTO2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList wpisane_liczby = new ArrayList<>(6);
        ArrayList trafione_liczby = new ArrayList<>();
        int liczba;
        int i = 0;
        int trafienia = 0;
        Random r = new Random();

        System.out.println("wpisz liczby:");
        while(i < 6){
            int x = scanner.nextInt();
            wpisane_liczby.add(x);

            i++;
        }

        long start=System.currentTimeMillis();

        while(trafienia != 6){
            trafienia = 0;
            i = 0;
            trafione_liczby.clear();
            while(i<6) {
                liczba = (r.nextInt(50));
                if (!trafione_liczby.contains(liczba)) {

                    if (wpisane_liczby.contains(liczba)) {
                        trafienia++;
                        trafione_liczby.add(liczba);
                        i++;

                    }
                    else {
                        break;
                    }
                }
                else{
                    break;
                }
            }
        }

        long stop=System.currentTimeMillis();

        System.out.println("ilosc trafien:" + trafienia);

        System.out.println("trafione liczby:" + trafione_liczby);

        System.out.println("czas:" + (stop-start));

    }
}
