package buildings.interfaces;

import java.io.Serializable;

public interface Space extends Serializable,Cloneable {
    public int getNumOfRooms();
    public void setNumOfRooms(int r);
    public double getSquare();
    public void setSquare(double s);
    public int compareTo(Space s);
    public String getString();
    public Object clone();
}
