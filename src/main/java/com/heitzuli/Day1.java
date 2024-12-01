package com.heitzuli;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        try {
            System.out.println(new Day1().getTotalDistance());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Integer getTotalDistance() throws URISyntaxException {
        var fileName = "Day1.txt";
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
        try (Stream<String> linestream = Files.lines(path)) {
            Pair pair = getPair(linestream);

            // Sort the lists in numerical order
            pair.firsts().sort(Integer::compareTo);
            pair.seconds().sort(Integer::compareTo);

            // Calculate the distance between each pair
            var totalDistance = 0;
            for (int i = 0; i < pair.firsts().size(); i++) {
                var first = pair.firsts().get(i);
                var second = pair.seconds().get(i);
                var distance = Math.abs(first - second);
                totalDistance += distance;
            }

            return totalDistance;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Pair getPair(Stream<String> linestream) {
        var lines = linestream.toList();

        // Split the lines into two separate lists
        var firsts = new ArrayList<Integer>();
        var seconds = new ArrayList<Integer>();

        for (String line : lines) {
            var x = line.split(" {3}");
            firsts.add(Integer.parseInt(x[0]));
            seconds.add(Integer.parseInt(x[1]));
        }
        Pair result = new Pair(firsts, seconds);
        return result;
    }

    private record Pair(ArrayList<Integer> firsts, ArrayList<Integer> seconds) {
    }
}