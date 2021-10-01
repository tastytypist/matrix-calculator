import java.util.*;

import matrix.*;
import matrixio.*;

public class MatrixCalculator {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
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
        System.out.print("""
                    List of operations:
                    1. System of Linear Equations
                    2. Determinant of Matrix
                    3. Inverse of Matrix
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
                    3. Matrix Inversion
                    4. Cramer's Rule
                    5. Back
                    
                    Input method (number) :\040""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> {
                    loop = false;
                    int input_choice = inputMenu();
                    Matrix m = Matrix.createMtr(0, 0);

                    switch (input_choice) {
                        case 1 -> m = MatrixTerminal.readMatrixTerminal();
                        case 2 -> m = MatrixFile.readMatrixFile();
                    }

                    Matrix m_copy = m;
                    Matrix.gaussElim(m_copy);
                    String[] spl_result = Matrix.spl(m);
                    
                    System.out.println();
                    int output_choice = outputMenu();
                    switch (output_choice) {
                        case 1 -> {
                            System.out.println();
                            MatrixTerminal.displayMatrixTerminal(m_copy);
                            System.out.println();
                            MatrixTerminal.displaySPLResTerminal(spl_result);
                        }
                        case 2 -> {
                            MatrixFile.displaySPLResFile(spl_result, m_copy);
                        }
                    }
                    
                }
                case 2 -> {
                    loop = false;
                    int input_choice = inputMenu();
                    Matrix m = Matrix.createMtr(0, 0);

                    switch (input_choice) {
                        case 1 -> m = MatrixTerminal.readMatrixTerminal();
                        case 2 -> m = MatrixFile.readMatrixFile();
                    }

                    Matrix m_copy = m;
                    Matrix.gaussJordanElim(m_copy);
                    String[] spl_result = Matrix.spl(m);

                    System.out.println();
                    int output_choice = outputMenu();
                    switch (output_choice) {
                        case 1 -> {
                            System.out.println();
                            MatrixTerminal.displayMatrixTerminal(m_copy);
                            System.out.println();
                            MatrixTerminal.displaySPLResTerminal(spl_result);
                        }
                        case 2 -> {
                            MatrixFile.displaySPLResFile(spl_result, m_copy);
                        }
                    }                    
                }
                case 3 -> {
                    loop = false;
                    int input_choice = inputMenu();
                    switch (input_choice){
                        case 1 ->{
                            System.out.print("""
                            =========================
                            Input Matrix to process the SPL AX = B by turning it into augmented matrix
                            for example:
                            2x - 3y = 3
                            x + 2y = 5
                            
                            Augemented matrix form 2X3(row x colomn)
                            2 -3 3
                            1 2 5
                            ==========================
                            """);
                            
                            Matrix m1 = MatrixTerminal.readMatrixTerminal();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> System.out.println(Matrix.matriksbalikan(m1));
                                case 2 -> MatrixFile.displaymatrixbalikan(m1); 
                            }
                        }
                        case 2 ->{
                            System.out.print("""
                            =========================
                            Input Matrix to process the SPL AX = B by turning it into augmented matrix
                            for example:
                            2x - 3y = 3
                            x + 2y = 5
                            
                            Augemented matrix form 2X3(row x colomn)
                            2 -3 3
                            1 2 5
                            ==========================
                            """);
                            Matrix m1 = MatrixFile.readMatrixFile();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> System.out.println(Matrix.matriksbalikan(m1));
                                case 2 -> MatrixFile.displaymatrixbalikan(m1); 
                            }
                        }
                    }  
                }
                case 4 -> {
                    loop = false;
                    int input_choice = inputMenu();
                    switch (input_choice){
                        case 1 ->{

                            System.out.print("Please input a matrix where its cols = rows +1 \n\n");
                            Matrix m1 = MatrixTerminal.readMatrixTerminal();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> System.out.println(Matrix.crammer(m1));
                                case 2 -> MatrixFile.displayCrammer(m1); 
                            }
                        }
                        case 2 ->{
                            System.out.print("Please input file which matrix size is i x (i+1) \n\n");
                            Matrix m1 = MatrixFile.readMatrixFile();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> System.out.println(Matrix.crammer(m1));
                                case 2 -> MatrixFile.displayCrammer(m1); 
                            }
                        }
                    }  
                }
                case 5 -> loop = false;
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
                3. Back

                Input method (number) :\040""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> {
                    Matrix m = Matrix.createMtr(0, 0);
                    int input_choice = inputMenu();

                    switch (input_choice) {
                        case 1 -> m = MatrixTerminal.readMatrixTerminal();
                        case 2 -> m = MatrixFile.readMatrixFile();
                    }

                    System.out.println();
                    double det = Matrix.determinant(m);
                    int output_choice = outputMenu();
                    System.out.println();

                    switch (output_choice) {
                        case 1 -> MatrixTerminal.displayDetTerminal(det);
                        case 2 -> MatrixFile.displayDetFile(det);
                    }
                }
                case 2 -> {
                    int input_choice = inputMenu();
                    double hasildet = 0;
                    switch (input_choice){
                        case 1 ->{
                            System.out.print("Please input a square matrix (row = col) \n\n");
                            Matrix m = MatrixTerminal.readMatrixTerminal();
                            hasildet = Matrix.cofactordeterminant(m);
                        }
                        case 2 ->{
                            System.out.print("Please input file which has a square matrix (row = col) \n\n");
                            Matrix m = MatrixFile.readMatrixFile();
                            hasildet = Matrix.cofactordeterminant(m);
                        }
                    }
                        int output_choice = outputMenu();
                        switch(output_choice) {
                            case 1 -> System.out.print("Determinant : " + hasildet + "\n\n");  
                            case 2 -> MatrixFile.displayDetFile(hasildet); 
                        }    
                }
                default -> System.out.println("Masukan tidak valid. Silakan ulangi!");
                case 3 -> loop = false; 
            }
        }
    }

    private static void inverseMenu() {
        boolean loop = true;

        while (loop) {
            System.out.print("""                
                List of methods:
                1. Gauss-Jordan Elimination
                2. Adjoint Matrix
                3. Back

                Input method (number) :\040""");
            int action = input.nextInt();

            switch (action) {
                case 1 -> {
                    int input_choice = inputMenu();
                    switch (input_choice){
                        case 1 ->{
                            System.out.print("Please input a square matrix (row = col) \n\n");
                            Matrix m1 = MatrixTerminal.readMatrixTerminal();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> Matrix.inversegauss(m1,1);
                                case 2 -> {
                                    Matrix.inversegauss(m1,0);
                                    MatrixFile.displayMatrixFile(m1);
                                }
                            }
                        }
                        case 2 ->{
                            System.out.print("Please input file which has a square matrix (row = col) \n\n");
                            Matrix m1 = MatrixFile.readMatrixFile();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1-> Matrix.inversegauss(m1,1);
                                case 2 -> {
                                    Matrix.inversegauss(m1,0);
                                    MatrixFile.displayMatrixFile(m1);
                                }
                            }
                        }
                    }
                }
                case 2 -> {

                    int input_choice = inputMenu() ;
                    switch (input_choice){

                        case 1 -> {

                            System.out.print("Please input a square matrix (row = col)\n\n");
                            Matrix m = MatrixTerminal.readMatrixTerminal();
                            Matrix inverse = Matrix.createMtr(m.row, m.col);
                            Matrix.inverseFunction(m, inverse);
                            System.out.println();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1->{
                                    if (Matrix.isallzero(inverse)) {
                                        System.out.print("unavailable to process, either matrix does not have inverse or not square\n\n");
                                        break;
                                    }
                                    else MatrixTerminal.displayMatrixTerminal(inverse);
                                }
                                case 2 -> MatrixFile.displayMatrixFile(inverse);
                            }
                        }
                        case 2 -> {

                            System.out.print("Please input file which has a square matrix (row = col) \n\n");
                            Matrix m = MatrixFile.readMatrixFile();
                            Matrix inverse = Matrix.createMtr(m.row, m.col);
                            Matrix.inverseFunction(m, inverse);
                            System.out.println();
                            int output_choice = outputMenu();
                            switch(output_choice) {
                                case 1->{
                                    if (Matrix.isallzero(inverse)) {
                                        System.out.print("unavailable to process, either matrix does not have inverse or not square\n\n");
                                        break;
                                    }
                                    else MatrixTerminal.displayMatrixTerminal(inverse);
                                }
                                case 2 -> MatrixFile.displayMatrixFile(inverse);
                            }
                        }
                    }
                }
                case 3 -> loop = false;
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
                double num = (double) matrix_pair[1];

                Object[] result_pair = MatrixInterpolation.createPolynomial(point_matrix, num);
                result = result_pair[0].toString();
                function = (String) result_pair[1];
            }
            case 2 -> {
                Object[] matrix_pair = MatrixFile.readInterpolationFile();
                Matrix point_matrix = (Matrix) matrix_pair[0];
                double num = (double) matrix_pair[1];

                Object[] result_pair = MatrixInterpolation.createPolynomial(point_matrix, num);
                result = result_pair[0].toString();
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
        double estimate_result = 0;
        int input_choice = inputMenu();

        switch (input_choice) {
            case 1 -> {
                Object[] regression_pair = MatrixTerminal.readRegressionTerminal();
                Matrix point_list = (Matrix) regression_pair[0];
                double[] estimate_list = (double[]) regression_pair[1];

                estimate_result = MatrixRegression.calcEstimation(point_list, estimate_list);
            }
            case 2 -> {
                Object[] regression_pair = MatrixFile.readRegressionFile();
                Matrix point_list = (Matrix) regression_pair[0];
                double[] estimate_list = (double[]) regression_pair[1];

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
                    
                    Output choice (number):\040""");
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
