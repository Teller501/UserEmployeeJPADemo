package dk.kea.useremployee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(unique = true)
    private String email;
    //@JsonIgnore
    private String password;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonBackReference
    private Employee employee;

    public User(int userID, String email) {
        this.userID = userID;
        this.email = email;
    }
}
