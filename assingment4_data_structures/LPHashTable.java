import java.util.List;
/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends HashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     * @param key will be taken to return its respective hash value and used to to find the index
     */
    protected int findIndex(String key) {
      int hashValue = hashFunction(key),full=0;
      
      while(full<tableSize()){
        incProbeCount();
        if(key.equals(table[hashValue]) || table[hashValue]== null ){
          //incProbeCount();
          return hashValue;
        }
        //incProbeCount();
        hashValue = (hashValue+1) % tableSize();
        full++;

      }//making sure that the table is not yet full
      return -1;
      }
}
