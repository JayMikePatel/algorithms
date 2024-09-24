import java.util.*;

public class GraphDFS {
    private Map<Character, List<Character>> adjList;
    private boolean directed;

    public Graph(boolean directed) {
        this.adjList = new HashMap<>();
        this.directed = directed;
    }

    public void addVertex(char vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(char source, char destination) {
        if (!adjList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjList.containsKey(destination)) {
            addVertex(destination);
        }
        adjList.get(source).add(destination);
        if (!directed) {
            adjList.get(destination).add(source);
        }
    }
    public List<Character> getNeighbors(char vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }

    public void depthFirstTraversal(char start) {
        Set <Character> visited = new HashSet<>();
        List <Character> path = new ArrayList<>();
        searchFrom(start, visited, path);
    }

    private void searchFrom(char vertex, Set<Character> visited, List<Character> currentPath) {
        if (visited.contains(vertex)) {
            return;
        }

        currentPath.add(vertex);
        visited.add(vertex);

        if (deadEnd(vertex, visited)) {
            if(currentPath.contains('M')) {
                System.out.println("Found valid path: " + currentPath);
            } else {
                System.out.println("Found invalid path: " + currentPath);
            }
        }

        for (char neighbor: getNeighbors(vertex)) {
            searchFrom(neighbor, visited, new ArrayList<> (currentPath));
        }

        currentPath.remove(currentPath.size() - 1);
        visited.remove(vertex);
    }

    private boolean deadEnd(char vertex, Set<Character> visited) {
        for (char neighbor: getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true);

        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('D', 'E');
        graph.addEdge('E', 'L');
        graph.addEdge('L', 'K');
        graph.addEdge('A', 'H');
        graph.addEdge('H', 'G');
        graph.addEdge('G', 'F');
        graph.addEdge('F', 'K');
        graph.addEdge('K', 'F');
        graph.addEdge('F', 'G');
        graph.addEdge('G', 'H');
        graph.addEdge('L', 'E');
        graph.addEdge('E', 'D');
        graph.addEdge('D', 'C');
        graph.addEdge('C', 'B');
        graph.addEdge('K', 'J');
        graph.addEdge('K', 'L');
        graph.addEdge('J', 'I');
        graph.addEdge('J', 'O');
        graph.addEdge('O', 'P');
        graph.addEdge('O', 'N');
        graph.addEdge('N', 'M');

        System.out.println("All Paths:");
        graph.depthFirstTraversal('A');

    }
}
