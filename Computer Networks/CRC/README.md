# Program for error detection using CRC-CCITT (16-bits).

## Objective:

The aim of an error detection technique is to enable the receiver of a message transmitted
through noisy channel to determine whether the message has been corrupted. To do this, the
transmitter constructs a value called a checksum that is the function of message and appends it to
the message. The receiver can then use the same function to calculate the checksum of the
received message and compare it with the appended checksum to see if the message was
correctly received.

## Instructions:

A linear block code is a code in which the exclusive OR (addition modulo-2) of two valid
codewords creates another valid codeword. Cyclic codes are special linear block codes with one
extra property. In a cyclic code, if a codeword is cyclically shifted (rotated), the result is another
codeword. cyclic redundancy check (CRC) is a cyclic code used for error detection.


![CRC](https://github.com/phantom2152/picrepo/raw/main/image.png)




## IMPLEMENTATION

![CRC](https://github.com/phantom2152/picrepo/raw/main/img2.png)

Figure: Euclidean division algorithm

The message is represented by a information polynomial i(x). i(x) is store as a bit pattern of k
length in an integer array. The k information bits are represented by k-1 degree polynomial

i(x) = i(k-1)x(k-1) + i(k-2)x(k-2) +……………………………..+i1x+i0

A polynomial code is specified by its generating polynomial g(x). If we assume that we are
dealing with a code in which codewords have n bits of which k are information bits and n-k are
check bits. The generator polynomial for such a code has degree n-k and has the form

g(x) = x(n-k) + g(n-k-1)x(n-k-1) +……………………….+g1x+1

The generator polynomial chosen is CCITT-16 (x16+x12+x5+1).

### Encoding Procedure at Sender

1) Multiply i(x) by x(n-k) by putting zeros in n-k low order positions
2) Divide x(n-k)i(x) by g(x) to get r(x). Use Euclidean division algorithm with a feedback
shift register as shown in above figure\

x(n-k)i(x) = g(x) q(x) + r(x) where q(x) is quotient and r(x) is remainder

3) Add remainder r(x) to x(n-k)i(x) by putting check bits in n-k lower order positions
4) Based on randomness, the message can be transmitted with error or without error.
5) For transmission with error, introduce an error at random position to the message x(nk)
i(x) and display the position of the error.
6) Transmitted codeword is b(x) = x(n-k) i(x) + r(x)

### Decoding Procedure at the Receiver

1) The received message b(x) is divided by g(x) using Euclidean division algorithm
2) If the remainder is 0 then there is no error in the transmission else Error in the
transmission   