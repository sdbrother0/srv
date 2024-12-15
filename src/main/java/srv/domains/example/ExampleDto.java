package srv.domains.example;

import lombok.Data;

import java.util.UUID;

@Data
public class ExampleDto {
    private UUID id;
    private String name;
}
