package com.ilyass.SpringREST.integration;

import com.ilyass.SpringREST.domain.ArticleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void testGetById() throws Exception {

        ArticleDTO article = new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(new MediaType[]{MediaType.APPLICATION_JSON}));

        HttpEntity<ArticleDTO> entity = new HttpEntity<ArticleDTO>(headers);
        ResponseEntity<ArticleDTO> result = this.restTemplate.exchange("http://localhost:" + port + "/api/articles/id/" + article.getId(), HttpMethod.GET,
                entity, ArticleDTO.class);
        assertThat(result).isNotNull();
        ArticleDTO dto = result.getBody();
        assertThat(dto.getId()).isEqualTo(article.getId());
        assertThat(dto.getDescription()).isEqualTo(article.getDescription());
        assertThat(dto.getPrice()).isEqualTo(article.getPrice());
        assertThat(dto.getQuantity()).isEqualTo(article.getQuantity());
    }

}

/**
 *     • les variables d’instance de la classe par @Autowired au lieu de @MockBean. En effet, l’objectif est d’effectuer les test d’intégration de bout en bout.
 *     • La classe TestRestTemplate offre la méthode exchange(..) pour testrer les méthodes Rest exposées par notre service web.
 */
