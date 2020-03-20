package deadwood.game;

import deadwood.player.PlayerController;
import deadwood.player.PlayerModel;
import deadwood.role.RoleController;
import deadwood.role.RoleModel;
import deadwood.role.RoleView;
import deadwood.room.RoomController;
import deadwood.room.actingroom.ActingRoomController;
import deadwood.room.actingroom.ActingRoomModel;
import deadwood.room.actingroom.ActingRoomView;
import deadwood.scene.SceneController;
import deadwood.scene.SceneModel;
import deadwood.scene.SceneView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class GameSetupService {
    // Private readonly attributes
    private static final String[] playerNames = {"Blue", "Cyan", "Green", "Orange", "Pink", "Red", "Violet", "Yellow"};
    private static final int sceneNumber = 40;

    // XML Paths and Tags
    private static final String boardXmlPath = "src/resources/board.xml";
    private static final String cardXmlPath = "src/resources/cards.xml";
    private static final String setTag = "set";
    private static final String nameTag = "name";
    private static final String numberTag = "number";
    private static final String levelTag = "level";
    private static final String cardTag = "card";
    private static final String budgetTag = "budget";

    static PlayerController[] initializePlayers(RoomController trailers, int playerCount) {
        PlayerController[] players = new PlayerController[playerCount];
        for (int playerIndex = 0; playerIndex < playerCount; ++playerIndex) {
            players[playerIndex] = new PlayerController(new PlayerModel(playerNames[playerIndex], trailers));
        }
        return players;
    }

    static void initializeBoard() throws ParserConfigurationException, IOException, SAXException {
        Board gameBoard = Board.board();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        File file = new File(boardXmlPath);
        Document doc = builder.parse(file);

        NodeList rooms = doc.getElementsByTagName(setTag);
        for (int roomIndex = 0; roomIndex < rooms.getLength(); ++roomIndex) {
            Node roomData = rooms.item(roomIndex);

            String roomName = roomData.getAttributes().getNamedItem(nameTag).getNodeValue();

            NodeList roomDetailData = roomData.getChildNodes();
            int numberOfTakes = Integer.parseInt(roomDetailData.item(5).getChildNodes().item(1).getAttributes()
                    .getNamedItem(numberTag).getNodeValue());
            NodeList parts = roomDetailData.item(7).getChildNodes();

            ActingRoomModel room = new ActingRoomModel(roomName, parts.getLength(), numberOfTakes);

            NodeList neighbors = roomDetailData.item(1).getChildNodes();
            for (int neighborIndex = 1; neighborIndex < neighbors.getLength(); neighborIndex += 2) {
                NamedNodeMap neighborInfo = neighbors.item(neighborIndex).getAttributes();
                room.addToAdjList(neighborInfo.getNamedItem(nameTag).getNodeValue().toLowerCase());
            }

            for (int partIndex = 1; partIndex < parts.getLength(); partIndex += 2) {
                Node roleData = parts.item(partIndex);
                NamedNodeMap roleTextData = roleData.getAttributes();
                Node lineInfo = roleData.getChildNodes().item(3).getFirstChild();
                RoleModel roleModel = new RoleModel(roleTextData.getNamedItem(nameTag).getNodeValue(),
                        Integer.parseInt(roleTextData.getNamedItem(levelTag).getNodeValue()),
                        lineInfo.getNodeValue(), false);
                RoleView roleView = new RoleView(roleModel);
                room.setRole(new RoleController(roleModel, roleView), partIndex);
            }

            ActingRoomView roomView = new ActingRoomView(room);
            ActingRoomController roomController = new ActingRoomController(room, roomView);
            gameBoard.addRoom(roomController.roomName().toLowerCase(), roomController);
        }
    }

    static SceneController[] initializeScenes() throws ParserConfigurationException, IOException, SAXException {
        SceneController[] unusedScenes = new SceneController[sceneNumber];

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        File file = new File(cardXmlPath);
        Document doc = builder.parse(file);

        NodeList cards = doc.getElementsByTagName(cardTag);
        for (int cardIndex = 0; cardIndex < cards.getLength(); ++cardIndex) {
            Node card = cards.item(cardIndex);

            NamedNodeMap cardDetail = card.getAttributes();
            String sceneName = cardDetail.getNamedItem(nameTag).getNodeValue();
            int budget = Integer.parseInt(cardDetail.getNamedItem(budgetTag).getNodeValue());

            NodeList sceneInfo = card.getChildNodes();
            Node sceneDetail = sceneInfo.item(1);

            String sceneText = sceneDetail.getFirstChild().getNodeValue();

            // New lines in XML lead to extra nodes in the list which need to be accounted for
            int roleCount = (sceneInfo.getLength() - 3) / 2;

            SceneModel sceneModel = new SceneModel(sceneName, roleCount, budget, sceneText);
            // Index for adding into SceneModel
            int scenePartIndex = 0;
            for (int partIndex = 3; partIndex < sceneInfo.getLength(); partIndex += 2) {
                Node part = sceneInfo.item(partIndex);
                NamedNodeMap partDetail = part.getAttributes();

                String partName = partDetail.getNamedItem(nameTag).getNodeValue();
                int level = Integer.parseInt(partDetail.getNamedItem(levelTag).getNodeValue());

                String line = part.getChildNodes().item(3).getFirstChild().getNodeValue();

                RoleModel roleModel = new RoleModel(partName, level, line, true);
                RoleView roleView = new RoleView(roleModel);
                sceneModel.setRole(new RoleController(roleModel, roleView), scenePartIndex);

                ++scenePartIndex;
            }

            SceneView sceneView = new SceneView(sceneModel);

            unusedScenes[cardIndex] = new SceneController(sceneModel, sceneView);
        }

        return unusedScenes;
    }

    static ArrayList<Integer> initializeSceneGenerator() {
        ArrayList<Integer> sceneGenerator = new ArrayList<>();
        for (int sceneIndex = 0; sceneIndex < sceneNumber; ++sceneIndex) {
            sceneGenerator.add(sceneIndex);
        }
        Collections.shuffle(sceneGenerator);
        return sceneGenerator;
    }
}
