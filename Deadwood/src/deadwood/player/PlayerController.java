package deadwood.player;
import deadwood.game.Board;
import deadwood.scene.SceneController;

public class PlayerController {
    // Private attributes
    private PlayerModel playerModel;
    private PlayerView playerView;

    // Constructor
    public PlayerController(PlayerModel playerModel) {
        this.playerModel = playerModel;
        this.playerView = new PlayerView(playerModel);
    }

    // Model getter
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    // Public methods
    public void resetRehearsals() {
        playerModel.setRehearsals(0);
    }

    public void incrementRehearsals() {
        playerModel.setRehearsals(playerModel.getRehearsals() + 1);
    }

    public boolean move(String destination) {
        Board board = Board.board();
        if(board.validateMove(playerModel.getCurrentRoom(), destination)){
            //playerModel.setCurrentRoom(destination); //destination is still a RoomController
            return true;
        } else {
            return false;
        }
    }

    public boolean takeExtraRole(String desiredRole) {
        return true;
    }

    public boolean takeSceneRole(int roleIndex) {
        return true;
    }

    public boolean act() {
        return true;
    }

    public boolean rehearse() {
        return true;
    }

    public boolean upgrade(int rankNew, boolean useMoney) {
        return true;
    }
}
