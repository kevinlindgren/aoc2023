package day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.FileUtil;

class DayTwoTest {

    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day2/day2example.txt");
        DayTwo dayTwo = new DayTwo();

        List<DayTwo.AmountAndColor> totalCubes = List.of(new DayTwo.AmountAndColor(12, "red"),
                new DayTwo.AmountAndColor(13, "green"),
                new DayTwo.AmountAndColor(14, "blue"));

        int sum = dayTwo.sumOfPossibleGames(rows, totalCubes);
        assertEquals(8, sum);
        System.out.println(sum);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day2/day2input.txt");
        DayTwo dayTwo = new DayTwo();

        List<DayTwo.AmountAndColor> totalCubes = List.of(new DayTwo.AmountAndColor(12, "red"),
                new DayTwo.AmountAndColor(13, "green"),
                new DayTwo.AmountAndColor(14, "blue"));

        int sum = dayTwo.sumOfPossibleGames(rows, totalCubes);
        assertEquals(2727, sum);
        System.out.println(sum);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day2/day2example.txt");
        DayTwo dayTwo = new DayTwo();

        int sum = dayTwo.sumOfPowerOfRequiredCubesEachGame(rows);
        assertEquals(2286, sum);
        System.out.println(sum);
    }

    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day2/day2input.txt");
        DayTwo dayTwo = new DayTwo();

        int sum = dayTwo.sumOfPowerOfRequiredCubesEachGame(rows);
        assertEquals(56580, sum);
        System.out.println(sum);
    }

}