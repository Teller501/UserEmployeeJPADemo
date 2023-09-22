package dk.kea.useremployee.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDateTime born;
    private Gender gender;
    private boolean vegetarian;
    @OneToOne
    @JoinColumn(name ="useridfk", referencedColumnName = "userID", nullable = false)
    private User user;

    public Employee(int id, String name, Gender gender, User user) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.user = user;
    }
}
