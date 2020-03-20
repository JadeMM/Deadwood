
package deadwood.room;

import deadwood.player.PlayerController;

public class RoomController {
    // Private attribute
    protected RoomModel roomModel;
    protected RoomView roomView;

    // Contructor
    public RoomController(RoomModel roomModel, RoomView roomView) {
        this.roomModel = roomModel;
        this.roomView = roomView;
    }

    // Public methods
    public String roomName() {
        return roomModel.getName();
    }

    public void addAdjRoom(RoomController room) {
    }

    public void removeAdjRoom(RoomController room) {
    }

    public void addPlayerToRoom(PlayerController player) {
    }

    public void removePlayerFromRoom(PlayerController player) {
    }

    public boolean isAdjacent(String room) {
        return roomModel.inAdjList(room);
    }

    public boolean isPlayerInRoom(PlayerController player) { return roomModel.inPlayerList(player); }
}
