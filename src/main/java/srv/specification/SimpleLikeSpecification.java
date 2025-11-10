package srv.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
public class SimpleLikeSpecification<T> implements Specification<T> {

    private final List<String> search;
    private final Sort sort;
    private final Pair<String, String> keyValue;
    private transient Map<String, Object> andEqPredicates = new HashMap<>();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (Objects.nonNull(this.search)) {
            for (String searchFieldValue : this.search) {
                String[] fieldValue = searchFieldValue.split("=");
                if (fieldValue.length == 2) {
                    String field = fieldValue[0];
                    String value = fieldValue[1];
                    Path<T> fieldPath = toPatch(field, root);
                    Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(fieldPath.as(String.class)), "%" + value.toUpperCase() + "%");
                    predicateList.add(predicate);
                }
            }
        }

        List<Order> orderList = new ArrayList<>();
        //for select by id in lookup component table
        if (Objects.nonNull(keyValue)) {
            Order orderByIdEqual = criteriaBuilder
                .asc(criteriaBuilder
                    .selectCase()
                    .when(criteriaBuilder.equal(root.get(keyValue.getFirst()).as(String.class), keyValue.getSecond()),
                        criteriaBuilder.literal(0))
                    .otherwise(criteriaBuilder.literal(1)));
            orderList.add(orderByIdEqual);
        }

        if (!sort.get().toList().isEmpty()) {
            Order orderByFields = QueryUtils.toOrders(sort, root, criteriaBuilder).getLast();
            orderList.add(orderByFields);
        }
        query.orderBy(orderList);

        andEqPredicates.forEach((key, val) -> predicateList.add(criteriaBuilder.equal(toPatch(key, root), val)));
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

    private Path<T> toPatch(String field, Path<T> root) {
        if (field.contains(".")) {
            String[] path = field.split("\\.");
            return root.get(path[0]).get(path[1]);
        } else {
            return root.get(field);
        }
    }
}
