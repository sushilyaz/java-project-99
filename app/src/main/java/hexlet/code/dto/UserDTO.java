package hexlet.code.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private LocalDate createdAt;
}
