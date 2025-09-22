package srv.domains.invoice.details.product;

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
public class InvoiceProductDetailsController {

    private final InvoiceProductDetailsService invoiceProductDetailsService;
    private static final String DATA_URL = "/invoice_product_details";

    @GetMapping(DATA_URL)
    public Page<InvoiceProductDetailsDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) Long masterId, @RequestParam(name = "search", required = false) List<String> search) {
        return invoiceProductDetailsService.findAll(pageable, masterId, search);
    }

    @DeleteMapping(DATA_URL)
    public InvoiceProductDetailsDto delete(@RequestParam Long id) {
        return invoiceProductDetailsService.delete(id);
    }

    @PostMapping(DATA_URL)
    public InvoiceProductDetailsDto save(@RequestBody InvoiceProductDetailsDto invoiceProductDetailsDto) {
        return invoiceProductDetailsService.save(invoiceProductDetailsDto);
    }

}
