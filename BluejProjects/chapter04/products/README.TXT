BlueJ project "products".
Authors:  David J. Barnes and Michael Kölling

This project is supplementary material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016

Purpose of the project
=======================
The project is intended to extend understanding of object
collections, including the use of loops, iterators and
casting. The exercises could be tackled after completing
Section 4.9 of Chapter 4.

The exercises
=============
A company records stock levels of the products it sells.
A StockManager object maintains an arbitrary-length list
of Product objects. Your task is to complete the outline
implementation of the StockManger class.

The StockDemo class has been provided to help demonstrate ways
in which StockManager and Product objects might be used. You
can create a StockDemo object on the object bench and call its
demo method. As you develop the StockManager class, this demo
should demonstrate increasing functionality.

The StockManager Class
======================
The StockManager class uses a LinkedList object to store zero
or more Product items. Its addProduct method adds a new
product to the collection. The following methods need
completing: delivery, findProduct, printProductDetails, and
numberInStock.

+ The delivery method should find the Product with the given
ID in the list of products and then call its increaseQuantity
method.

+ The findProduct method should look through the collection
for a product whose id field matches the id argument of this
method. If a matching product is found, that Product should be
returned as the method's result.  If no matching product is
found, return null from the method.

+ The printProductDetails method should iterate over the list
of products and print the result of calling the toString()
method on each.

+ The numberInStock method should locate a product in the
collection with a matching ID, and return the current quantity
of that product as a method result. If no product with a
matching ID is found, return zero.

The Product Class
=================
This class has been provided for you, and you should not need
to make any alterations to it.

Each product sold by the company is represented by an instance
of the Product class, which records a product's ID, name and
how many of that product are currently in stock. The Product
class defines the increaseQuantity method to record increases
in the stock level of that product.  The sellOne method
records that one item of that product has been sold by
reducing the quantity field level by one.

Staged Implementation
=====================
The overall task has been broken down into suggested separate
stages to help you create the finished version in small steps.
You are recommended to compile and run the program after each
stage to check that the changes you have made are correct.

1. Implement the printProductDetails method to ensure that you
are able to iterate over the collection of Products. Just
print out each product using System.out.  Using an Iterator is
the preferred approach, but use an integer index variable if
you find that easier to understand.

2. Implement the findProduct method. This differs from the
printProductDetails method in that it will not necessarily
have to examine every product in the collection before a match
is found.  For instance, if the first product in the
collection matches the product name, iteration can finish and
that first Product returned.  On the other hand, it is
possible that there might be no match for the name in the
collection. In that case, the whole collection will be
examined, without finding a product to return.  In this case
the null value should be returned.

When looking for a match, you will need to call the getID
method on a Product.  This means that you will need to use a
cast when you retrieve an item from the list.  You can read
about casts in the discussion of the auction project in
Chapter 4. In particular, section 4.9.3 on page 92 is about
casting.

3. Implement the numberInStock method.  This is relatively
simple to implement once the findProduct method has been
completed. For instance, numberInStock can call the
findProduct method to do the searching, and then call the
getQuantity method on the result. Watch out for products that
cannot be found, though.

4. Implement the delivery method using a similar approach to
that used in numberInStock.

Optional Challenge Exercises
============================
+ Implement a method in StockManager to print details of
all products with stock levels below a given value (passed
as a parameter to the method).

+ Modify the addProduct method so that a new product
cannot be added to the product list with the same ID
as an existing one.

+ Add to StockManager a method that finds a product from its
name rather than its ID:

    public Product findProduct(String name)

In order to do this, you need to know that two String objects
s1 and s2 can be tested for equality with the boolean
expression:

    s1.equals(s2)

More detail on this can be found in Chapter 5, page 115.
