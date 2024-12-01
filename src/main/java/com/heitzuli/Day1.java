package com.heitzuli;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
            for (Integer leftValue : pair.left) {
                totalSimilarityScore += leftValue * calculateOccurrences(leftValue, pair.right);
            }
            return totalSimilarityScore;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer calculateOccurrences(Integer valueToCheck, List<Integer> list) {
        var occurrences = 0;
        for (Integer value : list) {
            if (Objects.equals(value, valueToCheck)) {
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
            pair.left().sort(Integer::compareTo);
            pair.right().sort(Integer::compareTo);

            // Calculate the distance between each pair
            var totalDistance = 0;
            for (int i = 0; i < pair.left().size(); i++) {
                var left = pair.left().get(i);
                var right = pair.right().get(i);
                var distance = Math.abs(left - right);
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
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        for (String line : lines) {
            var x = line.split(" {3}");
            left.add(Integer.parseInt(x[0]));
            right.add(Integer.parseInt(x[1]));
        }
        return new Pair(left, right);
    }


    private record Pair(ArrayList<Integer> left, ArrayList<Integer> right) {
    }
}