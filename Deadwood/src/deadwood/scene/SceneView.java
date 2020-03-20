package deadwood.scene;

import deadwood.mvc.View;

public class SceneView extends View {
    // Private attributes
    private SceneModel sceneModel;

    // Constructor
    public SceneView(SceneModel sceneModel) {
        this.sceneModel = sceneModel;
    }

    // Public method
    @Override
    public void refreshView() {
    }
}
