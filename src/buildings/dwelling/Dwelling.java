package buildings.dwelling;


import buildings.Spaces.Flat;
import buildings.Floors.FlatFloor;
import buildings.interfaces.Building;
import buildings.Exceptions.FloorIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.Spaces.Office;
import buildings.interfaces.Space;

import java.io.ObjectInputStream;
import java.util.Iterator;

public class Dwelling implements Building {
    private Floor[] floors;

    public Dwelling(int nFloors, int... nFlats) {
        floors = new FlatFloor[nFloors];
        for (int i = 0; i < nFloors; i++) {
            floors[i] = new FlatFloor(nFlats[i]);
        }
    }

    public Dwelling(Floor... f) {
        floors = f;
    }

    public int getNumOfFloors() {
        return floors.length;
    }

    public int getNumOfSpaces() {
        int result = 0;
        for (var v : floors) {
            if (v != null)
                result += v.getNumOfSpaces();
        }
        return result;
    }

    public int getSquare() {
        int result = 0;
        for (var v : floors
        ) {
            if (v != null) {
                result += v.getAllSquare();
            }
        }
        return result;
    }

    public int getRooms() {
        int result = 0;
        for (var v : floors
        ) {
            if (v != null) {
                result += v.getAllRoom();
            }
        }
        return result;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Floor getFloor(int n) {
        try {
            return floors[n];
        } catch (IndexOutOfBoundsException e) {
           throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");
        }

    }

    public void setFloor(int n, Floor d) {
        try {
            floors[n] = (FlatFloor)d;
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");
        }
    }

    @Override
    public Space getSpace(int n) {
        try {
            for (int i = 0; i < floors.length; i++) {
                if (floors[i] != null && n >= floors[i].getNumOfSpaces()) {
                    n -= floors[i].getNumOfSpaces();
                } else {
                    return floors[i].getSpaces()[n];
                }
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");

        }
    }


    public void setSpace(int n, Space f) {
        try {
            for (int i = 0; i < floors.length; i++) {
                if (floors[i] != null && n >= floors[i].getNumOfSpaces()) {
                    n -= floors[i].getNumOfSpaces();
                } else {
                    floors[i].setSpace(n, (Office) f);
                }
            }

        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");

        }
    }

    public void addSpace(int n, Space f) {
        try {
            for (int i = 0; i < floors.length; i++) {
                if (floors[i] != null && n >= floors[i].getNumOfSpaces()) {
                    n -= floors[i].getNumOfSpaces();
                } else {
                    floors[i].addSpace(n, f);
                }
            }

        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");

        }
    }

    public void deleteSpace(int n) {
        try {
            for (int i = 0; i < floors.length; i++) {
                if (floors[i] != null && n >= floors[i].getNumOfSpaces()) {
                    n -= floors[i].getNumOfSpaces();
                } else {
                    floors[i].deleteSpace(n);
                }
            }

        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("Этажа с таким номерм не существует");

        }
    }

    public Space getBestSpace() {
        Space max = new Flat(0,0);
        for (var v : floors
        ) {
            if (v != null) {
                if (max.getSquare() < v.getBestSpace().getSquare()) {
                    max = v.getBestSpace();
                }
            }
        }
        return max;
    }

    public Office[] sortSpaces() {
        Office[] fl = new Office[getNumOfSpaces()];
        int i = 0;
        for (var v : floors ) {
            if (v != null) {
                for (Object f : v.getSpaces()
                ) {
                    fl[i] = (Office) f;
                    i++;
                }
            }
        }
        bubbleSort(fl);
        return fl;
    }

    @Override
    public String getString() {
        StringBuilder s = new StringBuilder();
        s.append(getNumOfFloors()+" ");
        Floor[] floors = getFloors();
        for(int i = 0;i<getNumOfFloors();i++){
            s.append(floors[i].getString());
        }
        return s.toString();
    }

    public void setFloors(Floor[] floors) {
       this.floors = floors;
    }
    @Override
    public Iterator iterator() {
        class Iter implements Iterator{
            int curr = -1;
            public boolean hasNext(){
                if(curr+1<getNumOfFloors()){
                    return true;
                }
                return false;
            }

            public Object next(){
                curr++;
                return getFloor(curr);
            }
        }
        return new Iter();
    }

    private static void bubbleSort(Office[] arr){
        for(int i = arr.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            if( arr[j].compareTo(arr[j+1])<0 ){
                Office tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
}

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Dwelling("+getNumOfFloors()+", ");
        Floor[] floors = getFloors();
        for(int i = 0;i<getNumOfFloors();i++){
            s.append(floors[i].toString()+",");
        }
        s.append(")");
        return s.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
       if(obj instanceof  Dwelling){
            Dwelling building = (Dwelling) obj;
            if(building.getNumOfFloors() == this.getNumOfFloors()){
                for (int i = 0; i < getNumOfFloors() ; i++) {
                    if(getFloor(i).equals(building.getFloor(i))){

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
    public int hashCode() {
        int a = getNumOfFloors();
        for (int i = 0; i < getNumOfFloors(); i++) {
            a = a ^ getFloor(i).hashCode();
        }
        return a;
    }

    public Object clone(){
        Dwelling dwelling = null;
        try {
            dwelling = (Dwelling) super.clone();
            dwelling.floors = new Floor[getNumOfFloors()];
            for (int i = 0; i < floors.length; i++) {
                dwelling.floors[i] = (Floor) getFloor(i).clone();
            }
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
//        Floor[] floors = new Floor[getNumOfFloors()];
//        for (int i = 0; i < floors.length; i++) {
//            floors[i] = (Floor) getFloor(i).clone();
//        }

        return dwelling;
    }
}
