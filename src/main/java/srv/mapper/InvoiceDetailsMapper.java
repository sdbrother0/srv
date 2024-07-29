package srv.mapper;

import org.mapstruct.Mapper;
import srv.dto.InvoiceDetailsDto;
import srv.entity.InvoiceDetailsEntity;

@Mapper
public interface InvoiceDetailsMapper {
    InvoiceDetailsEntity map(InvoiceDetailsDto invoiceDetailsDto);

    InvoiceDetailsDto map(InvoiceDetailsEntity invoiceDetailsEntity);
}
