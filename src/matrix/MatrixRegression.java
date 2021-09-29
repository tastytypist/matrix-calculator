package matrix;

public class MatrixRegression {

    public static float calcEstimation(Matrix point_list, float[] estimate_list) {
        int row_size = point_list.col;
        int column_size = point_list.col + 1;
        Matrix regression_system = Matrix.createMtr(row_size, column_size);

        float element;
        for (int i = 0; i < row_size; ++i) {
            for (int j = 0; j < column_size; ++j) {
                element = 0;
                if (i == 0 && j == 0) {
                    element = point_list.row;
                }
                else if (i == 0) {
                    for (int k = 0; k < point_list.row; ++k) {
                        element += point_list.contents[k][j - 1];
                    }
                }
                else if (i > j) {
                    element = regression_system.contents[j][i];
                }
                else {
                    for (int k = 0; k < point_list.row; ++k) {
                        element += point_list.contents[k][i - 1] * point_list.contents[k][j - 1];
                    }
                }
                regression_system.contents[i][j] = element;
            }
        }

        String[] result_array = Matrix.spl(regression_system);
        float func_result = 0;
        for (int i = 0; i < result_array.length; ++i) {
            if (i == 0) {
                func_result += Float.parseFloat(result_array[i]);
            }
            else {
                func_result += Float.parseFloat(result_array[i]) * estimate_list[i - 1];
            }
        }

        return func_result;
    }
}
