package deadwood.scene;

import deadwood.player.PlayerController;
import deadwood.player.PlayerModel;

public class SceneController {
    // Private attribute
    private SceneModel sceneModel;
    private SceneView sceneView;

    // Constructor
    public SceneController(SceneModel sceneModel, SceneView sceneView) {
        this.sceneModel = sceneModel;
        this.sceneView = sceneView;
    }

    // Public methods
    public void addPlayerToSceneRole(PlayerModel player, int roleIndex) {
    }

    public boolean isSceneRoleOccupied(int roleIndex) {
        return sceneModel.getRole(roleIndex).isOccupied();
    }

    public boolean isQualifiedForSceneRole(PlayerController player, int roleIndex) {
        return sceneModel.getRole(roleIndex).isQualified(player);
    }

    public boolean generateActRoll(PlayerController player) {
        return true;
    }

    public boolean canRehearse(PlayerController player) {
        return player.getPlayerModel().getRehearsals() < sceneModel.getBudget();
    }
}
