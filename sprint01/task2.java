public class Point {
    //your code
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getXYPair(){
        return new int[]{x,y};
    }

    public double distance(int x, int y){
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distance(Point point){
        return distance(point.x, point.y);
    }

    public double distance(){
        return distance(0,0);
    }
}