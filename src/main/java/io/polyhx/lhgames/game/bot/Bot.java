package io.polyhx.lhgames.game.bot;

import io.polyhx.lhgames.game.GameInfo;
import io.polyhx.lhgames.game.Map;
import io.polyhx.lhgames.game.Player;
import io.polyhx.lhgames.game.action.CollectAction;
import io.polyhx.lhgames.game.action.IAction;
import io.polyhx.lhgames.game.action.MeleeAttackAction;
import io.polyhx.lhgames.game.action.MoveAction;
import io.polyhx.lhgames.game.point.IPoint;
import io.polyhx.lhgames.game.point.Point;
import io.polyhx.lhgames.game.tile.ResourceTile;
import io.polyhx.lhgames.game.tile.Tile;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Bot extends BaseBot {
    class PathNode {
        private Tile fTile;
        private PathNode fParent;
        private int fDistance;

        PathNode(Tile tile, PathNode parent) {
            fTile = tile;
            fParent = parent;
            fDistance = (parent == null) ? 0 : parent.fDistance + 1;
            //if(tile.isWall()) fDistance = Integer.MAX_VALUE;
        }
    }

    private PathNode getNode(HashMap<Tile, PathNode> visited, PathNode origin, Tile requested) {
        if (requested == null) {
            return null;
        }
        PathNode node = visited.get(requested);
        if (node == null) {
            node = new PathNode(requested, origin);
            visited.put(requested, node);
        }
        return node;
    }

    private void updateNode(PathNode node, PathNode adjacent) {
        if (node == null) {
            return;
        }
        if (node.fParent == null) {
            return;
        }
        if (node.fTile.isWall()) {
            return;
        }
        if (node.fTile.isLava()) {
            return;
        }
        if (node.fTile.isHouse()) {
            return;
        }
        if (adjacent.fDistance + 1 < node.fDistance) {
            node.fParent = adjacent;
            node.fDistance = adjacent.fDistance + 1;
        }
    }

    private PathNode getPath(Map map, Tile start, Tile end) {
        Deque<Tile> queue = new ArrayDeque<>();
        queue.add(start);

        HashMap<Tile, PathNode> visited = new HashMap<>();
        visited.put(start, new PathNode(start, null));
        while (queue.size() > 0) {
            Tile current = queue.pop();
            PathNode currentNode = visited.get(current);

            Tile above = map.getTileAboveOf(current);
            Tile below = map.getTileBelowOf(current);
            Tile right = map.getTileRightOf(current);
            Tile left = map.getTileLeftOf(current);

            if (above != null && !visited.containsKey(above)) queue.add(above);
            if (below != null && !visited.containsKey(below)) queue.add(below);
            if (right != null && !visited.containsKey(right)) queue.add(right);
            if (left != null && !visited.containsKey(left)) queue.add(left);

            PathNode aboveNode = getNode(visited, currentNode, above);
            PathNode belowNode = getNode(visited, currentNode, below);
            PathNode rightNode = getNode(visited, currentNode, right);
            PathNode leftNode = getNode(visited, currentNode, left);

            updateNode(aboveNode, currentNode);
            updateNode(belowNode, currentNode);
            updateNode(rightNode, currentNode);
            updateNode(leftNode, currentNode);
        }

        PathNode endNode = visited.get(end);
        int finalDistance = endNode.fDistance;
        while (endNode.fParent.fTile != start) endNode = endNode.fParent;

        endNode.fDistance = finalDistance;
        return endNode;
    }

    public Point getRelativePoint(IPoint target, IPoint relative) {
        return new Point(target.getX() - relative.getX(), target.getY() - relative.getY());
    }

    private int state = 0;

    public IAction getAction(Map map, Player player, List<Player> others, GameInfo info) {
        map.print();

        ResourceTile found = null;
        for (ResourceTile resource : map.getResources()) {
            Point house = player.getHousePosition();
            System.out.println(resource.getPosition().toJSON() + "|" + resource.getDistanceTo(house));
            if (found == null) {
                found = resource;
                continue;
            }
            if (found.getDistanceTo(house) > resource.getDistanceTo(house)) {
                found = resource;
            }
        }

        if (found != null) {
            /* we want to go from player to resource */
            Tile start = map.getTile(player.getPosition().getX(), player.getPosition().getY());
            Tile end = found;

            System.out.println("WINNER:" + end.getPosition().toJSON());

            /* get the next node */
            boolean goingHome = false;
            if (player.getCarriedResource() == player.getResourceCapacity()) {
                end = map.getTile(player.getHousePosition());
                goingHome = true;
            }
            PathNode node = getPath(map, start, end);

            /* get the direction to go to this node */
            Point direction = getRelativePoint(node.fTile, start);
            System.out.println(start.getPosition().toJSON() + " " + end.getPosition().toJSON());

            /* move to or collect the resource */
            if (node.fDistance == 1 && !goingHome) {
                return new CollectAction(direction);
            } else {
                return new MoveAction(direction);
            }
        }

        Tile tile = map.getTile(player.getPosition().getX() + 1, player.getPosition().getY());
        if (tile != null && tile.isWall()) {
            return new MeleeAttackAction(Point.RIGHT);
        }
        return new MoveAction(Point.RIGHT);
    }
}
