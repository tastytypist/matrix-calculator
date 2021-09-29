import java.util.*;

import matrix.*;
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
        String result = "", function = "";
        int input_choice = inputMenu();

        switch (input_choice) {
            case 1 -> {
                Object[] matrix_pair = MatrixTerminal.readInterpolationTerminal();
                Matrix point_matrix = (Matrix) matrix_pair[0];
                float num = (float) matrix_pair[1];

                Object[] result_pair = MatrixInterpolation.createPolynomial(point_matrix, num);
                result = (String) result_pair[0];
                function = (String) result_pair[1];
            }
            case 2 -> {
                Object[] matrix_pair = MatrixFile.readInterpolationFile();
                Matrix point_matrix = (Matrix) matrix_pair[0];
                float num = (float) matrix_pair[1];

                Object[] result_pair = MatrixInterpolation.createPolynomial(point_matrix, num);
                result = (String) result_pair[0];
                function = (String) result_pair[1];
            }
        }

        int output_choice = outputMenu();

        switch (output_choice) {
            case 1 -> MatrixTerminal.displayInterpolationTerminal(result, function);
            case 2 -> MatrixFile.displayInterpolationFile(result, function);
        }
    }

    private static void regressionMenu() {
        float estimate_result = 0;
        int input_choice = inputMenu();

        switch (input_choice) {
            case 1 -> {
                Object[] regression_pair = MatrixTerminal.readRegressionTerminal();
                Matrix point_list = (Matrix) regression_pair[0];
                float[] estimate_list = (float[]) regression_pair[1];

                estimate_result = MatrixRegression.calcEstimation(point_list, estimate_list);
            }
            case 2 -> {
                Object[] regression_pair = MatrixFile.readRegressionFile();
                Matrix point_list = (Matrix) regression_pair[0];
                float[] estimate_list = (float[]) regression_pair[1];

                estimate_result = MatrixRegression.calcEstimation(point_list, estimate_list);

            }
        }

        int output_choice = outputMenu();

        switch (output_choice) {
            case 1 -> MatrixTerminal.displayRegressionTerminal(estimate_result);
            case 2 -> MatrixFile.displayRegressionFile(estimate_result);
        }
    }

    public static int inputMenu() {
        while (true) {
            System.out.print("""
                    Choice of input:
                    1. Terminal
                    2. File
                    
                    Input choice (number):\040""");
            int choice = input.nextInt();

            switch (choice) {
                case 1, 2 -> {
                    return choice;
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
            }
        }
    }

    public static int outputMenu() {
        while (true) {
            System.out.print("""
                    Choice of output:
                    1. Terminal
                    2. File
                    
                    Input choice (number):\040""");
            int choice = input.nextInt();

            switch (choice) {
                case 1, 2 -> {
                    return choice;
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
            }
        }
    }
}
