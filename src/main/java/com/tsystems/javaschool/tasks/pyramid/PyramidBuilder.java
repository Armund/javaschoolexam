package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class PyramidBuilder {
    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
        long S = 0;
        int rows = 0;
        long buf;
        for (int i = 1; S < inputNumbers.size(); i++) {
            buf = i;//(long) i;
            S = (buf*buf+buf)/2;
            rows = i;
        }
        if (S != inputNumbers.size()) {
            throw new CannotBuildPyramidException();
        }

        try {
            inputNumbers = MergeSort.sortList(inputNumbers);
        } catch (Exception e) {
            throw new CannotBuildPyramidException();
            //e.printStackTrace();
        }

        int columns = rows*2-1;
        int[][] resultArray = new int[rows][columns];
        int position;
        int inputPointer = 0;
        boolean insert;
        for (int i = 0; i < rows; i++) {
            insert = true;
            for (int j = 0; j < columns ; j++) {
                resultArray[i][j] = 0;
                position = i + j;
                if ((position >= (columns-1)/2)&&(position <= (columns-1)/2 + 2 * i)) {
                    if (insert) {
                        resultArray[i][j] = inputNumbers.get(inputPointer);
                        inputPointer++;
                        insert = false;
                    } else {
                        insert = true;
                    }
                }
            }
        }

        return resultArray;
    }


}
