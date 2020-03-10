package model;

import javax.persistence.*;

@Entity
@Table(name = "users_to_admin")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;


    @Column(name = "role")
    private String role;


    public User() {
    }

    public User(String mail, String password, String role) {
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String mail, String password, String role) {
        this(mail, password, role);
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
