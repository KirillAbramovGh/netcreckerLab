package buildings.dwelling;


import buildings.Spaces.Office;
import buildings.interfaces.Building;
import buildings.Exceptions.FloorIndexOutOfBoundsException;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.List.LinkedListTw;
import buildings.interfaces.Space;

import java.util.Iterator;

public class OfficeBuilding implements Building {
    private LinkedListTw floors;

    public OfficeBuilding(int nFloors, int... nFlats) {
        floors = new LinkedListTw(nFloors,nFlats);
    }

    public OfficeBuilding(Floor... f) {
        floors = new LinkedListTw(f);
    }

    public int getNumOfFloors() {
        return floors.size();
    }

    public int getNumOfSpaces() {
        int result = 0;
        for (var v : floors) {
            if (v != null)
                result += ((Floor)v).getNumOfSpaces();
        }
        return result;
    }

    public int getSquare() {
        int result = 0;
        for (var v : floors
        ) {
            if (v != null) {
                result += ((Floor)v).getAllSquare();
            }
        }
        return result;
    }

    public int getRooms() {
        int result = 0;
        for (var v : floors
        ) {
            if (v != null) {
                result += ((Floor)v).getAllRoom();
            }
        }
        return result;
    }

    public Floor[] getFloors() {
        return floors.toArray();
    }

    public Floor getFloor(int n) {
        try {
            return floors.get(n);
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("incorrect index");

        }

    }

    public void setFloor(int n, Floor d) {
        try {
            floors.set(n, d);
        }catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException("incorrect index");

        }
    }

    public Space getSpace(int n) {
        try {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i) != null && n >= floors.get(i).getNumOfSpaces()) {
                    n -= floors.get(i).getNumOfSpaces();
                } else {
                    return   floors.get(i).getSpaces()[n];
                }
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
       //     throw new SpaceIndexOutOfBoundsException("incorrect index");
return null;
        }
    }

    public void setSpace(int n, Space f) {
        try {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i) != null && n >= floors.get(i).getNumOfSpaces()) {
                    n -= floors.get(i).getNumOfSpaces();
                } else {
                    floors.get(i).setSpace(n, f);
                    break;
                }
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Квартиры с таким номером не существует");

        }
    }

    public void addSpace(int n, Space f) {
        try {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i) != null && n >= floors.get(i).getNumOfSpaces()) {
                    n -= floors.get(i).getNumOfSpaces();
                } else {
                    floors.get(i).addSpace(n, f);
                }
            }

        } catch (IndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("incorrect index");

        }
    }

    public void deleteSpace(int n) {
        try {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i) != null && n >= floors.get(i).getNumOfSpaces()) {
                    n -= floors.get(i).getNumOfSpaces();
                } else {
                    floors.get(i).deleteSpace(n);
                }
            }

        } catch (IndexOutOfBoundsException e) {
           throw new SpaceIndexOutOfBoundsException("incorrect index");

        }
    }

    public Space getBestSpace() {
        Space max = new Office(0,0);
        for (var v : floors
        ) {
            if (v != null) {
                if (max.getSquare() < ((Floor)v).getBestSpace().getSquare()) {
                    max = ((Floor)v).getBestSpace();
                }
            }
        }
        return max;
    }

    public Space[] sortSpaces() {
        var fl = new Space[getNumOfSpaces()];
        var i = 0;
        for (var j = 0; j < floors.size(); j++) {
            var o = floors.get(j);

            var arr =   o.getSpaces();
            for (int k = 0; k < arr.length; k++) {
                fl[i] =   arr[k];
                i++;
            }
        }

        bubbleSort(fl);
        return fl;
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("OfficeBuilding("+getNumOfFloors()+", ");
        Floor[] floors = getFloors();
        for(int i = 0;i<getNumOfFloors();i++){
            s.append(floors[i].toString()+",");
        }
        s.append(")");
        return s.toString();
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

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof OfficeBuilding) {
            OfficeBuilding building = (OfficeBuilding) obj;
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

    private static void bubbleSort(Space[] arr){
        for(int i = arr.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( arr[j].compareTo(arr[j+1])<0 ){
                    var tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
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
    @Override
    public int hashCode() {
        int a = getNumOfFloors();
        for (int i = 0; i < getNumOfFloors(); i++) {
            a = a ^ getFloor(i).hashCode();
        }
        return a;
    }

    public Object clone(){
        Floor[] floors = new Floor[getNumOfFloors()];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = (Floor) getFloor(i).clone();
        }
        OfficeBuilding officeFloor = new OfficeBuilding(floors);
        return officeFloor;
    }
}
