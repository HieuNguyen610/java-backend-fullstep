package hieu.javabackendfullstep.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    private String username;
    private String password;
}
