import java.util.Scanner;

public class ReverseWords {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String inputString = scanner.nextLine();

        String reversedString = reverseWords(inputString);
        System.out.println("Перевернутая строка: " + reversedString);
    }

    public static String reverseWords(String str) {
        char[] chars = str.toCharArray();
        int start = 0;
        int end = 0;

        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                end = i;
            } else {
                reverse(chars, start, end);
                start = i + 1;
                end = i + 1;
            }
        }

        // Обработка последнего слова
        reverse(chars, start, end);

        return new String(chars);
    }

    public static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
