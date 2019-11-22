package buildings.interfaces;

import buildings.Spaces.Office;
import buildings.dwelling.OfficeBuilding;
import buildings.Floors.OfficeFloor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

public interface Building extends Serializable,Cloneable {
    public int getNumOfFloors();
    public int getNumOfSpaces();
    public int getSquare();
    public int getRooms();
    public Floor[] getFloors();
    public Floor getFloor(int n);
    public void setFloor(int n, Floor d);
    public Space getSpace(int n);
    public void setSpace(int n, Space f);
    public void addSpace(int n, Space f);
    public void deleteSpace(int n);
    public Space getBestSpace();
    public Space[] sortSpaces();
    public static Building fromString(String s){

        Scanner scanner = new Scanner(s);
        int numOfFloors = scanner.nextInt();
        Floor[] floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++) {
            int numOfSpaces = scanner.nextInt();
            Space[] spaces = new Space[numOfSpaces];
            for (int j = 0; j < numOfSpaces; j++) {

                int square = scanner.nextInt();
                int numOfRooms = scanner.nextInt();
                spaces[j] = new Office(square,numOfRooms);
            }
            floors[i] = new OfficeFloor(spaces);
        }
        Building building = new OfficeBuilding(floors);
        return building;
    }
    public String getString();
    Iterator iterator();
    public Object clone();
}
