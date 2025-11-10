package srv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SrvApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Get menu test")
    @Test
    void menuTest() throws Exception {
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

    @DisplayName("Get meta Product test")
    @Test
    void metaProductTest() throws Exception {
        mockMvc.perform(get("/meta/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.url").value("/product"))
            .andExpect(jsonPath("$.name").value("product"))
            .andExpect(jsonPath("$.key").value("id"))
            .andExpect(jsonPath("$.fields").isArray())
            .andExpect(jsonPath("$.fields[0].name").value("id"))
            .andExpect(jsonPath("$.fields[0].label").value("Id"))
            .andExpect(jsonPath("$.fields[0].type.name").value("string"))
            .andExpect(jsonPath("$.fields[0].hidden").value("false"));
    }

    @DisplayName("Get date Product test")
    @Test
    void dataProductTest() throws Exception {
        mockMvc.perform(get("/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("content").isArray())
            .andExpect(jsonPath("page.totalElements").value(3))
            .andExpect(jsonPath("content", hasSize(3)));
    }

    @DisplayName("Save date Product test")
    @Test
    void dataProductSaveTest() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("""
                        {
                            "name": "name",
                            "price": 10
                        }
                    """)
            )
            .andExpect(status().isOk());

        mockMvc.perform(get("/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("content").isArray())
            .andExpect(jsonPath("page.totalElements").value(4))
            .andExpect(jsonPath("content", hasSize(4)));

        mockMvc.perform(delete("/product?id=4")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk());

        mockMvc.perform(get("/product")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("content").isArray())
            .andExpect(jsonPath("page.totalElements").value(3))
            .andExpect(jsonPath("content", hasSize(3)));
    }

}
