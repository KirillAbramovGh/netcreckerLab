package buildings.interfaces;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Serializable,Cloneable {
    public int getNumOfSpaces();
    public int getAllSquare();
    public int getAllRoom();
    public Space[] getSpaces();
    public Space getSpace(int n);
    public void addSpace(int n, Space f);
    public void deleteSpace(int n);
    public Space getBestSpace();
    public void setSpace(int n,Space f);
    Iterator iterator();
    public String getString();
    public Object clone();
}
