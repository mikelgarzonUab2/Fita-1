package baseNoStates.users;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import baseNoStates.Area;
import baseNoStates.areas.DirectoryAreas;
import baseNoStates.doorstates.Actions;

public final class Schedule {

    public static ArrayList<UserGroup> getAllGroups() {

        ArrayList<UserGroup> group = new ArrayList<>();

        // Crear les llistes per els dies de les setmanes i accions
        ArrayList<DayOfWeek> weekDays = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        // Crear accions que es poden fer en general
        ArrayList<String> allActions = new ArrayList<>(
                Arrays.asList(Actions.LOCK, Actions.UNLOCK, Actions.OPEN, Actions.CLOSE, Actions.UNLOCK_SHORTLY));

        // Recuperem les instacnies inicialitzades en el main
        Area exterior = DirectoryAreas.findAreaById("exterior");
        Area stairs = DirectoryAreas.findAreaById("stairs");
        Area hall = DirectoryAreas.findAreaById("hall");
        Area room1 = DirectoryAreas.findAreaById("room1");
        Area room2 = DirectoryAreas.findAreaById("room2");
        Area room3 = DirectoryAreas.findAreaById("room3");
        Area corridor = DirectoryAreas.findAreaById("corridor");
        Area IT = DirectoryAreas.findAreaById("IT");
        Area parking = DirectoryAreas.findAreaById("parking");

        // Es crea el grup de persones que no tenen autoritazió
        UserGroup cap_area = new UserGroup("Cap_area", null, null, null, null, null, null, null);
        cap_area.Append(new User("Bernat", "12345", cap_area));
        cap_area.Append(new User("Blai", "77532", cap_area));
        group.add(cap_area);

        // Si alguns del peramteres és null vol dir que l'usurai no té
        // permis per realitzar cap tipus d'acció que contingui aquest null

        // employees :
        // Sep. 1 this year to Mar. 1 next year
        // week days 9-17h
        // just shortly unlock
        // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere
        // but the parking

        // Es crean els empleats amb els permisos esmentats anteriorment
        ArrayList<Area> areasEmployees = new ArrayList<>(
                Arrays.asList(exterior, stairs, hall, room1, room2, room3, corridor, IT));

        UserGroup employee = new UserGroup("employee", areasEmployees,
                LocalDateTime.of(2024, 9, 1, 0, 0),
                LocalDateTime.of(2025, 3, 1, 23, 59),
                LocalTime.of(9, 0), LocalTime.of(17, 0),
                weekDays, new ArrayList<>(Arrays.asList(Actions.UNLOCK_SHORTLY, Actions.OPEN, Actions.CLOSE)));

        employee.Append(new User("Ernest", "74984", employee));
        employee.Append(new User("Eulalia", "43295", employee));
        group.add(employee);

        // managers :
        // Sep. 1 this year to Mar. 1 next year
        // week days + saturday, 8-20h
        // all actions
        // all spaces

        // Creem el grup dels manegers
        ArrayList<Area> allAreas = new ArrayList<>(areasEmployees);
        allAreas.add(parking);
        ArrayList<DayOfWeek> weekAndSat = new ArrayList<>(weekDays);
        weekAndSat.add(DayOfWeek.SATURDAY);

        UserGroup manager = new UserGroup("manager", allAreas,
                LocalDateTime.of(2024, 9, 1, 0, 0),
                LocalDateTime.of(2025, 3, 1, 23, 59),
                LocalTime.of(8, 0), LocalTime.of(20, 0), weekAndSat, allActions);

        manager.Append(new User("Manel", "95783", manager));
        manager.Append(new User("Marta", "05827", manager));
        group.add(manager);

        // admin :
        // always=Jan. 1 this year to 2100
        // all days of the week
        // all actions
        // all spaces

        // Esteblim l'adiminstració del sistema amb el control total del sistema
        ArrayList<DayOfWeek> allWeek = new ArrayList<>(weekAndSat);
        allWeek.add(DayOfWeek.SUNDAY);

        UserGroup admin = new UserGroup("admin", allAreas,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2100, 12, 31, 23, 59),
                LocalTime.of(0, 0), LocalTime.of(23, 59), allWeek, allActions);

        admin.Append(new User("Ana", "11343", admin));
        group.add(admin);

        return group;
    }
}
