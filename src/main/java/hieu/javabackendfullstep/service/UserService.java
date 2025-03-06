package hieu.javabackendfullstep.service;

import hieu.javabackendfullstep.request.CreateUserRequest;
import hieu.javabackendfullstep.request.UpdateUserRequest;
import hieu.javabackendfullstep.response.UserPagingResponse;
import hieu.javabackendfullstep.response.UserResponse;

public interface UserService {
    UserResponse createNewUser(CreateUserRequest request);

    UserPagingResponse getAllUsers(String keyword, int page, int pageSize, String sortBy);

    UserResponse getUserById(Long userId);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    UserResponse deleteById(Long userId);
}
