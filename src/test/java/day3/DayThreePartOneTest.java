package day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.FileUtil;

class DayThreePartOneTest {

    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day3/day3example.txt");
        DayThree dayThree = new DayThree();

        int sum = dayThree.calcuateSumOfPartNumbers(rows);
        
        assertEquals(4361, sum);
        System.out.println(sum);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day3/day3input.txt");
        DayThree dayThree = new DayThree();

        int sum = dayThree.calcuateSumOfPartNumbers(rows);

        assertEquals(540212, sum);
        System.out.println(sum);
    }
}