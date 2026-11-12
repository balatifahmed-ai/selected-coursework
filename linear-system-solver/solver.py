import numpy as np
from numpy.linalg import matrix_rank, lstsq 

"""
CS223 Project - Linear System Solver (Python Version)
Semester: 251
Authors: Ahmed Balatif and Sulieman Almahmoud

This program solves any linear system of the form Ax = b.
It analyzes the system using matrix ranks and determines whether it has:
- A unique solution
- Infinitely many solutions
- No solution (inconsistent)

It then computes the proper solution and saves the results to a file.
"""

# Making the user enter matrix dimentions
def get_matrix_input():
    print("Enter the number of rows in matrix A:")
    rows = int(input())
    print("Enter the number of columns in matrix A:")
    cols = int(input())
    
    # Storing matrix numbers using arrays
    print(f"Enter the elements of matrix A ({rows}x{cols}) row by row:")
    A = []
    for i in range(rows):
        row = list(map(float, input(f"Row {i+1}: ").split()))
        A.append(row)
    A = np.array(A)
    
    print(f"Enter the elements of vector b ({rows} elements):")
    b = list(map(float, input().split()))
    b = np.array(b)
    
    return A, b

# Analyze the system and compute things like ranks
def analyze_system(A, b):
    rank_A = matrix_rank(A)
    n = A.shape[1]
    
    augmented = np.column_stack([A, b])
    rank_augmented = matrix_rank(augmented)
    
    print("\n" + "="*50)
    print("SYSTEM ANALYSIS")
    print("="*50)
    print(f"Matrix A dimensions: {A.shape[0]}x{A.shape[1]}")
    print(f"Rank of A: {rank_A}")
    print(f"Rank of [A|b]: {rank_augmented}")
    print(f"Number of unknowns: {n}")
    print("="*50)
    
    if rank_A == rank_augmented:
        if rank_A == n:
            print("System Status: CONSISTENT - UNIQUE SOLUTION")
            return "unique"
        else:
            print("System Status: CONSISTENT - INFINITELY MANY SOLUTIONS")
            return "infinite"
    else:
        print("System Status: INCONSISTENT - NO EXACT SOLUTION")
        return "inconsistent"

# Solve the linear system
def solve_system(A, b, system_type):
    print("\n" + "="*50)
    print("SOLUTION")
    print("="*50)
    
    if system_type == "unique":
        if A.shape[0] == A.shape[1]:
            x = np.linalg.solve(A, b)
        else:
            x = lstsq(A, b, rcond=None)[0]
        print("Unique solution found:")
        for i, val in enumerate(x):
            print(f"x{i+1} = {val:.6f}")
        return x
    
    elif system_type == "infinite":
        x, residuals, rank, s = lstsq(A, b, rcond=None)
        print("One particular solution:")
        for i, val in enumerate(x):
            print(f"x{i+1} = {val:.6f}")
        print("\nNote: This is one of infinitely many solutions.")
        return x
    
    else:
        x, residuals, rank, s = lstsq(A, b, rcond=None)
        print("Least-squares solution:")
        for i, val in enumerate(x):
            print(f"x{i+1} = {val:.6f}")
        if len(residuals) > 0:
            print(f"\nResidual norm: {np.sqrt(residuals[0]):.6f}")
        return x

# Savin the result into a file named "solution_output.txt"
def save_results(A, b, x, system_type):
    with open("solution_output.txt", "w") as f:
        f.write("LINEAR SYSTEM SOLVER - RESULTS\n")
        f.write("="*50 + "\n\n")
        f.write("Matrix A:\n")
        for row in A:
            f.write(" ".join(f"{val:10.4f}" for val in row) + "\n")
        f.write("\nVector b:\n")
        f.write(" ".join(f"{val:10.4f}" for val in b) + "\n")
        f.write(f"\nSystem Type: {system_type.upper()}\n")
        f.write("\nSolution:\n")
        for i, val in enumerate(x):
            f.write(f"x{i+1} = {val:.6f}\n")
    print("\nResults saved to 'solution_output.txt'")

# Here the program runs, using all the previous methods to fully solve the linear system
def main():
    print("="*50)
    print("LINEAR SYSTEM SOLVER")
    print("Solves systems of the form Ax = b")
    print("="*50)
    
    A, b = get_matrix_input()
    
    system_type = analyze_system(A, b)
    
    x = solve_system(A, b, system_type)
    
    save_results(A, b, x, system_type)
    
    print("\n" + "="*50)
    print("Program completed successfully!")
    print("="*50)

if __name__ == "__main__":
    main()