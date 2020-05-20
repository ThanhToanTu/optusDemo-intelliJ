package optus.assessment.demo.model;

import java.util.HashMap;
import java.util.Map;

public class WordCount {

    private Map<String, Integer> wordCounts = new HashMap<String, Integer>();

    public WordCount() {

    }

    public WordCount(Map<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public Map<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(Map<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }

}
