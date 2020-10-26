package ex01;

import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: Program file1 file2");
            System.exit(-1);
        }

        String[] file1 = null;

        String[] file2 = null;

        try {
            file1 = readFileFromArgs(args[0]);
            file2 = readFileFromArgs(args[1]);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        Map<String, Integer> dictionary = createDictionary(file1, file2);

        Vector<Integer> vectorOfFile1;

        Vector<Integer> vectorOfFile2;

        try {
            writeDictionaryToFile(dictionary);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        vectorOfFile1 = createVector(file1, dictionary);
        clearDictionary(dictionary);
        vectorOfFile2 = createVector(file2, dictionary);

        System.out.printf("Similarity = %1.3f\n", calculateSimilarity(vectorOfFile1, vectorOfFile2));
    }

    private static double calculateSimilarity(Vector<Integer> file1, Vector<Integer> file2) {
        double numerator = 0;

        double denominator = 0;

        double tempA = 0;

        double tempB = 0;

        for (int i = 0; i < file1.size(); i++) {
            numerator += file1.get(i) * file2.get(i);
        }

        for (int i = 0; i < file1.size(); i++) {
            tempA += file1.get(i) * file1.get(i);
        }

        for (int i = 0; i < file2.size(); i++) {
            tempB += file2.get(i) * file2.get(i);
        }

        denominator = Math.sqrt(tempA) * Math.sqrt(tempB);

        return numerator / denominator;
    }

    private static void clearDictionary(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> tmp : map.entrySet()) {
            map.replace(tmp.getKey(), 0);
        }
    }
    private static Vector<Integer> createVector(String[] file, Map<String, Integer> map) {
        Vector<Integer> rtVector;

        for (int i = 0; i < file.length; i++) {
            for (Map.Entry<String, Integer> tmp : map.entrySet()) {
                if (tmp.getKey().equals(file[i])) {
                    map.replace(tmp.getKey(), map.get(tmp.getKey()) + 1);
                }
            }
        }

        rtVector = new Vector<Integer>(map.values());
        return rtVector;
    }

    private static String[] readFileFromArgs(String args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(args));

        LinkedList<String> listOfLine = new LinkedList<>();

        String line;

        String[] finalArr;

        while ((line = reader.readLine()) != null) {
            listOfLine.add(line.toLowerCase());
        }

        line = listOfLine.toString();
        line = line.replaceAll("[^а-яА-Яa-zA-Z\\s]", "");
        line = line.replaceAll("\\s{2,}", " ");

        finalArr = line.split(" ");

        reader.close();

        return finalArr;
    }

    private static Map<String, Integer> createDictionary(String[] file1, String[] file2) {
        Map<String, Integer> tmpMap = new HashMap<>();

        for (int i = 0; i < file1.length; i++) {
            tmpMap.put(file1[i].toLowerCase(), 0);
        }

        for (int i = 0; i < file2.length; i++) {
            tmpMap.put(file2[i].toLowerCase(), 0);
        }

        return tmpMap;
    }

    private static void writeDictionaryToFile(Map<String, Integer> dictionary) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));

        List<String> listOfWord = new ArrayList<String>(dictionary.keySet());

        Collections.sort(listOfWord);

        for (int i = 0; i < listOfWord.size() - 1; i++) {
            writer.write(listOfWord.get(i) + ", ");
        }
        writer.write(listOfWord.get(listOfWord.size() - 1) + '\n');

        writer.close();
    }
}
