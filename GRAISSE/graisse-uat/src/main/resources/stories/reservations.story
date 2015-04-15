reserve a room

Narrative:
In order to hold the meeting
As a organizer
I want to get a room reserved for the meeting
					 
Scenario:  get the first room available
Given some rooms
And an application to be processed
When the application is processed
Then the reservation should be associated with the first available room
					 
Scenario: bookings are queued 
Given some rooms
And an application to be processed
When an other application to be processed is added
Then two applications are to be processed

Scenario: queued bookings are to be processed at a specfified interval
Given some rooms
And some applications
And an interval
When the first application has been queued for process
Then the applications are processed at the end of the interval

