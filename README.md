Die-Roller-DnD
==============

    A die roller, specifically designed for the "Dungeons and Dragons" rule set 3.5. Intended to be a die roller for a modern age. Where gamers are not
necessarily across the table, but across the world, from each other.

    The first and foremost question on my mind is how to prevent a savvy gamer from cheating.  Maybe by pulling out the rolling class and replacing it with
a less than random variant.  The way that I intend to remove this possibility is by having the GM's client make all of the rolls for any given player.
This while introducing possible lag will at the very least remove most chances for cheating.

    As this is intended to be used in tandem with other programs there will be no way to chat.  Private messages should be handled from within ones specific
VOIP choice, be it vent, Teamspeak, Skype, etc.

    Given a proper amount of time and gumption I also intend to produce a character creation system, that rolls stats, health,
& calculate various other starting bits and bobs.

The goals of this project are as follows:

    1. Produce a rolling engine that will produce reasonably random results.  (Completed to an extent some minor modifications might still be made)
    2. Create a simple, and useful GUI.  The most important part of which should be ease of use, followed by clarity of results.
    3. Create a robust and accessible way to connect to a GM for games taking place over VOIP services.
    4. Try to minimize the chance of cheating being introduced, through various methods.

Current Goal:  To produce the basic pieces for the connection to clients over a TCP/IP network.

Parts of Current Goal:

    Interfaces defining basic operations, methods, constant fields, and constructors for the general model of the network interface.

    Interface defining basic packet function and methodology.