public class InversionCount {

    public static void main(String[] args) {
        int[] arr = { 2, 4, 1, 3, 5 };
        int[] temp = new int[arr.length];
        int inversionCount = mergeSortAndCount(arr, temp, 0, arr.length - 1);
        System.out.println("Total Inversions: " + inversionCount);
    }

    // Recursive Merge Sort function
    public static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        int mid, inversionCount = 0;
        if (right > left) {
            mid = (left + right) / 2;

            // Count inversions in left half
            inversionCount += mergeSortAndCount(arr, temp, left, mid);

            // Count inversions in right half
            inversionCount += mergeSortAndCount(arr, temp, mid + 1, right);

            // Count split inversions (cross merge)
            inversionCount += mergeAndCount(arr, temp, left, mid + 1, right);
        }
        return inversionCount;
    }

    public static int mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid;
        int k = left;
        int invCount = 0;

        while (i <= mid - 1 && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

                invCount += (mid - i);
            }
        }

        while (i <= mid - 1) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return invCount;
    }
}
