/*A class that implemets user interface
and interacts with BST to store general information from a file 
and retrieve for the the user
Thabo Dladla**/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericsKbBSTApp{
    static BinarySearchTree<InnerGeneric> generic = new BinarySearchTree<>();
    
    /*Inner class that will represent the instance of data that will be stored in BST**/
    public static class InnerGeneric implements Comparable<InnerGeneric>{
        String term ,sentence;
        double confidenceScore;

        public InnerGeneric(String term, String sentence, double confidenceScore) {
            this.term = term;
            this.sentence = sentence;
            this.confidenceScore = confidenceScore;
        }

        @Override/*redefine the compareTo method
        to make sure it uses terms to compare in the binary search tree**/
        public int compareTo(InnerGeneric other){
            return this.term.compareTo(other.term);
        }
    }

    public static void insert(String term, String sentence, double confidenceScore) {
        if (findData(term) != null) {
            if (new InnerGeneric(term, sentence, confidenceScore).confidenceScore > findData(term).confidenceScore) {
                generic.insert(new InnerGeneric(term, sentence, confidenceScore));
            }
        } else {
            generic.insert(new InnerGeneric(term, sentence, confidenceScore));}
    }



    
    public static InnerGeneric findData(String term){
        //find the data using the term.
        String string = "";
        double emptyDouble = 0.0;
        BinaryTreeNode<InnerGeneric> treeNode = generic.find(new InnerGeneric(term,string,emptyDouble));
        if (treeNode == null) {
            return null;
        }
        else{
            return treeNode.data;
        }
    }


    public static void main (String[] args){
        Scanner read =  new Scanner(System.in);
        Boolean app =true;

        while (app){
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Search for a statement in the knowledge base by term and sentence");
            System.out.println("5. Quit\n");
            System.out.print("Enter your choice: ");

            switch (read.nextInt()){
                case 1:
                    System.out.print("\nEnter file name:");
                    read.nextLine();
                    String name = read.nextLine();

                    Scanner keyboard ;
                    try{
                        keyboard =  new Scanner((new FileInputStream(name)));
                        while(keyboard.hasNextLine()){
                            String entry = keyboard.nextLine();
                            String [] entryArray = entry.split("\t");
                            String term = entryArray[0].trim();
                            String sentence = entryArray[1].trim();
                            double confidenceScore = Double.parseDouble(entryArray[2].trim());
                            insert(term, sentence ,confidenceScore);
                        }
                        System.out.println("\nKnowledge base loaded successfully.\n");
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("\nFile not found.\n");
                    }
                    break;
                case 2:
                    read.nextLine();
                    System.out.print("\nEnter the term: ");
                    String termCapture = read.nextLine();
                    System.out.print("Enter the statement: ");
                    String sentenceCapture = read.nextLine();
                    System.out.print("Enter the confidence score: ");
                    double score = read.nextDouble();
                    read.nextLine();
                    
                    if(findData(termCapture)==null){
                        insert(termCapture, sentenceCapture,score);
                        System.out.println("\nStatement for term "+termCapture+" has been updated.\n");}
                    else if(findData(termCapture) != null) {
                        InnerGeneric genericTemp = new InnerGeneric(termCapture, sentenceCapture, score);
                        if (genericTemp.confidenceScore > findData(termCapture).confidenceScore) {
                            //only insert new statement if the user's statement has a higher confidence score than existing one's.
                            insert(termCapture, sentenceCapture, score);
                            System.out.println("\nStatement for term " + termCapture + " has been updated.\n");
                        } else {
                            System.out.println("\nStatement for term " + termCapture + " has not be updated, the term's confidence score is lower than existing.\n");
                            System.out.println("\nCurrentky the existing information about that term is: "+findData(termCapture).sentence+"\nwith a confidence score of : "+findData(termCapture).confidenceScore);
                            System.out.println("\nI challenge you to find a fact with greater degree of confidence, all the best!\n");
                        }
                    }
                    break; 
                case 3:
                    //Search by term, and print out statement and confidence Score.
                    read.nextLine();
                    System.out.print("\nEnter the term to search: ");
                    InnerGeneric temp = findData(read.nextLine());
                    if(temp!=null){
                        System.out.println("\nStatement found: "+temp.sentence +" (Confidence score: "+ temp.confidenceScore +")\n");
                    }else{System.out.println("\nTerm is not on knowledge base.\n");}
                    break;
                    case 4:
                        read.nextLine();
                        System.out.print("\nEnter the term: ");
                        String string = (read.nextLine());
                        System.out.print("Enter the statement to search for: ");
                        String searchSentence = read.nextLine().trim();
                        if(findData(string)!=null) {
                            if (findData(string).sentence.equals(searchSentence)) {
                                System.out.println("\nThe statement was found and has a confidence score of " + findData(string).confidenceScore+".\n");
                            }else{System.out.println("\nTerm was found but the sentence you entered does not match sentence in the knowledge base.\n");}
                        }
                        else{
                            System.out.println("\nStatement not found as term does not exist in knowledge base.\n");
                        }
                        break; 
                    case 5:
                        app =false;
                        System.exit(0);
                        break; 
                    default:
                        System.out.println("\nWrong input please try again, chose from 1 to 5\n");
                        break;
            }
        }
    }
}







            
            

            
            
            
            
            
            