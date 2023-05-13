import static java.lang.StrictMath.*;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pozycja)) {
            return false;
        }
        var cel = (Pozycja)obj;
        return cel.getX() == x && cel.getY() == y;
    }

    public void setPozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void przemiesc(Pozycja cel,double aktualnaPredkosc,double time){
        if (x == cel.getX() && y == cel.getY()){
            System.out.println("Osiągnięto cel podróży");
        }
        else {
            double dx =  (aktualnaPredkosc * (time / 1000) * ((cel.x - this.x) / sqrt((pow((cel.x - this.x), 2)) + pow((cel.y - this.y), 2))));
            double dy =  (aktualnaPredkosc * (time / 1000) * ((cel.y - this.y) / sqrt((pow((cel.x - this.x), 2)) + pow((cel.y - this.y), 2))));

            if (dx < abs(cel.x - this.x))
                this.x += dx;

            else
                this.x = cel.x;

            if (dy < abs(cel.y - this.y))
                this.y += dy;

            else
                this.y = cel.y;
        }
    }
}
