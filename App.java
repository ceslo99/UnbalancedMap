/* Robert Mopia cssc0856
 * Cesar Lopez cssc0830
 * CS 310 T/TH
 */

package edu.sdsu.cs; //package should be edu.sdsu.cs

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends BalancedMap {
	// BalancedMap ngram = new BalancedMap();

	private static ArrayList<String> readFile(File file) throws IOException {
		ArrayList<String> alist = new ArrayList<>();
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			alist.add(input.next().replaceAll(" ", ""));
		}
		// System.out.println(Arrays.toString(alist.toArray()));
		return alist;
	}

	public static BalancedMap bigramParse(ArrayList<String> text) {
		BalancedMap bm = new BalancedMap();
		ArrayList<String> bigramList = new ArrayList();
		for (int i = 0; i < text.size() - 1; i++) {
			bigramList.add(text.get(i) + " " + text.get(i + 1));
		}
		bm = bigramMap(bigramList);
		return bm;
	}

	public static BalancedMap trigramParse(ArrayList<String> text) {
		BalancedMap bm = new BalancedMap();
		ArrayList<String> trigramList = new ArrayList();
		for (int i = 0; i < text.size() - 2; i++) {
			trigramList.add(text.get(i) + " " + text.get(i + 1) + " " + text.get(i + 2));
		}
		bm = bigramMap(trigramList);
		return bm;
	}

	private static BalancedMap bigramMap(ArrayList<String> bigramList) {
		BalancedMap ngram = new BalancedMap();
		for (String bi : bigramList) {
			if (ngram.contains(bi)) {
				int val = (int) ngram.getValue(bi);
				ngram.delete(bi);
				ngram.add(bi, val + 1);
			} else {
				ngram.add(bi, 1);
			}
		}
		return ngram;
	}

	private static void writeFile(File outputFile, BalancedMap bgMap,
								  BalancedMap tgMap) throws IOException{
		FileWriter fw = new FileWriter(outputFile);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(String.format("Bigrams %-10s %-10s\n", "", "Count"));
		for (Object k : bgMap.keyset()) {
			int value = (int) bgMap.getValue((Comparable) k);
			String key = (String) k;
			bw.write(String.format("%-20s %-20d \n", key, value));
		}
		bw.write("\n");
		bw.write(String.format("Trigrams %-10s %-10s\n", "", "Count"));
		for (Object k : tgMap.keyset()) {
			int value = (int) tgMap.getValue((Comparable) k);
			String key = (String) k;
			bw.write(String.format("%-20s %-20d \n", key, value));
		}
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) { // no args
			System.out.println("Error: No arguments found: input file-name "
					+ "and name of output file required.");
			System.exit(-1);
		} else if (args.length == 1) {
			System.out.println("Error: One argument found: input file-name "
					+ "and name of output file both required.");
			System.exit(-1);
		}
		File inputfile = new File(args[0]);
		String outputfile = args[1];

		if (inputfile.exists()) {
			ArrayList<String> text = readFile(inputfile);
			if (text.size() < 1) {
				System.out.println("No Text Found in File");
			} else {
				//System.out.println(text);
				BalancedMap bmap = bigramParse(text);
				BalancedMap tmap = trigramParse(text);


				System.out.printf("Bigrams %-10s %-10s\n", "", "Count");
				for (Object k : bmap.keyset()) {
					int value = (int) bmap.getValue((Comparable) k);
					String key = (String) k;
					System.out.printf("%-20s %-20d \n", key, value);
				}
				System.out.println();


				System.out.printf("Trigrams %-10s %-10s\n", "", "Count");
				for (Object k : tmap.keyset()) {
					int value = (int) tmap.getValue((Comparable) k);
					String key = (String) k;
					System.out.printf("%-20s %-20d \n", key, value);
				}
				writeFile(new File(outputfile), bmap, tmap);

			}

		} else {
			System.out.println("File Does not Exist");
		}
	}
}
