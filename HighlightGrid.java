import java.util.*;

public class HighlightGrid {
    private static final int MIN_VAL = 2;
    private static final int MAX_VAL = 20;
    private static final int CELL_WIDTH = 4;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = readinput(sc, "Enter array size (for NxN array): ", 1, 20);
        int[][] a = generateEvenGrid(n);
        System.out.println("\nGenerated 2D array:");
        print(a, -1);

        int x = read_even_input(sc, "\nEnter a number to highlight (even number 2-20): ", MIN_VAL, MAX_VAL);
        System.out.println("\nArray with " + x + " highlighted:");
        int count = print(a, x);

        System.out.printf("\nNumber %d appeared %d time(s)%n", x, count);
        sc.close();
    }

    private static int readinput(Scanner sc, String prompt, int low, int high) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < low || v > high) {
                    System.out.printf("Please enter an integer between %d and %d.%n", low, high);
                    continue;
                }
                return v;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid entry — please enter a whole number.");
            }
        }
    }
    private static int read_even_input(Scanner sc, String prompt, int low, int high) {
        while (true) {
            int v = readinput(sc, prompt, low, high);
            if (v % 2 != 0) {
                System.out.println("Please enter an even number.");
                continue;
            }
            return v;
        }
    }
    private static int[][] generateEvenGrid(int n) {
        Random rnd = new Random();
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // generate 1..10 then *2 -> yields 2,4,...,20
                g[i][j] = (rnd.nextInt(10) + 1) * 2;
            }
        }
        return g;
    }
    private static int print(int[][] a, int highlight) {
        int n = a.length;
        System.out.print("   ");
        for (int j = 0; j < n; j++) {
            System.out.print(String.format("%4s", String.format("%02d", j)));
        }
        System.out.println();

        System.out.print("   +");
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < CELL_WIDTH; k++) System.out.print("-");
            System.out.print("+");
        }
        System.out.println();

        int count = 0;
        for (int i = 0; i < n; i++) {
            System.out.print(String.format("%02d ", i));
            for (int j = 0; j < n; j++) {
                int val = a[i][j];
                String cell;
                if (val == highlight) {
                    cell = String.format("[%2d]", val);
                    count++;
                } else {
                    cell = String.format(" %2d ", val);
                }
                System.out.print("|" + cell);
            }
            System.out.println("|");
            System.out.print("   +");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < CELL_WIDTH; k++) System.out.print("-");
                System.out.print("+");
            }
            System.out.println();
        }
        return count;
    }
}
