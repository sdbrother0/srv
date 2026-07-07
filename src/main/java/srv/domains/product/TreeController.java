package srv.domains.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srv.domains.category.CategoryService;
import srv.domains.category.TreeNode;
import srv.domains.category.TreeResponse;

@RestController
@RequiredArgsConstructor
public class TreeController {

    private final CategoryService categoryService;
    private static final String TREE_URL = "/api/category/tree";

    @GetMapping(TREE_URL)
    public TreeResponse tree() {
        return categoryService.tree();
    }

    @PostMapping(TREE_URL)
    public TreeNode save(@RequestBody TreeNode node) {
        return categoryService.save(node);
    }

    @DeleteMapping(TREE_URL)
    public TreeNode delete(@RequestParam Long id) {
        return categoryService.delete(id);
    }

}
