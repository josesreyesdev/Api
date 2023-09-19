package med.jsrdev.api.controller;

import jakarta.validation.Valid;
import med.jsrdev.api.domain.user_auth.DataUserAuthentication;
import med.jsrdev.api.domain.user_auth.User;
import med.jsrdev.api.infra.security.JWTTokenData;
import med.jsrdev.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> authUser(@RequestBody @Valid DataUserAuthentication dataUserAuthentication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                dataUserAuthentication.login(), dataUserAuthentication.clave()
        );
        var userAuth = authenticationManager.authenticate(authToken);

        var JWTToken = tokenService.generateToken((User) userAuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
