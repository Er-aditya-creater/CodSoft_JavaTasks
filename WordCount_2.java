import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCount_2 {
    private static final String[] COMMON_WORDS = {
            "the", "of", "and", "to", "in", "a", "is", "that", "it", "was",
            "for", "on", "are", "with", "as", "be", "this", "by", "at", "from", "i", "am"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text or provide a file path: ");
        String input = sc.nextLine();

        try {
            String text;
            if (input.toLowerCase().endsWith(".txt")) {
                text = readTextFromFile(input);
            } else {
                text = input;
            }

            int totalWords = countWords(text);
            int uniqueWords = cntwrd(text);
            Map<String, Integer> wordFrequency = countWordFrequency(text);

            System.out.println("Total words: " + totalWords);
            System.out.println("Unique words: " + uniqueWords);

            System.out.println("Word Frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sc.close();
    }

    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static int countWords(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        int count = 0;
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int cntwrd(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (!word.trim().isEmpty() && !isCommonWord(word)) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords.size();
    }

    private static boolean isCommonWord(String word) {
        for (String commonWord : COMMON_WORDS) {
            if (commonWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
   private static Map<String, Integer> countWordFrequency(String text)
   {
	   String[] words = text.split("\\s+|\\p{Punct}");
	   Map<String, Integer> wordCounts = new HashMap<>();
	   for (String word : words) 
	   {
           if (!word.trim().isEmpty() && !isCommonWord(word)) 
           {
               wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
           }
	   }
	  return wordCounts;
   }
    
}