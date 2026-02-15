import java.util.*;

public class SeriesProject {

    public static void main(String[] args) {
        // Usamos Scanner para capturar los datos del usuario
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--- Generator and Binary Search ---");
            System.out.print("Enter value for a (start): ");
            int a = scanner.nextInt();
            System.out.print("Enter value for b (multiplier): ");
            int b = scanner.nextInt();
            System.out.print("Enter value for n (terms): ");
            int n = scanner.nextInt();

            // 1. Generamos la serie usando nuestra lógica optimizada
            List<Long> mySeries = generateSeries(a, b, n);
            System.out.println("\nGenerated Series: " + mySeries);

            // 2. Buscamos un número
            System.out.print("\nEnter a number to search in the series: ");
            long target = scanner.nextLong();
            
            int resultIndex = binarySearch(mySeries, target);

            if (resultIndex != -1) {
                System.out.println("🎯 Success! Target found at index: " + resultIndex);
            } else {
                System.out.println("❌ Target not found in the series.");
            }

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: Please enter only valid numbers.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Generates a series based on: a + (2^0 * b) + (2^1 * b) + ...
     * Optimized to avoid Math.pow and use Long for large numbers.
     */
    public static List<Long> generateSeries(int a, int b, int n) {
        List<Long> series = new ArrayList<>();
        long currentSum = a;
        long powerOfTwo = 1L; // 2^0

        for (int j = 0; j < n; j++) {
            long termToAdd = powerOfTwo * b;
            currentSum += termToAdd;
            series.add(currentSum);
            
            // Preparamos la potencia para la siguiente vuelta (2^1, 2^2...)
            powerOfTwo *= 2L; 
        }
        return series;
    }

    /**
     * Optimized Binary Search O(log n)
     */
    public static int binarySearch(List<Long> list, long target) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            long midValue = list.get(mid);

            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}