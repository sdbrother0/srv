package srv.domains.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.meta.MetaData;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private static final String DATA_URL = "/product";

    @GetMapping("/meta" + DATA_URL)
    public MetaData getMetaData() throws JsonProcessingException {
        return productService.getMetaData();
    }

    @GetMapping(DATA_URL)
    public Page<ProductDto> findAll(Pageable pageable,
                                    @RequestParam(name = "search", required = false) List<String> search,
                                    @RequestParam(name = "keyValue", required = false) String keyValue) {
        return productService.findAll(pageable, search, keyValue);
    }

    @DeleteMapping(DATA_URL)
    public ProductDto delete(@RequestParam Long id) {
        return productService.delete(id);
    }

    @PostMapping(DATA_URL)
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

}
