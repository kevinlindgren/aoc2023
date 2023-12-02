package day2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DayTwo {

    public int sumOfPossibleGames(List<String> rows, List<AmountAndColor> totalOfCubes) {
        int totalSum = 0;
        for (String row : rows) {
            String[] split = row.split("\\: ");
            String gameString = split[0];
            String cubesString = split[1];
            int gameId = Integer.parseInt(gameString.replaceAll("Game ", ""));

            List<AmountAndColor> amountAndColorsForGame = parseAmountAndColors(cubesString);
            if (isGamePossible(totalOfCubes, amountAndColorsForGame)) {
                totalSum += gameId;
            }
        }
        return totalSum;
    }

    public int sumOfPowerOfRequiredCubesEachGame(List<String> rows) {
        int totalSum = 0;
        for (String row : rows) {
            String[] split = row.split("\\: ");
            String cubesString = split[1];

            List<AmountAndColor> amountAndColors = parseAmountAndColors(cubesString);
            int maxRed = part2GetHighestAmountOfColor(amountAndColors, "red");
            int maxGreen = part2GetHighestAmountOfColor(amountAndColors, "green");
            int maxBlue = part2GetHighestAmountOfColor(amountAndColors, "blue");
            totalSum += (maxRed * maxGreen * maxBlue);
        }
        return totalSum;
    }

    private int part2GetHighestAmountOfColor(List<AmountAndColor> amountAndColors, String colorToMatch) {
        return amountAndColors.stream()
                .filter(v -> colorToMatch.equals(v.color))
                .max(Comparator.comparing(AmountAndColor::amount))
                .map(AmountAndColor::amount).orElse(0);
    }

    private List<AmountAndColor> parseAmountAndColors(String cubesString) {
        List<AmountAndColor> amountAndColors = new ArrayList<>();
        String[] setsOfCubes = cubesString.split(";");
        for (String setOfCube : setsOfCubes) {
            String[] amountAndColorStrings = setOfCube.split(",");
            for (String amountAndColorString : amountAndColorStrings) {
                String trimmedValue = amountAndColorString.trim();
                String[] amountAndColorStringSplit = trimmedValue.split(" ");
                amountAndColors.add(new AmountAndColor(Integer.parseInt(amountAndColorStringSplit[0]), amountAndColorStringSplit[1]));
            }
        }
        return amountAndColors;
    }

    private boolean isGamePossible(List<AmountAndColor> totalOfCubes, List<AmountAndColor> amountAndColorsForOneGame) {
        for (AmountAndColor totalOfCube : totalOfCubes) {
            for (AmountAndColor amountAndColor : amountAndColorsForOneGame) {
                if (totalOfCube.color.equals(amountAndColor.color) && totalOfCube.amount < amountAndColor.amount) {
                    return false;
                }
            }
        }
        return true;
    }

    public record AmountAndColor(int amount, String color) {
    }
}
