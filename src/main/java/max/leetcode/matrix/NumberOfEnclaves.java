package max.leetcode.matrix;

public class NumberOfEnclaves {

    public static void main(String[] args) {

        NumberOfEnclaves java = new NumberOfEnclaves();

        System.out.println("# grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] - '"+java.numEnclaves(new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}})+"' == '3'");
        System.out.println("# grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]] - '"+java.numEnclaves(new int[][]{{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}})+"' == '0'");
    }

    public int numEnclaves(int[][] grid) {
        if (grid.length < 2 || grid[0].length < 2)
            return 0; // [1, 1, 1] => 0

        final int lrow = grid.length - 1, lcol = grid[0].length - 1; // last index
        int enclaves = 0, count = 0;
        Map map = Map.init(grid);
        Cell current = map.next();
        do {
            int row = current.row, col = current.col;
            System.out.print(". grid[" + row + "][" + col + "] = " + grid[row][col]);
            if (grid[row][col] == 1) {
                Cell next = findNeighbors(current, grid, row, col, lrow, lcol, map);
                System.out.println(", neighbors = " + next.log(""));
                if (!next.land) {
                    enclaves += next.length(0);
                }
            }
            current = map.next(); // look for the next available cell
        } while (current != null);
        return enclaves;
    }

    private static Cell findNeighbors(Cell cell, int[][] grid, int row, int col, int lrow, int lcol, Map map) {
        cell = findNeighbor(cell, grid, row-1, col, lrow, lcol, map); // north
        cell = findNeighbor(cell, grid, row+1, col, lrow, lcol, map); // south
        cell = findNeighbor(cell, grid, row, col-1, lrow, lcol, map); // east
        cell = findNeighbor(cell, grid, row, col+1, lrow, lcol, map); // west
        return cell;
    }

    private static Cell findNeighbor(Cell cell, int[][] grid, int row, int col, int lrow, int lcol, Map map) {
        boolean boundary = row == 0 || row == lrow || col == 0 || col == lcol;
        if (grid[row][col] == 1) {
            if (boundary) {
                return Cell.land(); // failed due to land
            } else {
                System.out.print(", check[" + row + "," + col + "]="+grid[row][col] + ", free=" + map.free(row, col));
                if (map.free(row, col)) {
                    map.mark(row, col);
                    Cell neighbor = Cell.coordinate(row, col).append(cell);
                    System.out.print(", found[" + row + "," + col + "] ");
                    return findNeighbors(neighbor, grid, row, col, lrow, lcol, map);
                }
            }
        }
        return cell; // skip neighbor due to water
    }

    static class Map {
        final boolean[][] map;
        final int[][] grid;

        Map(int[][] grid) {
            this.grid = grid;
            this.map = new boolean[grid.length-2][grid[0].length-2];
        }

        static Map init(int[][] grid) {
            return new Map(grid);
        }

        void mark(int row, int col) {
            map[row-1][col-1] = true;
        }

        boolean free(int row, int col) {
            return !map[row-1][col-1]; // not marked
        }

        Cell next() {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[0].length; col++) {
                    if (!map[row][col]) {
                        map[row][col] = true;
                        if (grid[row+1][col+1] == 1) { // next cell with land
                            return Cell.coordinate(row+1, col+1);
                        }
                    }
                }
            }
            return null; // no more available
        }
    }

    static class Cell {
        int row;
        int col;
        boolean land;
        boolean sea;
        Cell next;

        static Cell coordinate(int row, int col) {
            Cell cell = new Cell();
            cell.row = row;
            cell.col = col;
            return cell;
        }

        static Cell land() {
            Cell cell = new Cell();
            cell.land = true;
            return cell;
        }

        static Cell sea() {
            Cell cell = new Cell();
            cell.sea = true;
            return cell;
        }

        Cell append(Cell next) {
            this.next = next;
            this.land = next.land;
            return this;
        }

        int length(int count) {
            if (next != null) {
                count += next.length(count);
            }
            return count+1;
        }

        String log(String log) {
            if (next != null) {
                log += ", next" + next.log(log);
            }
            return "("+ "parent[" + row + "," + col + "]" + log + ")";
        }
    }
}
