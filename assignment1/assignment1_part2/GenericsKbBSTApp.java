//Thabo Dladla

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericsKbBSTApp{
    static BinarySearchTree<InnerGeneric> generic = new BinarySearchTree<>();
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

    //public static void load(String term,String sentence ,double confidenceScore){
    //InnerGeneric data = new InnerGeneric(term, sentence, confidenceScore);
    //generic.insert(data);
    //updateEntry(term, sentence, confidenceScore);//have to return to fix
    //}

    public static void insert(String term, String sentence, double confidenceScore) {
        if (findData(term) != null) {
            if (new InnerGeneric(term, sentence, confidenceScore).confidenceScore > findData(term).confidenceScore) {
                generic.insert(new InnerGeneric(term, sentence, confidenceScore));
            }
        } else {
            generic.insert(new InnerGeneric(term, sentence, confidenceScore));}
    }



    //finding a statement using the term alone.
    public static InnerGeneric findData(String term){
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
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            switch (read.nextInt()){
                case 1:
                    System.out.print("Enter file name:");
                    read.nextLine();
                    String name = read.nextLine();

                    Scanner keyboard ;
                    try{
                        keyboard =  new Scanner((new FileInputStream(name)));
                        while(keyboard.hasNextLine()){
                            String entry = keyboard.nextLine(); //each entry is read from txt file
                            String [] entryArray = entry.split("\t");
                            String term = entryArray[0].trim();
                            String sentence = entryArray[1].trim();
                            double confidenceScore = Double.parseDouble(entryArray[2].trim());
                            //updateEntry(term, sentence, confidenceScore);
                            insert(term, sentence ,confidenceScore);
                        }
                        System.out.println("Knowledge base loaded successfully");
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("File not found");
                        //System.exit(0);
                    }
                    break;
                case 2:
                    read.nextLine();
                    System.out.print("Enter the term: ");
                    String termCapture = read.nextLine();
                    System.out.print("Enter the statement: ");
                    String sentenceCapture = read.nextLine();
                    System.out.print("Enter the confidence score: ");
                    double score = read.nextDouble();
                    read.nextLine();
                    
                    if(findData(termCapture)==null){
                        insert(termCapture, sentenceCapture,score);
                        System.out.println("Statement for term "+termCapture+" has been updated.");}
                    else if(findData(termCapture) != null) {
                        if (new InnerGeneric(termCapture, sentenceCapture, score).confidenceScore > findData(termCapture).confidenceScore) {
                            insert(termCapture, sentenceCapture, score);
                            System.out.println("Statement for term " + termCapture + " has been updated.");
                        } else {
                            System.out.println("Statement for term " + termCapture + " has not be updated,confidence score is lower than existing");
                        }
                    }
                    break; 
                case 3:
                    read.nextLine();
                    System.out.print("Enter the term to search: ");
                    InnerGeneric temp = findData(read.nextLine());
                    if(temp!=null){
                        //prints out the statement and confidence score after searching using term
                        System.out.println("Statement found: "+temp.sentence +" (Confidence score: "+ temp.confidenceScore +")");
                    }else{System.out.println("Term is not on knowledge base");}
                    break;
                    case 4:
                        read.nextLine();
                        System.out.print("Enter the term: ");
                        String string = (read.nextLine());
                        System.out.print("Enter the statement to search for: ");
                        String searchSentence = read.nextLine().trim();
                        if(findData(string)!=null) {
                            if (findData(string).sentence.equals(searchSentence)) {
                                System.out.println("The statement was found and has a confidence score of " + findData(string).confidenceScore);
                            }
                        }
                        else{
                            System.out.println("Statement not found as term does not exit in knowledge base.");
                        }
                        break; 
                    case 5:
                        app =false;
                        System.exit(0);
                        break; 
                    default:
                        System.out.println("Wrong input please try again");//when use user enters number bigger than 5
                        break;
            }
        }
    }
}







            
            

            
            
            
            
            
            