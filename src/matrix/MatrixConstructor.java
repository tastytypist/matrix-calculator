package matrix;

public class MatrixConstructor {

    public float[][] contents;
    int row_size;
    int column_size;

    public static MatrixConstructor createMatrix(int row, int column) {
        MatrixConstructor matrix = new MatrixConstructor();
        matrix.contents = new float[row][column];
        matrix.row_size = row;
        matrix.column_size = column;

        return matrix;
    }
}
