package srv.mapper.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import srv.mapper.CustomerMapper;
import srv.mapper.InvoiceDetailsMapper;
import srv.mapper.InvoiceMapper;
import srv.mapper.ProductMapper;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperService {
    public static final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    public static final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    public static final InvoiceMapper invoiceMapper = Mappers.getMapper(InvoiceMapper.class);
    public static final InvoiceDetailsMapper invoiceDetailsMapper = Mappers.getMapper(InvoiceDetailsMapper.class);
}
