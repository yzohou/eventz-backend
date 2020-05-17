# Back end Projet SIR 
 
# Description

Ce projet représente le backend de l'application développée. Il est fait en JAVA.


# Prerequisites
Java  -
Maven -
Apache tomcat -
Hibernate -
Javax servlet -
jersey -  
Postman - 

# Installation
1: Demarer le server de base de données MSQL (ou autres ) et créer un base de données dont le nom correspondra à celui fournit dans le fichier `persistence.xml` ( doodle dans notre cas )

2: Configuration du server

Dans le ficher persistence.xml (src/java/resources/META-INF), Utiliser les informations du server 'nom' 'password' et nom de la BD.

        <property name="hibernate.connection.password" value="root"/>
        <property name="hibernate.connection.url" value="jdbc:mysql://localhost/doodle"/>
        <property name="hibernate.connection.username" value="root"/>


3: laisser Maven télécharger les dépendances
 
4: Lancer l'application JpaTest(Java/jpa) afin que les tables soient automatiquement créées dans la base de données.

5: Lancer tomcat avec  Maven en faisant un clic droit sur le projet. Dans la configuration maven, il faut utiliser `tomcat7:run` comme goal. 


5: Faite vos requêtes avec Postman https://www.getpostman.com/ ou autre outil pour les interactions avec les données. Il donc possible de manipuler:

Liste des urls fournies par l'api: 

## Liste de tout les utilisateurs 
GET http://localhost:8181/rest/utilisateurs 

# Ajouter un utilisateur 
POST http://localhost:8181/rest/utilisateurs/add

Avec un JSOn sous le format:
JSON DATA a utiliser 
{
    "nom": "DOE",
    "prenom": "John",
    "email": "john.doe@test.fr",
    "password": "test",
}


# Lister le detail d'un utilisateur 
GET http://localhost:8181/rest/utilisateurs/6

# supprimer un utilisateur 
DELETE http://localhost:8181/rest/utilisateurs/delete/6

# Update un utilisateur 
POST http://localhost:8181/rest/utilisateurs/update

JSON DATA a utiliser
{
    "id": 5,
    "nom": "DOES",
    "prenom": "John",
    "email": "john.doe@test.fr",
}

