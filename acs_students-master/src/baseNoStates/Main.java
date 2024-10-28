package baseNoStates;

import baseNoStates.areas.DirectoryAreas;
import baseNoStates.users.DirectoryUserGroups;

public class Main {
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUsers();
    new WebServer();
  }
}
