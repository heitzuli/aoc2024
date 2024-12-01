package com.heitzuli;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}