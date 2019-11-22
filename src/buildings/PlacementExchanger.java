package buildings;

import buildings.Exceptions.InexchangeableFloorsException;
import buildings.Exceptions.InexchangeableSpacesException;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class PlacementExchanger {
    public static boolean canExchSpace(Space a, Space b){
        if(a.getNumOfRooms() == b.getNumOfRooms()){
            if (a.getSquare()==b.getSquare()){
                return true;
            }
        }
        return false;
    }

    public static boolean canExchFloor(Floor a, Floor b){
        if(a.getAllSquare() == b.getAllSquare()){
            if(a.getAllRoom() == b.getAllRoom()){
                return true;
            }
        }
        return false;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2){
        if(canExchSpace(floor1.getSpace(index1),floor2.getSpace(index2))){
            Space space1 = floor1.getSpace(index1);
            Space space2 = floor2.getSpace(index1);
            var v = space1;
            space1 = space2;
            space2 = v;
            floor1.setSpace(index1,space1);
            floor2.setSpace(index2,space2);
        }else {
            throw new InexchangeableSpacesException();
        }
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building Building2, int index2){
        if(canExchFloor(building1.getFloor(index1),Building2.getFloor(index2))){
            Floor floor1 = building1.getFloor(index1);
            Floor floor2 = Building2.getFloor(index1);
            var v = floor1;
            floor1 = floor2;
            floor2 = v;
            building1.setFloor(index1,floor1);
            Building2.setFloor(index2,floor2);
        }else {
            throw new InexchangeableFloorsException();
        }
    }


}
