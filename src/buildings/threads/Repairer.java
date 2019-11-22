package buildings.threads;

import buildings.interfaces.Floor;

public class Repairer extends Thread
{

    Floor floor;
    public void setFloor(Floor floor){
        this.floor = floor;
    }
    @Override
    public void run()
    {

            for (int i = 0; i < floor.getNumOfSpaces(); i++)
            {
                System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare() +
                        " square meters");
            }

    }

}
