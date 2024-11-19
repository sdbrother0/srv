package srv.domains.invoice.details.product;

import org.mapstruct.Mapper;
import srv.domains.invoice.master.InvoiceMapper;

@Mapper(uses = {InvoiceMapper.class})
public interface InvoiceProductDetailsMapper {
    InvoiceProductDetailsEntity map(InvoiceProductDetailsDto invoiceProductDetailsDto);

    InvoiceProductDetailsDto map(InvoiceProductDetailsEntity invoiceProductDetailsEntity);
}
