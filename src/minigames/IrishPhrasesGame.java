package minigames;
import com.fasterxml.jackson.annotation.JsonProperty;

// Main class for the IrishPhrasesGame
public class IrishPhrasesGame {

    // Array of Phrase objects, annotated for JSON property mapping
    @JsonProperty("phrases")
    private Phrase[] phrases;

    // Getter for phrases array
    public Phrase[] getPhrases() {
        return phrases;
    }

    // Setter for phrases array
    public void setPhrases(Phrase[] phrases) {
        this.phrases = phrases;
    }

    // Inner class representing a single phrase and its answer
    public static class Phrase {
        // The phrase text, annotated for JSON property mapping
        @JsonProperty("phrase")
        private String phrase;

        // The answer text, annotated for JSON property mapping
        @JsonProperty("answer")
        private String answer;

        // Getter for the phrase text
        public String getPhrase() {
            return phrase;
        }

        // Setter for the phrase text
        public void setPhrase(String phrase) {
            this.phrase = phrase;
        }

        // Getter for the answer text
        public String getAnswer() {
            return answer;
        }

        // Setter for the answer text
        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}