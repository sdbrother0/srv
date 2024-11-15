package srv.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import srv.dto.meta.Menu;
import srv.dto.meta.Route;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class RoutesController {

    private static final String DATA_URL = "/menu";

    @GetMapping("/meta" + DATA_URL)
    public List<Menu> getRoutes() {
        log.info("get routes");
        List<Route> invoiceRoutes = new ArrayList<>();
        invoiceRoutes.add(Route.builder().title("Product").path("product").metaUrl("/meta/product").build());
        invoiceRoutes.add(Route.builder().title("Customer").path("customer").metaUrl("/meta/customer").build());
        invoiceRoutes.add(Route.builder().title("Invoice").path("invoice").metaUrl("/meta/invoice").build());

        List<Menu> menuList = new ArrayList<>();
        menuList.add(Menu.builder().title("Invoice example").routes(invoiceRoutes).build());
        return menuList;
    }

}
