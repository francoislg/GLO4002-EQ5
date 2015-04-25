Order request by priority

Narrative:
In order to prioritize the most important meetings
As an enterprise
I want to treat requests by priority
					 
Scenario:  The application with highest priority is assigned first
Given two rooms with the same number of seats
And two applications to be processed with different priority
And a sort by priority strategy
When the applications are processed
Then the reservation should be associated with the booking with the highest priority
