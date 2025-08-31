/*A class that implemets user interface
and stores information from a file in an array
and retrieve for the the user
Thabo Dladla**/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericsKbArrayApp {
    private String term;
    private String sentence ;
    private double confidenceScore;
    private static GenericsKbArrayApp[] base = new GenericsKbArrayApp[100000];
    private static int count = 0;

    public GenericsKbArrayApp(String term, String sentence , double confidenceScore){
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }
    public void set(String sentence, double confidenceScore){
        this.confidenceScore = confidenceScore;
        this.sentence = sentence;
    }
    public String getTerm(){
        return term;
    }
    public String getSentence(){
        return sentence;
    }
    public double getConfidenceScore(){
        return confidenceScore;
    }
    
    
    /*search for term in array
    and return index.**/
    public static int index(String term){
        for (int i = 0; i < base.length; i++){
            if((base[i]!=null) && (base[i].getTerm().equalsIgnoreCase(term))){
                return i;
            }
        }
        return -1;
    }
    
    public static void load(String term, String sentence, double confidenceScore){
      int termIndex = index(term);
      if(termIndex!=-1){
        if (confidenceScore>base[termIndex].getConfidenceScore()){
          base[termIndex].set(sentence, confidenceScore);
          }
        }
       else{
         base[count++] = new GenericsKbArrayApp(term, sentence, confidenceScore);
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
                        
                        String [] entryArray = entry.split("\t");//split the line by tabs
                        String term = entryArray[0].trim();
                        String sentence = entryArray[1].trim();
                        double confidenceScore = Double.parseDouble(entryArray[2].trim());
                        load(term, sentence ,confidenceScore);
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
                
                int tempraryIndex = index(termCapture);
                
                if(tempraryIndex!=-1){
                  if (score>base[tempraryIndex].getConfidenceScore()){
                    load(termCapture, sentenceCapture ,score);
                    System.out.println("\nStatement for term " + termCapture + " has been updated.\n");
                    }
                  else{System.out.println("\nStatement for term " + termCapture + " has not be updated, the term's confidence score is lower than existing confidence score.\n");
                       System.out.println("\nCurrentky the existing information about that term is: "+base[tempraryIndex].getSentence()+"\nwith a confidence score of : "+base[tempraryIndex].getConfidenceScore());
                       System.out.println("\nI challenge you to find a fact with greater degree of confidence, all the best!\n");
                       }
                  }
                else{
                      load(termCapture, sentenceCapture ,score);
                      System.out.println("\nStatement for term " + termCapture + " has been updated.\n");
                    }
                break;
            case 3:
                read.nextLine();
                System.out.print("\nEnter the term to search: ");
                int index = index(read.nextLine());
                if(index!=-1){
                    System.out.println("\nStatement found: "+base[index].getSentence() +" (Confidence score: "+ base[index].getConfidenceScore() +")\n");//prints out the statement and confidence score after searching by term using the index of the term in the knowledge base

                }else{System.out.println("\nStatement is not found in knowledge base.\n");}
                break;
            case 4:
                read.nextLine();
                System.out.print("\nEnter the term: ");
                int index2 = index(read.nextLine());
                System.out.print("Enter the statement to search for: ");
                String searchSentence = read.nextLine().trim();
                if(index2!=-1){
                    if(base[index2].getSentence().equalsIgnoreCase(searchSentence)){
                        System.out.println("\nThe statement was found and has a confidence score of " +base[index2].getConfidenceScore()+".\n");
                    }else{System.out.println("\nTerm was found but the sentence does not match sentence in the knowledge base.\n");}
                }else{System.out.println("\nStatement not found as term does not exit in knowledge base.\n");}
                break;
            case 5:
                app = false;
                System.exit(0);
                break;
            default:
                System.out.println("\nYour have enetered an ivalid input ,please try again.");
                System.out.println("Please note that you have options 1 to 5.\n");
                break;




        }

    }}
}