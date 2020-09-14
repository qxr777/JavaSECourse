BlueJ project "club".

Authors: David J. Barnes and Michael Kölling

This project is supplementary material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

Purpose of the project
=======================
The project is intended to extend understanding of object
collections. The exercises could be tackled after completing
Section 4.6 of Chapter 4.

The exercise
============
Membership of a club is represented by an instance of the
Membership class.  A complete version of the Membership class
is already provided for you in the club project, and it should
not need any modification.  Each Membership object contains
details of a person's name, and the month and year in which
they joined the club.  All membership details are filled out
when a Membership object is created.

Your task is to complete the Club class, an outline of which
has been also been provided in the club project.

The Club class is intended to store Membership objects in a
flexible-size collection.

A new Membership object is added to a Club object's collection
via the Club object's join method, which has the following
signature and description:

    /**
     * Add a new member to the club's collection of members.
     * @param member The member object to be added.
     */
    public void join(Membership member)

You may assume that the Membership object passed as a
parameter has already been created and is fully initialized.

The Club class also defines a method to return how many
members are in the club. It has the following signature and
description:

    /**
     * @return The number of members (Membership objects) in
     *         the club.
     */
    public int numberOfMembers()

The number of members corresponds to the number of Membership
objects currently stored in the flexible-size collection.

Complete the definition of the Club class. This should
include:

    + A field to refer to a flexible-size collection
    object.

    + A no-arg constructor that sets up an appropriate
    flexible-size collection object to store Membership
    objects.

    + A full definition of the join method.

    + A full definition of the numberOfMembers method.

Below are some further challenge exercises for those who wish
to experiment with additional features of collections.

Possible Staged Implementation
==============================
This task has been broken down into suggested separate stages
to help you create the finished version in small steps. You
are recommended to compile and run the program after each
stage to check that the changes you have made are correct.

1. Define a field that will be used to refer to a
flexible-size collection object. Use an appropriate import
statement for this collection. In the constructor, create the
collection object and assign it to the field.

2. Complete the numberOfMembers method to return the current
size of the collection object. Of course, until you have
completed the join method, this will always return zero, but it
will be ready for further testing when the join method is
done.

3. Complete the join method.

When you wish to add a new Membership object to the Club
object from the object bench, there are two ways you can do
this.

    + Either create a new Membership object on the object
    bench, call the join method on the Club object, and click
    on the Membership object to supply the parameter.

    + Or call the join method on the Club object and type:

    new Membership("members name here ...", month-number-here, year-number-here)

    into the constructor's parameter dialogue box.

When you have added a new member use the numberOfMembers
method to check: a) That the join method is adding to the
collection, and b)That the numberOfMembers method is
working.

Challenge Exercises
===================
These challenge exercises are designed to take the club
project further towards a complete application. They
could be tackled after completing Section 4.9 of Chapter 4.

+ Define a method in the Club class with the following
signature and description:

    /** 
     * Return how many members joined in the given month. 
     * @param month The month we are interested in. 
     * @return How many members joined in the given month. 
     */
    public int joinedInMonth(int month) 

If the month parameter is outside the valid range of 1-12,
print an error message and return zero.

+ Define a method in the Club class with the following
signature and description:

    /**
     * Remove from the club's collection all members who joined
     * in the given month, and return them stored in a separate
     * collection object.
     * @param month The month of the Membership.
     * @param year The year of the Membership.
     */
    public ArrayList purge(int month, int year)

If the month parameter is outside the valid range of 1-12,
print an error message and return a collection object with no
objects stored in it.

Note: the purge method is significantly harder to write than
any of the others.
