# OCR - Projet 7 - API

## Introduction
Ce projet représente la partie constituée de l'API REST du Projet 7.
L'API permet d'exposer les informations relatives aux ouvrages mis à disposition par la bibliothèque.

## Configuration
#### Base de données
L'application est fournie avec deux modes d'exécution:
1) utilisation d'une base h2 en mémoire (mode par défaut)
2) utilisation d'une base mysql qui doit être préinstallée

La sélection du mode s'effectue dans le fichier application.properties, ligne #1. La propriété `spring.profiles.active`peut ainsi contenir la valeur `h2db`ou `mysql`.

Si le choix se porte sur la base h2, aucune configuration n'est requise.
Si le choix se porte sur la base mysql, il convient d'indiquer les informations de connexion dans le fichier `application-mysql.properties`.

#### Port d'écoute
Il est indiqué ligne #3, dans le fichier `application.properties`. Sa valeur par défaut est 9990.

### Log
La configuration des logs (niveau et appenders) est réalisée dans le fichier `logback.xml`. Par défaut, l'enregistrment dans un fichier est désactivé (ligne #33).

### Données de test
Au lancement de l'API, des données de test sont préchargées. Elles figurent dans les fichiers `data-h2.sql` ou `data-mysql.sql` selon le type de BdD choisi. 

## Exécution
Après récupération du projet (git clone), l'API se lance avec `mvn spring-boot:run`

Si nécessaire, l'application peut-être packagée via `mvn package` pour une exécution par `java -jar target\api-0.0.1-SNAPSHOT.jar`

## Test de l'application
Dans la suite, il est considéré que l'application est exposée sur le endpoint http://localhost:9990.

