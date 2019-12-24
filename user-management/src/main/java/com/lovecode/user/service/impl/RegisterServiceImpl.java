package com.lovecode.user.service.impl;

import com.lovecode.user.entity.User;
import com.lovecode.user.repository.UserRepository;
import com.lovecode.user.service.RegisterService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {
    private UserRepository userRepository;

    public RegisterServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUserInfo(User user) {
        return userRepository.save(user);
    }
}
