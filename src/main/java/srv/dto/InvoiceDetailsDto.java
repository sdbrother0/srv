package srv.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDetailsDto {
    private Long id;
    private InvoiceDto invoice;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
}
