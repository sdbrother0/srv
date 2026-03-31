package srv.dto.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Menu {
    private String title;
    private List<Route> routes;
}
