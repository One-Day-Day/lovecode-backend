package cc.lovecode.web;

import cc.lovecode.domain.entity.User;
import cc.lovecode.dto.UserDTO;
import cc.lovecode.dto.request.CreateUserRequest;
import cc.lovecode.enums.Role;
import cc.lovecode.exception.AccessDeniedException;
import cc.lovecode.jwt.JWTUser;
import cc.lovecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserDTO getUserInfo(JWTUser jwtUser, @PathVariable("id") long userId) {
        if (!Objects.equals(jwtUser.getId(), userId) && !jwtUser.isSuperAdmin()) {
            throw new AccessDeniedException();
        }
        return UserDTO.from(userService.queryUserById(userId));
    }

    @GetMapping
    public List<UserDTO> getAllUsers(JWTUser jwtUser) {
        jwtUser.validateRoles(Role.SUPER_ADMIN);
        return userService.getAllUsers();
    }

    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") long userId, @RequestBody UserDTO userDTO, JWTUser jwtUser) {
        jwtUser.validateRoles(Role.SUPER_ADMIN);
        return userService.updateUserDTO(userId, userDTO);
    }
}
