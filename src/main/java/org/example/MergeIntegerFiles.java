package org.example;
import java.io.*;
import java.util.*;

public class MergeIntegerFiles {
    public static void main(String[] args) {
        // File names
        String file1 = "input1.txt";
        String file2 = "input2.txt";
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        // Read integers from input files
        List<Integer> integersFromFile1 = readIntegersFromFile(file1);
        List<Integer> integersFromFile2 = readIntegersFromFile(file2);

        // Merge files and write to mergedFile
        mergeFiles(integersFromFile1, integersFromFile2, mergedFile);

        // Find common integers and write to commonFile
        List<Integer> commonIntegers = findCommonIntegers(integersFromFile1, integersFromFile2);
        writeIntegersToFile(commonIntegers, commonFile);

        System.out.println("Processing completed.");
    }

    private static List<Integer> readIntegersFromFile(String filename) {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    integers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid integer in file " + filename + ": " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return integers;
    }

    private static void mergeFiles(List<Integer> list1, List<Integer> list2, String outputFilename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (Integer number : list1) {
                writer.write(number.toString());
                writer.newLine();
            }
            for (Integer number : list2) {
                writer.write(number.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputFilename);
        }
    }

    private static List<Integer> findCommonIntegers(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }

    private static void writeIntegersToFile(List<Integer> integers, String outputFilename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (Integer number : integers) {
                writer.write(number.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputFilename);
        }
    }
}
