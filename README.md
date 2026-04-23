# OpenAddress-Hashing
Implement Open Addressing to store hashed data. Use as keys the last names in the patient.txt attached. 

Assignment 4
CS 3345

STRUCTURE
==========
Part1 has the Open Addressing
Part2 has the Chaining
I have placed patient.txt in both to access it from both Main.java scripts


HASH CODE
==========
In the hash function we sum the ascii values of the characters in the last name and then we MOD (%) that with the table size to get its position in the hash table

I chose this method because its fast to compute and will use all characters in the key (allowing names to be spread amongst the list) and using modulo allows the values to stay within the table bounds


PART 1
=======
When we detect a collision in the table, we use the common technique of linear probing to resolve it.
We check the next position until we find an empty spot that we can place the key into. If it reaches the end then it will wrap around to 0. 
This method allows us to maintain the data in the array without extra data structures.

Collision format: "Collision at position [index] for value [key]"


PART 2
=======
In this part we can keep most of the code from open addressing but switch the table to use LinkedLists instead
Now each position in the table holds its own linked list so that if a collision occurs, the key is just added to the end of the linked list where it should go
This allows us to always insert values even if there is collisions