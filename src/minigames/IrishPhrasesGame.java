package minigames;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IrishPhrasesGame {

    @JsonProperty("phrases")
    private Phrase[] phrases;

    public Phrase[] getPhrases() {
        return phrases;
    }

    public void setPhrases(Phrase[] phrases) {
        this.phrases = phrases;
    }

    public static class Phrase {
        @JsonProperty("phrase")
        private String phrase;

        @JsonProperty("answer")
        private String answer;

        public String getPhrase() {
            return phrase;
        }

        public void setPhrase(String phrase) {
            this.phrase = phrase;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}