package srv.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import srv.domains.customer.CustomerMapper;
import srv.domains.example.ExampleMapper;
import srv.domains.invoice.details.product.InvoiceProductDetailsMapper;
import srv.domains.invoice.master.InvoiceMapper;
import srv.domains.product.ProductMapper;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperService {
    public static final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    public static final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    public static final InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);
    public static final InvoiceProductDetailsMapper invoiceProductDetailsMapper = Mappers.getMapper(InvoiceProductDetailsMapper.class);
    public static final ExampleMapper exampleMapper = Mappers.getMapper(ExampleMapper.class);
}
