package hieu.javabackendfullstep.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hieu.javabackendfullstep.entity.Address;
import hieu.javabackendfullstep.entity.UserEntity;
import hieu.javabackendfullstep.entity.enums.UserStatus;
import hieu.javabackendfullstep.exception.UserAlreadyExistsException;
import hieu.javabackendfullstep.exception.UserNotFoundException;
import hieu.javabackendfullstep.repository.UserRepository;
import hieu.javabackendfullstep.request.CreateAddressRequest;
import hieu.javabackendfullstep.request.CreateUserRequest;
import hieu.javabackendfullstep.request.UpdateUserRequest;
import hieu.javabackendfullstep.response.UserPagingResponse;
import hieu.javabackendfullstep.response.UserResponse;
import hieu.javabackendfullstep.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic="USER-SERVICE")
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
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setPasswordConfirm(request.getPasswordConfirm());
        userEntity.setPhone(request.getPhone());

        userEntity.setStatus(request.getStatus());
        userEntity.setGender(request.getGender());


        userEntity.setAddresses(convertAddressesRequestToEntities(request.getAddresses(), userEntity));
        UserEntity savedUser = userRepository.save(userEntity);

        log.info("User created successfully");
        return convertEntityToResponse(savedUser);
    }

    private Address convertAddressRequestToEntity(CreateAddressRequest request, UserEntity userEntity) {
        if (request == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setAddressType(request.getAddressType());
        address.setCreatedAt(LocalDateTime.now());
        address.setCountry(request.getCountry());
        address.setBuilding(request.getBuilding());
        address.setApartmentNumber(request.getApartmentNumber());
        address.setStreetNumber(request.getStreetNumber());
        address.setFloor(request.getFloor());

        address.setUser(userEntity);

        return address;
    }

    private List<Address> convertAddressesRequestToEntities(List<CreateAddressRequest> addressRequestList, UserEntity userEntity) {
        if (addressRequestList == null) {
            return null;
        }
        List<Address> addresses = addressRequestList.stream().map(request -> convertAddressRequestToEntity(request, userEntity)).collect(Collectors.toList());
        return addresses;
    }

    @Override
    public UserPagingResponse getAllUsers(String keyword, int page, int pageSize, String sortBy) {

        int limit = pageSize;
        int offset = page * pageSize;
        keyword = "%" + keyword + "%";
        List<UserEntity> userList = userRepository.findByKeyword(keyword, limit, offset, sortBy);
        int totalElements = userRepository.countByKeyword(keyword);

        UserPagingResponse response = UserPagingResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .userList(convertEntitiesToResponses(userList))
                .totalElements(totalElements)
                .build();
        log.info("Get all users successfully");
        return response;
    }

    @Override
    @Cacheable(value = "users", key = "#userId")
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
            log.info("User id = {} updated successfully", userId);
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
            entity.get().setStatus(UserStatus.INACTIVE);
            UserEntity deletedUser = userRepository.save(entity.get());
            log.info("User id = {} deleted successfully", userId);
            return convertEntityToResponse(deletedUser);
        }
    }

    @Override
    public UserResponse getByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return convertEntityToResponse(userEntity);
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
        UserResponse response = objectMapper.convertValue(entity, UserResponse.class);
        return response;
    }

    private List<UserResponse> convertEntitiesToResponses(List<UserEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        return entities.stream().map(this::convertEntityToResponse).collect(Collectors.toList());
    }
}
