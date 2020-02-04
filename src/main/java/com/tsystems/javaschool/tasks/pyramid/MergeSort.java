package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.List;

public class MergeSort { // non-static
    private static Integer[] merge(Integer[] A, int p, int q, int r) {
        int[] buffer = new int[r-p+1]; // вспомогательный буферный массив, значения которого впоследствии вставятся в основной
        int i = 0, j = 0;
        while (p+i<=q && q+j+1<=r) { // сливаем две части в буферный массив, пока один из них не закончится
            if (A[p+i] < A[q+j+1]) {
                buffer[i+j] = A[p+i];
                i++;
            } else {
                buffer[i+j] = A[q+j+1];
                j++;
            }
        }
        if (p+i>q) { // вставляем в конец буферного массива оставшуюся часть оставшейся половины
            while (q+j+1<=r) { // если осталось в правой части
                buffer[j+i]=A[q+j+1];
                j++;
            }
        } else {
            while (p+i<=q) {// если осталось в левой части
                buffer[j+i]=A[p+i];
                i++;
            }
        }
        for (int it = 0; it < r - p + 1; it ++) { // записываем значения буферного массива в основной
            A[p+it] = buffer[it];
        }
        return A;
    }

    private static Integer[] sort(Integer[] A, int p, int r) {
        if (p < r) {
            int q = (int)Math.floor((p+r)/2);
            A = sort(A,p,q);
            A = sort(A,q+1,r);
            A = merge(A,p,q,r);
        }
        return A;
    }

    public static List<Integer> sortList(List<Integer> arrayList) {
        Integer[] array = new Integer[arrayList.size()];
        arrayList.toArray(array);
        return Arrays.asList(sort(array, 0, array.length-1));
    }
}