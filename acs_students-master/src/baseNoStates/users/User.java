package baseNoStates.users;

public class User {
  private final String name;
  private final String credential;
  private final UserGroup pare;

  public User(String name, String credential, UserGroup pare) {
    this.name = name;
    this.credential = credential;
    this.pare = pare;
  }

  public String getCredential() {
    return credential;
  }

  public UserGroup getPare() {
    return pare;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
