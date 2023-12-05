package day5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.FileUtil;

class DayFiveTest {

    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day5/day5example.txt");
        DayFive dayFive = new DayFive();

        long lowestLocationNumber = dayFive.calculateLowestLocation(rows);
        assertEquals(35, lowestLocationNumber);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day5/day5input.txt");
        DayFive dayFive = new DayFive();

        long lowestLocationNumber = dayFive.calculateLowestLocation(rows);
        assertEquals(379811651, lowestLocationNumber);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day5/day5example.txt");
        DayFive dayFive = new DayFive();

        long lowestLocationNumber = dayFive.calculateLowestLocationPart2(rows);
        assertEquals(46, lowestLocationNumber);
    }

    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day5/day5input.txt");
        DayFive dayFive = new DayFive();

        long lowestLocationNumber = dayFive.calculateLowestLocationPart2(rows);
        assertEquals(27992443, lowestLocationNumber);
    }
}