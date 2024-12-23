package com.ilyass.SpringREST.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    @Size(min = 1, max = 30, message = "description size must be between 1 and 30")
    private String description;
    private Double price;
    @Min(value = 1, message = "The quantity value must be greeter than 1")
    private Double quantity;
}

/*
    • Cette classe joue le rôle de DTO (Data Object Transfert). Ce sont les attributs de cette classe qui seront échangés entre la couche Front et notre application.
            ▪ Le DTO permet de réduire le problème de couplage fort. On peut changer les modèles de la couche Backend sans impacter la couche Front.
            ▪ Le DTO permet de ne pas utiliser les objets externes à une couche donnée.
    • @Size, @Min sont des annotations de l’api Bean validation. Cette dernière fait partie des spécifications de Java EE.
    Il s’agit ici des règles de gestion concernant les champs « description » et « quantity ».
    Il est à préciser que vous pouvez développer vos propres annotations pour implémenter des règles de gestion personnalisées.
 */
