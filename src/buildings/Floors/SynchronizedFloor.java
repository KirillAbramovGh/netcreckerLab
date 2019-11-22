package buildings.Floors;

import java.util.Iterator;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class SynchronizedFloor implements Floor
{
    private Floor floor;
    public SynchronizedFloor(Floor floor){
        this.floor = floor;
    }
    @Override
    public int getNumOfSpaces()
    {
        synchronized (floor){
            return floor.getNumOfSpaces();
        }
    }

    @Override
    public int getAllSquare()
    {
        synchronized (floor){
            return floor.getAllSquare();
        }
    }

    @Override
    public int getAllRoom()
    {
        synchronized (floor){
            return floor.getAllRoom();
        }
    }

    @Override
    public Space[] getSpaces()
    {
        synchronized (floor){
            return floor.getSpaces();
        }
    }

    @Override
    public Space getSpace(final int n)
    {
        synchronized (floor){
            return floor.getSpace(n);
        }
    }

    @Override
    public void addSpace(final int n, final Space f)
    {
        synchronized (floor){
            floor.addSpace(n,f);
        }
    }

    @Override
    public void deleteSpace(final int n)
    {
        synchronized (floor){
            floor.deleteSpace(n);
        }
    }

    @Override
    public Space getBestSpace()
    {
        synchronized (floor){
            return floor.getBestSpace();
        }
    }

    @Override
    public void setSpace(final int n, final Space f)
    {
        synchronized (floor){
            floor.setSpace(n,f);
        }
    }

    @Override
    public Iterator iterator()
    {
        synchronized (floor){
            return floor.iterator();
        }
    }

    @Override
    public String getString()
    {
        synchronized (floor){
            return floor.getString();
        }
    }

    @Override
    public Object clone()
    {
        synchronized (floor){
            return floor.clone();
        }
    }
}
