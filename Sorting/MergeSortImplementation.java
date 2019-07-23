public class MergeSortImplementation{

    public static int[] count;
    public static int rightCount = 0;

    public static void mergeSort(int[] arr, int l, int h){
        if(l < h){
            int mid = l + ((h - l) / 2);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, h);
            merge(arr, l, mid, h);
        }
    }

    public static void merge(int[] arr, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int[n2];

        int i = 0, j = 0;

        for(i = 0; i < n1; i++){
            L[i] = arr[p + i];
        }

        for(i = 0; i < n2; i++){
            R[i] = arr[q + 1 + i];
        }

        i = 0; j = 0;
        int c = 0;
        int k = p;
        while(i < n1 && j < n2){
            if(L[i] <= R[j]){
                arr[k++] = L[i++];
                count[c++] = rightCount;
            }
            else{
                arr[k++] = R[j++];
                rightCount++;
            }
        }

        while(i < n1){
            arr[k++] = L[i++];
        }
        while(j < n2){
            arr[k++] = R[j++];
        }
    }


    public static void main(String[] args){
        int[] arr = new int[]{2, 1, 10, 5, 7, 8, 9};
        int len = arr.length;

        count = new int[len];
        rightCount = 0;
        int rightSum = 0;

        mergeSort(arr, 0, len - 1);
        for(int i = 0; i < len; i++){
            System.out.print(arr[i] + " ");
            rightSum += count[i];
        }
        System.out.println();
        System.out.println("count > " +rightSum);
    }
}