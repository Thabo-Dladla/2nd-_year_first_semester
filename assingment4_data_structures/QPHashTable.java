/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under QP).
     * @param key will be taken to return its respective hash value and used to to find the index
     */
    protected int findIndex(String key) {
      int hashValue = hashFunction(key),full=0;
      int initial = hashValue;
      int quadratic =1;
      
      while(full<tableSize()){
        incProbeCount();
        if(key.equals(table[hashValue]) || table[hashValue]== null ){
          //incProbeCount();
          return hashValue;
        }
        //incProbeCount();
        hashValue =(initial+quadratic*quadratic) % tableSize();
        quadratic++;
        full++;

      }//making sure that the table is not yet full
      return -1;//if the index was found after quadratically probing through the table 

    }
}
