# Linear system solver

The ranks of A and the augmented matrix tell us whether a system has a unique solution, infinitely many solutions or no exact solution. NumPy computes a solution or a least-squares estimate.

## Try it

From this folder:

```sh
python3 -m pip install numpy
python3 solver.py < example.txt
```

The example has three equations and two unknowns. The third equation agrees with the first two, and the unique answer is x = (2, 3). The program writes solution_output.txt in the current folder.

## Credits and edition

CS223 team project by Ahmed Balatif and Sulieman Almahmoud. This copy uses least squares for consistent rectangular systems with a unique solution. The original sent all unique cases to a square-only solver.
