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
            new Day1().getLocations();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void getLocations() throws URISyntaxException {
        var fileName = "Day1.txt";
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
        try (Stream<String> linestream = Files.lines(path)) {
            var lines = linestream.toList();

            // Split the lines into two separate lists
            var firsts = new ArrayList<Integer>();
            var seconds = new ArrayList<Integer>();

            for (String line : lines) {
                var x = line.split(" {3}");
                firsts.add(Integer.parseInt(x[0]));
                seconds.add(Integer.parseInt(x[1]));
            }

            // Sort the lists in numerical order
            firsts.sort(Integer::compareTo);
            seconds.sort(Integer::compareTo);

            // Calculate the distance between each pair
            var totalDistance = 0;
            for (int i = 0; i < firsts.size(); i++) {
                var first = firsts.get(i);
                var second = seconds.get(i);
                var distance = Math.abs(first - second);
                totalDistance += distance;
            }

            System.out.println(totalDistance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}