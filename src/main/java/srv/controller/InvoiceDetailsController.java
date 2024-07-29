package srv.controller;

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
import srv.dto.CustomerDto;
import srv.dto.InvoiceDetailsDto;
import srv.dto.meta.MetaData;
import srv.service.InvoiceDetailsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InvoiceDetailsController {

    private final InvoiceDetailsService invoiceDetailsService;
    private static final String DATA_URL = "/invoice/details";

    @GetMapping("/meta" + DATA_URL)
    public MetaData getMetaData() throws JsonProcessingException {
        return invoiceDetailsService.getMetaData();
    }

    @GetMapping(DATA_URL)
    public Page<InvoiceDetailsDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) UUID masterId, @RequestParam(name = "search", required = false) List<String> search) {
        return invoiceDetailsService.findAll(pageable, masterId, search);
    }

    @DeleteMapping(DATA_URL)
    public InvoiceDetailsDto delete(@RequestParam Long id) {
        return invoiceDetailsService.delete(id);
    }

    @PostMapping(DATA_URL)
    public InvoiceDetailsDto save(@RequestBody InvoiceDetailsDto invoiceDetailsDto) {
        return invoiceDetailsService.save(invoiceDetailsDto);
    }

}
