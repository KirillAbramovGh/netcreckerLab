package buildings.factories;

import buildings.Floors.OfficeFloor;
import buildings.Spaces.Office;
import buildings.dwelling.OfficeBuilding;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Office(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        return new Office(area,roomsCount);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCounts) {
        return new OfficeBuilding(floorsCount,spacesCounts);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new OfficeBuilding(floors);
    }
}
