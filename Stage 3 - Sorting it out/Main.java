package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {

        String dataType = "word";
        if (args.length > 1) dataType = args[1];
        if (Arrays.asList(args).contains("-sortIntegers")) dataType = "int";

        switch (dataType) {
            case "long" -> typeLong();
            case "word" -> typeWord();
            case "line" -> typeLine();
            case "int" -> typeInt();
            default -> System.out.println("wrong dataType");
        }
    }

    static void typeInt() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            list.add(scanner.nextInt());
        }
        list.sort(Comparator.naturalOrder());
        int count = list.size();
        System.out.printf("%nTotal numbers: %d.%n", count);
        System.out.print("Sorted data:");
        list.forEach(x -> System.out.printf(" %d", x));
    }
    static void typeWord() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] str = scanner.next().split(" +");
            list.addAll(Arrays.asList(str));
        }
        String maxString = Collections.max(list, Comparator.comparing(String::length));
        int count = Collections.frequency(list, maxString);
        System.out.printf("%nTotal words: %d%n", list.size());
        System.out.printf("The longest word: %s (%d time(s), %d%%).%n"
                , maxString, count, (int)Math.round((double)count/list.size()*100));
    }

    static void typeLine() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        String maxString = Collections.max(list, Comparator.comparing(String::length));
        int count = Collections.frequency(list, maxString);
        System.out.printf("%nTotal lines: %d%n", list.size());
        System.out.printf("The longest line:%n%s%n(%d time(s) %d%%)."
                , maxString, count, (int)Math.round((double)count/list.size()*100));
    }

    static void typeLong() {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            list.add(scanner.nextLong());
        }
        long maxValue = Collections.max(list);
        int count = Collections.frequency(list, maxValue);
        System.out.printf("%nTotal numbers: %d%n", list.size());
        System.out.printf("The greatest number: %d (%d time(s)%d%%).%n"
                , maxValue, count, (int)Math.round((double)count/list.size()*100));
    }
}
