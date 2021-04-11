import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        try {
            FileReader fin = new FileReader(path);
            int c;
            StringBuilder charLine = new StringBuilder();
            StringBuilder input = new StringBuilder();
            StringBuilder output = new StringBuilder();

            while ((c = fin.read()) != -1) {
                if (c == '\n') {
                    String lineOut = lineParser(String.valueOf(charLine));
                } else {
                    charLine.append((char) c);
                }
                input.append((char) c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String lineParser(String line) {
        StringBuilder sb = new StringBuilder();

        return String.valueOf(sb);
    }

}
