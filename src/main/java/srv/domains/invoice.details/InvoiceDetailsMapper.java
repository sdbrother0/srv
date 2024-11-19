package srv.domains.invoice.details;

import org.mapstruct.Mapper;
import srv.domains.invoice.InvoiceMapper;

@Mapper(uses = {InvoiceMapper.class})
public interface InvoiceDetailsMapper {
    InvoiceDetailsEntity map(InvoiceDetailsDto invoiceDetailsDto);

    InvoiceDetailsDto map(InvoiceDetailsEntity invoiceDetailsEntity);
}
