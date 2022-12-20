package lk.ijse.dep9.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@ToString(exclude = "taskSet")
@EqualsAndHashCode(exclude = "taskSet")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private Set<Task> taskSet = new HashSet<>();

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
