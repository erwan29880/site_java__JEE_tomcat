# site web avec javaEE et tomcat  

Gestion administrateurs par session, création/suppression administrateur, connexion.  
Une page d'affichage liste/insère des utilisateurs quand l'administrateur est connecté.    
Architecture MVC, DAO.  

Une base de données Mysql est créée dans un containeur docker. Deux tables sont nécessaires dans une bdd nommée jee :   
- table users -> id, nom (varchar50), prenom (varchar50)  
- table admin -> id, pseudo (varchar50), pwd (text, SHA1)  