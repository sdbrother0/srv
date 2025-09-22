package srv.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import srv.dto.meta.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "menu")
    public List<Menu> getMenu() {
        Resource resource = resourceLoader.getResource("classpath:menu/index.json");
        try {
            String content = resource.getContentAsString(StandardCharsets.UTF_8);
            return objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
