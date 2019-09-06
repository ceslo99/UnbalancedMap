/* Robert Mopia cssc0856
 * Cesar Lopez cssc0830
 * CS 310 T/TH
 */
package edu.sdsu.cs.datastructures;

import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Collection;

public class BalancedMap<K extends Comparable<K>, V> implements IMap<K, V> {
    LinkedList llist;
    TreeMap<K, V> bmap;

    public BalancedMap() {
        bmap = new TreeMap<>();
    }

   public BalancedMap(IMap data) { 
		bmap = new TreeMap<>();
		int count = 0;
		LinkedList<K> ImapKeys = new LinkedList<K>( (Collection<? extends K>) data.keyset());
		LinkedList<V> ImapValues = new LinkedList<V>((Collection<? extends V>) data.values());
		while (count < ImapKeys.size()){
			add(ImapKeys.get(count), ImapValues.get(count));
			count++;
		}
    	
    }


    public boolean contains(K key) {
        if (bmap.containsKey(key))
            return true;
        else
            return false;
    }

    public boolean add(K key, V value) {
        for (K k : bmap.keySet()) {
            if (k.equals(key))
                return false;
        }
        bmap.put(key, value);
        return true;
    }

    public V delete(K key) {
        V val = bmap.get(key);
        if (val == null)
            return null;
        bmap.remove(key);
        return val;
    }

    public V getValue(K key) {
        V val = bmap.get(key);
        return val;
    }

    public K getKey(V value) {
        for (K key : bmap.keySet())
            if (bmap.get(key).equals(value))
                return key;

        return null;
    }

    public Iterable<K> getKeys(V value) {
        llist = new LinkedList();
        for (K key : bmap.keySet()) {
            if (bmap.get(key).equals(value)) {
                llist.add(key);
            }
        }
        return llist;
    }

    public int size() {
        return bmap.size();
    }

    public boolean isEmpty() {
        return (bmap.isEmpty());
    }

    public void clear() {
        bmap.clear();
    }

    public Iterable<K> keyset() {
        return bmap.keySet();
    }

    public Iterable<V> values() {
        return bmap.values();
    }

}
