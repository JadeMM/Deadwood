package deadwood.scene;

import deadwood.role.RoleController;

public class SceneModel {
    // Private attributes
    private String sceneName;
    private RoleController[] roles;
    private int budget;
    private String sceneText;

    // Constructor
    public SceneModel(String sceneName, int roleCount, int budget, String sceneText) {
        this.sceneName = sceneName;
        roles = new RoleController[roleCount];
        this.budget = budget;
        this.sceneText = sceneText;
    }

    // Getters
    public RoleController getRole(int roleIndex) {
        return roles[roleIndex];
    }

    public int getBudget() {
        return this.budget;
    }

    // Setter
    public void setRole(RoleController role, int roleIndex) {
        roles[roleIndex] = role;
    }
}
