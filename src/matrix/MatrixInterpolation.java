package matrix;

import java.lang.*;

public class MatrixInterpolation {

    public static Object[] createPolynomial(Matrix point_matrix, float num) {
        int row_size = point_matrix.row;
        int column_size = point_matrix.row + 1;
        Matrix poly_system = Matrix.createMtr(row_size, column_size);

        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                if (j == column_size - 1) {
                    poly_system.contents[i][j] = point_matrix.contents[i][1];
                }
                else {
                    poly_system.contents[i][j] = (float) Math.pow(point_matrix.contents[i][0], i);
                }
            }
        }

        String[] result_array = Matrix.spl(poly_system);
        StringBuilder result = new StringBuilder();
        float func_result = 0;

        for (int i = 0; i < result_array.length; ++i) {
            result.append(result_array[i]).append("x^").append(i);
            if (i == row_size - 1) {
                result.append(" + ");
            }
            func_result += Float.parseFloat(result_array[i]) * (float) Math.pow(num, i);
        }

        String func_string = "f(" + num + ") = " + func_result;

        return new Object[] {result, func_string};
    }
}
