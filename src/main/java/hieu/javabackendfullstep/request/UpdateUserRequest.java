package hieu.javabackendfullstep.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import hieu.javabackendfullstep.entity.enums.Gender;
import hieu.javabackendfullstep.entity.enums.UserStatus;
import hieu.javabackendfullstep.utils.validator.ConfirmPassword;
import hieu.javabackendfullstep.utils.validator.EnumPattern;
import hieu.javabackendfullstep.utils.validator.Password;
import hieu.javabackendfullstep.utils.validator.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequest implements Serializable {

    @NotBlank(message = "username is mandatory")
    private String username;
    @Password
    private String password;

    @NotBlank(message = "email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;
    @EnumPattern(name = "user_status", regexp = "ACTIVE|INACTIVE")
    private UserStatus status;
    @EnumPattern(name = "gender", regexp = "MALE|FEMALE|OTHER")
    private Gender gender;
    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @PhoneNumber
    private String phone;
    @ConfirmPassword
    private String passwordConfirm;
}
