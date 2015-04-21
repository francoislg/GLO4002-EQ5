maximize seats

Narrative:
In order to maximize seats
As an enterprise
I want to get the smallest room for my needs
					 
Scenario:  room with best fitting seats is assigned
Given a room with 20 seats
And a room with 20 seats
And a room with 10 seats
And a sort by seats strategy
And an application with 10 seats
When the application is processed
Then the reservation should be associated with the last room