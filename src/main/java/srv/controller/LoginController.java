package srv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.AuthDto;
import srv.dto.LoginDto;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final String LOGIN_URL = "/login";

    @PostMapping(LOGIN_URL)
    public AuthDto login(@RequestBody LoginDto loginDto) {
        if (loginDto.getUsername().equals("admin") && loginDto.getPassword().equals("admin")) {
            return AuthDto
                    .builder()
                    .token("jwt-token")
                    .build();
        }
        throw new RuntimeException("Invalid username or password");
    }

}
