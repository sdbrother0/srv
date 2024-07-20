package srv.dto.meta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Detail {
    private String label;
    private String metaUrl;
    private String masterObjectName;
    private String masterFieldKey;
}
