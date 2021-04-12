import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<Integer, Integer> levelPoint = new HashMap<>();
    static int listLevel = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        try {
            FileReader fin = new FileReader(path);
            int c;
            StringBuilder charLine = new StringBuilder();
            StringBuilder input = new StringBuilder();
            StringBuilder output = new StringBuilder();

            do {
                c = fin.read();
                if (c == '\n' || c == -1) {
                    String lineOut = lineParser(String.valueOf(charLine));
                    output.append(lineOut).append('\n');
                    charLine.delete(0, charLine.length() - 1);
                } else {
                    charLine.append((char) c);
                }
                input.append((char) c);
            } while (c != -1);
            input.deleteCharAt(input.length()-1);
            System.out.println(output);
            System.out.println(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String lineParser(String line) {
        String str = line;

        if (line.matches("^\\s*#+.*\\s*")) {
            int i = line.indexOf('#');
            while (line.charAt(i) == '#') {
                i++;
            }
            int count = i - line.indexOf('#');
            if (count > listLevel) {
                listLevel = listLevel + 1;
            } else {
                i = count;
                while (levelPoint.containsKey(++i)) {
                    levelPoint.put(i, 0);
                }
                listLevel = count;
            }
            levelPoint.put(listLevel, levelPoint.getOrDefault(listLevel, 0) + 1);
            String regex = "\\s*#{" + listLevel + "}\\s*";
            String replace = "\t".repeat(listLevel - 1) + levelPoint.get(listLevel).toString() + ". ";
            str = line.replaceFirst(regex, replace);
        }
        return str;
    }

}
