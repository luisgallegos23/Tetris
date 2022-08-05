public class Extent {

    private double x;
    private double y;
    private double rad;

    public Extent (double x, double y, double rad){
        this.x = x;
        this.y = y;
        this.rad = rad;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getRadius(){
        return rad;
    }

    public void move(double x2, double y2){
        if(x+x2>1||x+x2<0||y+2<0){
            return;
        }
        x += x2;
        y += y2;
    }

    public double distanceTo(Extent beta){
        double a = x-beta.getX();
        double b = y-beta.getY();
        return Math.sqrt(a*a+b*b);
    }

    public boolean overlaps(Extent beta){
        double distance = this.distanceTo(beta);
        distance-= beta.getRadius()+this.getRadius();
        if(distance<0){
            return true;
        }
        return false;
    }
}
