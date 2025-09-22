package srv.domains.customer;

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
public class CustomerController {

    private final CustomerService customerService;
    private static final String DATA_URL = "/customer";

    @GetMapping(DATA_URL)
    public Page<CustomerDto> findAll(Pageable pageable,
                                     @RequestParam(name = "search", required = false) List<String> search,
                                     @RequestParam(name = "keyValue", required = false) String keyValue) {
        return customerService.findAll(pageable, search, keyValue);
    }

    @DeleteMapping(DATA_URL)
    public CustomerDto delete(@RequestParam Long id) {
        return customerService.delete(id);
    }

    @PostMapping(DATA_URL)
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

}
