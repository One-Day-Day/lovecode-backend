package cc.lovecode.service;

import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.entity.UserRole;
import cc.lovecode.domain.repository.UserRepository;
import cc.lovecode.dto.UserDTO;
import cc.lovecode.dto.request.CreateUserRequest;
import cc.lovecode.enums.ErrorCode;
import cc.lovecode.enums.Role;
import cc.lovecode.exception.ObjectDoesAlreadyExistsException;
import cc.lovecode.exception.ObjectNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ObjectDoesAlreadyExistsException(ErrorCode.DUPLICATED_USERNAME);
        }
        User user = User.from(request);
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(Role.GENERIC_USER)
                .build();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    public User queryUserById(long userId) {
        return findUserById(userId);
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }

    public UserDTO updateUserDTO(long userId, UserDTO userDTO) {
        User oldUser = findUserById(userId);
        oldUser.setActive(userDTO.getActive());
        return UserDTO.from(userRepository.save(oldUser));
    }
}
