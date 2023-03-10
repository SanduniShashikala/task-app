package lk.ijse.dep9.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.ijse.dep9.app.util.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class UserDTO implements Serializable {
    @NotBlank(message = "Full name can not be empty or null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid name")
    private String fullName;
    @Null(groups = ValidationGroups.Update.class, message = "Username can not be updated")
    @NotBlank(message = "Username can not be empty or null", groups = ValidationGroups.Create.class)
    private String username;
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 3, message = "Password should be at least 3 character long")
    private String password;
}
