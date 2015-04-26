maximize seats

Narrative:
In order to maximize seats
As an enterprise
I want to get the smallest room for my needs
					 
Scenario:  room with best fitting seats is assigned
Given three rooms with differents numbers of seats
And a sort by seats strategy
And an application with the same number of seats as the last
When the application is processed
Then the reservation should be associated with the last room