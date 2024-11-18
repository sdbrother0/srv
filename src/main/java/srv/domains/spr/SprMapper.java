package srv.domains.spr;

import org.mapstruct.Mapper;

@Mapper
public interface SprMapper {
    SprEntity map(SprDto productDto);

    SprDto map(SprEntity productEntity);
}
