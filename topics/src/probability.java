public class probability {
    public double solution(int n) {

        double numerator = 1.0;

        double denominator;

        for (int i = 2; i <= n - 1; i++) {
            denominator = (1.0 - Math.pow((1.0 - 1.0/i), i) - Math.pow((1.0/i), i));
            numerator += (combinations(n, i) * Math.pow((1.0/n), i) * Math.pow((1.0 - 1.0/n), n - i)) * (numerator/denominator);
        }


        if (n > 1) {
            denominator = (1.0 - Math.pow((1.0 - 1.0/n), n) - Math.pow((1.0/n), n));
        } else {
            denominator = 1.0;
        }

        return numerator / denominator;
    }

    public static int combinations(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }
        int[] c = new int[k+1];
        c[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                c[j] = c[j] + c[j-1];
            }
        }
        return c[k];
    }


}