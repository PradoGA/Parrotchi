package minigames;
import java.util.regex.*;

// class that extract the red text from  the given text
public class ExtractRedText {
    public String extractText(String html){

        Pattern pattern = Pattern.compile("<span style='color: red;'>(.*?)</span>");
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String redText = matcher.group(1);
            return redText;
        } else {
            return "No red word found.";
        }
    }
}
