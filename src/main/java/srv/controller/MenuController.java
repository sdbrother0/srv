package srv.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.meta.Menu;
import srv.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MenuController {

    private static final String DATA_URL = "/menu";
    private final MenuService menuService;

    @GetMapping("/meta" + DATA_URL)
    public List<Menu> getRoutes() {
        log.info("get routes");
        return menuService.getMenu();
    }

}
