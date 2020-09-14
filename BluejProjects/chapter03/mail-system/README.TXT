Project: mail-system
Authors: David Barnes and Michael Kölling

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

It is discussed in chapter 3.

This project simulates a simple email system. Mail clients simulate email programs
of different users. If you create two or more email clients, they can send messages
to each other. (Note this this is a simulation: you cannot really send email. All
messages are exchanged only between the email client objects in this project.)

To use this project, 

 - create a MailServer instance.
 - create a MailClient instance. Here you have to pass the mail server as a 
   parameter, and you have to make up a name for this user.
 - create a second MailClient object with the same mail server and a different user 
   name.
 - Use the "sendMessage" method of a MailClient object to send a message to the 
   other mail client.
 - Use the "printNextMessage" method of the second mail client to receive the message.

The purpose of this project is to demonstrate object interaction. It includes external
method calls (one object calling methods of another object) and object creation
statements.

The debugger can be used to investigate these object interactions.
