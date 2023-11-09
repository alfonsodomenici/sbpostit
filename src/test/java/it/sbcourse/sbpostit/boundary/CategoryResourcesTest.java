package it.sbcourse.sbpostit.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;

import it.sbcourse.sbpostit.category.control.CategoryService;
import it.sbcourse.sbpostit.category.entity.Category;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryResourcesTest {

    @Autowired
    CategoryService srv;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void searchNoAuth() throws Exception {
        Category tosave = new Category("TEST");
        srv.create(tosave);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
    public void searchAuth() throws Exception {
        Category tosave = new Category("TEST");
        srv.create(tosave);

        String token = authUser();

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private String authUser() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
            .post("http://localhost:8081/auth/realms/sbrealm/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content(buildUrlEncodedFormEntity(
                "grant_type", "password", 
                "client_id", "sbpostit",
                "username", "admin-user", 
                "password", "admin")))
            .andReturn().getResponse();
        
            int status = response.getStatus();
        assertEquals(200, status);

        String token = JsonPath.parse(response.getContentAsString())
            .read("$.access_token");

        assertNotNull(token);

        return token;
    }

    private String buildUrlEncodedFormEntity(String... params) {
        if ((params.length % 2) > 0) {
            throw new IllegalArgumentException("Need to give an even number of parameters");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < params.length; i += 2) {
            if (i > 0) {
                result.append('&');
            }
            try {
                result.append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).append('=')
                        .append(URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return result.toString();
    }
}
