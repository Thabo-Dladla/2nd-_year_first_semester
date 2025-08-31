/**
 * A program that tests the performance of the AVL Tree
 * to determine if AVL trees really do balance nodes and provide good performance irrespective of the size of the data.
 * The program reads in two files , one containing the data and the other containing queries for the general data
 * The output is then analyzed and compared to the theoretical outcome
 *author:Thabo Dladla
 * 27 March 2025
 */
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class GenericsKbAVLApp {

    /**
     * The main method of the application.
     * Handles input, executes the reader method, and initiates experiments if required.
     *
     * @param args Command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // Load the main data file and the query file into the AVL Tree
        reader("GenericsKB.txt", "GenericsKB-queries.txt");

        // Prompt user for experiments
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDo you want to do the experiment? (y/n)");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            runExperiments();
        }
        scanner.close();
    }

    /**
     * Reads data from the specified file and processes queries against an AVL Tree.
     * Performs operations such as insertion of elements and searching for queries.
     *
     * @param filename The name of the data file to load into the AVL Tree
     * @param queries  The name of the query file to search for in the AVL Tree
     */
    public static void reader(String filename, String queries) {
        AVLTree<String> avl = new AVLTree<>();
        try {
            // Insert data from the main file into the AVL Tree
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String statement;
            while ((statement = reader.readLine()) != null) {
                avl.incrementer(); // Count operations
                avl.insert(statement);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("There was an error while reading in the file", e);
        }

        try {
            // Process each query and search in the AVL Tree
            BufferedReader buff = new BufferedReader(new FileReader(queries));
            String statement2;
            while ((statement2 = buff.readLine()) != null) {
                avl.incrementer();
                statement2 = statement2.trim();
                BinaryTreeNode<String> node = avl.find(statement2);

                if (filename.equalsIgnoreCase("GenericsKB.txt")) {
                    if (node != null) {
                        String results = node.data;
                        System.out.println(statement2 + ": " + results.split("\t")[1] + " (" + results.split("\t")[2] + ")");
                    } else {
                        System.out.println("Term not found: " + statement2);
                    }
                }
            }
            buff.close();
        } catch (IOException e) {
            throw new RuntimeException("There was an error while reading in the queries file", e);
        }

        // Print operational statistics
        System.out.println("Total number of comparisons made: " + avl.getOpCount());
        System.out.println("Total number of insert operations: " + avl.getInsertCount());
        System.out.println("Total number of search operations: " + avl.getSearchCount());
    }

    /**
     * Executes experiments for various subsets of data sizes.
     * Tests AVL Tree operations using different input cases (best, average, worst).
     */
    public static void runExperiments() {
        Experiment experiment = new Experiment();
        List<String> data;

        // Process subsets of 5 lines
        data = experiment.experimentMethod("random5.txt");
        if (data != null && data.size() == 3) {
            System.out.println("\nFor 5 lines, \nCase 1 is:\n");
            reader(data.get(0), "GenericsKB-queries.txt");
            System.out.println("\nCase 2 is:\n");
            reader(data.get(1), "GenericsKB-queries.txt");
            System.out.println("\nCase 3 is:\n");
            reader(data.get(2), "GenericsKB-queries.txt");
        } else {
            System.out.println("You don't have the experimentation file");
            System.exit(0);
        }

        // Process subsets of 50 lines
        data = experiment.experimentMethod("random50.txt");
        if (data != null && data.size() == 3) {
            System.out.println("\nFor 50 lines,\nCase 1 is:\n");
            reader(data.get(0), "GenericsKB-queries.txt");
            System.out.println("\nCase 2 is:\n");
            reader(data.get(1), "GenericsKB-queries.txt");
            System.out.println("\nCase 3 is:\n");
            reader(data.get(2), "GenericsKB-queries.txt");
        } else {
            System.out.println("You don't have the experimentation file");
            System.exit(0);
        }

        // Process subsets of 500 lines
        data = experiment.experimentMethod("random500.txt");
        if (data != null && data.size() == 3) {
            System.out.println("\nFor 500 lines,\nCase 1 is:\n");
            reader(data.get(0), "GenericsKB-queries.txt");
            System.out.println("\nCase 2 is:\n");
            reader(data.get(1), "GenericsKB-queries.txt");
            System.out.println("\nCase 3 is:\n");
            reader(data.get(2), "GenericsKB-queries.txt");
        } else {
            System.out.println("You don't have the experimentation file");
            System.exit(0);
        }

        // Process subsets of 5000 lines
        data = experiment.experimentMethod("random5000.txt");
        if (data != null && data.size() == 3) {
            System.out.println("\nFor 5000 lines,\nCase 1 is:\n");
            reader(data.get(0), "GenericsKB-queries.txt");
            System.out.println("\nCase 2 is:\n");
            reader(data.get(1), "GenericsKB-queries.txt");
            System.out.println("\nCase 3 is:\n");
            reader(data.get(2), "GenericsKB-queries.txt");
        } else {
            System.out.println("You don't have the experimentation file");
            System.exit(0);
        }

        // Process subsets of 50000 lines
        data = experiment.experimentMethod("random50000.txt");
        if (data != null && data.size() == 3) {
            System.out.println("\nFor 50000 lines,\nCase 1 is:\n");
            reader(data.get(0), "GenericsKB-queries.txt");
            System.out.println("\nCase 2 is:\n");
            reader(data.get(1), "GenericsKB-queries.txt");
            System.out.println("\nCase 3 is:\n");
            reader(data.get(2), "GenericsKB-queries.txt");
        } else {
            System.out.println("You don't have the experimentation file");
            System.exit(0);
        }
    }
}
