package srv.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import srv.dto.InvoiceDto;
import srv.entity.InvoiceEntity;

@Mapper
public interface InvoiceMapper {
    InvoiceEntity map(InvoiceDto invoiceDto);

    @Mapping(target = "id", expression = "java(String.format(\"%09d\", invoiceEntity.getId()))")
    InvoiceDto map(InvoiceEntity invoiceEntity);
}
