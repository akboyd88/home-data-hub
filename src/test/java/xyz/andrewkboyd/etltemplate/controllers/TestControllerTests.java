package xyz.andrewkboyd.etltemplate.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;


@RunWith(SpringRunner.class)
@WebFluxTest(TestController.class)
class TestControllerTests {
    @Autowired
    private WebTestClient testClient;

    @MockBean
    public LatestNumbersDAO postgresqDAO;

    @BeforeEach
    void setup(){
        Mockito.when(postgresqDAO.getLatestNumber()).thenReturn(0);
    }
    
    @Test
    @WithMockUser(username = "test", roles={"user"})
    void echoNumberAPI() throws Exception
    {
        testClient.get()
            .uri("/api/test/echo/11")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""
                        {
                           "echoNumber": 11
                        }
                        """);
    }


    @Test
    @WithMockUser(username = "test", roles={"user"})
    void getLatestNumbers() throws Exception
    {
        testClient.get()
                .uri("/api/test/latest-numbers")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .exchange()
                .expectStatus().isOk();
    }
}
