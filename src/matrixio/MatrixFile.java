package matrixio;

import java.io.*;
import java.util.*;

import matrix.*;

public class MatrixFile {

    static Scanner input = new Scanner(System.in);

    @SuppressWarnings("DuplicatedCode")
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
                    matrix.contents[i][j] = matrix_input.nextDouble();
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

        double num = 0;
        try {
            String file_path = new File(".\\test\\" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            num = file_input.nextDouble();
            int row_size = -1;
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
                    matrix.contents[i][j] = matrix_input.nextDouble();
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
            result_writer.printf("Function = %s\n", result);
            result_writer.print(function_result);
            result_writer.close();
            System.out.println();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static Object[] readRegressionFile() {
        System.out.print("Input file name: ");
        String file_name = input.next();
        String suffix = ".txt";

        if (!file_name.endsWith(suffix)) {
            file_name = file_name + suffix;
        }

        Matrix point_list = new Matrix();
        double[] estimate_list = new double[0];
        try {
            String file_path = new File(".\\test\\" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            int data_count = 0;
            int var_count = 0;
            while (file_input.hasNextLine()) {
                if (data_count == 0) {
                    String matrix_row = file_input.nextLine();
                    String[] array_row = matrix_row.split(" ");
                    var_count = array_row.length;
                }
                else {
                    file_input.nextLine();
                }
                data_count += 1;
            }
            file_input.close();

            int row_size = data_count - 1;
            int column_size = var_count;
            point_list = Matrix.createMtr(row_size, column_size);
            estimate_list = new double[var_count - 1];
            Scanner value_input = new Scanner(file);

            for (int i = 0; i < row_size; ++i) {
                for (int j = 0; j < column_size; ++j) {
                    point_list.contents[i][j] = value_input.nextDouble();
                }
            }

            for (int i = 0; i < var_count - 1; ++i) {
                estimate_list[i] = value_input.nextDouble();
            }
        }
        catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
        }

        return new Object[] {point_list, estimate_list};
    }

    public static void displayRegressionFile(double estimate_result) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter result_writer = new PrintWriter(file);
            result_writer.printf("Estimation result = %f", estimate_result);
            result_writer.close();
            System.out.println();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }

    private static File ensureDirectoriesExist() throws IOException {
        String folder_path = new File(".\\out\\").getCanonicalPath();
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

            String file_path = new File(".\\out\\" + file_name).getCanonicalPath();
            file = new File(file_path);
            if (file.createNewFile()) {
                break;
            }

            System.out.println("File already exist! Please try again.");
        }

        return file;
    }

    public static void displaySPLResFile (String[] splRes, Matrix m) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter result_writer = new PrintWriter(file);
            int i, j;

            for (i = 0; i < m.row; ++i) {
                for (j = 0; j < m.col; ++j) {
                    result_writer.printf("%f ", m.contents[i][j]);
                }
                result_writer.println();
            }
            result_writer.println();
            
            if (splRes.length == 0) {
                result_writer.printf("Sistem persamaan linear tidak memiliki solusi.");
            }
            else {
                for (i = 0; i < splRes.length; ++i) {
                    result_writer.printf("x%d = %s\n", i+1, splRes[i]);
                }
            }
            result_writer.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }

    public static void displayDetFile (double det) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter result_writer = new PrintWriter(file);
            result_writer.printf("Determinant = %f", det);
            result_writer.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }
    public static void displayCrammer(Matrix m) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter crammer_writer = new PrintWriter(file);
            crammer_writer.println(Matrix.crammer(m));
            crammer_writer.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }
    public static void displaymatrixbalikan(Matrix m) {
        try {
            File file = ensureDirectoriesExist();

            PrintWriter crammer_writer = new PrintWriter(file);
            crammer_writer.println(Matrix.matriksbalikan(m));
            crammer_writer.close();
        }
        catch (IOException error) {
            System.out.println("File cannot be written into!");
            error.printStackTrace();
        }
    }
}
