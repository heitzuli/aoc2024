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
        var fileName = "Day1.txt";
        try {
            System.out.println(new Day1().getTotalDistance(fileName));
            System.out.println(new Day1().getTotalSimilarityScore(fileName));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Integer getTotalSimilarityScore(String fileName) throws URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());

        try (Stream<String> linestream = Files.lines(path)) {
            Pair pair = getPair(linestream);
            var totalSimilarityScore = 0;
            for (Integer number : pair.firsts) {
                totalSimilarityScore += number * calculateOccurrences(number, pair.seconds);
            }
            return totalSimilarityScore;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer calculateOccurrences(Integer number, ArrayList<Integer> seconds) {
        var occurrences = 0;
        for (Integer second : seconds) {
            if (Objects.equals(second, number)) {
                occurrences++;
            }
        }
        return occurrences;
    }

    private Integer getTotalDistance(String fileName)throws URISyntaxException {
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