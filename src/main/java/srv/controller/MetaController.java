package srv.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.meta.MetaData;
import srv.service.MetaService;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MetaController {

    private final MetaService metaService;

    @GetMapping("/meta/{obj}")
    public MetaData getMetaData(@PathVariable("obj") String obj) {
        return metaService.getMetaData(obj);
    }
}
