package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFive {

    private List<Long> seedList = new ArrayList<>();
    private List<XtoYRange> seedToSoilRange = new ArrayList<>();
    private List<XtoYRange> soilToFertilizerRange = new ArrayList<>();
    private List<XtoYRange> fertilizerToWaterRange = new ArrayList<>();
    private List<XtoYRange> waterToLightRangep = new ArrayList<>();
    private List<XtoYRange> lightToTemperatureRange = new ArrayList<>();
    private List<XtoYRange> temperatureToHumidityRange = new ArrayList<>();
    private List<XtoYRange> humidityToLocationRange = new ArrayList<>();

    public long calculateLowestLocation(List<String> rows) {
        populateData(rows);

        long lowestLocationNumber = Integer.MAX_VALUE;
        for (Long seed : seedList) {
            long locationNumber = findLowestLocationForSeed(seed);
            if (locationNumber < lowestLocationNumber) {
                lowestLocationNumber = locationNumber;
            }
        }
        return lowestLocationNumber;
    }

    public long calculateLowestLocationPart2(List<String> rows) {
        populateData(rows);

        for (long i = 0; i < 100000000000L; i++) {
            if (doesLocationExist(i)) {
                return i;
            }
        }
        return -1;
    }

    private long findLowestLocationForSeed(Long seed) {
        long soil = getCorrespondingNumber(seedToSoilRange, seed);
        long fertilizer = getCorrespondingNumber(soilToFertilizerRange, soil);
        long water = getCorrespondingNumber(fertilizerToWaterRange, fertilizer);
        long light = getCorrespondingNumber(waterToLightRangep, water);
        long temperature = getCorrespondingNumber(lightToTemperatureRange, light);
        long humidity = getCorrespondingNumber(temperatureToHumidityRange, temperature);
        long location = getCorrespondingNumber(humidityToLocationRange, humidity);
        return location;
    }

    private long getCorrespondingNumber(List<XtoYRange> ranges, long key) {
        for (XtoYRange xtoYRange : ranges) {
            long sourceOffset = key - xtoYRange.source;
            if (sourceOffset <= xtoYRange.range() && sourceOffset >= 0) {
                return xtoYRange.dest + sourceOffset;
            }
        }
        return key;
    }

    private boolean doesLocationExist(long locationNum) {
        long humidity = getCorrespondingNumberBackwards(humidityToLocationRange, locationNum);
        long temperature = getCorrespondingNumberBackwards(temperatureToHumidityRange, humidity);
        long light = getCorrespondingNumberBackwards(lightToTemperatureRange, temperature);
        long water = getCorrespondingNumberBackwards(waterToLightRangep, light);
        long fertilizer = getCorrespondingNumberBackwards(fertilizerToWaterRange, water);
        long soil = getCorrespondingNumberBackwards(soilToFertilizerRange, fertilizer);
        long seed = getCorrespondingNumberBackwards(seedToSoilRange, soil);

        for (int i = 0; i < seedList.size(); i += 2) {
            Long start = seedList.get(i);
            Long offset = seedList.get(i + 1);

            if (seed >= start && seed <= start + offset) {
                return true;
            }
        }

        return false;
    }

    private long getCorrespondingNumberBackwards(List<XtoYRange> ranges, long key) {
        for (XtoYRange xtoYRange : ranges) {
            long destOffset = key - xtoYRange.dest;
            if (destOffset <= xtoYRange.range() && destOffset >= 0) {
                return xtoYRange.source + destOffset;
            }
        }
        return key;
    }

    private void populateData(List<String> rows) {
        List<XtoYRange> currentRange = null;
        for (String row : rows) {
            if (row.startsWith("seeds:")) {
                seedList = Arrays.stream(row.split(":")[1].trim().split(" "))
                        .filter(p -> !p.isEmpty())
                        .mapToLong(Long::parseLong).boxed().toList();
            } else if ("seed-to-soil map:".equals(row)) {
                currentRange = seedToSoilRange;
            } else if ("soil-to-fertilizer map:".equals(row)) {
                currentRange = soilToFertilizerRange;
            } else if ("fertilizer-to-water map:".equals(row)) {
                currentRange = fertilizerToWaterRange;
            } else if ("water-to-light map:".equals(row)) {
                currentRange = waterToLightRangep;
            } else if ("light-to-temperature map:".equals(row)) {
                currentRange = lightToTemperatureRange;
            } else if ("temperature-to-humidity map:".equals(row)) {
                currentRange = temperatureToHumidityRange;
            } else if ("humidity-to-location map:".equals(row)) {
                currentRange = humidityToLocationRange;
            } else if (!row.isEmpty()) {
                List<Long> dsr = Arrays.stream(row.trim().split(" "))
                        .filter(p -> !p.isEmpty())
                        .mapToLong(Long::parseLong).boxed().toList();
                currentRange.add(new XtoYRange(dsr.get(0), dsr.get(1), dsr.get(2)));
            }
        }
    }

    record XtoYRange(long dest, long source, long range) {
    }
}
