package deadwood.game;

import deadwood.player.PlayerController;
import deadwood.scene.SceneController;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    // Private static readonly attributes
    private static final String trailerName = "trailer";
    private static final int numberOfRooms = 10;

    // Private static attributes
    private static PlayerController[] playerList;
    private static SceneController[] unusedScenes;
    private static ArrayList<Integer> sceneGenerator;
    private static int sceneGeneratorIndex = 0;
    private static int playerCount;
    private static int currentDay = 3;
    private static int unfinishedScenes = numberOfRooms;

    // Private methods
    private static void startGame() {
        try {
            GameSetupService.initializeBoard();
            unusedScenes = GameSetupService.initializeScenes();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sceneGenerator = GameSetupService.initializeSceneGenerator();
        playerList = GameSetupService.initializePlayers(Board.board().getRoom(trailerName), playerCount);
    }

    private static void dayStart() {
        SceneController[] currentScenes = new SceneController[numberOfRooms];
        for (int sceneIndex = 0; sceneIndex < numberOfRooms; ++sceneIndex) {
            currentScenes[sceneIndex] = unusedScenes[sceneGenerator.get(sceneGeneratorIndex)];
            ++sceneGeneratorIndex;
        }

        Board.board().placeScenes(currentScenes);
        unfinishedScenes = numberOfRooms;
    }

    private static void gameLoop() {
        while (unfinishedScenes > 1) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
        }
    }

    private static void dayEnd() {
        Board.board().clearScenes();
        for (int playerIndex = 0; playerIndex < playerCount; ++playerIndex) {
            playerList[playerIndex].move(trailerName);
        }
    }

    private static void endGame() {
    }

    private static boolean validateArgs(String[] args) {
        boolean isValid = false;

        if (args.length > 0) {
            try {
                int input = Integer.parseInt(args[0]);
                if (input == 2 || input == 3) {
                    playerCount = input;
                    isValid = true;
                } else {
                    System.err.println("Must input either 2 or 3");
                }
            } catch (NumberFormatException e) {
                System.err.println("Must input an integer");
            }
        }

        return isValid;
    }

    // Main
    public static void main(String[] args) {
        if (validateArgs(args)) {
            startGame();

            while (currentDay > 0) {
                dayStart();
                //gameLoop();
                dayEnd();
                --currentDay;
            }

            endGame();
        }
    }
}
