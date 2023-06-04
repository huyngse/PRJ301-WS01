package huyng.registration;
public class RegistrationDTO {
    private String username;
    private String password;
    private String lastName;
    private boolean isAdmin;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String password, String lastName, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
