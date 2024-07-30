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
import srv.dto.InvoiceDto;
import srv.dto.meta.MetaData;
import srv.service.InvoiceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private static final String DATA_URL = "/invoice";

    @GetMapping("/meta" + DATA_URL)
    public MetaData getMetaData() throws JsonProcessingException {
        return invoiceService.getMetaData();
    }

    @GetMapping(DATA_URL)
    public Page<InvoiceDto> findAll(Pageable pageable, @RequestParam(value = "masterId", required = false) Long masterId, @RequestParam(name = "search", required = false) List<String> search) {
        return invoiceService.findAll(pageable, masterId, search);
    }

    @DeleteMapping(DATA_URL)
    public InvoiceDto delete(@RequestParam Long id) {
        return invoiceService.delete(id);
    }

    @PostMapping(DATA_URL)
    public InvoiceDto save(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.save(invoiceDto);
    }

}
