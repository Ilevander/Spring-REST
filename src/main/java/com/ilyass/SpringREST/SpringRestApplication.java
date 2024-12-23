package com.ilyass.SpringREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}

/*
 *C’est la classe de démarrage de Spring Boot. Cette classe doit avoir la méthode main pour exécuter l'application Spring Boot.
 *  • Elle est annotée par @SpringBootApplication. Cette dernière inclut la configuration automatique (Auto-Configuration), l'analyse des composants (component Scan) et le démarrage de Spring Boot.
    • Si vous ajoutez l’annotation @SpringBootApplication à la classe, vous n'avez pas besoin d'ajouter les annotations @EnableAutoConfiguration, @ComponentScan et @SpringBootConfiguration. L’annotation @SpringBootApplication inclut toutes les autres annotations.
    • Seulement une classe qui doit être annotée par @SpringBootApplication.
    • Remarquez que le package racine de votre projet est bien ma.formations.rest. Spring gère uniquement les Bean qui se trouvent dans ce package ou bien les sous packages de ce dernier.
 */
