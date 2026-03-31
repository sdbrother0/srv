package srv.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Route {
    private String path;
    private String metaUrl;
    private String title;
}
