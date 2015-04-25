Sample story

Narrative:
In order to not to unnecessarily monopolize room
As a organizer
I want to be able to cancel a reservation
					 
Scenario: Cancel a reservation already assigned
Given a reservation assigned to a room
When the reservation is cancelled
Then the reservation should be cancelled and the room should be available
