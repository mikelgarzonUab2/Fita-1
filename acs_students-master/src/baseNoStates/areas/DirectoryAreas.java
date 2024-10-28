package baseNoStates.areas;

import baseNoStates.Area;
import baseNoStates.Door;

import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryAreas {
  private static ArrayList<Door> allDoors;
  private static Area root; // Primer ària -> bulding

  public static void makeAreas() {

    // Es crean totes les particions
    Partition building = new Partition("building", null);
    root = building;

    Partition basement = new Partition("basement", building);
    Partition ground_floor = new Partition("ground_floor", building);
    Partition floor1 = new Partition("floor1", building);

    Space stairs = new Space("stairs", building);
    Space exterior = new Space("exterior", building);

    Space parking = new Space("parking", basement);
    Space hall = new Space("hall", ground_floor);
    Space room1 = new Space("room1", ground_floor);
    Space room2 = new Space("room2", ground_floor);
    Space room3 = new Space("room3", floor1);
    Space corridor = new Space("corridor", floor1);
    Space IT = new Space("IT", floor1);

    // Inicialitzen totes les portes
    // basement
    Door d1 = new Door("D1", exterior, parking); // exterior, parking
    Door d2 = new Door("D2", stairs, parking); // stairs, parking

    // ground floor
    Door d3 = new Door("D3", exterior, hall); // exterior, hall
    Door d4 = new Door("D4", stairs, hall); // stairs, hall
    Door d5 = new Door("D5", hall, room1); // hall, room1
    Door d6 = new Door("D6", hall, room2); // hall, room2

    // first floor
    Door d7 = new Door("D7", stairs, corridor); // stairs, corridor
    Door d8 = new Door("D8", corridor, room3); // corridor, room3
    Door d9 = new Door("D9", corridor, IT); // corridor, IT

    // Especifiquem cada espai quines portes té
    parking.setDoors(new ArrayList<>(Arrays.asList(d1, d2)));
    hall.setDoors(new ArrayList<>(Arrays.asList(d3, d4, d5, d6)));
    room1.setDoors(new ArrayList<>(Arrays.asList(d5)));
    room2.setDoors(new ArrayList<>(Arrays.asList(d6)));
    room3.setDoors(new ArrayList<>(Arrays.asList(d8)));
    corridor.setDoors(new ArrayList<>(Arrays.asList(d7, d8, d9)));
    IT.setDoors(new ArrayList<>(Arrays.asList(d9)));

    // Variable que conté totes les portes de l'edifici
    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

  }

  public static Area findAreaById(String id) {
    return root.findAreaById(id);
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
