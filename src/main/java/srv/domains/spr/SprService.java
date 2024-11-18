package srv.domains.spr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import srv.dto.meta.MetaData;
import srv.specification.SimpleLikeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static srv.mapper.service.MapperService.sprMapper;

@RequiredArgsConstructor
@Service
public class SprService {

    private final SprRepository sprRepository;
    private final ObjectMapper objectMapper;

    public Page<SprDto> findAll(Pageable pageable, @RequestParam(name = "search", required = false) List<String> search) {
        if (Objects.isNull(search)) {
            search = new ArrayList<>();
        }
        Specification<SprEntity> simpleLikeSpecification = new SimpleLikeSpecification<>(search, pageable.getSort(), null);
        return sprRepository.findAll(simpleLikeSpecification, pageable).map(sprMapper::map);
    }

    @Transactional
    public SprDto delete(@RequestParam UUID id) {
        SprEntity sprEntity = sprRepository.findById(id).orElseThrow();
        sprRepository.deleteById(id);
        return sprMapper.map(sprEntity);
    }

    @Transactional
    public SprDto save(@RequestBody SprDto sprDto) {
        SprEntity sprEntity = sprMapper.map(sprDto);
        SprEntity savedSprEntity = sprRepository.save(sprEntity);
        return sprMapper.map(savedSprEntity);
    }

    public MetaData getMetaData() throws JsonProcessingException {
        String meta = """
                {
                    "url" : "/spr",
                    "name": "spr",
                    "key": "id",
                    "fields": [
                        {
                            "name": "id",
                            "label": "Id",
                            "type": {
                                "name": "string"
                            },
                            "hidden": false
                        },
                        {
                            "name": "name",
                            "label": "Spr name",
                            "type": {
                                "name": "string"
                            },
                            "editable": true
                        }
                    ]
                }
            """;
        return objectMapper.readValue(meta, MetaData.class);
    }

}
