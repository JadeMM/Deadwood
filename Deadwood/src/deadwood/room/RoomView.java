package deadwood.room;

import deadwood.mvc.View;

public class RoomView extends View {
    // Private attribute
    private RoomModel roomModel;

    // Constructor
    public RoomView(RoomModel roomModel) {
        this.roomModel = roomModel;
    }

    // Public method
    @Override
    public void refreshView() {
    }
}
