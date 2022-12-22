package lk.ijse.dep9.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements SuperEntity {
    private String username;
    private String password;
    private String fullName;
}