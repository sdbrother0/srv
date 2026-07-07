package srv.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Tree {
    private String url;
    private String keyFieldName;
    private String titleFieldName;
    private String parentFieldName;
    private String fkFieldName;
}
