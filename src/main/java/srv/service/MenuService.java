package srv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import srv.dto.meta.Menu;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuService {

    private final ObjectMapper objectMapper;

    public List<Menu> getMenu() {
        String menu = """
                [
                    {
                        "title": "Invoice example",
                        "routes": [
                            {
                                "path": "product",
                                "metaUrl": "/meta/product",
                                "title": "Product"
                            },
                            {
                                "path": "customer",
                                "metaUrl": "/meta/customer",
                                "title": "Customer"
                            },
                            {
                                "path": "invoice",
                                "metaUrl": "/meta/invoice",
                                "title": "Invoice"
                            }
                        ]
                    }
                ]
                """;
        try {
            return objectMapper.readValue(menu, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            log.error(e);
            return new ArrayList<>();
        }
    }

}
