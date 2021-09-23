import java.util.*;
import inputoutput.*;

public class MatrixCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean loop = true;

        System.out.println("Welcome to Matrix Calculator!\n");
        while (loop) {
            mainMenu();
            int action = input.nextInt();

            switch (action) {
                case 1 -> linearMenu();
                case 2 -> determinantMenu();
                case 3 -> inverseMenu();
                case 4 -> polynomialMenu();
                case 5 -> regressionMenu();
                case 6 -> loop = false;
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!\n");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("""
                    List of operations:
                    1. System of Linear Equations
                    2. Determinant of Matrix
                    3. Inverse of Matrix
                    4. Polynomial Interpolation
                    5. Multiple Linear Regression
                    6. Exit
                    
                    Input choice (number) :""");
    }

    private static void linearMenu() {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("""
                    List of methods:
                    1. Gaussian Elimination
                    2. Gauss-Jordan Elimination
                    3. Matrix Inversion
                    4. Cramer's Rule
                                    
                    Input method (number) :""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> // pass 1
                          loop = false;
                case 2 -> {
                    // pass 2
                }
                case 3 -> {
                    // pass 3
                }
                case 4 -> {
                    // pass 4
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
            }
        }
    }

    private static void determinantMenu() {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("""                
                List of methods:
                1. Row Reduction
                2. Cofactor Expansion
                
                Input method (number) :""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> // pass 1
                          loop = false;
                case 2 -> {
                    // pass 2
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
            }
        }
    }

    private static void inverseMenu() {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("""                
                List of methods:
                1. Gauss-Jordan Elimination
                2. Adjoint Matrix
                
                Input method (number) :""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> // pass 1
                          loop = false;
                case 2 -> {
                    // pass 2
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
            }
        }
    }

    private static void polynomialMenu() {
        // pass
    }

    private static void regressionMenu() {
        // pass
    }
}