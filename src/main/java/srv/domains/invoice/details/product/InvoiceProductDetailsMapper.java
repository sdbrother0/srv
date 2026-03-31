package srv.domains.invoice.details.product;

import org.mapstruct.Mapper;
import srv.domains.invoice.master.InvoiceMapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(uses = {InvoiceMapper.class}, componentModel = SPRING)
public interface InvoiceProductDetailsMapper {
    InvoiceProductDetailsEntity map(InvoiceProductDetailsDto invoiceProductDetailsDto);

    InvoiceProductDetailsDto map(InvoiceProductDetailsEntity invoiceProductDetailsEntity);
}
