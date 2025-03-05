package hieu.javabackendfullstep.controller;

import hieu.javabackendfullstep.request.CreateUserRequest;
import hieu.javabackendfullstep.request.UpdateUserRequest;
import hieu.javabackendfullstep.response.ApiResponse;
import hieu.javabackendfullstep.response.UserPagingResponse;
import hieu.javabackendfullstep.response.UserResponse;
import hieu.javabackendfullstep.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j(topic = "USER-CONTROLLER")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createNewUser(@Validated @RequestBody CreateUserRequest request) {
        log.info("Create new user request : {}", request);
        UserResponse userResponse = userService.createNewUser(request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Create user")
                        .data(userResponse)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(required = false) String keyword,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "20") int pageSize) {
        log.info("Get all users request");
        UserPagingResponse response = userService.getAllUsers(keyword, page, pageSize);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Get all users")
                        .data(response) // Placeholder for actual data
                        .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        log.info("Get user by id request : {}", userId);
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Get user by id")
                        .data(response) // Placeholder for actual data
                        .build());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUserById(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        log.info("Update user id {}", userId);
        UserResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Update user by id " + userId)
                        .data(response) // Placeholder for actual data
                        .build());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId) {
        log.info("Delete user by id = {}", userId);
        UserResponse response = userService.deleteById(userId);
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Delete user by id " + userId)
                        .data(response) // Placeholder for actual data
                        .build());
    }
}

