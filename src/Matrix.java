import java.util.Scanner;
import java.lang.Math;

public class Matrix {
    float[][] contents;
    int row;
    int col;

    static Matrix createMtr (int a, int b) {
        // membuat matriks kosong berukuran a x b
        Matrix m = new Matrix();
        m.contents = new float[a][b];
        m.row = a;
        m.col = b;
        return m;
    }

    static void readMtr (Matrix m) {
        // melakukan iterasi sebanyak jumlah baris dikali jumlah kolom matriks
        // kemudian membaca elemen matriks satu per satu
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < m.row; ++i) {
            for (int j = 0; j < m.col; ++j) {
                m.contents[i][j] = sc.nextFloat();
            }
        }
        sc.close();
    }
    static void displayMtr (Matrix m) {
        // elemen ditampilkan sampai 2 angka di belakang koma
        int i, j;
        for (i = 0; i < m.row - 1; ++i) {
            for (j = 0; j < m.col - 1; ++j) {
                System.out.printf("%.2f ", m.contents[i][j]);
            }
            System.out.printf("%.2f", m.contents[i][j]);
            System.out.printf("\n");
        }
        for (j = 0; j < m.col - 1; ++j) {
            System.out.printf("%.2f ", m.contents[i][j]);
        }
            System.out.printf("%.2f", m.contents[i][j]);
    }
    static int countZero (Matrix m, int i) {
        // menghitung jumlah 0 di awal baris ke-i suatu matriks
        int j, count_zero;
        boolean is_zero;
        count_zero = 0;
        j = 0;
        is_zero = true;
        while ((j < m.col) && is_zero) {
            if (m.contents[i][j] == 0) {
                count_zero++;
                j++;
            }
            else {
                is_zero = false;
            }
        }   
        return count_zero;
    }

    static int rowtoSwap (Matrix m, int i) {
        // mencari baris di bawah baris ke-i yang jumlah leading zerosnya
        // lebih sedikit atau sama dengan i
        int n;
        boolean found;
        n = i + 1;
        found = false;
        while ((n < m.row) && !found) {
            if (countZero(m, n) <= i) {
                found = true;
            }
            else {
                n++;
            }
        }
        if (!found) {
            n = -1;
        }
        return n;
    }

    static void swapRow(Matrix m, int a, int b) {
        // menukar baris m[a] dengan m[b]
        float tempVal;
        for (int i = 0; i < m.col; ++i) {
            tempVal = m.contents[a][i];
            m.contents[a][i] = m.contents[b][i];
            m.contents[b][i] = tempVal;
        }
    }

    static void segitigaAtas (Matrix m, float[] det) {
        // Matriks dipastikan adalah matriks persegi
        // menghasilkan matriks segitiga atas
        int i, j, n, k;
        float c;
        for (i = 0; i < m.row; ++i) {
            if (countZero(m, i) > i) {
                n = rowtoSwap(m, i);
                if (n != -1) {
                    swapRow(m, i, n);
                    det[0] *= -1;
                }
            }
        } // penukaran baris selesai
        for (j = 0; j < m.col - 1; ++j) {
            for (i = j + 1; i < m.row; ++i) {
                n = j;
                while ((n >= 0) && (m.contents[n][j] == 0)) {
                    n--;
                }
                if (n >= 0) {
                    c = m.contents[i][j]/m.contents[n][j];
                    for (k = 0; k < m.col; ++k) {
                        m.contents[i][k] -= c * m.contents[n][k];
                    }
                }
            }
        }
    }

    static float determinant (Matrix m) {
        // m dipastikan adalah matriks persegi
        // mencari determinan dari matriks
        int i;
        Matrix mCpy;
        float[] det = new float[1];
        if (zeroRowIdx(m) != -1) {
            det[0] = 0;
        }
        else { 
            det[0] = 1;
            mCpy = copyMtr(m);
            segitigaAtas(mCpy, det);
            for (i = 0; i < mCpy.row; ++i) {
                det[0] *= mCpy.contents[i][i];
            }
        }
        if (det[0] == -0) {
            det[0] = 0;
        }
        return det[0];
    }

    static int zeroRowIdx(Matrix m){
        // mengembalikan indeks baris yang semua elemennya bernilai 0
        int i, j;
        boolean next_row;
        i = 0;
        next_row = true;
        while ((i < m.row) && next_row) {
            j = 0;
            while ((j < m.col) && (m.contents[i][j]) == 0) {
                j++;
            }
            if (j == m.col) {
                // tidak ada baris yang semua elemennya bernilai nol
                next_row = false;
            }
            else {
                i++;
            }
        }
        if (i == m.row) {
            i = -1;
        }
        return i;
    }

    static Matrix copyMtr (Matrix m) {
        // mengembalikan matriks salinan dari matriks m
        Matrix mCpy = createMtr(m.row, m.col);
        int i, j;
        for (i = 0; i < m.row; ++i) {
            for (j = 0; j < m.col; ++j) {
                mCpy.contents[i][j] = m.contents[i][j];
            }
        }
        return mCpy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris");
        int a = sc.nextInt();
        System.out.println("Masukkan jumlah kolom");
        int b = sc.nextInt();
        Matrix m1 = createMtr(a, b);
        readMtr(m1);
        Matrix m2 = copyMtr(m1);
        float[] xx = new float[1];
        segitigaAtas(m2, xx);
        displayMtr(m2);
        System.out.println();
        float det = determinant(m1);
        System.out.printf("det(m1) = %.2f\n", det);
        sc.close();
    }
}