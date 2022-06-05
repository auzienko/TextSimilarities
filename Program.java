package ex01;

import java.io.IOException;

public class Program {
    private static final String USAGE = "uses:\n$ java Program file1 file2";

    public static void main(String[] args) throws IOException {
        if (args.length != 2) errorPrint(USAGE);
        try {
            Dictionary dictionary = new Dictionary(args[0], args[1]);
            dictionary.makeDictionary();
            System.out.printf("Similarity = %.2f\n", dictionary.getSimilarity());
            dictionary.toFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void errorPrint(String message) {
        System.err.println(message);
        System.exit(-1);
    }
}
