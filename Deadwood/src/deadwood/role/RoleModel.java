package deadwood.role;

import deadwood.player.PlayerController;

public class RoleModel {
    // Private attributes
    private String name;
    private PlayerController playerController;
    private int requiredRank;
    private boolean sceneRole;

    // Constructor
    public RoleModel(String name, int requiredRank, boolean sceneRole) {
        this.name = name;
        playerController = null;
        this.requiredRank = requiredRank;
        this.sceneRole = sceneRole;
    }

    // Getter
    String getName() {
        return name;
    }

    public PlayerController getPlayerController() {
        return this.playerController;
    }

    boolean isOccupied() {
        return !(playerController == null);
    }

    boolean isSceneRole() {
        return sceneRole;
    }

    int getRequiredRank() {
        return requiredRank;
    }

    // Setter
    void setOccupied(boolean occupied) {
        this.playerController = playerController;
    }
}
