package srv.domains.invoice.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import srv.domains.customer.CustomerDto;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private CustomerDto customer;
    private Long total;
    private Long taxTotal;
}
