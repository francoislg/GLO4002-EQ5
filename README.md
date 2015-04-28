# G.R.A.I.S.S.E
<b>G</b>estionnaire de <b>R</b>éservation <b>A</b>gile et <b>I</b>ntelligent de <b>S</b>alle <b>S</b>aisi pour les <b>E</b>ntreprises

## Créé par l'équipe #5

Nom                            | matricule
-------------------------------|-----------------------------------
Alex Gagnon                    |111 044 926
Clotioloman Yeo                |909 272 477
François Lachance-Guillemette  |111 080 517
Jean-Philippe Giroux           |111 088 846
Kouamé Ange Martial Konan      |909 318 064
Marie-Christine Noreau         |910 049 538
Xavier Bourgeois-Vézina        |111 102 056


## Scénarios

User story                                        | statut
--------------------------------------------------|-----------------------------------
Assigner périodiquement des salles aux demandes   |terminé
Assignation en lot des salles aux demandes        |terminé
Maximiser les places dans la salle                |terminé
Ordonner les demandes par priorité                |terminé
Notifier par courriel après l'assignation         |terminé
Annuler une demande                               |terminé
Notifier par courriel lors d'une annulation       |terminé
Afficher une demande                              |terminé
Permettre la réservation d'une salle              |terminé
Afficher les demandes d'un organisateur           |terminé
Conserver l'historique                            |pas fait

## Comment fonctionne notre API

Tout d'abord, vous devez créer un booker. Voici un exemple utilisant 
les répertoires en mémoire ainsi que la stratégie d'assignation des bookings par défaut:

```java
BookerStrategiesFactory bookerStrategiesFactory = new BookerStrategiesFactory();
BookerStrategy bookerStrategy = bookerStrategiesFactory.create(BookerStrategiesFactory.StrategyType.BASIC);

Bookings bookings = new Bookings(new BookingInMemoryRepository());
Boardrooms boardrooms = new Boardrooms(new BoardroomInMemoryRepository());

Booker booker = new Booker(bookerStrategy, bookings, boardrooms);
```

Ensuite, enregistrer des triggers pour déclencher l'assignation des bookings selon différents critères.
Voici un exemple utilisant un ThresholdTrigger:

```java
ThresholdTrigger thresholdTrigger = new ThresholdTrigger(3);
booker.registerTrigger(thresholdTrigger);
```

Dans cet exemple, le trigger sera déclenché lorsque le booker atteindra 3 bookings à assigner.

Finalement, ajouter des bookings pour déclencher l'assignation. Pour créer un booking vous devez lui fournir le nombre de siège minimum nécessaire pour le client:

```java
User bookingOwner = userRepository.retrieve(email);
int aNumberOfSeatsNeeded = 10;

Booking booking1 = new Booking(bookingOwner, aNumberOfSeatsNeeded);
Booking booking2 = new Booking(bookingOwner, aNumberOfSeatsNeeded);
Booking booking3 = new Booking(bookingOwner, aNumberOfSeatsNeeded);

booker.addBooking(booking1);
booker.addBooking(booking2);
booker.addBooking(booking3);
```

## Comment exécuter nos tests

Dans Eclipse, il suffit d'appuyer sur le Projet principal (GRAISSE), et de le lancer en tant que "Maven Install".
Ceci construira tout le projet, lancera les tests unitaires, ainsi que les tests d'acceptation

### Problèmes avec le test de performance

Il est roulable à partir de l'UI JMeter, mais nous n'avons pas trouvé de façon d'exécuter notre serveur lorsque nous voulions exécuter les tests d'integrations dans Maven.

Donc il suffit d'exécuter en tant qu'application Java "RestMain" dans le package "ca.ulaval.glo4002.GRAISSE.rest.service", lancer les tests à partir de l'UI JMeter, et les tests seront exécutés correctement.

## Notes aux correcteurs

### Décisions à communiquer

#### JavaMailMailSender

Tel que suggéré par Jean-Nicolas, cette classe n'est pas testée unitairement car elle est extrèmement dépendante de JavaMail. Considérant que la librairie, en théorie, fonctionne très bien, c'est la seule couche du système qui a 0% de couverture de code.

#### Demandes spéciales

Tenir compte que nous avons eu quelques difficultés dans l'équipe, et que nous avons réussi à implémenter la plupart des fonctionnalités malgré cela.

Ce qui a été fait depuis lundi le 13 avril : 
* Le service REST et les ID reliés
* Pratiquement tous les tests d'acceptation
* Test de performance (qui fonctionne presque)
* Notifier lors d'une annulation

### Tests non réalisés

#### Test de la base de données

Ce test n'est pas implémenté puisque nous n'avons tout simplement pas de base de données

### Suggestions
Fournir TOUTES les diapos avant le cours.
