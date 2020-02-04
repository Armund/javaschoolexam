package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here

        try {
            if (x.size() > y.size()) {
                return false;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
            //e.printStackTrace();
        }
        int counterY = 0;
        boolean found;
        for (int counterX = 0; counterX < x.size(); counterX++) {
            found = false;
            while (!found) {
                if (x.get(counterX).equals(y.get(counterY))) {
                    found = true;
                }
                counterY++;
                if (!found && counterY == y.size()) {
                    return false;
                }
            }
        }
        return true;
    }
}
