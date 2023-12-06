package day6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import day5.DayFive;
import org.junit.jupiter.api.Test;
import util.FileUtil;

class DaySixTest {
    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day6/day6example.txt");
        DaySix daySix = new DaySix();

        long sum = daySix.calculateAmountOfPossibleWins(rows);
        assertEquals(288, sum);
    }
    
    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day6/day6input.txt");
        DaySix daySix = new DaySix();

        long sum = daySix.calculateAmountOfPossibleWins(rows);
        assertEquals(281600, sum);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day6/day6example.txt");
        DaySix daySix = new DaySix();

        long sum = daySix.calculateAmountOfPossibleWinsPart2(rows);
        assertEquals(71503, sum);
    }

    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day6/day6input.txt");
        DaySix daySix = new DaySix();

        long sum = daySix.calculateAmountOfPossibleWinsPart2(rows);
        assertEquals(33875953, sum);
    }
}