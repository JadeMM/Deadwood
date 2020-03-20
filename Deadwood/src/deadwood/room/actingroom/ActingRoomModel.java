package deadwood.room.actingroom;

import deadwood.role.RoleController;
import deadwood.room.RoomModel;
import deadwood.scene.SceneController;

public class ActingRoomModel extends RoomModel {
    // Private attributes
    private RoleController[] roles;
    private SceneController scene;
    private int shotNumber;

    // Constructor
    public ActingRoomModel(String name, int roleCount, int shotNumber) {
        super(name);
        roles = new RoleController[roleCount];
        scene = null;
        this.shotNumber = shotNumber;
    }

    // Getters
    public RoleController getRole(int roleIndex) {
        return this.roles[roleIndex];
    }

    SceneController getScene() {
        return this.scene;
    }

    int getShotNumber() {
        return this.shotNumber;
    }

    // Setters
    public void setRole(RoleController role, int roleIndex) {
        roles[roleIndex] = role;
    }

    void setScene(SceneController scene) {
        this.scene = scene;
    }

    void removeScene() {
        scene = null;
    }

    void decrementShotNumber() {
        --shotNumber;
    }
}
