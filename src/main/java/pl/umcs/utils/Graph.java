package pl.umcs.utils;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.umcs.map.Map;

import java.util.*;

public class Graph {
    @Getter
    @Setter
    public static class Node {
        int x;
        int y;

        int cost;

        List<Node> history;

        public Node(int x, int y, int cost) {
            this(x, y, cost, new ArrayList<>());
        }

        Node(int x, int y, int cost, List<Node> history) {
            this.x = x;
            this.y = y;

            this.cost = cost;

            this.history = history;
        }
    }

    public static @Nullable List<Node> BFS(@NotNull Map map, @NotNull Node root, Node target) {
        short[] offsetX = {-1, 0, 1, 0};
        short[] offsetY = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        HashSet<Node> visited = new HashSet<>();
        visited.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (int idx = 0; idx < 4; idx++) {
                int neighbourX = currentNode.getX() + offsetX[idx];
                int neighbourY = currentNode.getY() + offsetY[idx];
                int neighbourCost = currentNode.getCost() + 1;

                List<Graph.Node> neighbourHistory = new ArrayList<>(currentNode.getHistory());
                neighbourHistory.add(currentNode);

                if (neighbourX == target.getX() && neighbourY == target.getY()) {
                    neighbourHistory.add(target);
                    return neighbourHistory;
                }

                if (map.canPlaceEntity(neighbourX, neighbourY)) {
                    if (visited.stream()
                            .filter(n -> n.getX() == neighbourX && n.getY() == neighbourY)
                            .findAny()
                            .isEmpty()) {
                        Node neighbour =
                                new Node(
                                        neighbourX,
                                        neighbourY,
                                        neighbourCost,
                                        neighbourHistory);

                        visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }

        return null;
    }
}
