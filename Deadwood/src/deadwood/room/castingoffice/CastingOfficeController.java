package deadwood.room.castingoffice;

import deadwood.room.RoomController;

public class CastingOfficeController extends RoomController {
    // Constructor
    public CastingOfficeController(CastingOfficeModel castingOfficeModel, CastingOfficeView castingOfficeView) {
        super(castingOfficeModel, castingOfficeView);
    }

    // Public methods
    public int moneyCostForLevel(int level) {
        if(level <= 6 && level >= 1) {
            return CastingOfficeModel.getMoneyCosts()[level - 1];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int fameCostForLevel(int level) {
        if(level <= 6 && level >= 1) {
            return CastingOfficeModel.getFameCosts()[level - 1];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
