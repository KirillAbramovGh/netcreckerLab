package buildings.threads;

import buildings.interfaces.Floor;

public class Cleaner extends Thread
{
    Floor floor;

    public void setFloor(Floor floor)
    {
        this.floor = floor;
    }

    @Override
    public void run()
    {

            for (int i = 0; i < floor.getNumOfSpaces(); i++)
            {
                System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getSquare() +
                        " square meters");
            }

    }
}
