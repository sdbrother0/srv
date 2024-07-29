package srv.mapper;

import org.mapstruct.Mapper;
import srv.dto.CustomerDto;
import srv.dto.InvoiceDto;
import srv.entity.CustomerEntity;
import srv.entity.InvoiceEntity;

@Mapper
public interface InvoiceMapper {
    InvoiceEntity map(InvoiceDto invoiceDto);

    InvoiceDto map(InvoiceEntity invoiceEntity);
}
