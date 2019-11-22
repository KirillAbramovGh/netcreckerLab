package buildings.Spaces;

import buildings.interfaces.Space;

public class Office implements Comparable<Space>, Space,Cloneable {
    private double square;
    private int numOfRooms;

    private static final int DEFAULT_NUM_OF_ROOMS = 1;
    private static final double DEFAULT_SQUARE = 250;

    public Office() {
        numOfRooms = DEFAULT_NUM_OF_ROOMS;
        square = DEFAULT_SQUARE;
    }

    public Office(double s){
        square = s;
        numOfRooms = DEFAULT_NUM_OF_ROOMS;
    }

    public Office(double s, int r){
        square = s;
        numOfRooms = r;
    }

    public int getNumOfRooms(){
        return numOfRooms;
    }

    public void setSquare(double s){
        square = s;
    }

    public double getSquare(){
        return square;
    }

    public void setNumOfRooms(int r){
        numOfRooms = r;
    }


    @Override
    public int compareTo(Space o) {
        if(square > o.getSquare()){
            return 1;
        }else if(square < o.getSquare()){
            return -1;
        }
        return 0;
    }
    @Override
    public String toString(){
        return "Office("+getNumOfRooms()+" "+getSquare()+")";
    }

    @Override
    public int hashCode() {
        return getNumOfRooms() ^ Integer.parseInt(Integer.toBinaryString((int)getSquare()));
    }
    @Override
    public boolean equals(Object obj) {

        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
            if(obj instanceof Office){
                var space = (Office)obj;
            if(space.getSquare()==this.getSquare()){
                if(space.getNumOfRooms() == this.getNumOfRooms()){
                    return true;
                }
            }
            }
            return false;

    }
    @Override
    public String getString() {
        return square + " "+ numOfRooms;
    }

    public Object clone(){
        return new Office(getSquare(),getNumOfRooms());
    }
}
