package buildings.Spaces;

import buildings.Exceptions.InvalidRoomsCountException;
import buildings.Exceptions.InvalidSpaceAreaException;
import buildings.interfaces.Space;

public class Flat implements Comparable<Space>, Space,Cloneable {
   private double square;
   private int numOfRooms;

    private static final int DEFAULT_NUM_OF_ROOMS = 1;
    private static final double DEFAULT_SQUARE = 30;

    public Flat() {
        numOfRooms = DEFAULT_NUM_OF_ROOMS;
        square = DEFAULT_SQUARE;
    }

    public Flat(double s){
        if(s<0){
            throw new InvalidSpaceAreaException();
        }else{
        square = s;}
        numOfRooms = DEFAULT_NUM_OF_ROOMS;
    }

    public Flat(double s, int r){
        this(s);
        if(r<0){
            throw new InvalidRoomsCountException();
        }else {
        numOfRooms = r;}
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
    public String getString() {
        return square + " "+ numOfRooms;
    }

    @Override
    public String toString(){
        return "Flat("+getNumOfRooms()+"  "+getSquare()+")";
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof Flat){
            Flat space = (Flat) obj;
            if(space.getSquare()==this.getSquare()){
                if(space.getNumOfRooms() == this.getNumOfRooms()){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getNumOfRooms() ^ Integer.parseInt(Integer.toBinaryString((int)getSquare()));
    }

    public Object clone(){
        return new Flat(getSquare(),getNumOfRooms());
    }
}
