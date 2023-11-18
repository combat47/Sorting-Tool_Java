package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        String typeOfInput = args[1];
        switch (typeOfInput) {
            case "long" -> {
                longData();
            }
            case "line" -> {
                lineData();
            }
            case "word" -> {
                wordData();
            }
        }

    }
    static void lineData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        scanner.close();

        Comparator<String> comparator = (s1,s2) -> Integer.compare(s1.length(), s2.length());
        Collections.sort(lines, comparator);
        String max = lines.get(lines.size() - 1);
        int count = Collections.frequency(lines, max);
        int percent = count * 100 / lines.size();

        System.out.printf("Total lines: %d\n", lines.size());
        System.out.printf("The longest line:\n%s\n(%d time(s), %d%%).\n", max, count, percent);
    }
    static void longData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> numbers = new ArrayList<>();

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            numbers.add(number);
        }
        scanner.close();
        //numbers.sort(Comparator.naturalOrder());
        long max = Collections.max(numbers);
        int count = Collections.frequency(numbers, max);
        int percent = count * 100 / numbers.size();

        System.out.printf("Total numbers: %d\n", numbers.size());
        System.out.printf("The greatest number: %d (%d time(s), %d%%).\n", max, count, percent);
    }
    static void wordData() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        scanner.close();

        Comparator<String> comparator = (s1,s2) -> Integer.compare(s1.length(), s2.length());
        Collections.sort(words, comparator);
        String max = words.get(words.size()-1);
        int count = Collections.frequency(words, max);
        int percent = count * 100 / words.size();

        System.out.printf("Total numbers: %d\n", words.size());
        System.out.printf("The greatest number: %s (%d time(s), %d%%).\n", max, count, percent);
    }
}
