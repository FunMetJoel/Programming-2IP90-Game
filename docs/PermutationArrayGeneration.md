# Documentation for Pseudo-random generation

## Sources
1. https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle

## Explanation
1. We use a modified version of Fisher-Yates shuffle to generate a paermutation of an array. We want the permutation to be reproducable and bounded to a given seed. The change in our implementation respect to the original algorithm is the use of pseudo-random generated array which we use to do the shuffling.