package day4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import day2.DayTwo;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class DayFourTest {

    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day4/day4example.txt");
        DayFour dayFour = new DayFour();
        
        int sum = dayFour.sumOfWinningNumbers(rows);
        assertEquals(13, sum);
        System.out.println(sum);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day4/day4input.txt");
        DayFour dayFour = new DayFour();

        int sum = dayFour.sumOfWinningNumbers(rows);
        assertEquals(22488, sum);
        System.out.println(sum);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day4/day4example.txt");
        DayFour dayFour = new DayFour();

        int sum = dayFour.numberOfScratchCards(rows);
        assertEquals(30, sum);
        System.out.println(sum);
    }

    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day4/day4input.txt");
        DayFour dayFour = new DayFour();

        int sum = dayFour.numberOfScratchCards(rows);
        assertEquals(7013204, sum);
        System.out.println(sum);
    }
}