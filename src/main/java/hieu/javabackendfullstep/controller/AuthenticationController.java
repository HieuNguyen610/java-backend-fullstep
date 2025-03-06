package hieu.javabackendfullstep.controller;

import hieu.javabackendfullstep.request.SignInRequest;
import hieu.javabackendfullstep.response.ApiResponse;
import hieu.javabackendfullstep.response.SignInResponse;
import hieu.javabackendfullstep.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationService authService;

    @GetMapping("/sign-in")
    public ResponseEntity<ApiResponse> singIn(@RequestBody SignInRequest request) {
        SignInResponse response = authService.signIn(request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Sign in")
                        .data(response)
                .build());
    }
}
