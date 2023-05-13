import Animals.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Animal a;
        Bird b = new Bird();
        Parrot p = new Parrot();
        Eagle e = new Eagle();
        Hawk h = new Hawk();
        Snake s = new Snake();
        Cow c = new Cow();

        Animal test[] = new Animal[5];
        test[0] = p;
        test[1] = e;
        test[2] = h;
        test[3] = s;
        test[4] = c;

        Random x = new Random();
        int r;

        a = b;
        System.out.println(a.getName());

        a = p;
        System.out.println(a.getName());

        b = p;
        b.fly();

        p.live(); //dziedziczone!

        Animal tablica[] = new Animal[100]; //tablica 100 zwierzat
        //dodaj klasy 2 nowych zwierzar
        //dodaj losowo różne zwierzęta do tablicy
        //wykorzystaj funkcję Random z pakietu java.util
        //wypisz w pętli nazwy wszystkich zwierząt

        for(int i = 0; i <100; i++){
            r = (x.nextInt(4));
            tablica[i] = test[r];
            System.out.print(tablica[i].getName()+"\n");
        }

    }
}