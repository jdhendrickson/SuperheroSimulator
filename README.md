# SER316Assign6

How To Use:
By default, the program looks for a file named "SecondCityExample.json"
This can be overridden by adding a filename for a different json as a command line argument.
Gradle has been set up so that

	gradle run -PjsonFileName="['SecondCityExample.json']"

will run the code with the json file named SecondCityExample.



There is a limit to how long the code will run.
This was added due to some simulations taking almost an hour to complete.
If this limit is hit, the code will declare that neither side can win and stop the simulation.
To adjust how many days this is, adjust the "Max Days" value in the Json file.

There is a limit to the number of hideouts that can be in the city.
If this limit is hit by either the heros or the villains, that faction will be unable to get more people.
To adjust the limit of the number of hideouts, change "Hideouts Per City"
_______________________

Supers are a decorator of a person class

Each decoration alters the stats of a person

At the end of each fight, a person takes some of the stats of their enemy

Hideouts are a builder class

A hideout can have at most 5 people before a new hideout is created

Hansel is the starting hero, and Baba Yaga is one of the starting villains.
