package baseNoStates.users;

import baseNoStates.Area;
import baseNoStates.areas.Space;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserGroup {
    private ArrayList<User> users = new ArrayList<User>(); // Total d'usuaris dintre el grup
    private String idGroup;
    private ArrayList<Area> areas = new ArrayList<Area>();
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<DayOfWeek> validDays;
    private ArrayList<String> allowedActions;

    public UserGroup(String nameGroup, ArrayList<Area> areas, LocalDateTime validFrom,
            LocalDateTime validTo, LocalTime startTime, LocalTime endTime, ArrayList<DayOfWeek> validDays,
            ArrayList<String> allowedActions) {
        this.idGroup = nameGroup;
        this.areas = areas;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validDays = validDays;
        this.allowedActions = allowedActions;
    }

    public void Append(User u) {
        users.add(u);
    }

    public String getIdGroup() {
        return idGroup;
    }

    public User findUserByCredential(String credential) {

        for (User user : this.users) {
            if (user.getCredential().equals(credential)) {
                return user;
            }
        }
        System.out.println("user with credential " + credential + " not found");
        return null; // otherwise we get a Java error
    }

    public boolean canBeInTheSpace(Space sp) {
        if (areas != null) {
            String id = sp.getId();
            for (Area a : areas) {
                Area cerca = a.findAreaById(id);
                if (cerca != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canSendRequests(LocalDateTime now) {
        // Es comporva si està dins del període de validesa
        if (validFrom != null && validTo != null) {
            if (now.isBefore(validFrom) || now.isAfter(validTo)) {
                return false;
            }
        } else {
            return false;
        }

        // Que el dia sigui vàlid
        if (validDays != null) {
            if (!validDays.contains(now.getDayOfWeek())) {
                return false;
            }
        } else {
            return false;
        }

        // Es comprova si és dins de l'horari permès
        if (startTime != null && endTime != null) {
            LocalTime currentTime = now.toLocalTime();
            if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean canDoAction(String action) {
        return allowedActions != null ? allowedActions.contains(action) : false;
    }

    public ArrayList<Space> getSpaces() { // De totes les areas agafem tots els espais
        ArrayList<Space> resultat = new ArrayList<>();
        for (Area a : areas) {
            ArrayList<Space> espais = a.getSpaces();
            resultat.addAll(espais);
        }
        return resultat;
    }

}
