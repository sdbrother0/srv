package srv.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Validation {
    private boolean required;
    private String message;
}
