package matrixio;

import java.util.*;
import matrix.*;

public class MatrixTerminal {

    static Scanner input = new Scanner(System.in);

    public static Matrix readMatrixTerminal() {
        System.out.print("Input row size: ");
        int row_size = input.nextInt();
        System.out.print("Input column size: ");
        int column_size = input.nextInt();

        Matrix matrix = Matrix.createMtr(row_size, column_size);

        System.out.println("Input matrix: ");
        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                float element = input.nextFloat();
                matrix.contents[i][j] = element;
            }
        }

        return matrix;
    }

    public static void displayMatrixTerminal(Matrix matrix) {
        int row_size = matrix.row;
        int column_size = matrix.col;

        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                System.out.printf("%.2f ", matrix.contents[i][j]);
                if (i != row_size - 1 && j == column_size - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public static Object[] readInterpolationTerminal() {
        System.out.print("Input n: ");
        int num = input.nextInt();
        System.out.print("Input number point: ");
        int point_count = input.nextInt();

        Matrix point_matrix = Matrix.createMtr(point_count, 2);

        System.out.println("Input points: ");
        for (int i = 0; i < point_count; ++i) {
            for (int j = 0; j < 2; ++j) {
                float element = input.nextFloat();
                point_matrix.contents[i][j] = element;
            }
        }

        return new Object[] {point_count, num};
    }

    public static void displayInterpolationTerminal(String result, String function_result) {
        System.out.println(result);
        System.out.println(function_result);
    }
}
