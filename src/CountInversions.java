import java.io.*;

/**
 * Created by dongnanzhy on 7/18/15.
 */
public class CountInversions {
    public int[] readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int[] rst = new int[100000];
        try {
            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                rst[i] = Integer.valueOf(line);
                i++;
            }
            return rst;
        } finally
        {
            br.close();
        }
    }

    private long merge(int[] src, int[] tmp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long count_inversions = 0;
        while (i <= mid && j <= right) {
            if (src[i] <= src[j]) {
                tmp[k] = src[i];
                i++;
            } else {
                tmp[k] = src[j];
                j++;
                count_inversions += mid - i + 1;
            }
            k++;
        }
        while (i <= mid) {
            tmp[k++] = src[i++];
        }
        while (j <= right) {
            tmp[k++] = src[j++];
        }
        for (int index = left; index <= right; index++) {
            src[index] = tmp[index];
        }
        return count_inversions;
    }

    public long count(int[] src, int[] tmp, int left, int right) {
        if (left >= right) {
            return 0;
        }
        long count_inversions = 0;
        int mid = (left + right) / 2;
        count_inversions += count(src, tmp, left, mid);
        count_inversions += count(src, tmp, mid+1, right);
        count_inversions += merge(src, tmp, left, mid, right);
        return count_inversions;
    }

    public static void main(String[] args) throws IOException {
        CountInversions ci = new CountInversions();
        File filename = new File("IntegerArray.txt");
        int[] array = ci.readFile(filename);
        //int[] array = {1,3,5,2,4,6};
        int[] tmp = new int[array.length];
        System.out.println(ci.count(array, tmp, 0, array.length-1));

    }
}
