package day1;

import java.util.List;

public class DayOne {

    public int calculateCalibrationValue(List<String> rows) {
        int totalSum = 0;
        for (String row : rows) {
            List<Integer> digitList = row.chars()
                    .filter(Character::isDigit)
                    .map(Character::getNumericValue)
                    .boxed()
                    .toList();
            totalSum += Integer.parseInt("" + digitList.getFirst() + digitList.getLast());
        }

        return totalSum;
    }

    public int calculateCalibrationValuePartTwo(List<String> rows) {
        List<String> convertedRows = rows.stream().map(this::convertNumericsInText).toList();
        return calculateCalibrationValue(convertedRows);
    }

    private String convertNumericsInText(String row) {
        return row.replaceAll("one", "o1e")
                .replaceAll("two", "t2o").
                replaceAll("three", "t3e").
                replaceAll("four", "f4r").
                replaceAll("five", "f5e").
                replaceAll("six", "s6x").
                replaceAll("seven", "s7n").
                replaceAll("eight", "e8t").
                replaceAll("nine", "n9e");
    }
}
