package srv.domains.invoice.master;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface InvoiceMapper {
    InvoiceEntity map(InvoiceDto invoiceDto);

    @Mapping(target = "id", expression = "java(String.format(\"%09d\", invoiceEntity.getId()))")
    InvoiceDto map(InvoiceEntity invoiceEntity);
}
