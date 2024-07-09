package frontend.devs.server.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private boolean create_or_save;
    private boolean is_delete;

    @ManyToOne
    @JoinColumn(name = "IDUser")
    private User user;
}
