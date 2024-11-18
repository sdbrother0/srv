package srv.domains.invoice;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface InvoiceMapper {
    InvoiceEntity map(InvoiceDto invoiceDto);

    @Mapping(target = "id", expression = "java(String.format(\"%09d\", invoiceEntity.getId()))")
    InvoiceDto map(InvoiceEntity invoiceEntity);
}
