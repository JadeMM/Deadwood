package deadwood.room.castingoffice;

import deadwood.room.RoomModel;

public class CastingOfficeModel extends RoomModel {
    // Private static attributes
    private static final int[] moneyCosts = new int[]{4, 10, 18, 28, 40};
    private static final int[] fameCosts = new int[]{5, 10, 15, 20, 25};

    // Constructor
    public CastingOfficeModel(String name) {
        super(name);
    }

    // Getters
    static int[] getFameCosts() {
        return fameCosts;
    }

    static int[] getMoneyCosts() {
        return moneyCosts;
    }
}
