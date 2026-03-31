package srv.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Detail {
    private String label;
    private String metaUrl;
    private String masterObjectName;
    private String masterFieldKey;
}
