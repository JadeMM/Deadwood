package deadwood.room.actingroom;

import deadwood.game.Board;
import deadwood.player.MoneyService;
import deadwood.player.PlayerController;
import deadwood.role.RoleController;
import deadwood.room.RoomController;
import deadwood.scene.SceneModel;

import java.util.ArrayList;

public class ActingRoomController extends RoomController {
    // Contructor
    public ActingRoomController(ActingRoomModel actingRoomModel, ActingRoomView actingRoomView) {
        super(actingRoomModel, actingRoomView);
    }

    // Public methods
    public void decrementShotNumber() {
        ((ActingRoomModel)roomModel).decrementShotNumber();
    }

    public boolean isSceneFinished() {
        return ((ActingRoomModel)roomModel).getShotNumber() == 0;
    }

    //used in comleteScene to give list of player in scene roles
    private PlayerController[] getPlayersOnRole(SceneModel sceneModel){
        PlayerController[] onScene = new PlayerController[5];//replace 5 with number of roles
        for(int i =0; i>0; i++) {
            if(sceneModel.getRole(i)!=null) {
                onScene[i] = (sceneModel.getRole(i).getRoleModel().getPlayerController());
            } else {
                onScene[i] = null;
            }
        }
        return onScene;
    }

    private PlayerController[] getPlayersOffRole(ActingRoomModel actingRoomModel){
        ActingRoomModel room = ((ActingRoomModel)roomModel);
        PlayerController[] offScene = new PlayerController[5];//replace 5 with number of roles
        for(int i =0;  i>0; i++) {
            if(room.getRole(i)!=null) {
                offScene[i] = (room.getRole(i).getRoleModel().getPlayerController());
            } else {
                offScene[i] = null;
            }
        }
        return offScene;
    }

    public void completeScene(SceneModel sceneModel) {
        MoneyService moneyService = MoneyService.moneyService();
        moneyService.disperseSceneRewards(getPlayersOnRole(sceneModel), getPlayersOffRole(((ActingRoomModel)roomModel)), sceneModel.getBudget());
        ((ActingRoomModel)roomModel).removeScene();
    }

    public void addPlayerToExtraRole(PlayerController player, RoleController desiredRole) {
    }

    public boolean attemptAct(PlayerController player) {
        return true;
    }
}
