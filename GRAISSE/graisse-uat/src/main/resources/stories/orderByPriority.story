order request by priority

Narrative:
In order to prioritize the most important meetings
As an enterprise
I want to treat requests by priority
					 
Scenario: the application with the highest priority is assigned first
Given two rooms with the same number of seats
And two applications to be processed with differents priorities
And a sort by priority strategy
When the applications are processed
Then the reservation with the application with the highest priority should be associated with the first room available

Scenario: the applications with the same priority are processed according to their order of arrival
Given two rooms with the same number of seats
And an application to be processed with a priority 3
And a sort by priority strategy
When another application to be processed with the same priority is added and the applications are processed
Then the reservation with the first application should be associated with the first room available