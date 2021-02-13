package controller;

public class Point {
    private int x;
    private int y;
    public Point(int a, int b){
        x = a;
        y = b;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int a){
        x += a;
    }
    public void setY(int a){
        y += a;
    }
}