package buildings.Floors;

import buildings.Spaces.Flat;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Floor;
import buildings.Exceptions.*;
import buildings.interfaces.Space;

import java.util.ArrayList;
import java.util.Iterator;


public class FlatFloor implements Floor {

    private ArrayList<Space> flats;

    public void setSpaces(Space ... spaces){
        flats = new ArrayList<>();
        for(int i = 0;i<spaces.length;i++){
            flats.add(spaces[i]);
        }
    }
    public FlatFloor(int numOfFlats){
        flats = new ArrayList<>(numOfFlats);
    }
    public FlatFloor(Space... spaces){
        flats = new ArrayList<>();
        for(int i = 0;i<spaces.length;i++){
            flats.add(spaces[i]);
        }
    }
    public int getNumOfSpaces(){
        return  flats.size();
    }
    public int getAllSquare(){
        int result = 0;
        for(int i = 0; i< flats.size(); i++){
            if(flats.get(i)!= null){
                result+= flats.get(i).getSquare();
            }
        }
        return result;
    }
    public int getAllRoom(){
        int result = 0;
        for(int i = 0; i< flats.size(); i++){
            if(flats.get(i)!= null){
                result+= flats.get(i).getNumOfRooms();
            }
        }
        return result;
    }

    public Space[] getSpaces(){
        return (Space[]) flats.toArray();
    }

    public Space getSpace(int n){
        synchronized (this){
        try {
            if(flats.get(n) == null){
                throw new SpaceIndexOutOfBoundsException("Квартиры с этим номером не существует!");

            }
            return flats.get(n);
        }catch (Exception e){
            throw new SpaceIndexOutOfBoundsException("Квартиры с этим номером не существует!");
        }}
    }

    public void setSpace(int n, Space f){
        try {
             flats.set(n,f);
       }catch (IndexOutOfBoundsException e){
           throw new SpaceIndexOutOfBoundsException("Квартира с таким номером не существует");
        }
    }

    public void addSpace(int n, Space f){
        flats.add(n,f);
    }

    public void deleteSpace(int n){
       flats.remove(n);
    }

    public Space getBestSpace(){
        Space max = new Flat(0,0);
        for (int i = 0; i < flats.size(); i++) {
            if(flats.get(i)!=null){
                if(flats.get(i).getSquare()>max.getSquare()){
                    max = flats.get(i);
                }
            }

        }
        return max;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof FlatFloor){
            FlatFloor floor = (FlatFloor) obj;
            if(floor.getNumOfSpaces()==getNumOfSpaces()){
                for (int i = 0; i < getNumOfSpaces(); i++) {
                    if(floor.getSpace(i).equals(this.getSpace(i))){

                    }else {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
            return false;

    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FlatFloor("+getNumOfSpaces()+", ");
        for (int i = 0; i < getNumOfSpaces(); i++) {
            stringBuilder.append(getSpace(i).toString()+", ");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public Iterator iterator() {
        class Iter implements Iterator{
            int cur = -1;
            @Override
            public boolean hasNext() {
                if(cur+1<getNumOfSpaces()){
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                cur++;
                return getSpace(cur);
            }
        }
        Iter iter = new Iter();
        return iter;
    }

    @Override
    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getNumOfSpaces()+" ");
        for (int i = 0; i < getNumOfSpaces(); i++) {
            stringBuilder.append(getSpace(i).getString()+" ");
        }
        return stringBuilder.toString();
    }
    @Override
    public int hashCode() {
        int a = getNumOfSpaces();
        for (int i = 0; i < getNumOfSpaces(); i++) {
            a = a ^ getSpace(i).hashCode();
        }
        return a;
    }

    public Object clone()  {
        Space[] spaces = new Space[getNumOfSpaces()];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = (Space) getSpace(i).clone();
        }
        HotelFloor officeFloor = null;
        try {
            officeFloor = (HotelFloor)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        officeFloor.setSpaces(spaces);
        return officeFloor;
    }
}
