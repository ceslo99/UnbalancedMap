package edu.sdsu.cs.datastructures;

public class TestC extends BalancedMap { //tests balanced map we created
    public static void main(String[] args){
        BalancedMap bm = new BalancedMap();
        bm.add(2,"two"); //(key, value)
        bm.add(1, "one");
        bm.add(11, "one");
        bm.add(111, "one");
        bm.add(4, "four");
        bm.add(12, "twelve");
        bm.add(8, "eight");
        bm.add(99, "ninety nine");
        bm.add(9, "five (actually nine)");
        System.out.println("Add duplicate of 9? " + bm.add(9,"nine"));
        System.out.println("Add new pair? " + bm.add(10,"ten"));

        System.out.println("bmap contains 1?: " + bm.contains(1));
        System.out.println("bmap contains 3?: " + bm.contains(3));
        System.out.println("bmap key: " + bm.getKey("one"));
        System.out.println("bmap KEYS: " + bm.getKeys("one"));
        System.out.println("bmap value of 1: " + bm.getValue(1));
        System.out.println("size of tree map: " + bm.size());
        System.out.println("Is the tree map empty? " + bm.isEmpty());
        System.out.println("Keyset: " + bm.keyset());
        System.out.println("Values: " + bm.values());
        System.out.println("value from key removed: " + bm.delete(111));
        System.out.println("New values: " + bm.values());
        System.out.println("bmap new keyset: " + bm.keyset());
        bm.clear();
        System.out.println("Is the tree map empty? " + bm.isEmpty());
    }
}
