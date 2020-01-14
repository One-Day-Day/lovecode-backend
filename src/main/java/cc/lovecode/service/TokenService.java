package cc.lovecode.service;

import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.repository.UserRepository;
import cc.lovecode.dto.LoginRequest;
import cc.lovecode.dto.LoginResponse;
import cc.lovecode.exception.IncorrectUsernameOrPasswordException;
import cc.lovecode.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(IncorrectUsernameOrPasswordException::new);
        return new LoginResponse(JWTUtils.generateToken(user));
    }
}
