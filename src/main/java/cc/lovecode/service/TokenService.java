package cc.lovecode.service;

import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.repository.UserRepository;
import cc.lovecode.dto.request.LoginRequest;
import cc.lovecode.dto.response.LoginResponse;
import cc.lovecode.exception.IncorrectUsernameOrPasswordException;
import cc.lovecode.exception.UserDoesNotActiveException;
import cc.lovecode.jwt.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TokenService {
    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        String password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        User user = userRepository.findByUsernameAndPassword(request.getUsername(), password)
                .orElseThrow(IncorrectUsernameOrPasswordException::new);
        if (!user.getActive()) {
            throw new UserDoesNotActiveException();
        }
        return new LoginResponse(JWTUtils.generateToken(user));
    }
}
