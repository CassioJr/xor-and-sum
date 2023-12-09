import java.io.*;

class Result {

    /*
     * Complete the 'xorAndSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */
    public static int xorAndSum(String a, String b) {
        final long MOD_VALUE = 1000000007; // Modulo que se deve encontrar a soma 10^9+7
        final long MAX_ITERATIONS = 314159; // Tamanho máximo que pode se atingir de 0 até 314159
        long sum = 0;
        long count = 0;
        long pow = 1;

        int[] aInverseArray = inverse(a, a.length());
        int[] bInverseArray = inverse(b, b.length());

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (i < bInverseArray.length)
                count += bInverseArray[i];
            long multiplier = (i < aInverseArray.length && aInverseArray[i] == 1) ? MAX_ITERATIONS - count + 1 : count;
            sum += (pow * multiplier) % MOD_VALUE;
            pow = (pow << 1) % MOD_VALUE;
        }

        for (int j : bInverseArray) {
            sum = (sum + pow * count) % MOD_VALUE;
            pow = (pow << 1) % MOD_VALUE;
            count -= j;
        }

        return ((int) sum);
    }

    private static int[] inverse(String x, int arrayLength) {
        StringBuilder sb = new StringBuilder(x).reverse();
        int[] arr = new int[arrayLength];
        for (int i = 0; i < x.length(); i++) {
            arr[i] = sb.charAt(i) - '0';
        }
        return arr;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        long startTime = System.nanoTime();

        int result = Result.xorAndSum(a, b);

        System.out.println(result);

        long finishTime = System.nanoTime() - startTime;
        System.out.println("Tempo de execução total em millis: "
                + finishTime/1000000);

        bufferedReader.close();
    }
}

