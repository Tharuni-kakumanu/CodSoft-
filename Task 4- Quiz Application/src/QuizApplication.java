package codsoft;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    static Scanner scanner = new Scanner(System.in);
    static Timer timer = new Timer();
    static boolean timeUp = false;
    static int score = 0;

    public static void main(String[] args) {
        String[] questions = {
                "What is the capital of France?",
                "Which planet is known as the Red Planet?",
                "What is the largest ocean on Earth?"
        };

        String[][] options = {
                {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
                {"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
                {"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"}
        };

        int[] correctAnswers = {3, 2, 4};

        for (int i = 0; i < questions.length; i++) {
            timeUp = false;
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                }
            };

            timer.schedule(task, 10000); // 10 seconds timer

            int userAnswer = getUserAnswer();
            task.cancel();

            if (!timeUp) {
                if (userAnswer == correctAnswers[i]) {
                    score++;
                }
            }
        }

        displayResults(questions, correctAnswers);
    }

    private static int getUserAnswer() {
        int userAnswer = -1;
        while (!timeUp) {
            System.out.print("Enter your answer (1-4): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= 4) {
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }
        return userAnswer;
    }

    private static void displayResults(String[] questions, int[] correctAnswers) {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + " out of " + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            System.out.println("Correct answer: " + correctAnswers[i]);
        }
        scanner.close();
    }
}


