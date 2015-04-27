notify the users

Narrative:
In order to know which room the meeting will take place
As an organizer
I want to receive an email informing me of the result of the assignment

Scenario: notify by email after the assignation
Given some rooms
And an application to be processed
And an application notification system
When the application is processed
Then the promoter is notified by email


Scenario: notify by email after the cancelling
Given a reservation awaiting treatment
And a cancelling notification system
When the reservation awaiting treatment is cancelled
Then the promoter is notified by email