package buildings.dwelling.hotel;

import buildings.Floors.FlatFloor;
import buildings.interfaces.Space;

import java.util.Iterator;

public class HotelFloor extends FlatFloor {

    private int numOfStars;
    public HotelFloor(int numOfSpaces) {
        super(numOfSpaces);
    }

    public HotelFloor(Space... f) {
        super(f);
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public void setNumOfStars(int numOfStars) {
        this.numOfStars = numOfStars;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            HotelFloor floor = (HotelFloor) obj;
            if(getNumOfSpaces()==floor.getNumOfSpaces() && getNumOfStars()==floor.getNumOfStars() ){
                for (int i = 0; i < getNumOfSpaces(); i++) {
                    if(floor.getSpace(i).equals(this.getSpace(i))){

                    }else {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }catch (ClassCastException e){
            return false;
        }
    }

    public Object clone(){
        Space[] spaces = new Space[getNumOfSpaces()];
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = (Space) getSpace(i).clone();
        }
        HotelFloor officeFloor = (HotelFloor)super.clone();
        officeFloor.setSpaces(spaces);
        return officeFloor;
    }
}
