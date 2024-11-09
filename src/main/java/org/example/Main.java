package org.example;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int[] createArrayWithRandomData(int size, int randomMaxValue) {
        int[] arr = new int[size];
        Random random = new Random();
        for(int i = 0; i < size; i++) {

            arr[i] = random.nextInt(randomMaxValue);
        }

        return arr;
    }

    public static void show(int[] arr) {
        System.out.print("Elements: ");
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {

        int[] ar1 = createArrayWithRandomData(1000, 10);
        int[] ar2 = Arrays.copyOf(ar1, ar1.length);
        int[] ar3 = Arrays.copyOf(ar1, ar1.length);
        show(ar1);
        show(ar2);
        show(ar3);

        long currentTime = 0;

        currentTime = System.nanoTime();
        runJavaSort(ar1);
        System.out.println("runJavasort: " + (System.nanoTime() - currentTime));

        currentTime = System.nanoTime();
        bubbleSort(ar2);
        System.out.println("bubbleSort: " + (System.nanoTime() - currentTime));

        currentTime = System.nanoTime();
        int[] newArr = countingSort(ar3);
        System.out.println("counting sort: " + (System.nanoTime() - currentTime));

    }

    public static void runJavaSort(int[] arr) {
        Arrays.sort(arr);
    }

    public static void bubbleSort(int[] arr) {

         int i = arr.length - 1;

         while(i >= 0) {
             for (int j = 0; j <= i - 1; j++) {
                 if(arr[j] > arr[j+1]) {
                     int temp = arr[j];
                     arr[j] = arr[j+1];
                     arr[j + 1] = temp;
                 }
             }
             i--;
         }
    }

    public static int[] countingSort(int[] arr) {
        //Assume we know the range.
        int[] countArr = new int[10];
        int[] outputArr = new int[arr.length];
        for(int i : arr) {
            countArr[i]++;
        }

        for(int i = 0; i < countArr.length - 1; i++) {
            countArr[i + 1] += countArr[i];
        }

        for(int i = arr.length - 1; i >= 0; i--) {
            int curr = arr[i];
            countArr[curr]--;

            outputArr[countArr[curr]] = curr;
        }
        return outputArr;

    }
}