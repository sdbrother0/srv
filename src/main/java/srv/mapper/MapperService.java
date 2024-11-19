package srv.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import srv.domains.customer.CustomerMapper;
import srv.domains.invoice.InvoiceMapper;
import srv.domains.invoice.details.InvoiceProductDetailsMapper;
import srv.domains.product.ProductMapper;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperService {
    public static final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    public static final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    public static final InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);
    public static final InvoiceProductDetailsMapper invoiceProductDetailsMapper = Mappers.getMapper(InvoiceProductDetailsMapper.class);
}
