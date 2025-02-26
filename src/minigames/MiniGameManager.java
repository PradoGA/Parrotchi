package minigames;

public class MiniGameManager {

    private GameParser gameParser;


    private String currentPhrase;
    private String currentAnswer;


    public  MiniGameManager()
    {
        this.gameParser = new GameParser("irishPhrases.json");
    }


   public void getRandomPhrase()
    {

       this.currentPhrase = gameParser.getRandomPhrase();
       this.currentAnswer= gameParser.getRandomAnswer();

    }

    public String getCurrentPhrase()
    {
        System.out.println("Phrase: " + currentPhrase);
        return currentPhrase;
    }



    public String getCurrentAnswer()
    {
        System.out.println("Answer: " + currentAnswer);
        return currentAnswer;
    }

    public  boolean isCorrect(String userAnswer)
    {
        if (userAnswer.equalsIgnoreCase(currentAnswer))
        {
            return true;
        }

        else
        {
            return false;
        }
    }


}
