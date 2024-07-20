package srv.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SaveDto implements Serializable {
    private Object object;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object masterObject;
}
