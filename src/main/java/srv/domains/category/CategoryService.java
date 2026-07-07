package srv.domains.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public TreeResponse tree() {
        List<TreeNode> content = categoryRepository.findAll().stream()
            .map(this::toNode)
            .toList();
        return new TreeResponse(content);
    }

    @Transactional
    public TreeNode save(TreeNode node) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(node.getId());
        entity.setName(node.getName());
        entity.setParentId(node.getParentId());
        return toNode(categoryRepository.save(entity));
    }

    @Transactional
    public TreeNode delete(Long id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(id);
        return toNode(entity);
    }

    private TreeNode toNode(CategoryEntity entity) {
        return new TreeNode(entity.getId(), entity.getName(), entity.getParentId());
    }

}
