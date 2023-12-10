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
        final long MOD_VALUE = 1000000007; // O módulo pelo qual a soma deve ser calculada (10^9+7)
        final long MAX_ITERATIONS = 314159; // Tamanho máximo de iterações  que pode se atingir de 0 até 314159
        long numberOneCount = 0;
        long pow = 1;
        long sum = 0;

        int[] aBinaryInverseArray = inverse(a, a.length());
        int[] bBinaryInverseArray = inverse(b, b.length());

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            // Realiza a contagem de números 1 presente no array
            if (i < bBinaryInverseArray.length)
                numberOneCount += bBinaryInverseArray[i];
            long multiplier = (i < aBinaryInverseArray.length && aBinaryInverseArray[i] == 1) ? MAX_ITERATIONS - numberOneCount + 1 : numberOneCount;
            sum += (pow * multiplier) % MOD_VALUE;
            pow = (pow << 1) % MOD_VALUE;
        }

        for (int j : bBinaryInverseArray) {
            sum = (sum + pow * numberOneCount) % MOD_VALUE;
            pow = (pow << 1) % MOD_VALUE;
            numberOneCount -= j;
        }

        return ((int) sum);
    }

    /**
     * Método para converter cada dígito para um inteiro e armazenar em um array
     * @return Retorna um array de inteiros
     */
    private static int[] inverse(String binaryPositiveInteger, int arrayLength) {
        StringBuilder sb = new StringBuilder(binaryPositiveInteger).reverse();
        int[] arr = new int[arrayLength];
        for (int i = 0; i < binaryPositiveInteger.length(); i++) {
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

