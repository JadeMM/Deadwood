package deadwood.room;

import deadwood.player.PlayerController;
import java.util.*;


//Imports Seth's
public class RoomModel {
    // Private attributes
    private String name;
    private HashSet<String> adjList;
    private Hashtable<String,PlayerController> players;

    // Constructor
    public RoomModel(String name) {
        this.name = name;
        adjList = new HashSet<>();
        players = new Hashtable<>();
    }

    // Getters
    String getName() {
        return this.name;
    }

    HashSet<String> getAdjList() {
        return adjList;
    }

    Hashtable<String, PlayerController> getPlayers() {
        return players;
    }

    // Methods
    boolean inAdjList(String roomName) {
        return adjList.contains(roomName);
    }

    boolean inPlayerList(PlayerController player) { return players.containsValue(player); }
}
