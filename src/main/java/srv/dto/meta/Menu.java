package srv.dto.meta;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Menu {
    private String title;
    private List<Route> routes;
}
