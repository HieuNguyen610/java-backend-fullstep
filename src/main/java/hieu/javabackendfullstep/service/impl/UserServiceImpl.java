package hieu.javabackendfullstep.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.javabackendfullstep.entity.UserEntity;
import hieu.javabackendfullstep.exception.UserAlreadyExistsException;
import hieu.javabackendfullstep.exception.UserNotFoundException;
import hieu.javabackendfullstep.repository.UserRepository;
import hieu.javabackendfullstep.request.CreateUserRequest;
import hieu.javabackendfullstep.request.UpdateUserRequest;
import hieu.javabackendfullstep.response.UserPagingResponse;
import hieu.javabackendfullstep.response.UserResponse;
import hieu.javabackendfullstep.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserResponse createNewUser(CreateUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null");
        }

        if (request.getUsername() != null) {
            UserEntity userEntity = userRepository.findByUsername(request.getUsername());
            if (userEntity != null) {
                throw new UserAlreadyExistsException("Username already exists");
            }
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity.setGender(request.getGender());
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setPasswordConfirm(request.getPasswordConfirm());
        userEntity.setPhone(request.getPhone());
        userEntity.setStatus(request.getStatus());

        UserEntity savedUser = userRepository.save(userEntity);
        return convertEntityToResponse(savedUser);
    }

    @Override
    public UserPagingResponse getAllUsers(String keyword, int page, int pageSize) {

        int limit = pageSize;
        int offset = page * pageSize;
        keyword = "%" + keyword + "%";
        List<UserEntity> userList = userRepository.findByKeyword(keyword, limit, offset);
        int totalElements = userRepository.countByKeyword(keyword);

        UserPagingResponse response = UserPagingResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .userList(convertEntitiesToResponses(userList))
                .totalElements(totalElements)
                .build();
        return response;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException ("User id " + userId + " not found"));
        return convertEntityToResponse(entity);
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
        if (userId != null && request != null) {
            UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException ("User id " + userId + " not found"));
            entity.setEmail(request.getEmail());
            entity.setFirstName(request.getFirstName());
            entity.setLastName(request.getLastName());
            entity.setGender(request.getGender());
            entity.setBirthDate(request.getBirthDate());
            entity.setPassword(request.getPassword());
            entity.setPhone(request.getPhone());
            entity.setStatus(request.getStatus());
            entity.setUsername(request.getUsername());

            UserEntity updatedUser = userRepository.save(entity);
            return convertEntityToResponse(updatedUser);
        }
        return null;
    }

    @Override
    @Transactional
    public UserResponse deleteById(Long userId) {
        Optional<UserEntity> entity = userRepository.findById(userId);
        if (entity.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        } else {
            entity.get().setStatus("INACTIVE");
            UserEntity deletedUser = userRepository.save(entity.get());
            return convertEntityToResponse(deletedUser);
        }
    }

    private UserEntity convertResponseToEntity(UserResponse response) {
        if (response == null) {
            return null;
        }
        return objectMapper.convertValue(response, UserEntity.class);
    }

    private UserResponse convertEntityToResponse(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return objectMapper.convertValue(entity, UserResponse.class);
    }

    private List<UserResponse> convertEntitiesToResponses(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }
}
