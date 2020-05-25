package optus.assessment.demo.controller;

import optus.assessment.demo.exception.WrongNumberException;
import optus.assessment.demo.model.SearchText;
import optus.assessment.demo.service.SearchTextService;
import optus.assessment.demo.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/counter-api")
public class SearchTextController {

    // Task 1 - search texts and their number of occurrences
    @Autowired
    private SearchTextService searchTextService;

    //Task 2 - top repeated word
    @Autowired
    private WordCountService wordCountService;

    // Get POST request, do "search" and return result
    @PostMapping("/search")
    public Map<String, List<Map<String, Integer>>> search(@RequestBody SearchText searchText) throws FileNotFoundException {
        return searchTextService.search(searchText);
    }

    // get GET request, do "top" function and return result
    @GetMapping("/top/{num}")
    @ResponseBody
    public String top(@PathVariable int num) {

        // Only accept input 5, 10, 20 and 30
        if (num != 5 && num != 10 && num != 20 && num != 30) {
            throw new WrongNumberException("Number is not 5, 10, 20 or 30 - " + num);
        }

        return wordCountService.top(num);
    }

}

