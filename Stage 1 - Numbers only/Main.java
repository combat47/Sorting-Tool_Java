package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        HashMap<Long, Integer> times = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        long max = 0;
        int count = 0;
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            // write your code here
            if (number > max) {
                max = number;
            }
            if (times.containsKey(number))
                times.put(number, times.get(number) + 1);
            else
                times.put(number, 1);
            count++;
        }

        System.out.printf("Total numbers: %d.%n", count);
        System.out.printf("The greatest number: %d (%d time(s)).%n", max, times.get(max));
    }
}
