package optus.assessment.demo.service;

import optus.assessment.demo.model.SearchText;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class SearchTextServiceImpl implements SearchTextService {

    ClassPathResource resourceFile = new ClassPathResource("sample.txt");

    File file;

    Map<String, List<Map<String, Integer>>> result = new HashMap<String, List<Map<String, Integer>>>();

    // post construct - load sample file
    @PostConstruct
    public void loadFile() throws IOException {

        file = resourceFile.getFile();

    }

    @Override
    public Map<String, List<Map<String, Integer>>> search(@RequestBody SearchText searchText) throws FileNotFoundException {

        List<Map<String, Integer>> counts = new ArrayList<Map<String, Integer>>(1);

        Map<String, Integer> tempCounts = new HashMap<String, Integer>();

        List<String> searchTexts = searchText.getSearchText();

        for (String text : searchTexts) {

            text = text.toUpperCase();

            Scanner scanner = new Scanner(file);

            int count = 0;

            while (scanner.hasNext()) {

                // remove commas and dots before comparing
                String nextText = scanner.next().replace(",", "").replace(".", "").toUpperCase();

                if (nextText.equals(text)) {
                    count++;
                }

            }

            // first letter uppercase
            text = text.substring(0, 1) + text.substring(1).toLowerCase();

            tempCounts.put(text, count);

            scanner.close();

        }

        counts.add(tempCounts);

        result.put("counts", counts);

        return result;

    }

}

