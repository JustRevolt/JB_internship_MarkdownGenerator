import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        MarkdownGenerator generator = new MarkdownGenerator();
        System.out.print(generator.addTableOfContents(path));
        scanner.close();
    }
}

