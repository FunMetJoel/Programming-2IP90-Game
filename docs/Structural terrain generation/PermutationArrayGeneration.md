# Documentation for Pseudo-random generation

## Sources
1. https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle

## Explanation
1. We use a modified version of Fisher-Yates shuffle to generate a paermutation of an array. We want the permutation to be reproducable and bounded to a given seed. The change in our implementation respect to the original algorithm is the use of pseudo-random generated array which we use to do the shuffling.

2. The array of pseudo-random indicies is generated using the PseudoRandomGenerator. Every time it is called, the next number of the sequence is generated. Then, a simple method converts the doubles to ints and creates the indicies array.

3. Another method iterates through the initial permutationArray ([0-255], ASC) and swaps the element with the element of the index received from the pseudo-random array.