package buildings.factories;

import buildings.Floors.FlatFloor;
import buildings.Spaces.Flat;
import buildings.dwelling.Dwelling;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        return new Flat(area,roomsCount);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new FlatFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space... spaces) {
        return new FlatFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int... spacesCounts) {
        return new Dwelling(floorsCount,spacesCounts);
    }

    @Override
    public Building createBuilding(Floor... floors) {
        return new Dwelling(floors);
    }
}
