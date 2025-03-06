package hieu.javabackendfullstep.service;

import hieu.javabackendfullstep.request.SignInRequest;
import hieu.javabackendfullstep.response.SignInResponse;

public interface AuthenticationService {
    SignInResponse signIn(SignInRequest request);
}
