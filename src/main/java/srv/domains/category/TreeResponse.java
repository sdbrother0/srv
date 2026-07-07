package srv.domains.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TreeResponse {
    private List<TreeNode> content;
}
