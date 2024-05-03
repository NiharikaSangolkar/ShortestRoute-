package Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestRoute {
    public static void main(String[] args) {
        // Define the graph as a list of edges
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 1));
        edges.add(new Edge("B", "C", 3));
        edges.add(new Edge("B", "E", 3.5));
        // ...

        // Define the start and end nodes
        String startNode = "C";
        String endNode = "F";

        // Find the shortest route
        List<String> route = findShortestRoute(edges, startNode, endNode);

        // Print the route
        System.out.println("Shortest Route: " + route);
    }

    public static List<String> findShortestRoute(List<Edge> edges, String startNode, String endNode) {
        // Create a map to store the distance to each node
        Map<String, Double> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        // Initialize distances to infinity and previous nodes to null
        for (Edge edge : edges) {
            distance.put(edge.from, Double.POSITIVE_INFINITY);
            distance.put(edge.to, Double.POSITIVE_INFINITY);
            previous.put(edge.from, null);
            previous.put(edge.to, null);
        }

        distance.put(startNode, 0.0);

        // Loop through the edges
        for (int i = 0; i < distance.size() - 1; i++) {
            for (Edge edge : edges) {
                double newDistance = distance.get(edge.from) + edge.weight;
                if (newDistance < distance.get(edge.to)) {
                    distance.put(edge.to, newDistance);
                    previous.put(edge.to, edge.from);
                }
            }
        }

        // Build the shortest path by backtracking from the end node
        List<String> route = new ArrayList<>();
        String currentNode = endNode;
        while (currentNode != null) {
            route.add(0, currentNode);
            currentNode = previous.get(currentNode);
        }

        return route;
    }

    public static class Edge {
        String from;
        String to;
        double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
