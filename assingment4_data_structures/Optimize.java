/**
* A class that computes weights that optimise insertion of a specific list of names in an LPHashTable.
* Insertion is optimised if it uses the least number of probes.
* @author Thabo Dladla
* @version 18/04/2025
*/import java.io.*;
import java.util.*;

public class Optimize {
    //My Class constants
    private static final int WEIGHT_COUNT = 9;
    private static final double TOTAL_COMBINATIONS = Math.pow(5,WEIGHT_COUNT);//these are the total possible combinations for each weight which is equal to 5*5*5*5....for 9 total weights that is 5^9
    
    public static void main(String[] args) {
        String[] studentNumbers = readIn("mydata.txt");
        if ( studentNumbers== null) {System.out.println("The array has no student numbers!");
            return;
        }
        optimizer(studentNumbers);}
    
   
    /**
    * this methods reads in the student numbers from the txt file "mydata.txt"
    * @param fileName is the name of the fileto be read in
    * @return the array containing the student names
    */
private static String[] readIn(String fileName) {
        String[] usernames = new String[36];
        int index = 0;
        Scanner scanner =null;

        try{scanner = new Scanner(new FileInputStream(fileName));}//try opening the file
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName+" was not found");
            return null;}
        while(scanner.hasNextLine() && index < usernames.length) {
                String name = scanner.nextLine().trim();
                usernames[index++] =name;}
                scanner.close();
                return usernames;}
    
    
    /**
    * this methods write to the text file "results.txt" and  thereafter prints out the results to screen as well
    * the results are the minimum no. of probes and the number of combinations that yileded this minimumnumber
    * @param minimumProbes is the minimum no. of probes
    * @param combinationCount is the number of combinations that yileded this minimumnumber
    */
    private static void output(int minimumProbes, int combinationCount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
            String result = minimumProbes + " " + combinationCount;
            writer.write(result);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Could not write to: results.txt");
            return;
        }
    }
    
    
    /**
    * this methods generates new weight for each combination 
    * @param combination is the combination for which new weight will be computed 
    * @return a new array containing the new weights
    */
    private static int[] weightGenerator(int combination) {
        int[] weights = new int[WEIGHT_COUNT];
        for (int n = 0; n < weights.length;n++)
        {   weights[n] =combination % 5;
            combination=combination/5;
        }
        return weights;
    }
    
    /**
    * this methods computes the total numberof probes done linearly when insterting the usernames/student numbers into the hashtable
    * @param hashTable is the table to inserted into
    * @param students is the array containg the usernames to be inserted into the hash tabler
    * @return the total linear probes when insterting the usernames/student numbers into the hashtable
    */
    private static int totalProbes(LPHashTable hashTable,String[] students) {
        int counter=0;
        for (String student : students) {
            hashTable.insert(student);
            counter =counter + hashTable.getProbeCount();
            hashTable.resetProbeCount();
        }
        return counter;
    }
    
    /**
    * this methods computes the optimal combinations that results in the least number of probes for linear probing, and also gets those probing numbers
    * it then prints the results out and writes them to a txt file 
    * @param usernames is an array containg the usernames that were read in
    */
    private static void optimizer(String [] usernames)
    {
        int minimumProbes= 1000000, optimalCombinationCount = 0;

        for (int combination = 0; combination < TOTAL_COMBINATIONS ; combination++)
        {
            LPHashTable hashTable = new LPHashTable(37);
            int[] newWeights = weightGenerator(combination);//new weights combination generated for each combination
            
            hashTable.setWeights(newWeights);

            int totalProbes = totalProbes(hashTable, usernames);

            if (totalProbes < minimumProbes) {
                minimumProbes = totalProbes;
                optimalCombinationCount = 1;//if there is a smaller nummber number of probes then the optimalCombinationCount will be reset to start over
            } else if (totalProbes == minimumProbes) {optimalCombinationCount++;}//incrementing when there is more than one combination that produces the smallest probe count
        }
        output(minimumProbes,optimalCombinationCount);
        }
 }
    
    
    
    
    
    
    
    
    
    
    
  