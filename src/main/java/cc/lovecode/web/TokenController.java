package cc.lovecode.web;

import cc.lovecode.dto.request.LoginRequest;
import cc.lovecode.dto.response.LoginResponse;
import cc.lovecode.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return tokenService.login(request);
    }
}
