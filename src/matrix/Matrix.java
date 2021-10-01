package matrix;
import java.lang.Math;
import java.util.Scanner;

public class Matrix {
    public double[][] contents;
    public int row;
    public int col;

    public static Matrix createMtr (int a, int b) {
        // membuat matriks kosong berukuran a x b
        Matrix m = new Matrix();
        m.contents = new double[a][b];
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
                m.contents[i][j] = sc.nextDouble();
            }
        }
        sc.close();
    }
    public static void displayMtr (Matrix m) {
        // elemen ditampilkan sampai 2 angka di belakang koma
        int i, j;
        for (i = 0; i < m.row - 1; ++i) {
            for (j = 0; j < m.col - 1; ++j) {
                System.out.printf("%f ", m.contents[i][j]);
            }
            System.out.printf("%f", m.contents[i][j]);
            System.out.printf("\n");
        }
        for (j = 0; j < m.col - 1; ++j) {
            System.out.printf("%f ", m.contents[i][j]);
        }
            System.out.printf("%f", m.contents[i][j]);
    }
    static int countZero (Matrix m, int i) {
        // menghitung jumlah 0 di awal baris ke-i suatu matriks
        int j, count_zero;
        boolean is_zero;
        double scale = Math.pow(10,5);
        count_zero = 0;
        j = 0;
        is_zero = true;
        while ((j < m.col) && is_zero) {
            if (Math.round(m.contents[i][j] * scale) / scale == 0) {
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

    public static boolean  isallzero(Matrix m){
        // mengembalikan kalau matrix berisi nol semua
        int i =0;
        boolean sign = true;
        while (i<m.row && sign ){
            int j = 0;
            while (j<m.col && sign){
                if (m.contents[i][j] == 0 ) j++;
                else sign = false;
            }
            i++;
        }
        return sign;
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
        double tempVal;
        for (int i = 0; i < m.col; ++i) {
            tempVal = m.contents[a][i];
            m.contents[a][i] = m.contents[b][i];
            m.contents[b][i] = tempVal;
        }
    }

    public static void segitigaAtas (Matrix m, double[] det) {
        // Matriks dipastikan adalah matriks persegi
        // menghasilkan matriks segitiga atas
        int i, j, n, k;
        double c;
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

    public static double determinant (Matrix m) {
        // m dipastikan adalah matriks persegi
        // mencari determinan dari matriks
        int i;
        Matrix mCpy;
        double[] det = new double[1];
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

    static Matrix CofactorFunction(Matrix main) {
        Matrix coFactor = createMtr(main.row, main.col);
        for (int x = 0; x < main.row; x++) {
            for (int y = 0; y < main.col; y++) {
                Matrix sementara = createMtr(main.row - 1, main.col - 1);
                int rowCount = 0;
                int colCount = 0;
                for (int a = 0; a < main.row; a++) {
                    for (int b = 0; b < main.col; b++) {
                        double hasilkali = Math.pow(-1,(a+b+2));
                        double resulthasil = (double)hasilkali;
                        if (b == 0) {
                            colCount = 0;
                        }
                        if (a != x && b != y) {
                            // System.out.printf("a = %d\n", a);
                            // System.out.printf("b = %d\n", b);
                            // System.out.printf("rowCount = %d\n", rowCount);
                            // System.out.printf("colCount = %d\n", colCount);
                            sementara.contents[rowCount][colCount] = resulthasil*main.contents[a][b];
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

    static Matrix transpose(Matrix main) {
        Matrix adJoint = createMtr(main.row, main.col);
        for (int x = 0; x < main.row; x++) {
            for (int y = 0; y < main.col; y++) {
                adJoint.contents[x][y] = main.contents[y][x];
            }
        }

        return adJoint;
    }
    
    public static void inverseFunction(Matrix main, Matrix inverse) {
        //inverse metode adjoin
        if (isSquare(main)){
            Matrix hasilAdj = transpose((CofactorFunction(main)));
            double det = determinant(main);
           //
            if (det != 0) {
                // tanda akan diproses
                for (int x = 0; x < main.row; x++) {
                    for (int y = 0; y < main.col; y++) {
                        inverse.contents[x][y] = (1/det)* hasilAdj.contents[x][y];
                    }
                }
            }
        }
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
		
    public static String crammer(Matrix m) {
        double D = 1;
        D = determinant(m);
        String temp = new String ();
        temp = " ";
        temp = "Determinant of the main matrix is zero. This means that the system of linear equations is either inconsistent or has infinitely many solutions. Gauss-Jordan elimination method will help get the complete answer.\n";
        if (m.col  == m.row + 1 && D != 0) {
            temp = "";
            Matrix mCpy;
            mCpy = copyMtr(m);
            for (int a = 0; a < m.col-1; a++) {
                mCpy = copyMtr(m);
                for (int b = 0; b < m.row; b++) {			   
                    mCpy.contents[b][a] = m.contents[b][mCpy.col-1];
                } 
                temp = temp.concat(String.format("x%d = %f\n",(a+1), (determinant(mCpy)/D)));
            }
        }
        return temp;
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
	 
	public static void gaussElim (Matrix m) {
        // melakukan eliminasi gauss pada matriks m
        // hasil akhir berupa matriks eselon
        // matriks tidak harus berupa matriks persegi
        int i, j, n, k, nextRow;
        double c;
        double scale = Math.pow(10, 5);
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
                    if (Math.round(m.contents[i][k] * scale) / scale == 0) {
                        m.contents[i][k] = 0;
                    }
                }
            }
        }
    }

    public static void gaussJordanElim (Matrix m) {
        int i, j, k, n;
        double c;
        double scale = Math.pow(10, 5);
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
                            if (Math.round(m.contents[n][k] * scale) / scale == 0) {
                                m.contents[n][k] = 0;
                            }
                        }
                    }
                }
            }
        }
    }
	 
    public static String[] spl (Matrix m) {
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
                        temp += String.format("%f", m.contents[i][m.col - 1]) + " ";
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
                                temp += String.format("%f", m.contents[i][k] * -1) + "x" + Integer.toString(k + 1) + " ";
                            }
                            else {
                                if (temp != "") {
                                    temp += "+ ";
                                }
                                temp += String.format("%f", m.contents[i][k] * -1) + "x" + Integer.toString(k + 1) + " ";
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

    public static void inversegauss (Matrix m, int key){
        // inverse dengan metode gauss elimination
        // periska jika matrix bisa diinverse
        if (isSquare(m)){
            Matrix matrixinver,mtrxidn;
            mtrxidn = createMtr(m.row, m.col);
            matrixinver = createMtr(m.row, 2*m.col);
            identity(m, mtrxidn);
            // membuat matriks untuk proses eliminasi gauss jordon
            for (int i=0; i<matrixinver.row; i++){
                for (int j=0; j<matrixinver.col; j++){
                    if (i < m.row && j < m.col){
                        matrixinver.contents[i][j] = m.contents[i][j];
                    }
                    else {
                        matrixinver.contents[i][j] = mtrxidn.contents[i][j-m.col];
                    }
                }
            }
            // diproses
            gaussJordanElim (matrixinver);
            // periska jika hasil adalah bentuk inverse
            int p = 0;
            boolean flag = true;
            while (p<m.row && flag){
                int q = 0;
                while(q<m.col && flag){
                    if (matrixinver.contents[p][q] != mtrxidn.contents[p][q]) flag = false;
                    q++;
                }
                p++;
            }
            if (flag){
                if (key == 1){
                    System.out.print("\nBentuk Matriks setelah Diinverse: \n");
                    displayMtr(matrixinver); //untuk display hasilnya, setelah dilakukan gauss jordon elimination,
                    // diganti m untuk memasukkan hasil inverse dan display hasilnya juga
                    System.out.print("\n\n");
                    }
                    for (int u = 0; u<matrixinver.row; u++){
                        for (int v = m.col ;v<matrixinver.col; v++){
                            m.contents[u][v-m.col] = matrixinver.contents[u][v];
                        }
                    }
                    if (key == 1){
                    System.out.print("Hasilnya inverse adalah :\n");
                    displayMtr(m);
                    System.out.print("\n\n");
                    }
                }
                else System.out.println("Does not have an inverse\n");
            }
            else System.out.println("Does not have an inverse\n");
        }
    
        public static double cofactordeterminant(Matrix m){
            double det = 0;
            if (isSquare(m)){
                det = 0;
                int i = 0;
                for (int j = 0; j < m.col ; j ++){
                    det = det +  m.contents[i][j]*CofactorFunction(m).contents[i][j];
                    }
                }
                return (det);
        }
    
        static Matrix multiplyMatrix(Matrix m1, Matrix m2){
                // mengalikan matriks
                Matrix multiply = createMtr(m1.row, m2.col);
                int i=0 ;// inisialisasi untuk baris
                int j=0 ;// inisialisasi untuk kolom
                int k=0 ;// inisialisasi untuk kolom       
                // Menginput nilai
                for (i=0; i < multiply.row; i++){
                    for (j=0; j< multiply.col ; j++){
                        double temp = 0;
                        for (k=0; k< m2.row ;k++){
                            temp += m1.contents[i][k] * m2.contents[k][j];
                        }
                        multiply.contents[i][j] = temp;
                    }
                }
                return multiply;
            }
    
        public static String matriksbalikan(Matrix m){
            double det = determinant(m);
            String tempst = new String ();
            tempst = " ";
            tempst = "Wrong matrix input or Augmented matrix does not have an inverse\n";
            if (m.row == m.col -1 && det != 0){
                tempst = "";
                Matrix temp1 = createMtr(m.row, 1);
                Matrix temp2 = createMtr(m.row, m.col-1);
                int row = 0;
                int col = m.col -1;
                for (row= 0; row < m.row ; row++){
                    temp1.contents[row][0] = m.contents[row][col];
                }
                for (row = 0; row < m.row;row++){
                    for (int j = 0; j < m.col-1; j++){
                        temp2.contents[row][j] = m.contents[row][j];
                    }
                }
                inverseFunction(temp2, temp2);
                Matrix hasil = multiplyMatrix(temp2, temp1);
                for (int a = 0; a < hasil.row; a++){
                    for (int b = 0; b <hasil.col; b++){
                        tempst = tempst.concat(String.format("x%d = %f\n",(a+1), hasil.contents[a][b]));
                    }               
                }
                return tempst;
            }
            return tempst;
    }
}