package algorithm;

import java.util.*;

public class Geography {
    public int maxPoints(int[][] points) {
        TreeMap<Direction, Integer> lineCount = new TreeMap<>();
        if (points.length == 0) {
            return 0;
        }
        int countMax = 1;
        for (int i = 0; i < points.length - 1; i++) {
            lineCount.clear();
            int count = 1, coincidence = 0, vertical = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0]) {
                    if (points[i][1] == points[j][1]) { // coincidence
                        coincidence++;
                    } else { // vertical line
                        vertical++;
                        count = vertical > count ? vertical : count;
                    }
                } else {
                    int d0 = points[i][0] - points[j][0];
                    int d1 = points[i][1] - points[j][1];
                    Direction d = new Direction(d0, d1);
                    int p = lineCount.getOrDefault(d, 1) + 1;
                    lineCount.put(d, p);
                    count = p > count ? p : count;
                }
            }
            count += coincidence;
            countMax = count > countMax ? count : countMax;
        }
        return countMax;
    }

    class Direction implements Comparable<Direction> {
        int d0, d1;

        Direction(int d0, int d1) {
            this.d0 = d0;
            this.d1 = d1;
        }

        @Override
        public int compareTo(Direction direction) {
            long diff = (long)d1 * direction.d0 - (long)direction.d1 * d0;
            if ((d0 > 0 && direction.d0 > 0) || (d0 < 0 && direction.d0 < 0)) {
                // d1/d0 < dd1/dd0 <=> d1*dd0<dd1*d0
                if (diff < 0) {
                    return -1;
                } else if (diff == 0) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                // d1/d0 < dd1/dd0 <=> d1*dd0>dd1*d0
                if (diff < 0) {
                    return 1;
                } else if (diff == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }
    }
}
