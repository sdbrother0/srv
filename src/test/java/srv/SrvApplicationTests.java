package srv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SrvApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Get menu test")
    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/menu")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Invoice example"))
            .andExpect(jsonPath("$[0].routes").isArray())

            .andExpect(jsonPath("$[0].routes[0].path").value("product"))
            .andExpect(jsonPath("$[0].routes[0].metaUrl").value("/meta/product"))
            .andExpect(jsonPath("$[0].routes[0].title").value("Product"))

            .andExpect(jsonPath("$[0].routes[1].path").value("customer"))
            .andExpect(jsonPath("$[0].routes[1].metaUrl").value("/meta/customer"))
            .andExpect(jsonPath("$[0].routes[1].title").value("Customer"))

            .andExpect(jsonPath("$[0].routes[2].path").value("invoice"))
            .andExpect(jsonPath("$[0].routes[2].metaUrl").value("/meta/invoice"))
            .andExpect(jsonPath("$[0].routes[2].title").value("Invoice"));
    }

}
