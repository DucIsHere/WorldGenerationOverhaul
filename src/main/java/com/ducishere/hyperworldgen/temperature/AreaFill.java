package toughasnails.temperature;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.init.ModConfig;
import toughasnails.init.ModTags;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AreaFill {

    public static void fill(Level level, BlockPos pos, PositionChecker checker) {
        fill(level, pos, checker, ModConfig.temperature.nearHeatCoolProximity);
    }

    public static void fill(Level level, BlockPos pos, PositionChecker checker, final int maxDepth) {
        Set<FillPos> checked = Sets.newHashSet();
        Queue<FillPos> queue = new LinkedList<>();
        queue.add(new FillPos(pos, 1, Direction.DOWN));

        while (!queue.isEmpty()) {
            FillPos current = queue.poll();
            if (checked.contains(current)) continue;

            if (tryPassable(checker, level, current)) {
                // Spread west
                FillPos west = current;
                while (west.depth() < maxDepth && tryPassable(checker, level, west)) {
                    checked.add(west);
                    expand(queue, checked, checker, level, west);
                    west = west.west();
                }
                checked.add(west);
                if (!checker.isPassable(level, west)) checker.onSolid(level, west);

                // Spread east
                if (current.depth() < maxDepth) {
                    FillPos east = current.east();
                    while (east.depth() < maxDepth && tryPassable(checker, level, east)) {
                        checked.add(east);
                        expand(queue, checked, checker, level, east);
                        east = east.east();
                    }
                    checked.add(east);
                    if (!checker.isPassable(level, east)) checker.onSolid(level, east);
                }
            } else {
                checker.onSolid(level, current);
                checked.add(current);
            }
        }
    }

    private static void expand(Queue<FillPos> queue, Set<FillPos> checked, PositionChecker checker, Level level, FillPos pos) {
        for (FillPos neighbor : pos.neighbors()) {
            if (tryPassable(checker, level, neighbor)) queue.add(neighbor);
            else {
                checked.add(neighbor);
                checker.onSolid(level, neighbor);
            }
        }
    }

    private static boolean tryPassable(PositionChecker checker, Level level, FillPos pos) {
        boolean passable = checker.isPassable(level, pos);
        if (passable) checker.onPassable(level, pos);
        return passable;
    }

    public interface PositionChecker {
        void onSolid(Level level, FillPos pos);

        default void onPassable(Level level, FillPos pos) { }

        default boolean isPassable(Level level, FillPos pos) {
            BlockState state = level.getBlockState(pos.pos());
            return state.isAir() || state.is(ModTags.Blocks.PASSABLE_BLOCKS) || isNeutralBlock(level, pos, state);
        }

        default boolean isConfined(Level level, BlockPos pos) {
            return pos.getY() < level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos).below().getY();
        }

        default boolean isFlowBlocking(Level level, FillPos pos, BlockState state) {
            return state.isFaceSturdy(level, pos.pos(), pos.source()) ||
                   state.isFaceSturdy(level, pos.pos(), pos.source().getOpposite());
        }

        default boolean isNeutralBlock(Level level, FillPos pos, BlockState state) {
            return !isFlowBlocking(level, pos, state) &&
                   !TemperatureHelper.isHeatingBlock(state) &&
                   !TemperatureHelper.isCoolingBlock(state);
        }
    }

    public record FillPos(BlockPos pos, int depth, Direction source) {
        public FillPos north() { return new FillPos(pos().north(), depth() + 1, Direction.SOUTH); }
        public FillPos south() { return new FillPos(pos().south(), depth() + 1, Direction.NORTH); }
        public FillPos east()  { return new FillPos(pos().east(), depth() + 1, Direction.WEST); }
        public FillPos west()  { return new FillPos(pos().west(), depth() + 1, Direction.EAST); }
        public FillPos above() { return new FillPos(pos().above(), depth() + 1, Direction.DOWN); }
        public FillPos below() { return new FillPos(pos().below(), depth() + 1, Direction.UP); }

        public FillPos[] neighbors() {
            return new FillPos[]{north(), south(), east(), west(), above(), below()};
        }
    }
}
