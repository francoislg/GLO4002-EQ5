# G.R.A.I.S.S.E
<b>G</b>estionnaire de <b>R</b>éservation <b>A</b>gile et <b>I</b>ntelligent de <b>S</b>alle <b>S</b>aisi pour les <b>E</b>ntreprises

<b>Créé par l'équipe #5 :</b>

Nom                            | matricule
-------------------------------|-----------------------------------
Alex Gagnon                    |111 044 926
Clotioloman Yeo                |909 272 477
François Lachance-Guillemette  |
Jean-Philippe Giroux           |111 088 846
Kouamé Ange Martial Konan      |111 080 517
Marie-Christine Noreau         |
Xavier Bourgeois-Vézina        |111 102 056


<b>Avancement :</b>

User story                                        | statut
--------------------------------------------------|-----------------------------------
Assigner périodiquement des salles aux demandes   |terminé
Assignation en lot des salles aux demandes        |terminé
Maximiser les places dans la salle                |terminé
Ordonner les demandes par priorité                |terminé
Notifier par courriel après l'assignation         |en cours
Annuler une demande                               |en attente
Notifier par courriel lors d'une annulation       |en attente
Afficher une demande                              |en attente
Permettre la réservation d'une salle              |en attente
Afficher les demandes d'un organisateur           |en attente

<b>Comment fonctionne notre API: </b>

Tout d'abord, vous devez créer un booker. Voici un exemple utilisant 
les répertoires en mémoire ainsi que la stratégie d'assignation des bookings par défaut:

```java
BookerStrategiesFactory bookerStrategiesFactory = new BookerStrategiesFactory();
BookerStrategy bookerStrategy = bookerStrategiesFactory.create(BookerStrategiesFactory.StrategyType.BASIC);

Bookings bookings = new Bookings(new BookingInMemoryRepository());
Boardrooms boardrooms = new Boardrooms(new BoardroomInMemoryRepository());

Booker booker = new Booker(bookerStrategy, bookings, boardrooms);
```

Ensuite, enregistrer des triggers pour déclencher l'assignation des bookings selon différent critères.
Voici un exemple utilisant un ThresholdTrigger:

```java
ThresholdTrigger thresholdTrigger = new ThresholdTrigger(3);
booker.registerTrigger(thresholdTrigger);
```

Dans cet exemple, le trigger sera déclenché lorsque le booker atteindra 3 bookings à assigner.

Fianlement, ajouter des bookings pour déclencher l'assignation. Pour créer un booking vous devez lui fournir le nombre de siège minimum nécessaire pour le client:

```java
int aNumberOfSeatsNeeded = 10;

Booking booking1 = new Booking(aNumberOfSeatsNeeded);
Booking booking2 = new Booking(aNumberOfSeatsNeeded);
Booking booking3 = new Booking(aNumberOfSeatsNeeded);

booker.addBooking(booking1);
booker.addBooking(booking2);
booker.addBooking(booking3);
```


