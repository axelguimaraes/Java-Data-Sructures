package mygame.game;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mygame.structs.graphs.LinkedGraph;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class Map {
    private LinkedGraph<Local> mapGraph;

    public Map() {
        mapGraph = new LinkedGraph<>();
    }

    public void addLocal(Local local) {
        mapGraph.addVertex(local);
    }

    public void removeLocal(Local local) {
        mapGraph.removeVertex(local);
    }

    public void addRoute(Local from, Local to) {
        mapGraph.addEdge(from, to);
    }

    public void removeRoute(Local from, Local to) {
        mapGraph.removeEdge(from, to);
    }

    public Iterator<Local> getBFSIterator(Local start) {
        return mapGraph.iteratorBFS(start);
    }

    public Iterator<Local> getDFSIterator(Local start) {
        return mapGraph.iteratorDFS(start);
    }

    public Iterator<Local> getShortestPathIterator(Local start, Local target) {
        return mapGraph.iteratorShortestPath(start, target);
    }

    public boolean isMapEmpty() {
        return mapGraph.isEmpty();
    }

    public boolean isMapConnected() {
        return mapGraph.isConnected();
    }

    public int getMapSize() {
        return mapGraph.size();
    }

    public void importData(String filePath) throws IOException {
        Gson gson = new Gson();
        Type localListType = new TypeToken<List<LocalData>>(){}.getType();

        FileReader fileReader = new FileReader(filePath);
        List<LocalData> localDataList = gson.fromJson(fileReader, localListType);

        for (LocalData localData : localDataList) {
            Local local = new Local(localData.id, localData.type, localData.coordinates, localData.gameSettings);
            mapGraph.addVertex(local);
        }

        //Add edges to the graph
        for (RouteData routeData : localDataList.routes) {
            Local fromLocal = getLocalById(routeData.from);
            Local toLocal = getLocalById(routeData.to);
            mapGraph.addEdge(fromLocal, toLocal);
        }
    }

    private Local getLocalById(int id) {
        for (Local local : mapGraph) {
            if (local.getId() == id) {
                return local;
            }
        }
        return null;
    }

    private static class LocalData {
        int id;
        String type;
        Coordinates coordinates;
        GameSettings gameSettings;
    }

    private static class RouteData {
        int from;
        int to;
    }

    private static class Coordinates {
        double latitude;
        double longitude;
    }

    private static class GameSettings {
        //fields will vary depending on the type of Local
    }
}
