package com.ilyass.SpringREST.unitaire;

import com.ilyass.SpringREST.domain.ArticleDTO;
import com.ilyass.SpringREST.service.IService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IService service;

    @Test
    void testGetAll() throws Exception {
        List<ArticleDTO> articles = new ArrayList<>
                (Arrays.asList(
                        new ArticleDTO(1L, "PC PORTABLE HP I7", 15000d, 10d),
                        new ArticleDTO(2L, "ECRAN", 1500d, 10d),
                        new ArticleDTO(3L, "CAMERA LG", 3000d, 10d),
                        new ArticleDTO(4L, "SOURIS", 200d, 10d)));


        when(service.getAll()).thenReturn(articles);
        mvc.perform(get("/api/articles/all").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].description").value("ECRAN"))
                .andExpect(jsonPath("$[2].price").value(3000d))
                .andExpect(jsonPath("$[3].quantity").value(10d));
    }
}

/**
 *     • L’annotation @SpringBootTest permet de créer le contexte de votre application pour pouvoir effectuer les tests unitaires et les tests d’intégration.
 *     Cette dernière permet d’activer des fonctionnalités supplémentaires telles que des propriétés d'environnement personnalisées, différents modes d'environnement Web, des ports aléatoires, des Bean TestRestTemplate et WebTestClient.
 *     • L’annotation @AutoConfigureMockMvc permet de configurer l’objet MockMvc.
 *     • L’attribut mockMvc est un élément important. Il permet d’appeler la méthode “perform” qui déclenche la requête.
 *     • L’attribut service est annoté @MockBean. Il s’agit d’un objet mock (un objet fictif). Le Framework Mockito offre certains services très pratiques dans les tests unitaires.
 *     Par exemple, Mockito permet de simuler le résultat de retour d’une méthode.
 *     Il est obligatoire, car la méthode du controller exécutée par l’appel de ("/api/articles/”) utilise cette classe.
 *     • La méthode perform prend en paramètre l’instruction get("/api/articles/all"). On exécute donc une requête GET sur l’URL "/api/articles/all".
 *     • Ensuite, l’instruction .andExpect(status().isOk()) indique que nous attendons une réponse HTTP 200. 
 *     • Remarquer que la variable service est annotée par @MockBean.
 */
