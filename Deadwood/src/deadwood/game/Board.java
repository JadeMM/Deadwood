package deadwood.game;

import deadwood.player.PlayerController;
import deadwood.room.RoomController;
import deadwood.room.actingroom.ActingRoomController;
import deadwood.scene.SceneController;
import deadwood.player.PlayerModel;

import java.util.Hashtable;
import java.util.Iterator;

public class Board {
    // Singleton attribute
    private static Board board;

    // Private attribute
    private Hashtable<String,RoomController> rooms;

    // Constructor
    private Board() { rooms = new Hashtable<>(); }

    // Singleton getter
    public static Board board() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    // Getter
    RoomController getRoom(String roomName) { return rooms.get(roomName); }

    // Public methods
    void addRoom(String roomName, RoomController room) { rooms.put(roomName, room); }

    public boolean validateMove(String playerRoom, String destination) {
        return true;
        //return playerRoom.isAdjacent(destination); //playerRoom needs to be a RoomController
    }

    public void updatePlayerLocation(PlayerModel player, String newLocation) {
        player.setCurrentRoom(newLocation);
    }

    void placeScenes(SceneController[] scenes) {
        int sceneIndex = 0;
        Iterator<RoomController> roomIter = rooms.values().iterator();
        while (roomIter.hasNext()) {
            RoomController room = roomIter.next();
            if (room instanceof ActingRoomController) {
                ActingRoomController actingRoomController = (ActingRoomController)room;
                actingRoomController.setScene(scenes[sceneIndex]);
                ++sceneIndex;
            }
        }
    }

    public void clearScenes() {
        Iterator<RoomController> roomIter = rooms.values().iterator();
        while (roomIter.hasNext()) {
            RoomController room = roomIter.next();
            if (room instanceof ActingRoomController) {
                ((ActingRoomController)room).removeScene();
            }
        }
    }
}
