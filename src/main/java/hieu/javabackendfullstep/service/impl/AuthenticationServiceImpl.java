package hieu.javabackendfullstep.service.impl;

import hieu.javabackendfullstep.exception.InvalidDataException;
import hieu.javabackendfullstep.request.SignInRequest;
import hieu.javabackendfullstep.response.SignInResponse;
import hieu.javabackendfullstep.service.AuthenticationService;
import hieu.javabackendfullstep.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic="AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        log.info("---------- accessToken ----------");

        var user = userService.getByUsername(signInRequest.getUsername());
        if (!user.getStatus().equals("ACTIVE")) {
            throw new InvalidDataException("User not active");
        }

        return null;
    }
}
