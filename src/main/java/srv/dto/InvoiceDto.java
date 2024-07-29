package srv.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {
    private Long id;
    private LocalDateTime created;
    private CustomerDto customer;
    private Long total;
}
