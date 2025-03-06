package hieu.javabackendfullstep.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse implements Serializable {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private String resetToken;
}
