package srv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import srv.dto.meta.MetaData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Log4j2
public class MetaService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "meta", key = "#obj")
    public MetaData getMetaData(String obj)  {
        Resource resource = resourceLoader.getResource(String.format("classpath:meta/%s.json", obj));
        try {
            String content = resource.getContentAsString(StandardCharsets.UTF_8);
            return objectMapper.readValue(content, MetaData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
