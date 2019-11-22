package buildings.threads;

import buildings.interfaces.Floor;

public class SequentalCleaner implements Runnable
{
    Floor floor;
    MySemaphore mySemaphore;

    public void setMySemaphore(final MySemaphore mySemaphore)
    {
        this.mySemaphore = mySemaphore;
    }

    public void setFloor(Floor floor)
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
                              "Cleaning room number " + i + " with total area " + floor.getSpace(i).getSquare() +
                                      " square meters");
                      mySemaphore.realize();
                  }

          }catch (Exception e){
              e.printStackTrace();
          }
      }
    }
}
