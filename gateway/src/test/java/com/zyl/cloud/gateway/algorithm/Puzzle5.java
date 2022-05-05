package com.zyl.cloud.gateway.algorithm;

/**
 * 最长回文子串(abccba abcba abbcbb abbbba abbcdbba abbcddcbba)
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/
 */
public class Puzzle5 {

	public static void main(String[] args) {
		Puzzle5 puzzle5 = new Puzzle5();
		String s = "abcba";
		System.out.println(puzzle5.toolVersion(s));
	}

	public static int findLongest(char[] str, int low, int[] range) {
		// 查找中间部分
		int high = low;
		while (high < str.length - 1 && str[high + 1] == str[low]) {
			high++;
		}
		// 定位中间部分的最后一个字符
		int ans = high;
		// 从中间向左右扩散
		while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
			low--;
			high++;
		}
		// 记录最大长度
		if (high - low > range[1] - range[0]) {
			range[0] = low;
			range[1] = high;
		}
		// 这里和我想的一样 因为出现连续重复字符时不管奇偶都是回文 所以取最后一个重复字符看做中间字符
		return ans;
	}

	/**
	 * 评论里找到的更好的方案
	 * 比我的方案好的地方在于回文本身是对称的 可以用向相反方向的两个游标表示回文 而不是一个
	 */
	public String betterVersion(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		// 保存起始位置，测试了用数组似乎能比全局变量稍快一点
		int[] range = new int[2];
		char[] str = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			// 把回文看成中间的部分全是同一字符，左右部分相对称
			// 找到下一个与当前字符不同的字符
			i = findLongest(str, i, range);
		}
		return s.substring(range[0], range[1] + 1);
	}

	/**
	 * 执行用时： 11 ms, 在所有 Java 提交中击败了 91.55%的用户
	 * 内存消耗： 41 MB, 在所有 Java 提交中击败了 90.76%的用户
	 * 通过测试用例： 180 / 180
	 * <p>
	 * 游标1:遍历游标
	 * 游标2:最大回文下标 回文(①等于第n-1个字符②等于第n-2个字符) 数组[0]=字符下标 数组[1]=最大偶回文数 数组[2]=最大奇回文数
	 * length:最长回文子串长度
	 */
	private String myVersion(String s) {
		if (null == s || 0 == s.length()) {
			return "";
		}
		if (1 == s.length()) {
			return s;
		}
		char[] chars = s.toCharArray();
		if (2 == s.length()) {
			return chars[0] == chars[1] ? s : String.valueOf(chars[0]);
		}

		// TODO: 2022/4/26 zylTodo 错误1: 忘记考虑长度大于2但是没有任何回文多于1个字符的情况
		int[] p = {0, 1, 1};
		int length = 0;

		for (int i = 1; i < chars.length; i++) {
			// 如果偶回文 找最大回文
			if (chars[i] == chars[i - 1]) {
				for (int o = 0; o < Math.min(chars.length - i, i); o++) {
					if (chars[i + o] == chars[i - o - 1]) {
						p[1] = 2 * (o + 1);
					} else {
						break;
					}
				}
			}
			// 如果奇回文 找最大回文
			if (i > 1 && chars[i] == chars[i - 2]) {
				for (int j = 0; j < Math.min(chars.length - i, i - 1); j++) {
					if (chars[i + j] == chars[i - j - 2]) {
						p[2] = 2 * (j + 1) + 1;
					} else {
						break;
					}
				}
			}
			if (length < Math.max(p[1], p[2])) {
				p[0] = i;
				length = Math.max(p[1], p[2]);
			}
		}

		int t = length % 2 == 0 ? length / 2 : (length + 2) / 2;
		return s.substring(p[0] - t, p[0] + length / 2);
	}

	private String toolVersion(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int[] range = new int[2];
		char[] str = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			// 把回文看成中间的部分全是同一字符，左右部分相对称
			// 找到下一个与当前字符不同的字符
			i = findLongest(str, i, range);
		}
		return s.substring(range[0], range[1] + 1);
	}

}
