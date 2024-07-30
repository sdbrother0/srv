package srv.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {
    private String id;
    private LocalDateTime created;
    private CustomerDto customer;
    private Long total;
}
