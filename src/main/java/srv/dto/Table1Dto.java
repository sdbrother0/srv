package srv.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Table1Dto {
    private UUID id;
    private String test;
    private Integer field1;
    private String field2;
    private Table2Dto test2;
    private LocalDateTime created;
    private Long myCount;
}
