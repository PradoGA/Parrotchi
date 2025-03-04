package responses;
import java.util.Map;

public class ParrotResponses {

    // Map to hold responses categorized by a string key
    private Map<String, String[]> responses;

    // Getter method for responses map
    public Map<String, String[]> getResponses() {
        return responses;
    }

    // Setter method for responses map
    public void setResponses(Map<String, String[]> responses) {
        this.responses = responses;
    }
}