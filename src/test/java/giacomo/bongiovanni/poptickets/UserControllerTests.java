package giacomo.bongiovanni.poptickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import giacomo.bongiovanni.poptickets.dto.UserDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;

@SpringBootTest
@ContextConfiguration(classes = PoPTicketsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void registerCustomer() throws Exception {
        UserDTO user = new UserDTO();
        user.setName("Giacomo");
        user.setSurname("Bongiovanni");
        user.setEmail("giacomo@gmail.com");
        user.setPassword("1234");
        user.setBirthDate(LocalDate.parse("2003-05-19"));
        user.setFiscalCode("BNGGCM03E19D423D");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/all/user/registerCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}
