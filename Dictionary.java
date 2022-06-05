package ex01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private final Map<String, Entity> dictionary = new HashMap<>();
    private final String[] files = new String[2];

    public Dictionary(String firstFileName, String secondFileName) {
        files[0] = firstFileName;
        files[1] = secondFileName;
    }

    public void addWord(String word, int sourceId) {
        Entity tmp = dictionary.get(word);
        if (tmp == null) {
            tmp = new Entity();
            tmp.add(sourceId);
            dictionary.put(word, tmp);
        } else {
            tmp.add(sourceId);
        }
    }

    public void makeDictionary() throws IOException {
        readFile(0);
        readFile(1);
    }

    private void readFile(int index) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(files[index]));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() != 0) {
                String[] tmp = line.split(" ", 0);
                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i].length() != 0) addWord(tmp[i], index);
                }
            }
        }
        reader.close();
    }

    public double getSimilarity() {
        double numerator = 0., denominator1 = 0., denominator2 = 0.;
        for (Map.Entry<String, Entity> entry : dictionary.entrySet()) {
            numerator += entry.getValue().getCount(0) * entry.getValue().getCount(1);
            denominator1 += entry.getValue().getCount(0) * entry.getValue().getCount(0);
            denominator2 += entry.getValue().getCount(1) * entry.getValue().getCount(1);
        }
        double denominator = (Math.sqrt(denominator1) * Math.sqrt(denominator2));
        return denominator == 0 ? 0 : numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }

    public void toFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));
        for (Map.Entry<String, Entity> entry : dictionary.entrySet()) {
            writer.write(entry.getKey());
            writer.newLine();
        }
        writer.close();
    }
}
