package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    @Email
    @NotNull
    private String email;
    private String firstName;
    private String lastName;
    @Size(min = 3)
    @NotNull
    private String password;
}
