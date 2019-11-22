package buildings.threads;

public class MySemaphore
{
    private int count;

    public MySemaphore(int count){
        this.count = count;
    }


    public void acquire() throws InterruptedException
    {
        synchronized (this)
        {
            if (count > 0)
            {
                count--;
            }
            else
            {
                wait();
            }
        }

    }

    public void realize() throws InterruptedException
    {
        synchronized (this)
        {
            count++;
            notify();
            wait();
        }

    }

}
