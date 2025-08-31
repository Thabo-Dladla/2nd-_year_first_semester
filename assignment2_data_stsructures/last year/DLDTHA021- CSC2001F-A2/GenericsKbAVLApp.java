
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericsKbAVLApp {
    public static void main(String[] args) {
        AVLTree<String> tree = new AVLTree<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("GenericsKB.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                AVLTree.opCount++;
                tree.insert(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("GenericsKB-queries.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                AVLTree.opCount++;
                String fixedLine = line.trim();
                BinaryTreeNode <String> result = tree.find(fixedLine);
                
                if (result != null) {
                    String results = result.data;
                    System.out.println(fixedLine+": "+results.split("\t")[1]+"("+results.split("\t")[2]+")");
                } 
                else {
                    System.out.println("Term not found: " + fixedLine);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    System.out.println("Total number of comparisons made: " + AVLTree.opCount);
        System.out.println("Total number of insert operations: "+tree.getInsertOpCount());
        System.out.println("Total number of search operations: "+tree.getSearchOpCount());
    }
    
}
