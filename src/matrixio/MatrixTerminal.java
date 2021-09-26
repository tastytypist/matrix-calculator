package matrixio;

import java.util.*;
import matrix.*;

public class MatrixTerminal {

    static Scanner input = new Scanner(System.in);

    public static MatrixConstructor readMatrixTerminal() {
        System.out.print("Input row size: ");
        int row_size = input.nextInt();
        System.out.print("Input column size: ");
        int column_size = input.nextInt();

        MatrixConstructor matrix = MatrixConstructor.createMatrix(row_size, column_size);

        System.out.println("Input matrix: ");
        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                float element = input.nextFloat();
                matrix.contents[i][j] = element;
            }
        }
        input.close();

        return matrix;
    }

    public static void displayMatrixTerminal(MatrixConstructor matrix) {
        int row_size = matrix.row_size;
        int column_size = matrix.column_size;

        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                System.out.printf("%.2f ", matrix.contents[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
