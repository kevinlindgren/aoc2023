package day1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.FileUtil;

class DayOneTest {
    
    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day1/day1example.txt");
        DayOne dayOne = new DayOne();
        
        int sum = dayOne.calculateCalibrationValue(rows);
        assertEquals(142, sum);
        System.out.println(sum);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day1/day1input.txt");
        DayOne dayOne = new DayOne();

        int sum = dayOne.calculateCalibrationValue(rows);
        System.out.println(sum);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day1/day1part2example.txt");
        DayOne dayOne = new DayOne();

        int sum = dayOne.calculateCalibrationValuePartTwo(rows);
        assertEquals(281, sum);
        System.out.println(sum);
    }
    
    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day1/day1input.txt");
        DayOne dayOne = new DayOne();

        int sum = dayOne.calculateCalibrationValuePartTwo(rows);
        System.out.println(sum);
    }
}