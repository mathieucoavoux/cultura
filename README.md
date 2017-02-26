Cultura 
=======

Cette application a été créée afin de créer un exemple d'utilisation de la méthode invoke.

FAQ
-------

- L'application ne se lance pas avec des erreurs NoClassDefFound:
Vérifier que les dépendances maven sont paramétrées "Deployment Assembly" pour Eclipse

- Les recherches de livres sont limités ou ne retourne aucun résultat:
Ajouter une clé API Google dans la classe GoogleBookWSMap, ligne 50 du constructeur, troisème paramètre (à la place de null)

- Les recherches de peintures et de lieux ne fonctionnent pas:
Elles n'ont pas encore été implémentées