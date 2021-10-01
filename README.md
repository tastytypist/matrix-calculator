# Matrix Calculator | Algeo01_20005
Sistem Persamaan Linier, Determinan, dan Aplikasinya  
Aljabar Linier dan Geometri  
Kelompok 39  
2021  
  
**Matrix Calculator** is a calculator that can be used to execute calculations involving matrices.  
  
Supported operations:
1. System of linear equations
2. Determinant of matrix
3. Inverse of matrix
4. Polynomial interpolation
5. Multiple linear regression

## Project Structure
```
matrixcalculator
├── bin                          # Contains java bytecode (.class) 
├── doc                          # Contains documentation in the form of a report
├── out                          # Contains results created by the calculator
├── src                          # Contains source files (.java)
│   ├── matrix                   # Contains matrix operations
│   │   ├── Matrix               # Operations for matrix determinant, matrix inverse, and system of linear equations
│   │   ├── MatrixInterpolation  # Operations for polynomial interpolation
│   │   └── MatrixRegression     # Operation for multiple linear regression
│   ├── matrixio                 # Contains methods handling matrix input and output
│   │   ├── MatrixFile           # Matrix input and output from a file
│   │   └── MatrixTerminal       # Matrix input and output from the terminal
│   └── MatrixCalculator         # Contains the main program
└── test                         # Contains test cases
```

## Setup
1. Go to folder bin using ```cd bin```
2. Run the calculator using ```java MatrixCalculator```
