package cc.lovecode.service;

import cc.lovecode.dto.request.CreateUserRequest;
import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserRequest request) {
        return userRepository.save(User.from(request));
    }
}
