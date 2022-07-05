package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Core {
    public static Color inversionColor;
    public static boolean fillCell = false;
    public static boolean winner = false;
    public static int stepX, stepY, condition;
    public static double leftX, upY, resizable, borderLine, HEIGHT, WIDTH;
    public static boolean startGame = false;
    public static boolean winGame = false;
    private static final int arrLength = 9;
    public static int level = 1;
    public static int[][] winnerArr = new int[arrLength][arrLength];
    public static int[][] arrUserNumber = new int[arrLength][arrLength];
    public static int[][] sudokuArr = new int[arrLength][arrLength];
    public static int[][] sudokuArrDef = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}};


    public static void startGame() {
        if (startGame == false) {
            setArrZero(arrUserNumber);
            sudokuArr = copyArr(sudokuArr, sudokuArrDef);
            mixingRandom(1000);
            hideNumber(levelHide());
            startGame = true;
        }
    }

    public static int levelHide() {
        return 1;//(70 / 5) * level;
    }

    private static void hideNumber(int hideNumber) {
        int x, y;
        for (int i = 0; i < hideNumber; i++) {
            x = randomMinMax(0, 8);
            y = randomMinMax(0, 8);
            if (sudokuArr[y][x] != 0) {
                sudokuArr[y][x] = 0;
            } else {
                i--;
            }
        }
    }

    public static int[][] copyArr(int[][] arrTo, int[][] arrFrom) {
        for (int i = 0; i < arrTo.length; i++) {
            for (int j = 0; j < arrTo.length; j++) {
                arrTo[i][j] = arrFrom[i][j];
            }
        }
        return arrTo;
    }

    public static void mixingRandom(int replaceCounter) {
        for (int i = 0; i < replaceCounter; i++) {
            int x = randomMinMax(0, 8);
            int y;
            if (x == 0 || x == 3 || x == 6) {
                y = randomMinMax(1, 2) + x;
                replace(x, y);
                //System.out.println("1. Меняем x = " + x + " с y= " + y);
            } else if (x == 2 || x == 5 || x == 8) {
                y = x - randomMinMax(1, 2);
                replace(y, x);
                //System.out.println("2. Меняем x = " + y + " с y= " + x);
            } else {
                i--;
            }
        }
    }

    private static void replace(int x, int y) {
        int cr = randomMinMax(0, 1);
        if (cr == 0) {
            //System.out.println("1. Меняем строки ");
            replaceRow(x, y);
        } else {
            //System.out.println("2. Меняем столбцы ");
            replaceColumn(x, y);
        }
    }

    private static void replaceColumn(int x, int y) {
        int a, b;
        for (int i = 0; i < sudokuArr.length; i++) {
            a = sudokuArr[i][x];
            b = sudokuArr[i][y];
            sudokuArr[i][x] = b;
            sudokuArr[i][y] = a;
        }
    }

    private static void replaceRow(int x, int y) {
        int a, b;
        for (int i = 0; i < sudokuArr.length; i++) {
            a = sudokuArr[x][i];
            b = sudokuArr[y][i];
            sudokuArr[x][i] = b;
            sudokuArr[y][i] = a;
        }
    }

    public static void synchrArr(int[][] arrUserNumber, int sudokuArr[][]) {
        for (int i = 0; i < sudokuArr.length; i++) {
            for (int j = 0; j < sudokuArr.length; j++) {
                if (sudokuArr[j][i] != 0) {
                    arrUserNumber[j][i] = 0;
                }
            }
        }
    }

    public static void mergerArr(int[][] arrUserNumber, int sudokuArr[][]) {
        for (int i = 0; i < sudokuArr.length; i++) {
            for (int j = 0; j < sudokuArr.length; j++) {
                if (sudokuArr[i][j] != 0) {
                    winnerArr[i][j] = sudokuArr[i][j];
                } else {
                    winnerArr[i][j] = arrUserNumber[i][j];
                }
            }
        }
    }

    public static boolean winSudoku() {
        mergerArr(arrUserNumber, sudokuArr);
        for (int k = 0; k < 9; k++) {
            if (winerH(k, winnerArr) & winerV(k, winnerArr) & winerRactzngle(k, winnerArr)) {
                if (k == 8) {
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }

    public static boolean winerH(int vert, int[][] arr) {
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            if (arr[vert][i] == 0 || !set.add(arr[vert][i])) {
                return false;
            }
        }
        //System.out.println("горизонталь True");
        return true;
    }

    public static boolean winerV(int horiz, int[][] arr) {
        Set set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][horiz] == 0 || !set.add(arr[i][horiz])) {
                return false;
            }
        }
        //System.out.println("Вертикаль True");
        return true;
    }

    public static boolean winerRactzngle(int cube, int[][] arr) {
        int vert = gpsRactangle(cube)[0];
        int horiz = gpsRactangle(cube)[1];
        Set set = new HashSet();
        for (int i = vert; i < vert + 3; i++) {
            for (int j = horiz; j < horiz + 3; j++) {
                if (arr[i][j] == 0 || !set.add(arr[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] gpsRactangle(int number) {
        int[] arr = new int[2];
        switch (number) {
            case 0:
                arr[0] = 0;
                arr[1] = 0;
                break;
            case 1:
                arr[0] = 0;
                arr[1] = 3;
                break;
            case 2:
                arr[0] = 0;
                arr[1] = 6;
                break;
            case 3:
                arr[0] = 3;
                arr[1] = 0;
                break;
            case 4:
                arr[0] = 3;
                arr[1] = 3;
                break;
            case 5:
                arr[0] = 3;
                arr[1] = 6;
                break;
            case 6:
                arr[0] = 6;
                arr[1] = 0;
                break;
            case 7:
                arr[0] = 6;
                arr[1] = 3;
                break;
            case 8:
                arr[0] = 6;
                arr[1] = 6;
                break;
        }
        return arr;
    }


    public static int[][] setArrZero(int arrUserNumber[][]) {
        for (int i = 0; i < arrUserNumber.length; i++) {
            for (int j = 0; j < arrUserNumber.length; j++) {
                arrUserNumber[i][j] = 0;
            }
        }
        return arrUserNumber;
    }

    public static int arrSetNext(int x) {
        if (x == 9) {
            x = 0;
        } else {
            x += 1;
        }
        return x;
    }

    public static int randomMinMax(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static void printArr(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Color randomColor() {
        int x = randomMinMax(0, 255);
        int y = randomMinMax(0, 255);
        int z = randomMinMax(0, 255);
        int w = randomMinMax(0, 255);
        inversionColor(new int[]{x, y, z, w});
        return new Color(x, y, z, w);
    }


    public static void inversionColor(int arr[]) {
        inversionColor = new Color(adden(arr[0]), adden(arr[1]), adden(arr[2]), adden(arr[3]));
    }

    public static int adden(int x) {
        x = x + (255 / 2);
        return (x > 255 ? x - 255 : x);
    }


    public static void centeringJFrame(int sizeWidth, int sizeHeight, JComponent component, JFrame frame) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (s.width - sizeWidth) / 2;
        int Y = (s.height - sizeHeight) / 2;
        if (frame == null) {
            component.setBounds(X, Y, sizeWidth, sizeHeight);
        } else {
            frame.setBounds(X, Y, sizeWidth, sizeHeight);
        }
    }
}
