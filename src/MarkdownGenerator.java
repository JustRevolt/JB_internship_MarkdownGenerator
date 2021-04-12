import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MarkdownGenerator {

    static HashMap<Integer, Integer> listLevelPoints = new HashMap<>();
    static int currentListLevel = 0;

    public String addTableOfContents(String path) {
        StringBuilder charLine = new StringBuilder();
        StringBuilder input = new StringBuilder();
        StringBuilder tableOfContents = new StringBuilder();

        try {
            FileReader fin = new FileReader(path);
            int c;

            do {
                c = fin.read();
                charLine.append((char) c);
                if (c == '\n' || c == -1) {
                    String lineOut = lineParser(String.valueOf(charLine));
                    tableOfContents.append(lineOut);
                    charLine.delete(0, charLine.length());
                }
                input.append((char) c);
            } while (c != -1);

            input.deleteCharAt(input.indexOf(String.valueOf((char) -1)));
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(tableOfContents.append("\n").append(input));
    }

    static String lineParser(String line) {
        if (line.matches("^\\s*#+.*\\s*")) {
            return generateTableOfContents(line);
        } else {
            return "";
        }
    }

    static String generateTableOfContents(String line) {
        StringBuilder resultStr = new StringBuilder();

        int i = line.indexOf('#');
        while (line.charAt(i) == '#') {
            i++;
        }

        int count = i - line.indexOf('#');
        if (count > currentListLevel) {
            currentListLevel = currentListLevel + 1;
        } else {
            i = count;
            while (listLevelPoints.containsKey(++i)) {
                listLevelPoints.put(i, 0);
            }
            currentListLevel = count;
        }

        listLevelPoints.put(currentListLevel, listLevelPoints.getOrDefault(currentListLevel, 0) + 1);
        resultStr.append("\t".repeat(currentListLevel - 1)).append(listLevelPoints.get(currentListLevel).toString()).append(". ");

        String regex = "\\s*#{" + currentListLevel + "}\\s*";
        String headerText = line.replaceFirst(regex, "").replaceAll("[\\r\\n]", "");
        String objectLink = headerText.toLowerCase().replaceAll(" ", "-");

        resultStr.append("[").append(headerText).append("]");
        resultStr.append("(#").append(objectLink).append(")");
        resultStr.append(line.replaceAll("[^\\n\\r]*", ""));

        return String.valueOf(resultStr);
    }
}
