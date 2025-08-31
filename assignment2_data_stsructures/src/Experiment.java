import java.io.*;
import java.util.*;

public class Experiment {

    // Generate and save best-case order
    public void generateBestCase(List<String> sortedData, String filename) throws IOException {
        List<String> bestCaseOrder = new ArrayList<>();
        createBalancedOrder(sortedData, 0, sortedData.size() - 1, bestCaseOrder);
        saveToFile(bestCaseOrder, filename);
    }

    private void createBalancedOrder(List<String> data, int start, int end, List<String> result) {
        if (start > end) return;

        int mid = (start + end) / 2;
        result.add(data.get(mid)); // Add middle element
        createBalancedOrder(data, start, mid - 1, result); // Left half
        createBalancedOrder(data, mid + 1, end, result);   // Right half
    }

    // Generate and save worst-case order (ascending)
    public void generateWorstCase(List<String> data, String filename) throws IOException {
        Collections.sort(data);
        saveToFile(data, filename);
    }

    // Generate and save average-case order (randomized)
    public void generateAverageCase(List<String> data, String filename) throws IOException {
        List<String> randomizedData = new ArrayList<>(data);
        Collections.shuffle(randomizedData); // Randomize the data
        saveToFile(randomizedData, filename);
    }

    // Save a list of strings to a text file
    public void saveToFile(List<String> data, String filename) throws IOException {
        try (BufferedWriter buff = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                buff.write(line);
                buff.newLine();
            }
        }
    }

    // Automate experiments, returning all generated filenames
    public List<String> automateExperiments(List<String> subset) throws IOException {
        List<String> cases = new ArrayList<>();

        // Generate all cases
        String bestFilename = "bestcase.txt";
        generateBestCase(subset, bestFilename);
        cases.add(bestFilename);

        String avgFilename = "averagecase.txt";
        generateAverageCase(subset, avgFilename);
        cases.add(avgFilename);

        String worstFilename = "worstcase.txt";
        generateWorstCase(subset, worstFilename);
        cases.add(worstFilename);

        return cases;
    }

    // Process a single file, generating case files
    public List<String> experimentMethod(String file1) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            List<String> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();

            // Generate cases
            List<String> subsetCases = automateExperiments(data);

            // Log the generated filenames
            for (String file : subsetCases) {
                System.out.println("Generated file: " + file);
            }
            return subsetCases;
        } catch (IOException e) {
            System.err.println("There was an error while reading in your experimental file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
