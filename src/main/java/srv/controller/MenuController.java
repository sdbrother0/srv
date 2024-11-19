package srv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.meta.Menu;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MenuController {

    private static final String DATA_URL = "/menu";
    private final ObjectMapper objectMapper;

    @GetMapping("/meta" + DATA_URL)
    public List<Menu> getRoutes() throws JsonProcessingException {
        log.info("get routes");

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
        return objectMapper.readValue(menu, new TypeReference<>(){});

    }

}
