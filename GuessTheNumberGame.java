import java.util.*;

public class GuessTheNumberGame {
    public static void main(String args[]) {
        String playerName;
        List<ScoreCard> scoreCardList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();

        while (true) {
            System.out.println("\n\t <<>> Guess The Number <<>>");

            if (!scoreCardList.isEmpty()) {

                scoreCardList.sort(new SortbyScore());

                System.out.println("\n\t <<>> Score Card <<>>");

                for (int i = 0; i < scoreCardList.size(); i++) {
                    ScoreCard scoreCard = scoreCardList.get(i);
                    System.out.printf("\n>>> %02d - %s \t %02d\n", i + 1, scoreCard.playerName.toUpperCase(),
                            scoreCard.score);
                }

            }

            System.out.println("\n\t <<>> Play Game <<>>\n");

            System.out.print("Please Enter player Name :: ");

            playerName = sc.next();

            int randomNumber = rn.nextInt(100) + 1, trailsCount = 100, currentTrail, score = 0;

            System.out.println("\nrandom number " + randomNumber);

            System.out.printf("\nA number is chosen between 1 to 100. Guess the number within %02d trials.\n",
                    trailsCount);

            for (currentTrail = 0; currentTrail < trailsCount; currentTrail++) {

                System.out.print("\nGuess the number :: ");

                // Take input for guessing
                int guess = sc.nextInt();

                // If the number is guessed
                if (randomNumber == guess) {
                    System.out.println("\n>>> Congratulations!! You guessed the number.");
                    break;
                } else if (randomNumber > guess
                        && currentTrail != trailsCount - 1) {
                    System.out.println("\nThe number is  greater than " + guess);
                } else if (randomNumber < guess
                        && currentTrail != trailsCount - 1) {
                    System.out.println(
                            "\nThe number is less than " + guess);
                }
            }

            if (currentTrail == trailsCount) {
                System.out.printf(
                        "\nYou have exhausted %02d trials.\n", trailsCount);

                System.out.println(
                        "The number was " + randomNumber);

            } else {
                score = trailsCount - currentTrail;
            }

            boolean duplicate = false;

            for (int j = 0; j < scoreCardList.size(); j++) {
                ScoreCard scoreCard = scoreCardList.get(j);
                if (scoreCard.playerName.trim().equalsIgnoreCase(playerName.trim())) {
                    if (score > scoreCard.score) {
                        scoreCardList.get(j).score = score;
                    }
                    duplicate = true;
                }
            }
            if (!duplicate)
                scoreCardList.add(new ScoreCard(playerName, score));
        }
    }
}

class ScoreCard {
    String playerName;
    int score = -1;

    ScoreCard(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
}

class SortbyScore implements Comparator<ScoreCard> {
 
    // Method
    // Sorting in descending order of score
    public int compare(ScoreCard a, ScoreCard b)
    {
        return  b.score - a.score ;
    }
}