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

**Liste des ressources:**

GET http://localhost:9990

Réponse:

`{
  "_links" : {
    "members" : {
      "href" : "http://localhost:9990/members{?page,size,sort}",
      "templated" : true
    },
    "documents" : {
      "href" : "http://localhost:9990/documents{?page,size,sort}",
      "templated" : true
    },
    "users" : {
      "href" : "http://localhost:9990/users{?page,size,sort}",
      "templated" : true
    },
    "lendings" : {
      "href" : "http://localhost:9990/lendings{?page,size,sort}",
      "templated" : true
    },
    "copies" : {
      "href" : "http://localhost:9990/copies{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:9990/profile"
    }
  }
}`


**Liste des documents**

GET http://localhost:9990/documents

Réponse (volontairement tronquée):

`{
  "_embedded" : {
    "documents" : [ {
      "title" : "Le petit chaperon rouge",
      "author" : "Charles Perrault",
      "image" : "chaperon_rouge.jpg",
      "description" : "Conte de tradition orale",
      "documentId" : 1,
      "_links" : {
        "self" : {
          "href" : "http://localhost:9990/documents/1"
        },
        "document" : {
          "href" : "http://localhost:9990/documents/1"
        },
        "copies" : {
          "href" : "http://localhost:9990/documents/1/copies"
        }
      }
    }, {
      "title" : "Bilbo le hobbit",
      "author" : "J.R.R. Tolkien",
      "image" : "biblo.jpg",
      "description" : "Célèbre roman fantastique",
      "documentId" : 2,
      "_l...`

**Liste des exemplaires d'un ouvrage**

GET http://localhost:9990/documents/2/copies

Réponse:

`{
  "_embedded" : {
    "copies" : [ {
      "status" : "AVAILABLE",
      "copyId" : 5,
      "_links" : {
        "self" : {
          "href" : "http://localhost:9990/copies/5"
        },
        "copy" : {
          "href" : "http://localhost:9990/copies/5"
        },
        "lending" : {
          "href" : "http://localhost:9990/copies/5/lending"
        },
        "document" : {
          "href" : "http://localhost:9990/copies/5/document"
        }
      }
    }, {
      "status" : "AVAILABLE",
      "copyId" : 6,
      "_links" : {
        "self" : {
          "href" : "http://localhost:9990/copies/6"
        },
        "copy" : {
          "href" : "http://localhost:9990/copies/6"
        },
        "lending" : {
          "href" : "http://localhost:9990/copies/6/lending"
        },
        "document" : {
          "href" : "http://localhost:9990/copies/6/document"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:9990/documents/2/copies"
    }
  }
}`

**Liste des exemplaires disponibles au prêt d'un ouvrage**

GET http://localhost:9990/copies/search/searchFreeCopyOfDocument?documentId=1

Réponse

{
  "_embedded" : {
    "copies" : [ {
      "status" : "AVAILABLE",
      "copyId" : 2,
      "_links" : {
        "self" : {
          "href" : "http://localhost:9990/lendings/2"
        },
        "document" : {
          "href" : "http://localhost:9990/documents/1"
        }
      }
    } ]
  },
  "page" : {
    "size" : 0,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}


**Liste des users**

GET http://localhost:9990/users

L'accès à cette ressource nécessite une authentification par Basic Auth.
Utiliser `harry@hell.com, 1234`
