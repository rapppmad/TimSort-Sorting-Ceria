package HackerRankPraktikum.Sorting;

import java.util.Arrays;
import java.util.Scanner;

//Baris pertama berisi bilangan n yang menunjukan banyaknya elemen dalam array.
// Baris selanjutnya berisi data d sebanyak n berupa angka yang harus diurutkan.

//Notes !!!
//Kumpulan semua bilangan d yang sudah diurutkan, dipisahkan dengan spasi
    public class TimSort {
        private static final int finalNumber = 32;
        public static void timSort(int[] listArray, int x) {
            for (int i = 0; i < x; i += finalNumber) {
                if (i + finalNumber <= x) {
                    Arrays.sort(listArray, i, i + finalNumber);
                } else {
                    insertionSort(listArray, i, x - 1);
                }
            }
            for (int size = finalNumber; size < x; size = 2 * size) {
                for (int left = 0; left < x; left += 2 * size) {
                    int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (x - 1));
                    merge(listArray, left, mid, right);
                }
            }
        }
    public static void insertionSort(int[] listArray, int kiri, int kanan) {
        for (int i = kiri + 1; i <= kanan; i++) {
            int key = listArray[i];
            int j = i - 1;
            while (j >= kiri && listArray[j] > key) {
                listArray[j + 1] = listArray[j];
                j--;
            }
            listArray[j + 1] = key;
        }
    }
    public static void merge(int[] listArray, int kiri, int tengah, int kanan) {
        int len1 = tengah - kiri + 1;
        int len2 = kanan - tengah;
        int[] temp = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (listArray[kiri + i] <= listArray[tengah + 1 + j]) {
                temp[k] = listArray[kiri + i];
                i++;
            } else {
                temp[k] = listArray[tengah + 1 + j];
                j++;
            }
            k++;
        }
        while (i < len1) {
            temp[k] = listArray[kiri + i];
            i++;
            k++;
        }
        while (j < len2) {
            temp[k] = listArray[tengah + 1 + j];
            j++;
            k++;
        }
        for (int p = 0; p < k; p++) {
            listArray[kiri + p] = temp[p];
        }
    }
    public static void main(String[] args) {
        try (Scanner inputUser = new Scanner(System.in)) {
            int ukuranData = inputUser.nextInt();
            int[] data = new int[ukuranData];

            for (int i = 0; i < ukuranData; i++) {
                data[i] = inputUser.nextInt();
            }
            timSort(data, ukuranData);
            for (int i : data) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}