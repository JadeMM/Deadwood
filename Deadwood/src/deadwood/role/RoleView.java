package deadwood.role;

import deadwood.mvc.View;

public class RoleView extends View {
    // Private atttribute
    private RoleModel roleModel;

    // Constructor
    public RoleView(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    // Public method
    @Override
    public void refreshView() {
    }
}
