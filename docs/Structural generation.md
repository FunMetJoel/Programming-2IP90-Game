# Topic of choice: structural generation of terrain
## We geneate the terrain using the Perlin noise algorithm established by Ken Perlin


1. To implement the algorithm we used a detailed explanation of its features and properties. The document we used can be found here: https://rtouti.github.io/graphics/perlin-noise-algorithm

2. Since our version is not totally random and the same boards can be retraced using the same seed, we needed to be able to adapt the algorithm. This part comes down to making the pemuatation array used for generating the constant vectors dependent on the seed, so it is reproducable.

3. To generate sequence of pseudo random numbers, we use the concepts from Lehmer random number generator, mainly the constants. We used this source: https://en.wikipedia.org/wiki/Lehmer_random_number_generator

4. This was used to later generate a permuatation array, described in the first document, needed for establishing the constants vectors. We used a simple function which shuffles the array, which we came up with alone.

5. For later steps, we followed the guide described in the first document. There were a lot of intermediate steps, like establishing constatn vectors for each corner, calculating dot products and approximating values using linear interpolation. For each of those we have a separate class.

6. Finally, to determine the final shape of obstacles we used a simple function that marks approximately above average noise values. The parameter for this was established by trying and comapring the effects.

7. We also thought of merging two grids to make the final effect look even better. In the end, this idea was discarded because we didn't achieve the expected effects.