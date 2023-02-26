import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextProcessor {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // check the args have one parameter
        if (args.length != 1) {
            String usage = """
                    Usage: java TextProcessor <input file path>
                    eg. java TextProcessor input1.txt
                    """;
            System.out.print(usage);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader file = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(file);
            int i;
            while ((i = reader.read()) != -1) {
                stringBuilder.append((char) i);
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            return;
        } catch (IOException exception) {
            System.out.println("Error when read file.");
            return;
        }

        Processor processor = new Processor(stringBuilder.toString());
        printResults(processor);

        long endTime = System.currentTimeMillis();

        System.out.println("Total time to execute this program: " + (endTime - startTime) + " ms.");
    }

    private static void printResults(Processor processor) {
        System.out.println("Total # Character Count: " + processor.getCharCount());
        System.out.println("Total # of Palindrome found: " + processor.getPalindromeCount());
        System.out.println("Total number of tokens: " + processor.getTotalTokens());
        System.out.println("Total # of new lines: " + processor.getNewLineCount());
        System.out.println("Total number of emoticons: " + processor.getTotalEmoticon());
        System.out.println("Longest token size: " + processor.getLongestTokenSizes());
        System.out.println("Average token size: " + processor.getAverageTokenSize());
    }
}