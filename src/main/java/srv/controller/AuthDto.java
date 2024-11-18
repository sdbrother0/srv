package srv.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String token;
}
