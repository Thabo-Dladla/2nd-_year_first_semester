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

    public static int index(String term){
        //find index of given term and return it ...return -1 if not found.
        for (int i = 0; i < base.length; i++){
            if((base[i]!=null) && (base[i].getTerm().equalsIgnoreCase(term))){
                return i;
            }
        }
        return -1;
    }
    public static String updateEntry(String term, String sentence, double confidenceScore){
        int termIndex = index(term);
        if((termIndex!=-1) && (base[termIndex]!=null)){
            if (confidenceScore>base[termIndex].getConfidenceScore()){
                //updating the sentence and term if the confidence score is higher than existing one
                base[termIndex].set(sentence, confidenceScore);
                return "Statement for term "+term+" has been updated.";
            }
            else{
                return "Confidence score is lower than existing one cannot add";}
        }else{return "non existent term!";
    }}
    
    public static void load(String term, String sentence, double confidenceScore){
      base[count++] = new GenericsKbArrayApp(term, sentence, confidenceScore);
      }






    public static void main (String[] args){
        //base = new GenericsKbArrayApp[100000];
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
                        load(term, sentence ,confidenceScore);
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
                
                System.out.println(updateEntry(termCapture,sentenceCapture, score));
                //System.out.println("Statement for term "+termCapture+" has been updated.");
                break;
            case 3:
                read.nextLine();
                System.out.print("Enter the term to search: ");
                int index = index(read.nextLine());
                if(index!=-1){
                    //prints out the statement and confidence score after searching by term using the index of the term in the knowledge base
                    System.out.println("Statement found: "+base[index].getSentence() +" (Confidence score: "+ base[index].getConfidenceScore() +")");

                }else{System.out.println("Term is not on knowledge base");}
                break;
            case 4:
                read.nextLine();
                System.out.print("Enter the term: ");
                int index2 = index(read.nextLine());
                System.out.print("Enter the statement to search for: ");
                String searchSentence = read.nextLine().trim();
                if(index2!=-1){
                    if(base[index2].getSentence().equals(searchSentence)){
                        System.out.println("The statement was found and has a confidence score of " +base[index2].getConfidenceScore());
                    }
                }else{System.out.println("Statement not found as term does not exit in knowledge base.");}
                break;
            case 5:
                app = false;
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
                break;




        }

    }}
}