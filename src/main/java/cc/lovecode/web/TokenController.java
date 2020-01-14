package cc.lovecode.web;

import cc.lovecode.dto.ErrorResult;
import cc.lovecode.dto.request.LoginRequest;
import cc.lovecode.exception.APIException;
import cc.lovecode.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(tokenService.login(request));
        } catch (APIException ex) {
            return ResponseEntity.badRequest().body(new ErrorResult(ex.getCode().toString(), ex.getMessage()));
        }
    }
}
