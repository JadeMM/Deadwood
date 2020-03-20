package deadwood.player;

import deadwood.room.RoomController;

public class PlayerModel {
    // Static attribute
    private static int playerNumberCounter = -1;

    // Private attributes
    private int playerNumber;
    private String currentRoom;
    private int rank;
    private boolean hasRole;
    private int rehearsals;
    private int money;
    private int fame;

    public PlayerModel(String trailers) {
        this.playerNumber = ++playerNumberCounter;
        this.currentRoom = trailers;
        this.rank = 1;
        this.rehearsals = 0;
        this.money = 0;
        this.fame = 0;
    }

    // Getters
    int getPlayerNumber() {
        return playerNumber;
    }

    String getCurrentRoom() {
        return currentRoom;
    }

    public int getRank() { return rank; }

    boolean getHasRole() {
        return hasRole;
    }

    public int getRehearsals() {
        return rehearsals;
    }

    int getMoney() {
        return money;
    }

    int getFame() {
        return fame;
    }

    // Setters
    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setHasRole(boolean hasRole) {
        this.hasRole = hasRole;
    }

    void setRehearsals(int rehearsals) {
        this.rehearsals = rehearsals;
    }

    void setMoney(int money) {
        this.money = money;
    }

    void setFame(int fame) { this.fame = fame; }
}
