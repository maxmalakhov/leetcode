package max.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CloneGraph {

    public static void main(String[] args) {

        CloneGraph solution = new CloneGraph();

        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        first.neighbors = List.of(fourth, second);
        second.neighbors = List.of(first, third);
        third.neighbors = List.of(second, fourth);
        fourth.neighbors = List.of(third, first);

        System.out.println("! node = " + first.log("", first.val, new Touch()));
        System.out.println("# adjList = [[2,4],[1,3],[2,4],[1,3]] - '[" + solution.cloneGraph(first).log("", first.val, new Touch()) + "]' == '[[2,4],[1,3],[2,4],[1,3]]'" );
        System.out.println("# adjList = [[]] - '[" + solution.cloneGraph(new Node(1)).log("", 1, new Touch()) + "]' == '[[]]'" );

    }

    public Node cloneGraph(Node node) {
        // base condition
        if (node.neighbors.isEmpty()) {
            return new Node(node.val);
        }
        // traverse recursively
        Node clone = cloneWithNextNeighbors(node, new Touch(), node.val); // stop when it back to first
        System.out.println("! node(" + node.val + ")=" + node + " and clone(" + clone.val + ")=" + clone + " are the same = '" + (node == clone) +"'");
        return clone;
//        return fillPreviousNeighbors(clone, new Touch(), node.val); // stop when it back to first
    }

    private Node cloneWithNextNeighbors(Node node, Touch check, int stop) {
        if(check.touch() && node.val == stop) {
            System.out.println("! end cloning, value = " + node.val);
            return node;
        }
        System.out.println(". clone node, value = " + node.val);
        Node next = cloneWithNextNeighbors(next(node.neighbors, node.val), check, stop);
        return new Node(node.val, new ArrayList<>(Arrays.asList(next, next)));
    }

    private Node fillPreviousNeighbors(Node node, Touch check, int stop) {
        if(check.touch() && node.val == stop) {
            System.out.println("! end fill, value = " + node.val);
            return node;
        }
        System.out.println(". fill node, value = " + node.val);
        return fillPreviousNeighbors(previous(node.neighbors, node.val), check, stop);
    }

    private static Node next(List<Node> neighbors, int val) {
        Optional<Node> next = neighbors.stream()
                .filter(neighbor -> neighbor.val == val + 1)
                .findFirst();

        if (next.isPresent()) {
            return next.get();
        }
        // else let's back to the first
        System.out.println("! next to the first - [" + neighbors.stream().map(node -> String.valueOf(node.val)).collect(Collectors.joining(",")) + "]");
        return neighbors.stream()
                .filter(neighbor -> neighbor.val == 1)
                .findFirst()
                .get();
    }

    private static Node previous(List<Node> neighbors, int val) {
        Optional<Node> previous = neighbors.stream()
                .filter(neighbor -> neighbor.val == val-1)
                .findFirst(); // sure

        if (previous.isPresent()) {
            return previous.get();
        }
        // else let's back to the first
        System.out.println("! previous to the first - [" + neighbors.stream().map(node -> String.valueOf(node.val)).collect(Collectors.joining(",")) + "]");
        return neighbors.stream()
                .filter(neighbor -> neighbor.val > 2)
                .findFirst()
                .get();
    }

    static class Node {

        int val;
        List<Node> neighbors;
        Node() {
            this.val = 0;
            this.neighbors = new ArrayList<>();
        }
        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
        Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }

        String log(String log, int stop, Touch first) {
            // check if back to first
            if((first.touch() && this.val == stop)) {
                System.out.println("! end logging, value = " + this.val);
                return log;
            }
            System.out.println(". log node, value = " + this.val);
            // log current neighbors
            if (neighbors.isEmpty()) {
                return log + "[]";
            }

            final Touch end = new Touch();
            StringBuilder logs = new StringBuilder("[");
            neighbors.forEach(neighbor -> {
                logs.append(neighbor.val);
                logs.append(!end.touch() ? "," : "]");
            });
            // next second neighbor
            Node next = next(neighbors, val);
            logs.append(next.log(log, stop, first));

            return log + logs;
        }
    }

    static class Touch {
        boolean state = false;

        public boolean touch() {
            boolean buffer = state;
            state = true;
            return buffer;
        }
    }
}
