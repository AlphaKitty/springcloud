package com.zyl.cloud.gateway.algorithm;

/**
 * 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 * <p>
 * 示例 1：输入：x = 123 输出：321
 * 示例 2：输入：x = 120 输出：21
 */
public class Puzzle7 {

	public static void main(String[] args) {
		Puzzle7 puzzle7 = new Puzzle7();
		System.out.println(puzzle7.myVersion(1000000003));
	}

	//
	// """321 = 123/100*100 + 123-20/10 + 3
	// 		begin=6543     end=3456
	// a0=0             b0=0              mult=1 [mult*=10]最后
	// a1=begin%10mult    b1=(a1-a0)/mult=3     mult=10
	// a2=begin%10mult    b2=(a2-a1)/mult=4     mult=100
	// a3=begin%10mult    b3=(a3-a2)/mult=5     mult-1000
	// ...
	//
	// """

	/**
	 * 想太复杂了 根本不用数字拆成字符串再拼 也不能让个位数一步到位变成最高位 而是从头到尾看成一个数字变成另一个数字的过程
	 */
	public int myVersion(int x) {
		return 0;
	}

	public int betterVersion(int x) {
		long n = 0;
		while (x != 0) {
			n = n * 10 + x % 10;
			x = x / 10;
		}
		return (int) n == n ? (int) n : 0;
	}

}
