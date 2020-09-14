Project: weblog-analyzer
Aothors: David J. Barnes and Michael Kölling

This project is part of the material for chapter 7 of the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016


Purpose of project: To provide an illustration of the use of arrays.
How to start this project: Create a LogAnalyzer object.

The LogfileReader expects to read a file, weblog.txt,
containing lines of data in the format:

    year month day hour minute

month values are in the range 1-12 and day values in the range 1-31.
If the sample file cannot be found, the reader will create some simulated
data. Alternatively, use the LogfileCreator to create some random data.
Use its createFile method to give a file name and the number of entries
to create.
