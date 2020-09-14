Project: taxi-company-outline
Authors: David J. Barnes and Michael Kölling

This project is part of the material for chapter 14 of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

This project provides a partial implementation of a simulation of
taxis operating on a city grid to pick up and transport passengers.
    
This is the first stage of this project. It illustrates an outline 
implementation form derived from CRC interaction.

How to start this project:
    Create a TaxiCompany and a PassengerSource. Call the requestPickup
    method of the source. However, as there are no vehicles created,
    no passengers will ever be picked up. (This functionality is also
    capture in the Demo class.)

    Create at least one Taxi in the constructor of TaxiCompany and
    add it to the vehicles collection. Then a passenger will have
    a taxi scheduled to pick them up. However, as vehicles do not
    yet move, they never will be picked up.
