package wooter.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays_0(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length, length_m = length1 + length2;
        int[] merge = new int[length_m];

        for (int m = 0, i1 = 0, i2 = 0; i1 < nums1.length || i2 < nums2.length; m++) {
            if (i1 == length1) {
                merge[m] = nums2[i2];
                i2++;
            } else if (i2 == length2) {
                merge[m] = nums1[i1];
                i1++;
            } else if (nums1[i1] <= nums2[i2]) {
                merge[m] = nums1[i1];
                i1++;
            } else {
                merge[m] = nums2[i2];
                i2++;
            }
        }
        System.out.println(Arrays.toString(merge));

        if (length_m % 2 == 1) {
            return merge[length_m / 2];
        } else {
            return (merge[length_m / 2] + merge[length_m / 2 - 1]) / 2.0;
        }
    }

    public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double r = new MedianOfTwoSortedArrays().findMedianSortedArrays_1(nums1, nums2);
        System.out.println(r);
    }

}
