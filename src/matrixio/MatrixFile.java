package matrixio;

import matrix.MatrixConstructor;

import java.io.*;
import java.util.*;

public class MatrixFile {

    static Scanner input = new Scanner(System.in);

    public static MatrixConstructor readMatrixFile() {
        MatrixConstructor matrix = new MatrixConstructor();

        System.out.print("Input file name: ");
        String file_name = input.next();
        String suffix = ".txt";

        if (!file_name.endsWith(suffix)) {
            file_name = file_name + suffix;
        }

        try {
            String file_path = new File(".\\test\\" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            int row_size = 0;
            int column_size = 0;
            while (file_input.hasNextLine()) {
                if (row_size == 0) {
                    String matrix_row = file_input.nextLine();
                    String[] array_row = matrix_row.split(" ");
                    column_size = array_row.length;
                }
                else {
                    file_input.nextLine();
                }
                row_size += 1;
            }
            file_input.close();

            matrix = MatrixConstructor.createMatrix(row_size, column_size);
            Scanner matrix_input = new Scanner(file);

            for (int i = 0; i < row_size; ++i) {
                for (int j = 0; j < column_size; ++j) {
                    matrix.contents[i][j] = matrix_input.nextFloat();
                }
            }
            matrix_input.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
        }

        return matrix;
    }

    public static void displayMatrixFile(MatrixConstructor matrix) {
        try {
            String folder_path = new File(".\\output\\").getCanonicalPath();
            File folder = new File(folder_path);
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException();
                }
            }

            File file;
            while (true) {
                System.out.print("Input file name: ");
                String file_name = input.next();
                String suffix = ".txt";

                if (!file_name.endsWith(suffix)) {
                    file_name = file_name + suffix;
                }

                String file_path = new File(".\\output\\" + file_name).getCanonicalPath();
                file = new File(file_path);
                if (file.createNewFile()) {
                    break;
                }

                System.out.println("File already exist! Please try again.");
            }

            PrintWriter matrix_writer = new PrintWriter(file);
            int row_size = matrix.row_size;
            int column_size = matrix.column_size;

            for (int i = 0; i < row_size; ++i) {
                for (int j = 0; j < column_size; ++j) {
                    matrix_writer.printf("%.2f", matrix.contents[i][j]);
                    if (j != column_size - 1) {
                        matrix_writer.print(" ");
                    }
                    if (i != row_size - 1 && j == column_size - 1) {
                        matrix_writer.println();
                    }
                }
            }

            matrix_writer.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
        }
    }
}
