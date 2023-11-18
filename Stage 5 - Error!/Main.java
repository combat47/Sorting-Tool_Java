package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> argsMap = new HashMap<>();
        String currKey = null;

        try {
            for (String arg : args) {
                if (arg.equals("-dataType") || arg.equals("-sortingType")) {   //if current arg is valid, set is a curr key
                    currKey = arg;                                             // to be passed as key in the args hashmap
                } else if (currKey != null) {
                    argsMap.put(currKey, arg);
                    currKey = null;
                } else {
                    throw new InvalidArgumentException(arg);                   //if current arg being read is not a valid arg or
                }                                                              //parameter, throws InvalidArgumentException
            }
        } catch (InvalidArgumentException e) {
            System.out.println("\"" + e.getInvalidArgument() + "\" is not a valid parameter. It will be skipped.");
        }

        String sortingType = "natural";
        String dataType = "word";

        try {
            String dataTypeValue = argsMap.get("-dataType");
            if (dataTypeValue != null) {
                switch (dataTypeValue) {
                    case "long":
                        dataType = "long";
                        break;
                    case "number":
                        dataType = "number";
                        break;
                    case "line":
                        dataType = "line";
                        break;
                    default:
                        dataType = "word";
                }
            } else if (argsMap.containsKey("-dataType")) {
                throw new DataTypeException();
            }
        } catch (DataTypeException e) {
            System.out.println("No data type defined!");
        }

        try {
            String sortingTypeValue = argsMap.get("-sortingType");
            if (sortingTypeValue != null) { //checks if type for the param is passed, throws SortingTypeException if not
                switch (sortingTypeValue) {
                    case "byCount":
                        sortingType = "byCount";
                        break;
                    default:
                        sortingType = "natural";
                }
            } else if (argsMap.containsKey("-sortingType")){
                throw new SortingTypeException();
            }
        } catch (SortingTypeException e) {
            System.out.println("No sorting type defined!");
        }

        if (dataType.equals("long") || dataType.equals("number")) {
            computeNumberData(sortingType, scanner);
        }

        if (dataType.equals("line")) {
            computeLineData(sortingType, scanner);
        }

        if (dataType.equals("word")) {
            computeWordData(sortingType, scanner);
        }
    }

    public static void computeNumberData(String sortingType, Scanner scanner) {
        ArrayList<Long> numbers = new ArrayList<>();

        //only add numbers to the array
        //prints message showing the incorrect (non-numeric) inputs
        while (scanner.hasNext()) {
            if (scanner.hasNextLong()) {
                long number = scanner.nextLong();
                numbers.add(number);
            } else {
                String nonNumericInput = scanner.next();
                System.out.println("\"" + nonNumericInput + "\" is not a " + sortingType + ". It will be skipped.");
            }
        }

        long numberCount = numbers.size();

        if (sortingType.equals("natural")) {
            System.out.println("Total numbers: " + numberCount + ".");

            System.out.print("Sorted data: ");
            numbers.stream()
                    .sorted()
                    .map(Object::toString)                              // convert each number to a string
                    .forEach(number -> System.out.print(number + " ")); // append a space after each number
        }

        if (sortingType.equals("byCount")) {
            //sorts list by ascending order
            Collections.sort(numbers);
            //sorts list by freq while keeping ascending order
            Collections.sort(numbers, (n1, n2) -> Collections.frequency(numbers, n1) - Collections.frequency(numbers, n2));

            System.out.println("Total numbers: " + numberCount);

            long lastNumberPrinted = numbers.get(0) + 1; //this is dumb
            for (Long number : numbers) {
                int currNumberCount = Collections.frequency(numbers, number);

                if (lastNumberPrinted != number) {
                    lastNumberPrinted = number;
                    System.out.println(number + ": " + currNumberCount + " time(s), "
                            + ((double) currNumberCount / numberCount * 100) + "%).");
                }
            }
        }
    }

    public static void computeLineData(String sortingType, Scanner scanner) {
        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String currLine = scanner.nextLine();
            lines.add(currLine);
        }

        int lineCount = lines.size();

        if (sortingType.equals("natural")) {
            //sorts all lines from longest to shortest; sorts it alphabetically if same length
            Collections.sort(lines, (s1, s2) -> {
                int lengthComparison = Integer.compare(s2.length(), s1.length());
                if (lengthComparison == 0) {
                    return s1.compareTo(s2);
                }
                return lengthComparison;
            });

            System.out.println("Total lines: " + lineCount);
            lines.forEach(System.out::println);
        }

        if (sortingType.equals("byCount")) {
            //sorts list by ascending order
            Collections.sort(lines);
            //sorts list by frequency (count)
            Collections.sort(lines, (l1, l2)
                    -> Collections.frequency(lines, l1) - Collections.frequency(lines, l2));

            System.out.println("Total lines: " + lineCount);

            String lastLinePrinted = lines.get(0) + "last"; //...
            for (String line : lines) {
                int currNumberCount = Collections.frequency(lines, line);

                if (!lastLinePrinted.equals(line)) {
                    lastLinePrinted = line;
                    System.out.println(line + ": " + currNumberCount + " time(s), "
                            + ((double) currNumberCount / lineCount * 100) + "%).");
                }
            }
        }
    }

    public static void computeWordData(String sortingType, Scanner scanner) {
        ArrayList<String> words = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String currLine = scanner.nextLine();

            //splits current line into an array of words and adds each word  separately in the list of words
            String[] lineSplitInWords = currLine.split("\\s+");
            for (String word : lineSplitInWords) {
                if (!word.isEmpty()) { // empty spaces do not count as words
                    words.add(word);
                }
            }
        }

        int wordCount = words.size();

        if (sortingType.equals("natural")) {
            //sorts all words from longest to shortest; sorts it alphabetically if same length
            Collections.sort(words, (s1, s2) -> {
                int lengthComparison = Integer.compare(s2.length(), s1.length());
                if (lengthComparison == 0) {
                    return s1.compareTo(s2);
                }
                return lengthComparison;
            });

            System.out.println("Total words: " + wordCount);

            System.out.println("Sorted data: ");
            words.forEach(System.out::print);
        }

        if (sortingType.equals("byCount")) {
            //sorts list by ascending order
            Collections.sort(words);
            //sorts list by freq while keeping ascending order
            Collections.sort(words, (n1, n2) -> Collections.frequency(words, n1) - Collections.frequency(words, n2));

            System.out.println("Total words: " + wordCount);

            String lastWordPrinted = words.get(0) + "last"; //this is even dumber
            for (String word : words) {
                int currWordCount = Collections.frequency(words, word);

                if (!lastWordPrinted.equals(word)) {
                    lastWordPrinted = word;
                    System.out.println(word + ": " + currWordCount + " time(s), "
                            + ((double) currWordCount / wordCount * 100) + "%).");
                }
            }
        }
    }
}

class DataTypeException extends Exception {
    public DataTypeException() {
        super();
    }
}

class InvalidArgumentException extends Exception {
    public String invalidArgument;

    public InvalidArgumentException(String invalidArg) {
        this.invalidArgument = invalidArg;
    }

    public String getInvalidArgument() {
        return invalidArgument;
    }
}

class SortingTypeException extends Exception {
    public SortingTypeException() {
        super();
    }
}
