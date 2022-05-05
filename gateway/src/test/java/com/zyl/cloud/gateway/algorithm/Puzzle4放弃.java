package com.zyl.cloud.gateway.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 寻找两个正序数组的中位数
 */
public class Puzzle4放弃 {

	public static void main(String[] args) {
		Random random = new Random();
		int[] nums1 = new int[5];
		int[] nums2 = new int[5];
		for (int i = 0; i < 5; i++) {
			nums1[i] = random.nextInt(10);
			nums2[i] = random.nextInt(10);
		}
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		Puzzle4放弃 puzzle4放弃 = new Puzzle4放弃();
		// int[] nums1 = {1, 2, 3, 4, 5, 6};
		// int[] nums2 = {2, 3, 9, 77, 88};
		int result = puzzle4放弃.myVersion(nums1, nums2);
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		return 0;
	}

	/**
	 * 取两个数组的中位比大小 大的左移一位 小的右移一位 直到找到互有大小的四个 最大的右边的一定在右边 最小的左边一定在左边
	 * 比较左边和右边的大小 for循环从大值到(size1+size2)/2-大值 左边的
	 */
	private int myVersion(int[] nums1, int[] nums2) {
		int size1 = nums1.length;
		int size2 = nums2.length;
		int mid1 = nums1[size1 / 2];
		int mid2 = nums2[size2 / 2];
		int[] resultArray = new int[size1 + size2];

		int a = 0;
		int b = 0;
		for (int i = 0; i < size1 + size2; i++) {
			if (a == size1) {
				if (b != size2) {
					resultArray[i] = nums2[b];
					b++;
					continue;
				}
			}
			if (b == size2) {
				if (a != size1) {
					resultArray[i] = nums1[a];
					a++;
					continue;
				}
			}
			if (nums1[a] <= nums2[b]) {
				resultArray[i] = nums1[a];
				a++;
			} else if (nums1[a] > nums2[b]) {
				resultArray[i] = nums2[b];
				b++;
			}
			System.out.println(resultArray[i]);
			System.out.println(resultArray[i]);
		}
		return 0;
	}

}
