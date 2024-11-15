package srv.dto.meta;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Route {
    private String path;
    private String metaUrl;
    private String title;
}
