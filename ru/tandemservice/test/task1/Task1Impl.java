package ru.tandemservice.test.task1;

import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    private Task1Impl() {}

    public static Task1Impl getInstance() {
        return (Task1Impl) INSTANCE;
    }

    @Override
    public void sort(List<String[]> rows, int columnIndex) {
        rows.sort(Comparator.comparing(row -> row[columnIndex], this::compareStrings));
    }

    private int compareStrings(String string1, String string2) { // O(minLength)
        if (string1 == null && string2 == null) { return 0; }
        if (string1 == null) { return -1; }
        if (string2 == null) { return 1; }
        if (string1.isEmpty() && string2.isEmpty()) { return 0; }
        if (string1.isEmpty()) { return -1; }
        if (string2.isEmpty()) { return 1; }
        List<String> substrings1 = extractSubstrings(string1);
        List<String> substrings2 = extractSubstrings(string2);
        int minLength = Math.min(substrings1.size(), substrings2.size());
        for (int i = 0; i < minLength; i++) {
            String substr1 = substrings1.get(i);
            String substr2 = substrings2.get(i);
            if (isNumeric(substr1) && isNumeric(substr2)) {
                int number1 = Integer.parseInt(substr1);
                int number2 = Integer.parseInt(substr2);
                if (number1 != number2) { return number1 - number2; }
            } else {
                int comparisonResult = substr1.compareTo(substr2);
                if (comparisonResult != 0) { return comparisonResult; }
            }
        }
        return substrings1.size() - substrings2.size();
    }

    private List<String> extractSubstrings(String string) {
        return Pattern
                .compile("\\d+|\\D+")
                .matcher(string)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

    private boolean isNumeric(String string) {
        return string.matches("-?\\d+");
    }

}
