package srv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import srv.domains.user.UserService;
import srv.dto.AuthDto;
import srv.dto.LoginDto;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private static final String LOGIN_URL = "/login";

    private final UserService userService;

    @PostMapping(LOGIN_URL)
    public AuthDto login(@RequestBody LoginDto loginDto) {
        if (!userService.authenticate(loginDto)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        return AuthDto.builder().token("jwt-token").build();
    }

}
