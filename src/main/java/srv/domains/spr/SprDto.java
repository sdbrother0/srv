package srv.domains.spr;

import lombok.Data;

import java.util.UUID;

@Data
public class SprDto {
    private UUID id;
    private String name;
}
