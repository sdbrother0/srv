package srv.domains.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import srv.dto.LoginDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean authenticate(LoginDto loginDto) {
        return userRepository.findByUsername(loginDto.getUsername())
                .map(user -> passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
                .orElse(false);
    }

}