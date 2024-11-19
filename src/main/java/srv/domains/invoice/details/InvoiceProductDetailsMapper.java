package srv.domains.invoice.details;

import org.mapstruct.Mapper;
import srv.domains.invoice.InvoiceMapper;

@Mapper(uses = {InvoiceMapper.class})
public interface InvoiceProductDetailsMapper {
    InvoiceProductDetailsEntity map(InvoiceProductDetailsDto invoiceProductDetailsDto);

    InvoiceProductDetailsDto map(InvoiceProductDetailsEntity invoiceProductDetailsEntity);
}
