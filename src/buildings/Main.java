package buildings;

import buildings.Floors.FlatFloor;
import buildings.Spaces.Flat;
import buildings.interfaces.Floor;
import buildings.threads.MySemaphore;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

public class Main {
    public static void main(String[] args) throws Exception{
        Floor firstFloor = new FlatFloor(new Flat(1,2),new Flat(3,4),new Flat(5,6));
                Floor secondFloor = new FlatFloor(new Flat(6,9),new Flat(2,4),new Flat(9,6));
                Floor thirdFloor = new FlatFloor(new Flat(2,8),new Flat(8,4),new Flat(1,1));
                Floor[] floors = new Floor[]{firstFloor,secondFloor,thirdFloor};

//        Repairer repairer = new Repairer();
//        Cleaner cleaner = new Cleaner();
//        repairer.setPriority(Thread.MAX_PRIORITY);
//        cleaner.setPriority(Thread.MIN_PRIORITY);
//        repairer.setFloor(firstFloor,secondFloor,thirdFloor);
//        cleaner.setFloor(firstFloor,secondFloor,thirdFloor);
//        repairer.start();
//        cleaner.start();


        MySemaphore mySemaphore = new MySemaphore(1);
        SequentalCleaner sequentalCleaner0 = new SequentalCleaner();
        sequentalCleaner0.setFloor(floors[0]);
        sequentalCleaner0.setMySemaphore(mySemaphore);


        SequentalCleaner sequentalCleaner1 = new SequentalCleaner();
        sequentalCleaner1.setFloor(floors[1]);
        sequentalCleaner1.setMySemaphore(mySemaphore);

        SequentalRepairer sequentalRepairer0 = new SequentalRepairer();
        sequentalRepairer0.setFloors(floors[0]);
        sequentalRepairer0.setMySemaphore(mySemaphore);

        SequentalRepairer sequentalRepairer1 = new SequentalRepairer();
        sequentalRepairer1.setFloors(floors[1]);
        sequentalRepairer1.setMySemaphore(mySemaphore);

        Thread thread1 = new Thread(sequentalCleaner0);
        Thread thread2 = new Thread(sequentalRepairer0);

        Thread thread3 = new Thread(sequentalCleaner1);
        Thread thread4 = new Thread(sequentalRepairer1);

        thread2.start();
        thread1.start();
        thread3.start();
        thread4.start();

    }
}


