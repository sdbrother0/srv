package srv.dto.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {
    private String name;
    private String label;
    private FieldType type;
    private Boolean editable;
    private Boolean hidden;
    private Validation validation;
}
