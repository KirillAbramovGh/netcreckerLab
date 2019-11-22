package buildings.Floors;

import buildings.Spaces.Office;
import buildings.interfaces.Floor;
import buildings.List.LinkedList;
import buildings.interfaces.Space;

import java.util.Iterator;

public class OfficeFloor implements Floor,Cloneable {

    private LinkedList offices;

    public OfficeFloor(int numOfFlats){
        offices = new LinkedList(numOfFlats);

    }
    public OfficeFloor(Space... f){
        offices = new LinkedList(f);
    }

    public int getNumOfSpaces(){
        return  offices.getSize();
    }
    public int getAllSquare(){
        int result = 0;
        for(int i = 0; i< offices.getSize(); i++){
            if(offices.get(i)!= null){
                result+= ((Space)offices.get(i)).getSquare();
            }
        }
        return result;
    }
    public int getAllRoom(){
        int result = 0;
        for(int i = 0; i< offices.getSize(); i++){
            if(offices.get(i)!= null){
                result+= ((Space)offices.get(i)).getNumOfRooms();
            }
        }
        return result;
    }

    public Space[] getSpaces(){
        return offices.toArray();
    }

    public Space getSpace(int n){
        try {
            return offices.get(n);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Квартиры с этим номером не существует!");
            return null;
        }
    }

    public void setSpace(int n, Space f){
        try {
            offices.set(n,f);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Квартира с таким номером не существует");
        }
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

    public void addSpace(int n, Space f){
        LinkedList fl;
        if(n>= offices.getSize()){
            fl = new LinkedList(n+1);
            for (int i = 0; i < offices.getSize(); i++) {
                fl.set(i,offices.get(i));
            }
            fl.set(n,f);
        }
        else {
            fl = new LinkedList(offices.getSize() + 1);
            for (int i = 0; i < n; i++) {
                fl.set(i,offices.get(i));
            }
            fl.set(n,f);
            for (int i = n+1; i < fl.getSize(); i++) {
                fl.set(i,offices.get(i-1));
            }
        }
        offices = fl;
    }

    public void deleteSpace(int n){
        try {
            offices.remove(n);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Квартиры с таким номером не существует");
        }
    }

    public Space getBestSpace(){
        Space max = new Office(0,0);
        for (int i = 0; i < offices.getSize(); i++) {
            if(offices.get(i)!=null){
                if(offices.get(i).getSquare()>max.getSquare()){
                    max = offices.get(i);
                }
            }

        }
        return max;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OfficeFloor("+getNumOfSpaces()+", ");
        for (int i = 0; i < getNumOfSpaces(); i++) {
            stringBuilder.append(getSpace(i).toString()+", ");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj instanceof OfficeFloor){
            OfficeFloor floor = (OfficeFloor) obj;
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

    public Object clone(){
        Space[] spaces = new Space[getNumOfSpaces()];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = (Space) getSpace(i).clone();
        }
        OfficeFloor officeFloor = new OfficeFloor(spaces);
        return officeFloor;
    }
}
