package srv.domains.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TreeNode {
    private Long id;
    private String name;
    private Long parentId;
}
