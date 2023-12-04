package day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayFour {
    
    public int sumOfWinningNumbers(List<String> rows) { 
        int totalSum = 0;
        for (String row : rows) {
            String[] cardAndValues = row.split(":");
            String[] winningNumbersAndMyNumbers = cardAndValues[1].split("\\|");
            List<String> winningNumbers = Arrays.stream(winningNumbersAndMyNumbers[0].trim().split(" ")).toList();
            List<String> myNumbers = Arrays.stream(winningNumbersAndMyNumbers[1].trim().split(" ")).toList();

            int sumOfCard = 0;
            for (String myNumber : myNumbers) {
                if (!myNumber.isEmpty() && winningNumbers.contains(myNumber)) {
                    if (sumOfCard == 0) {
                        sumOfCard = 1;
                    } else {
                        sumOfCard *= 2;
                    }
                }
            }
            totalSum += sumOfCard;
        }
        return totalSum;
    }

    public int numberOfScratchCards(List<String> rows) {
        Map<Integer, Integer> cardNumberToAmount = new HashMap<>();

        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(i);
            String[] cardAndValues = row.split(":");
            String[] winningNumbersAndMyNumbers = cardAndValues[1].split("\\|");
            List<Integer> winningNumbers = Arrays.stream(winningNumbersAndMyNumbers[0].trim().split(" "))
                    .filter(p -> !p.isEmpty())
                    .mapToInt(Integer::parseInt).boxed().toList();
            List<Integer> myNumbers = Arrays.stream(winningNumbersAndMyNumbers[1].trim().split(" "))
                    .filter(p -> !p.isEmpty())
                    .mapToInt(Integer::parseInt).boxed().toList();;
                    
            long winningsOnThisCard = myNumbers.stream().filter(winningNumbers::contains).count(); // Get amount of winnings for this card
            // Since every card is always read at least once, add the "original card" (1) value to the total amount of this card
            if (cardNumberToAmount.get(i) == null) {
                cardNumberToAmount.put(i, 1);
            } else {
                cardNumberToAmount.put(i, cardNumberToAmount.get(i) + 1);
            }
            int numCopiesOfThisCard = cardNumberToAmount.get(i);
            // For all the winnings on this card, iterate over that amount of subsequent cards
            for (int j = 1; j <= winningsOnThisCard; j++) {
                int cardNumberToModify = i + j;
                // For each copy of our current card (+ the one original we set in row 50) add it to the amount of copies for the subsequent card
                cardNumberToAmount.merge(cardNumberToModify, numCopiesOfThisCard, Integer::sum);
            }
        }

        return cardNumberToAmount.values().stream().mapToInt(Integer::intValue).sum();
    }
}
