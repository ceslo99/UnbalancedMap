/* Robert Mopia cssc0856
 * Cesar Lopez cssc0830
 * CS 310 T/TH
 */
package edu.sdsu.cs.datastructures;

import java.util.LinkedList;

public class UnbalancedMap<K extends Comparable<K>, V> implements IMap<K, V> {
    Node root; // root of BST

    private class Node<K, V> {
        private K key; // key
        private V value; // associated value
        private Node left, right; // links to subtrees
        private int size = 0; // # nodes in subtree rooted here

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

    }

    public UnbalancedMap() {
        root = null;
    }
 public UnbalancedMap(IMap data) {
    	int counter = 0;
    	LinkedList<K> ImapKeys = new LinkedList<>();
    	LinkedList<V> ImapValues = new LinkedList<>();
    	ImapKeys = (LinkedList<K>) data.keyset();
    	ImapValues = (LinkedList<V>) data.values();
    	while(counter <= data.size() -1){
    		add(ImapKeys.get(counter), ImapValues.get(counter));
    		counter++;
    	}

    }

    public boolean contains(K k) {
        if (root == null) {
            return false;
        }
        boolean isIn = containsRec(root, k);
        return isIn;
    }

    private boolean containsRec(Node current, K k) {
        if (current == null) {
            return false;
        } else if (k.compareTo((K) current.key) == 0) {
            return true;
        } else if (k.compareTo((K) current.key) < 0) {
            return containsRec(current.left, k);
        } else {
            return containsRec(current.right, k);
        }
    }

    public boolean add(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            root.size = 1;
            return true;
        } else if (contains(key) == true) {
            return false; // duplicates are not allowed
        } else {
            root = addItem(root, key, value);// recursive code to add
            root.size++;
            return true;

        }

    }

    private Node addItem(Node cur, K key, V value) {
        if (cur == null) {
            cur = new Node(key, value);

        }
        if (key.compareTo((K) cur.key) < 0) {
            cur.left = addItem(cur.left, key, value);
        } else if (key.compareTo((K) cur.key) > 0) {
            cur.right = addItem(cur.right, key, value);
        }
        return cur;
    }

    public V delete(K key) {
        if (contains(key)) {
            V temp = getValue(key);
            int sizeTemp = root.size;
            root = deleteItem(root, key);
            if (sizeTemp != 1) {
                root.size = sizeTemp;
                root.size--;
            }

            return temp;

        }

        return null;
    }

    private Node deleteItem(Node newHead, K key) {

        if (newHead == null) {
            return null;
        }

        if (key.compareTo((K) newHead.key) < 0)
            newHead.left = deleteItem(newHead.left, key);
        else if (key.compareTo((K) newHead.key) > 0)
            newHead.right = deleteItem(newHead.right, key);
        else {
            if (newHead.right == null && newHead.left == null) {
                return null;
            }
            if (newHead.right == null) {
                return newHead.left;
            }

            if (newHead.left == null) {
                return newHead.right;
            }

            Node oldHead = newHead;

            newHead = min(oldHead.right);
            newHead.right = deleteMin(oldHead.right);
            newHead.left = oldHead.left;
            oldHead = null;

        }

        return newHead;

    }

    private Node deleteMin(Node y) {

        if (y.left == null) {
            return y.right;
        }

        y.left = deleteMin(y.left);
        return y;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);

    }

    public V getValue(K key) {
        V value;
        value = getValueRec(root, key);
        return value;

    }

    private V getValueRec(Node n, K k) {
        if (n == null)
            return null;
        else if (k.compareTo((K) n.key) < 0)
            return (V) getValueRec(n.left, k);
        else if (k.compareTo((K) n.key) > 0)
            return (V) getValueRec(n.right, k);
        else {
            return (V) n.value;
        }
    }

    public K getKey(V value) {
        LinkedList<K> gKey = new LinkedList<>();

        keyWithValue(root, value, gKey);

        if (gKey.size() != 0) {
            return gKey.get(0);
        } else {

            return null;
        }

    }

    public void keyWithValue(Node current, V val, LinkedList<K> gKe) {

        if (current == null) {
            return;
        }
        if (current.value == val) {
            gKe.add((K) current.key);
        }

        keyWithValue(current.left, val, gKe);
        keyWithValue(current.right, val, gKe);

    }

    public Iterable<K> getKeys(V value) {
        LinkedList<K> gKeys = new LinkedList<>();
        keysWithValue(root, value, gKeys);
        return gKeys;
    }


    private void keysWithValue(Node current, V val, LinkedList<K> gK) {
        if (current == null) {
            return;
        }
        if (current.value.equals(val)) {
            gK.add((K) current.key);
        }
        keysWithValue(current.left, val, gK);
        keysWithValue(current.right, val, gK);

    }


    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }

    }

    public boolean isEmpty() {
        if (root == null)
            return true;
        else
            return false;

    }

    public void clear() {
        root = null;
    }

    public Iterable<K> keyset() {
        LinkedList<K> keylist;
        keylist = inOrderKeys(root);
        return keylist;
    }

    private LinkedList<K> inOrderKeys(Node n) {
        LinkedList<K> ll = new LinkedList<>();
        if (n == null)
            return ll;
        ll.addAll((inOrderKeys(n.left)));
        ll.add((K) n.key);
        ll.addAll((inOrderKeys(n.right)));
        return ll;
    }

    public Iterable<V> values() { // values in order
        LinkedList<V> valuelist;
        valuelist = inOrderVals(root);
        return valuelist;
    }

    private LinkedList<V> inOrderVals(Node n) {
        LinkedList<V> ll = new LinkedList<>();
        if (n == null)
            return ll;
        ll.addAll(inOrderVals(n.left));
        ll.add((V) n.value);
        ll.addAll(inOrderVals(n.right));
        return ll;
    }

}
