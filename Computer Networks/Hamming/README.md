# Program in C/ C++ for hamming code generation for error detection/correction


## Objective:

To Detect and Correct Single bit errors using Hamming Code.

## Instructions:

Hamming Code is used to detect and correct single bit error. The key to Hamming Code is the
use of extra parity bits. Hamming code consists of k information bits and n-k check bits, where n
is the total number of bits in the codeword. Parity bits are placed in positions having power of 2.
##  IMPLEMENTATION (7,4) HAMMING CODE:

- The Hamming Code for 4-bits of data uses 3 redundant bits.

![img](https://github.com/phantom2152/picrepo/raw/main/img3.png)

## PROCEDURE FOR DETERMINING REDUNDANT BITS:

The ability of detecting and correcting errors of Hamming Code comes with the cost of
redundant bits. These 3-bits are used to take care of all the 8 different possible states of
transmitted 7-bits.

- The r-bits are determined using following equations:

d1 = r1 + r2 |\
d2 = r1 + r3 |  modulo-2 arithmetic\
d3 = r2 + r3 |\
d4 = r1 + r2 + r3 |


These equations are further solved to calculate r-bits:

r1 = d1 + d2 + d4\
r2 = d1 + d3 + d4\
r3 = d2 + d3 + d4


## PROCEDURE FOR ERROR DETECTION AND CORRECTION:

- On the receiver side 3 position bits(p1-p3) are calculated:

p1 = r1 + d1 + d2 + d4\
p2 = r2 + d1 + d3 + d4\
p3 = r3 + d2 + d3 + d4

- These values indicate one of the eight possible states of received code:

![img](https://github.com/phantom2152/picrepo/raw/main/img4.png)

## Steps to execute the program

At Sender: Code Formation:
1. Get 4-bit(d4-d1) input
2. Determine r-bits(r3-r1) using above equations
3. Form the 7-bit Code by placing r-bits and d-bits in appropriate positions.
4. Send the Code to Receiver

At receiver: Error Detection and Correction:
1. Determine position bits(p3-p1) using above equations
2. Use the Table to find the position of error (if present) and to take corrective
action.
