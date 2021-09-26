import java.util.*;
import matrixio.*;

public class MatrixCalculator {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loop = true;

        System.out.println("Welcome to matrix.Matrix Calculator!\n");
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
        System.out.print("""
                    List of operations:
                    1. System of Linear Equations
                    2. Determinant of matrix.Matrix
                    3. Inverse of matrix.Matrix
                    4. Polynomial Interpolation
                    5. Multiple Linear Regression
                    6. Exit
                    
                    Input choice (number) :\040""");
    }

    private static void linearMenu() {
        boolean loop = true;

        while (loop) {
            System.out.print("""
                    List of methods:
                    1. Gaussian Elimination
                    2. Gauss-Jordan Elimination
                    3. matrix.Matrix Inversion
                    4. Cramer's Rule
                                    
                    Input method (number) :\040""");
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
        boolean loop = true;

        while (loop) {
            System.out.print("""
                List of methods:
                1. Row Reduction
                2. Cofactor Expansion
                
                Input method (number) :\040""");
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
        boolean loop = true;

        while (loop) {
            System.out.print("""                
                List of methods:
                1. Gauss-Jordan Elimination
                2. Adjoint matrix.Matrix
                
                Input method (number) :\040""");
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
