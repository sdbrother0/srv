package srv.domains.example;

import org.mapstruct.Mapper;

@Mapper
public interface ExampleMapper {
    ExampleEntity map(ExampleDto exampleDto);

    ExampleDto map(ExampleEntity exampleEntity);
}
