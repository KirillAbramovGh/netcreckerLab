package buildings.threads;

import java.util.concurrent.Semaphore;

import buildings.interfaces.Floor;

public class SequentalRepairer implements Runnable
{
    private Floor floor;
    private MySemaphore mySemaphore;

    public void setMySemaphore(final MySemaphore mySemaphore)
    {
        this.mySemaphore = mySemaphore;
    }

    public void setFloors(Floor floor)
    {
        this.floor = floor;
    }

    @Override
    public void run()
    {
        synchronized (mySemaphore)
        {
            try
            {

                    for (int i = 0; i < floor.getNumOfSpaces(); i++)
                    {

                            mySemaphore.acquire();

                        System.out.println(
                                "Repairing space number " + i + " with total area " + floor.getSpace(i).getSquare() +
                                        " square meters");
                        mySemaphore.realize();

                    }


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
