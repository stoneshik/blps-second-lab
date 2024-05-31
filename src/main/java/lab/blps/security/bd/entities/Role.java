package lab.blps.security.bd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public Role() {}

    public Role(RoleEnum name) {
        this.name = name;
    }
}
