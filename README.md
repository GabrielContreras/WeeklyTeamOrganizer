# Weekly Team Organizer
## Organize teams for Final Fantasy XIV Online raid groups with a pool of over 8 players.

### Specifications
- List of players
- Available jobs per player
- Available time slots per player


Weekly Team Organizer was made to simplify the team making process for Savage Raids when there are more than 8 people who are available to join. It will take into consideration how many runs people are assigned and can potentially be assigned to in order to balance out the distribution of runs throughout the week. The organizer will try to create teams with the following setup,

- 2 Tanks
- 1 Shield Healer
- 1 Pure Healer
- 2 Melee DPS
- 2 Ranged DPS
- Unique job for each player

The option for soft job checks will allow for more than 2 ranged/melee dps to be slotted in if needed to allow for more team comps.

Players will need to provide what time slots they are available to participate throught the week. This will be populated with a Date/Time format.

### Running the Weekly Team Organizer

When running the program, it'll first ask the user to initiate the data scraping from the Google Sheets page which the players are filling out their information in. It will then follow up on if it should do soft job checking as mentioned above.

The first output will return the complete list of time slots that can field a full elligable team. User will input a list of time slots to auto generate teams for based of provided time slot list. This will provide the fully generated team list for the week as well as a balance list for total number of runs per player.

The output will look something like this: <br>
Day   Time<br>
Player1    Role<br>
Player2    Role<br>
...<br>


Player1 3 <br>
Player2 2 <br>
Player3 3 <br>
Player4 1 <br>
...<br>
