package baseNoStates.users;
import java.util.ArrayList;

public final class DirectoryUserGroups {
  private static final ArrayList<UserGroup> groups = new ArrayList<>();

  public static void makeUsers() {
    groups.addAll(Schedule.getAllGroups());
  }

  
  public static User findUserByCredential(String credential) {
    for (UserGroup group : groups) {
      if (group.findUserByCredential(credential) != null) {
        return group.findUserByCredential(credential);
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
