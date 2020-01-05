package com.lovecode.user.service;

import com.lovecode.user.domain.entity.User;
import com.lovecode.user.domain.repository.UserRepository;
import com.lovecode.user.dto.CreateUserRequest;
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
