package srv.domains.spr;

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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SprController {

    private final SprService productService;
    private static final String DATA_URL = "/spr";

    @GetMapping("/meta" + DATA_URL)
    public MetaData getMetaData() throws JsonProcessingException {
        return productService.getMetaData();
    }

    @GetMapping(DATA_URL)
    public Page<SprDto> findAll(Pageable pageable, @RequestParam(name = "search", required = false) List<String> search) {
        return productService.findAll(pageable, search);
    }

    @DeleteMapping(DATA_URL)
    public SprDto delete(@RequestParam UUID id) {
        return productService.delete(id);
    }

    @PostMapping(DATA_URL)
    public SprDto save(@RequestBody SprDto sprDto) {
        return productService.save(sprDto);
    }

}
