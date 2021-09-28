package matrixio;

import java.io.*;
import java.util.*;

import matrix.*;

public class MatrixFile {

    static Scanner input = new Scanner(System.in);

    public static Matrix readMatrixFile() {
        Matrix matrix = new Matrix();

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

            matrix = Matrix.createMtr(row_size, column_size);
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

    public static void displayMatrixFile(Matrix matrix) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter matrix_writer = new PrintWriter(file);
            int row_size = matrix.row;
            int column_size = matrix.col;

            for (int i = 0; i < row_size; ++i) {
                for (int j = 0; j < column_size; ++j) {
                    matrix_writer.printf("%f", matrix.contents[i][j]);
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
            error.printStackTrace();
        }
    }

    public static Object[] readInterpolationFile() {
        Matrix matrix = new Matrix();

        System.out.print("Input file name: ");
        String file_name = input.next();
        String suffix = ".txt";

        if (!file_name.endsWith(suffix)) {
            file_name = file_name + suffix;
        }

        float num = 0;
        try {
            String file_path = new File(".\\test\\" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            num = file_input.nextFloat();
            int row_size = 0;
            while (file_input.hasNextLine()) {
                file_input.nextLine();
                row_size += 1;
            }
            file_input.close();

            matrix = Matrix.createMtr(row_size, 2);
            Scanner matrix_input = new Scanner(file);

            matrix_input.nextLine();
            for (int i = 0; i < row_size; ++i) {
                for (int j = 0; j < 2; ++j) {
                    matrix.contents[i][j] = matrix_input.nextFloat();
                }
            }
            matrix_input.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
        }

        return new Object[] {matrix, num};
    }

    public static void displayInterpolationFile(String result, String function_result) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter result_writer = new PrintWriter(file);
            result_writer.println(result);
            result_writer.println(function_result);
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }

    public static Object[] readRegressionFile() {
        System.out.print("Input file name: ");
        String file_name = input.next();
        String suffix = ".txt";

        if (!file_name.endsWith(suffix)) {
            file_name = file_name + suffix;
        }

        float[] point_list = new float[0];
        float[] estimate_list = new float[0];
        float result_constant = 0;
        try {
            String file_path = new File(".\\test\\" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            int var_count = 0;
            while (file_input.hasNextLine()) {
                file_input.nextLine();
                var_count += 1;
            }
            var_count = (var_count - 1) / 2;
            file_input.close();

            point_list = new float[var_count];
            estimate_list = new float[var_count];
            Scanner value_input = new Scanner(file);

            for (int i = 0; i < var_count; ++i) {
                point_list[i] = value_input.nextFloat();
            }

            result_constant = value_input.nextFloat();

            for (int i = 0; i < var_count; ++i) {
                estimate_list[i] = value_input.nextFloat();
            }
        }
        catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
        }

        return new Object[] {point_list, result_constant, estimate_list};
    }

    public static void displayRegressionFile(float estimate_result) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter result_writer = new PrintWriter(file);
            result_writer.printf("Estimation result = %f", estimate_result);
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }

    private static File ensureDirectoriesExist() throws IOException {
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

        return file;
    }
}
