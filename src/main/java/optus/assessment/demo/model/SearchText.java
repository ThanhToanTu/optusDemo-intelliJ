package optus.assessment.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchText {

    private List<String> searchText;

    public SearchText() {
        // default constructor
    }

    @Autowired
    public SearchText(List<String> searchText) {
        this.searchText = searchText;
    }

    public List<String> getSearchText() {
        return searchText;
    }

    public void setSearchTexts(List<String> searchText) {
        this.searchText = searchText;
    }

}
