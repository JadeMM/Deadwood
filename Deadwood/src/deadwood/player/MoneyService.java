package deadwood.player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MoneyService {
    // Singleton attribute
    private static MoneyService moneyService;

    // Private constructor
    private MoneyService() {
    }

    // Give access to singleton instance
    public static MoneyService moneyService() {
        if (moneyService == null) {
            moneyService = new MoneyService();
        }

        return moneyService;
    }

    // Public methods
    public void giveMoney(PlayerController player, int amount) {
        PlayerModel playerModel = player.getPlayerModel();
        playerModel.setMoney(playerModel.getMoney() + amount);
    }

    public void giveFame(PlayerController player, int amount) {
        PlayerModel playerModel = player.getPlayerModel();
        playerModel.setFame(playerModel.getFame() + amount); }

    public boolean takeMoney(PlayerController player, int amount) {
        PlayerModel playerModel = player.getPlayerModel();
        boolean canDoTransaction = playerModel.getMoney() >= amount;
        if (canDoTransaction) { playerModel.setMoney(playerModel.getMoney() - amount); }
        return canDoTransaction;
    }

    public boolean takeFame(PlayerController player, int amount) {
        PlayerModel playerModel = player.getPlayerModel();
        boolean canDoTransaction = playerModel.getFame() >= amount;
        if (canDoTransaction) { playerModel.setFame(playerModel.getFame() - amount); }
        return canDoTransaction;
    }

    //Called from ActingRoomController
    public void disperseSceneRewards(PlayerController[] onScene, PlayerController[] offScene, int budget) {

        Integer rolls[] = new Integer[budget];
        Random randomGenerator = new Random();

        for (int i  = 0; i < budget; i++) {
            rolls[i] = randomGenerator.nextInt(6) + 1;
        }

        Arrays.sort(rolls, Collections.reverseOrder());

        int currentPlayer = 0;
        for (int i = 0; i < budget; i++) {

            if (onScene[currentPlayer] != null) {
                giveMoney(onScene[currentPlayer], rolls[i]);
            }

            currentPlayer++;

            if (currentPlayer == onScene.length) {
                currentPlayer = 0;
            }
        }

        //TODO add role controller to player model

    }
}
