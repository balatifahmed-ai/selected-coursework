# Parallel array benchmark

How does the time to sum the cubes of 50 million integers change as more threads join the calculation? Each worker handles an array slice. The main thread joins the workers and checks their combined result against the serial sum.

## Try it

From this folder:

```sh
javac -d build ArraySum.java Summer.java
java -cp build ArraySum
```

The program prints elapsed time and speedup for worker counts from 2 to 1,000. A mismatch is flagged. Timings vary by machine and run; each configuration gets a single timed pass.

## Credits and edition

CS330 team project by Ahmed Balatif, Faisal Niyaz, Omar Alwahashi and Nawaf Alfraikh, based on instructor-supplied starter code. These source files are unchanged from the recovered Phase 2 package. ArraySum uses its nested Worker class for the benchmark; the accompanying Summer class is retained from the package.
