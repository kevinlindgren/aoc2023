package day3;

import java.util.ArrayList;
import java.util.List;

public class DayThree {

    public int calcuateSumOfPartNumbers(List<String> rows) {
        List<PartNumber> partNumberList = new ArrayList<>();
        List<Symbol> symbolList = new ArrayList<>();
        for (int allRowsIndex = 0; allRowsIndex < rows.size(); allRowsIndex++) {
            String row = rows.get(allRowsIndex);

            StringBuilder currentDigit = new StringBuilder();
            List<Integer> indiciesForDigit = new ArrayList<>();
            for (int oneRowIndex = 0; oneRowIndex < row.toCharArray().length; oneRowIndex++) {
                char ch = row.toCharArray()[oneRowIndex];
                if (Character.isDigit(ch)) {
                    indiciesForDigit.add(oneRowIndex);
                    currentDigit.append(ch);
                }
                // Reset and store PartNumber if next char is not a digit OR if it's the end of the row
                if ((!Character.isDigit(ch) || oneRowIndex == row.toCharArray().length - 1)
                        && !currentDigit.isEmpty()) {
                    int value = Integer.parseInt(currentDigit.toString());
                    partNumberList.add(new PartNumber(value, allRowsIndex, indiciesForDigit));
                    currentDigit = new StringBuilder();
                    indiciesForDigit = new ArrayList<>();
                }

                // handle symbols
                // (Part 1) if (!Character.isDigit(ch) && ch != '.') {
                //    symbolList.add(new Symbol(allRowsIndex, oneRowIndex));
                // }
                if (ch == '*') { // Part 2
                    symbolList.add(new Symbol(allRowsIndex, oneRowIndex));
                }
            }
        }

        return getSumOfAllAdjacentPartNumbers(symbolList, partNumberList);
    }

    private int getSumOfAllAdjacentPartNumbers(List<Symbol> symbolList, List<PartNumber> partNumberList) {
        int totalSum = 0;
        for (Symbol symbol : symbolList) {
            List<PartNumber> adjacentPartNumbers = new ArrayList<>(); // Part 2
            for (PartNumber candidatePartNumber : partNumberList) {
                if (candidatePartNumber.rowIndex == symbol.rowIndex
                        || candidatePartNumber.rowIndex == symbol.rowIndex - 1
                        || candidatePartNumber.rowIndex == symbol.rowIndex + 1) {
                    if (candidatePartNumber.occupiesIndices.contains(symbol.startIndex)
                            || candidatePartNumber.occupiesIndices.contains(symbol.startIndex - 1)
                            || candidatePartNumber.occupiesIndices.contains(symbol.startIndex + 1)) {
                        // (Part 1) totalSum += candidatePartNumber.value;
                        adjacentPartNumbers.add(candidatePartNumber); // Part 2
                    }
                }
            }
            // Part 2
            if (adjacentPartNumbers.size() == 2) {
                totalSum += adjacentPartNumbers.get(0).value * adjacentPartNumbers.get(1).value;
            }
        }
        return totalSum;
    }

    private record PartNumber(int value, int rowIndex, List<Integer> occupiesIndices) {
    }

    private record Symbol(int rowIndex, int startIndex) {
    }
}
