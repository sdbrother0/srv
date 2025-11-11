package srv.domains.example;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;
    private static final String DATA_URL = "/example";

    @GetMapping(DATA_URL)
    public Page<ExampleDto> findAll(Pageable pageable, @RequestParam(name = "search", required = false) List<String> search) {
        return exampleService.findAll(pageable, search);
    }

    @DeleteMapping(DATA_URL)
    public ExampleDto delete(@RequestParam UUID id) {
        return exampleService.delete(id);
    }

    @PostMapping(DATA_URL)
    public ExampleDto save(@RequestBody ExampleDto exampleDto) {
        return exampleService.save(exampleDto);
    }

}
