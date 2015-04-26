Sample story

Narrative:
In order to not to unnecessarily monopolize room
As a organizer
I want to be able to cancel a reservation
					 
Scenario: Cancel a reservation already assigned
Given a reservation assigned to a room
When the reservation is cancelled
Then the reservation should be cancelled and the room should be available

Scenario: Cancel a pending reservation
Given a reservation awaiting treatment
When the reservation awaiting treatment is cancelled
Then the reservation should be cancelled

Scenario: Cancel only one reservation already assigned
Given two reservations assigned to a room
When the first reservation is cancelled
Then only the first reservation should be cancelled

Scenario: Cancel only one pending reservation
Given two reservations awaiting treatment
When the first reservation awaiting treatment is cancelled
Then only the first reservation should be cancelled
