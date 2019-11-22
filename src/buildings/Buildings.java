package buildings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import buildings.Floors.OfficeFloor;
import buildings.Floors.SynchronizedFloor;
import buildings.factories.DwellingFactory;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class Buildings
{

    private static BuildingFactory buildingFactory = new DwellingFactory();


    public static Floor synchronizedFloor(Floor floor){
        return new SynchronizedFloor(floor);
    }


    public static void setBuildingFactory(BuildingFactory buildingFactory)
    {
        Buildings.buildingFactory = buildingFactory;
    }

    public static void outputBuilding(Building building, OutputStream out)
    {
        try (out)
        {
            out.write(building.getString().getBytes());
        }
        catch (Exception e)
        {

        }
    }

    public static Building inputBuilding(InputStream in, Class spaceClass, Class floorClass, Class buildingClass)
    {
        StringBuilder stringBuilder = null;
        try (in)
        {
            byte[] b = in.readAllBytes();
            stringBuilder = new StringBuilder();
            for (var v : b)
            {
                stringBuilder.append((char) v);
            }

        }
        catch (Exception e)
        {

        }
        return fromString(stringBuilder.toString(), spaceClass, floorClass, buildingClass);
    }

    public static void writeBuilding(Building building, Writer out) throws IOException
    {
        out.write(building.getString());
    }

    public static Building readBuilding(Reader in, Class spaceClass, Class floorClass, Class buildingClass)
    {
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\n");
        String s = scanner.next();
        System.out.println(s);
        Building building = fromString(s, spaceClass, floorClass, buildingClass);
        return building;
    }

    private static Building fromString(String s)
    {
        Scanner scanner = new Scanner(s);
        int numOfFloors = scanner.nextInt();
        Floor[] floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++)
        {
            int numOfSpaces = scanner.nextInt();
            Space[] spaces = new Space[numOfSpaces];
            for (int j = 0; j < numOfSpaces; j++)
            {
                double square = Double.parseDouble(scanner.next());
                int numOfRooms = scanner.nextInt();
                spaces[j] = buildingFactory.createSpace(numOfRooms, square);
            }
            floors[i] = new OfficeFloor(spaces);
        }
        Building building = buildingFactory.createBuilding(floors);
        return building;
    }

    public static Space createSpace(double square, int numOfRooms, Class spaceClass)
    {
        try
        {
            return (Space) spaceClass.getConstructor(double.class, int.class).newInstance(square, numOfRooms);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            return null;
        }

    }

    public static Floor createFloor(Space[] spaces, Class floorClass)
    {
        try
        {
            return (Floor) floorClass.getConstructor(spaces.getClass()).newInstance((Object) spaces);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            return null;
        }
    }

    public static Building createBuilding(Floor[] floors, Class buildingClass)
    {
        try
        {
            return (Building) buildingClass.getConstructor(floors.getClass()).newInstance((Object) floors);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            return null;
        }
    }

    private static Building fromString(String s, Class spaceClass, Class floorClass, Class buildingClass)
    {

        Scanner scanner = new Scanner(s);
        int numOfFloors = scanner.nextInt();
        Floor[] floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++)
        {
            int numOfSpaces = scanner.nextInt();
            Space[] spaces = new Space[numOfSpaces];
            for (int j = 0; j < numOfSpaces; j++)
            {
                double square = Double.parseDouble(scanner.next());
                int numOfRooms = scanner.nextInt();
                spaces[j] = createSpace(square, numOfRooms, spaceClass);
            }
            floors[i] = createFloor(spaces, floorClass);
        }
        Building building = createBuilding(floors, buildingClass);

        return building;
    }

}
