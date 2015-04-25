package gr.uoa.di.ted.std08169.std09158.domain;

/**
 *
 * 
 */
public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    //rolos kathe xrhsth
    private Role role;

    public User(String username, String password, String name, String surname, String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }
    
    public User(String username, String password, String name, String surname, String email) {
        //xrhsimopoiei ton panw construcror kai prosthetei ex orismou ton rolo tou episkepth
        this(username, password, name, surname, email, Role.GUEST);
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
    
    public Role getRole() {
        return role;
    }
}
