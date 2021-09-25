package matrix;

import java.util.Scanner;

class Matrix {
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
    
    static boolean isSquare(Matrix m) {
    	return (m.row == m.col);
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
        i = 0;
        while ((i < m.row) && !isZeroRow(m, i)) {
            i++;
        }
        if (i < m.row) { // terdapat baris yang semua elemennya bernilai nol
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

    static boolean isZeroRow (Matrix m, int i) {
        // mengembalikan true jika baris i semua elemennya bernilai 0
        int j;
        j = 0;
        while ((j < m.col) && (m.contents[i][j] == 0)) {
            j++;
        }
        return (j == m.col);
    }

    static boolean isZeroCol (Matrix m, int j) {
        // mengembalikan true jika kolom j semua elemennya bernilai nol
        int i;
        i = 0;
        while ((i < m.row) && (m.contents[i][j] == 0)) {
            i++;
        }
        return (i == m.row);
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

    static Matrix signCofactorFunction(Matrix main) {
        Matrix signCofactor = createMtr(main.row, main.col);
        for (int x = 0; x < main.row; x++) {
            for (int y = 0; y < main.col; y++) {
                if (x % 2 != 0 && y % 2 == 0) {
                    signCofactor.contents[x][y] = -main.contents[x][y];
                } else if (x % 2 != 0 && y % 2 != 0) {
                    signCofactor.contents[x][y] = (main.contents[x][y]);
                } else if (x % 2 == 0 && y % 2 != 0) {
                    signCofactor.contents[x][y] = -main.contents[x][y];
                } else if (x % 2 == 0 && y % 2 == 0) {
                    signCofactor.contents[x][y] = (main.contents[x][y]);
                }
            }
        }

        return signCofactor;
    }

    static Matrix CofactorFunction(Matrix main) {
        Matrix coFactor = createMtr(main.row, main.col);
        for (int x = 0; x < main.row; x++) {
            for (int y = 0; y < main.col; y++) {
                Matrix sementara = createMtr(main.row - 1, main.col - 1);
                int rowCount = 0;
                int colCount = 0;
                for (int a = 0; a < main.row; a++) {
                    for (int b = 0; b < main.col; b++) {
                        if (b == 0) {
                            colCount = 0;
                        }
                        if (a != x && b != y) {
                            // System.out.printf("a = %d\n", a);
                            // System.out.printf("b = %d\n", b);
                            // System.out.printf("rowCount = %d\n", rowCount);
                            // System.out.printf("colCount = %d\n", colCount);
                            sementara.contents[rowCount][colCount] = main.contents[a][b];
                            colCount++;
                            if (colCount == main.col - 1) {
                                rowCount++;
                            } 
                        }
                    }
                }
                coFactor.contents[x][y] = determinant(sementara);
            }
        }
        return coFactor;
    }

    static Matrix adJointFunction(Matrix main) {
        Matrix adJoint = createMtr(main.row, main.col);
        for (int x = 0; x < main.row; x++) {
            for (int y = 0; y < main.col; y++) {
                adJoint.contents[x][y] = main.contents[y][x];
            }
        }

        return adJoint;
    }
    
    static void inverseFunction(Matrix main, Matrix inverse) {
    	// metode inverse dengan adjoint
    	Matrix hasilAdj = adJointFunction(signCofactorFunction(CofactorFunction(main)));
    	float det = determinant(main);
    	if (det != 0) {
	    	for (int x = 0; x < main.row; x++) {
	    		for (int y = 0; y < main.col; y++) {
	    			inverse.contents[x][y] = (1/det)* hasilAdj.contents[x][y];
	    		}
	    	}
	    }
    	else  System.out.println("Does not have an inverse.");
    }
    
	public static void identity(Matrix m, Matrix mIdn) {
		if (m.row == m.col) {
			for (int i = 0; i < mIdn.row; i++) {
				for (int j = 0; j < mIdn.col; j++){
					if (i == j) mIdn.contents[i][j] = 1;
					else mIdn.contents[i][j] = 0;
				}
			}
		}
		else System.out.println("Can't make an identity matrix");
	}
		
	 public static void crammer(Matrix m) {
	 if (m.col  == m.row + 1) {
		 Matrix mCpy;
		 mCpy = copyMtr(m);
		 float D = 1;
		 D = determinant(m);
		 if (D == 0) {//handle if main determinant is 0
		   System.out.println("Determinant of the main matrix is zero. This means that the system of linear equations is either inconsistent or has infinitely many solutions. Gauss-Jordan elimination method will help get the complete answer.");
		 }
		 else {
		   for (int a = 0; a < m.col-1; a++) {
			   mCpy = copyMtr(m);
			   for (int b = 0; b < m.row; b++) {			   
				   mCpy.contents[b][a] = m.contents[b][mCpy.col-1];
			   } 
			   System.out.printf("x%d = %.3f\n",(a+1), determinant(mCpy)/D);
		   }
	   }
	 }
	     else System.out.println("Unavailable SPL");
	 }
	 
    static void sortRow (Matrix m) {
        // mengurutkan baris berdasarkan jumlah leading zero dari yang terkecil hingga terbesar
        int i, j, prevZero;
        for (i = 0; i < m.row - 1; ++i) {
            j = i + 1;
            prevZero = countZero(m, i);
            while (j < m.row) {
                if (countZero(m, j) < prevZero) {
                    prevZero = countZero(m, j);
                    swapRow(m, i, j);
                }
                j++;
            }
        }
    }
	 
	static void gaussElim (Matrix m) {
        // melakukan eliminasi gauss pada matriks m
        // hasil akhir berupa matriks eselon
        // matriks tidak harus berupa matriks persegi
        int i, j, n, k, nextRow;
        float c;
        sortRow(m);
        nextRow = 0;
        for (j = 0; j < m.col; ++j) {
            if (!isZeroCol(m, j)) {
                i = nextRow;
                // mencari baris pertama yang memiliki elemen tidak nol pada kolom j
                while ((i < m.row) && (m.contents[i][j] == 0)) {
                    i++;
                }
                if (i < m.row) {
                    for (n = i + 1; n < m.row; ++n) {
                        c = m.contents[n][j]/m.contents[i][j];
                        for (k = 0; k < m.col; ++k) {
                            m.contents[n][k]  -= c * m.contents[i][k];
                        }
                    }
                    sortRow(m);
                    nextRow++;
                }
            }
        }
        for (i = 0; i < m.row; ++i) {
            // mencari indeks kolom pertama pada baris yang tidak bernilai nol
            j = i;
            while ((j < m.col) && (m.contents[i][j] == 0)) {
                j++;
            }
            if (j < m.col) {
                c = m.contents[i][j];
                for (k = 0; k < m.col; ++k) {
                    m.contents[i][k] /= c;
                    if (m.contents[i][k] == -0) {
                        m.contents[i][k] = 0;
                    }
                }
            }
        }
    }

    static void gaussJordanElim (Matrix m) {
        int i, j, k, n;
        float c;
        gaussElim(m);
        for (i = m.row - 1; i > 0; --i) {
            if (!isZeroRow(m, i)) {
                // cari leading one dalam tiap barisnya
                j = countZero(m, i);
                if (j < m.col) {
                    for (n = i - 1; n >= 0; --n) {
                        c = m.contents[n][j]/m.contents[i][j];
                        for (k = 0; k < m.col; ++k) {
                            m.contents[n][k] -= c * m.contents[i][k];
                        }
                    }
                }
            }
        }
    }
	 
    static String[] spl (Matrix m) {
        // Menerima matriks augmented dan mengembalikan hasil spl dalam bentuk array of strings
        // Mengembalikan array kosong jika persamaan tidak memiliki solusi
        int i, j, k;
        String[] res = new String[m.col - 1]; // kontainer untuk menyimpan hasil spl dalam bentuk string
        String temp;
        gaussJordanElim(m);
        i = m.row - 1;
        while ((i >= 0) && (countZero(m, i) != m.col - 1)) {
            i--;
        }
        if (i >= 0) {
            // Persamaan tidak memiliki solusi
            res = new String[0];
        }
        else {
            for (i = 0; i < res.length; ++i) {
                res[i] = "free"; // Inisialisasi nilai hasil
            }
            for (i = 0; i < m.row; ++i) {
                j = countZero(m, i);
                if (j < m.col - 1) {
                    temp = "";
                    if (m.contents[i][m.col - 1] != 0) {
                        temp += String.format("%.2f", m.contents[i][m.col - 1]) + " ";
                    }
                    for (k = m.col - 2; k > j; --k) {
                        if (m.contents[i][k] == 1) {
                            temp += "- x" + Integer.toString(k + 1) + " "; 
                        }
                        else if (m.contents[i][k] == -1) {
                            if (temp != "") {
                                temp += "+ ";
                            }
                            temp += "x" + Integer.toString(k + 1) + " ";
                        }
                        else if (m.contents[i][k] != 0) {
                            if (m.contents[i][k] * -1 < 0) {
                                temp += String.format("%.2f", m.contents[i][k] * -1) + "x" + Integer.toString(k + 1) + " ";
                            }
                            else {
                                if (temp != "") {
                                    temp += "+ ";
                                }
                                temp += String.format("%.2f", m.contents[i][k] * -1) + "x" + Integer.toString(k + 1) + " ";
                            }
                        }
                    }
                    if (temp != "") {
                        res[j] = temp;
                    }
                    else {
                        res[j] = "0.0";
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
    	//hanya untuk memeriksa
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
        System.out.println("\nCoFactor Functions");
        Matrix hasilCoFactor = signCofactorFunction(CofactorFunction(m1));
        displayMtr(hasilCoFactor);
        System.out.println("\nAdjoint matrix.Matrix");
        displayMtr(adJointFunction(hasilCoFactor));
        System.out.println("\nInverse matrix.Matrix");
        inverseFunction(m1,m2);
        displayMtr(m2);
        System.out.println("\nChange m2 to identity matrix");
        identity(m1,m2);
        displayMtr(m2);
        sc.close();
    }
}

