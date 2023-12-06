package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySix {

    public long calculateAmountOfPossibleWins(List<String> rows) {
        String timeString = rows.get(0).split(":")[1];
        String distanceString = rows.get(1).split(":")[1];

        List<Integer> times = Arrays.stream(timeString.trim().split(" "))
                .filter(p -> !p.isEmpty())
                .mapToInt(Integer::parseInt).boxed().toList();
        List<Integer> distances = Arrays.stream(distanceString.trim().split(" "))
                .filter(p -> !p.isEmpty())
                .mapToInt(Integer::parseInt).boxed().toList();

        List<Integer> winsInRaces = new ArrayList<>();
        for (int i = 0; i < distances.size(); i++) {
            Integer time = times.get(i);
            Integer distance = distances.get(i);

            int totalWinsInRace = 0;
            for (int j = 0; j < time; j++) { //ignore last press its just 0 anyway
                int timeLeftOfRace = time - j;
                int distanceTravelled = j * timeLeftOfRace;
                if (distanceTravelled > distance) {
                    totalWinsInRace += 1;
                }
            }
            winsInRaces.add(totalWinsInRace);
        }

        int sum = 1;
        for (Integer winsInRace : winsInRaces) {
            sum *= winsInRace;
        }
        return sum;
    }

    public long calculateAmountOfPossibleWinsPart2(List<String> rows) {
        String timeString = rows.get(0).split(":")[1];
        String distanceString = rows.get(1).split(":")[1];

        Long time = Long.parseLong(timeString.replaceAll(" ", ""));
        Long distance = Long.parseLong(distanceString.replaceAll(" ", ""));

        int totalWinsInRace = 0;
        for (int j = 0; j < time; j++) { //ignore last press its just 0 anyway
            long timeLeftOfRace = time - j;
            long distanceTravelled = j * timeLeftOfRace;
            if (distanceTravelled > distance) {
                totalWinsInRace += 1;
            }
        }

        return totalWinsInRace;
    }
}
