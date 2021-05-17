package de.qaware.fasttrack.warmingup;

public class Copy {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = a.clone();

        b[0] = 0;
        System.out.println(a[0]);
        System.out.println(b[0]);

        // clone with 2-dimensional arrays
        int[][] c = {{1, 2, 3}};
        int[][] d = c.clone();
        int[][] f = new int[1][3];

        System.arraycopy(c[0], 0, f[0], 0, 3);

        d[0][0] = 0;
        System.out.println(c[0][0]);
        System.out.println(d[0][0]);
        c[0][0] = 0;
        System.out.println(f[0][0]);
    }
}
