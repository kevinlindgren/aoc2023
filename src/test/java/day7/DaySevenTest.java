package day7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.FileUtil;

class DaySevenTest {
    @Test
    void example1() {
        List<String> rows = FileUtil.readFile("day7/day7example.txt");
        DaySeven daySeven = new DaySeven();

        long sum = daySeven.summ(rows);
        assertEquals(6440, sum);
    }

    @Test
    void input1() {
        List<String> rows = FileUtil.readFile("day7/day7input.txt");
        DaySeven daySeven = new DaySeven();

        long sum = daySeven.summ(rows);
        assertEquals(247961593, sum);
    }

    @Test
    void example2() {
        List<String> rows = FileUtil.readFile("day7/day7example.txt");
        DaySeven daySeven = new DaySeven();

        long sum = daySeven.summ(rows);
        assertEquals(5905, sum);
    }

    @Test
    void input2() {
        List<String> rows = FileUtil.readFile("day7/day7input.txt");
        DaySeven daySeven = new DaySeven();

        long sum = daySeven.summ(rows);
        assertEquals(248750699, sum);
    }

}