package entity;

public class User {
    String username;
    String role;
    int id;

    public User(String username, String role, int id) {
        this.username = username;
        this.role = role;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role='" + role+ '\'' +
                ", id=" + id +
                '}';
    }
}
