package srv.domains.invoice.details;

import lombok.Data;
import srv.domains.invoice.InvoiceDto;
import srv.domains.product.ProductDto;

import java.math.BigDecimal;

@Data
public class InvoiceProductDetailsDto {
    private Long id;
    private InvoiceDto invoice;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal tax;
    private BigDecimal amount;
}
