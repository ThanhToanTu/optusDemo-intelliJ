package optus.assessment.demo.service;

import optus.assessment.demo.model.SearchText;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface SearchTextService {

    public Map<String, List<Map<String, Integer>>> search(@RequestBody SearchText searchText) throws FileNotFoundException;

}

