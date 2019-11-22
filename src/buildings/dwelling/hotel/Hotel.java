package buildings.dwelling.hotel;

import buildings.Floors.FlatFloor;
import buildings.Spaces.Flat;
import buildings.Spaces.Office;
import buildings.dwelling.Dwelling;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class Hotel extends Dwelling {
    public Hotel(int nFloors, int... nSpaces) {
        super(nFloors, nSpaces);
    }

    public Hotel(Floor... f) {
        super(f);
    }

    public int getNumOfStars(){
        int max = 0;
        for (int i = 0; i < getNumOfFloors(); i++) {
            if(max<((HotelFloor)getFloor(i)).getNumOfStars()){
           max = ((HotelFloor)getFloor(i)).getNumOfStars();
            }
        }
        return max;
    }

    @Override
    public Space getBestSpace(){
         Space max = new Flat(0,0);
         double p = 0;
        for (int i = 0; i < getNumOfFloors(); i++) {
            Space space = getFloor(i).getBestSpace();
            if(getFloor(i) instanceof HotelFloor) {
                int stars = ((HotelFloor) getFloor(i)).getNumOfStars();
                if (space.getSquare() * stars > p * max.getSquare()) {
                    p = stars;
                    max = space;
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
        if(obj instanceof  Hotel){
            Hotel building = (Hotel) obj;
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

    public Object clone(){
        Floor[] floors = new Floor[getNumOfSpaces()];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = (Floor) getFloor(i).clone();
        }
        Hotel hotel = (Hotel)super.clone();
        hotel.setFloors(floors);
        return hotel;
    }


}
