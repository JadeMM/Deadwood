
package deadwood.role;

import deadwood.player.PlayerController;

public class RoleController {
    // Private attributes
    private RoleModel roleModel;
    private RoleView roleView;

    // Constructor
    public RoleController(RoleModel roleModel, RoleView roleView) {
        this.roleModel = roleModel;
        this.roleView = roleView;
    }

    public RoleModel getRoleModel(){
        return this.roleModel;
    }

    // Public methods
    public boolean isQualified(PlayerController player) {
        return player.getPlayerModel().getRank() >= roleModel.getRequiredRank();
    }

    public boolean isOccupied() {
        return roleModel.isOccupied();
    }
}
