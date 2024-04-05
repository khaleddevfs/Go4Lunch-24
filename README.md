Go4Lunch est une application conçue pour être utilisée par l'ensemble des employés. Son objectif est de faciliter la recherche de restaurants à proximité. Les utilisateurs peuvent choisir un établissement
. De même, ils peuvent voir les choix de restaurants faits par les autres et décider de les rejoindre. Peu avant l'heure du déjeuner, l'application envoie une notification aux employés pour les encourager à se regrouper avec leurs collègues.



Authentification :
- Connexion sécurisée via Google ou Email/Mot de passe ou Twitter.


Carte des Restaurants:

- Affichage des restaurants environnants sur une carte.

- Utilisation de punaises personnalisées pour indiquer l'emplacement des restaurants.

- Différenciation visuelle des restaurants choisis par les collègues.


Liste des Restaurants:

- Présentation détaillée des restaurants incluant nom, distance, image, adresse, nombre de collègues intéressés, horaires d'ouverture et avis.


Fiche Détaillée d'un Restaurant:

- Informations détaillées sur le restaurant sélectionné.

- Options pour choisir le restaurant, appeler le restaurant, "aimer" le restaurant, et voir les collègues intéressés.


Liste des Collègues:

- Affichage des choix de restaurants des collègues.


Recherche de Restaurants:

- Fonction de recherche pour trouver des restaurants par nom.


Menu Latéral:

- Accès à des fonctionnalités supplémentaires comme voir le restaurant choisi pour le déjeuner, accéder aux paramètres, et se déconnecter.


Notifications:

- Envoi automatique de notifications pour rappeler les arrangements du déjeuner.


Traduction:

- Support du français et de l'anglais, au minimum.


L’architecture MVVM 
- L'application utilise un modèle MVVM (Model-View-ViewModel) où les ViewModels interagissent avec les Repositories pour récupérer/modifier les données, tandis que les Activités et Fragments gèrent l'affichage de l'interface utilisateur. Les services de notification et l'intégration avec l'API Google Places jouent un rôle clé dans la fonctionnalité de l'application.


  
