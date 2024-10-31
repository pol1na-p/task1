import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindDuplicates {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите числа, разделенные пробелами (например, 1 2 3 2 4 1): ");
        String input = scanner.nextLine();

        String[] numbersStr = input.split(" ");
        int[] numbers = new int[numbersStr.length];
        for (int i = 0; i < numbersStr.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i]);
        }

        Map<Integer, Integer> duplicateCounts = findDuplicates(numbers);

        System.out.println("Дубликаты и количество их повторений:");
        for (Map.Entry<Integer, Integer> entry : duplicateCounts.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " раз");
            }
        }
    }

    public static Map<Integer, Integer> findDuplicates(int[] numbers) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int number : numbers) {
            counts.put(number, counts.getOrDefault(number, 0) + 1);
        }

        return counts;
    }
}
