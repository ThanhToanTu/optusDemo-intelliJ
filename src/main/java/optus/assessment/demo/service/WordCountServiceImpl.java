package optus.assessment.demo.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordCountServiceImpl implements WordCountService {

    // store sample.txt into a resource file
    ClassPathResource resourceFile = new ClassPathResource("sample.txt");

    Map<String, Integer> wordCountMap;

    Map<String, Integer> sortedWordCountMap;

    // Post construct - Load sample text, remove commas and dots
    @PostConstruct
    public void searchWordsOccurence() {

        // map of pairs of word : count
        wordCountMap = new HashMap<String, Integer>();

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(resourceFile.getFile()));

            String currentLine = reader.readLine();

            // loop through every line of sample text
            while(currentLine != null) {

                String[] words = currentLine.toUpperCase().split(" ");

                // loop through every word in the line, compare and count, put in the map
                for (String word : words) {

                    word = word.replace(",", "").replace(".", "");

                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word)+1);
                    }
                    else {
                        wordCountMap.put(word, 1);
                    }

                }

                // next line
                currentLine = reader.readLine();

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String top(@PathVariable int num) {

        // use entrySet and Stream API to sort in descending order, collect top sets into LinkedHashMap
        sortedWordCountMap = wordCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // sort by value, descending
                .limit(num) // limit/take the top set of "num" (5, 10, 20, 30)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // apply to a new LinkedHashMap

        // return a String, formatted like scv
        return sortedWordCountMap.keySet().stream()
                .map(key -> key + "|" + sortedWordCountMap.get(key)) // format each line = "key | value"
                .collect(Collectors.joining("\n")); // join and separate them by \n new line

    }

}
