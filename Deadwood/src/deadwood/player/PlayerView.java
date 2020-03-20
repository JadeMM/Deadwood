
package deadwood.player;

import deadwood.mvc.View;

public class PlayerView extends View {
    // Private attributes
    private PlayerModel playerModel;

    // Constructor
    public PlayerView(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    // Public methods
    @Override
    public void refreshView() {
    }
}
