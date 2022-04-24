package com.zyl.cloud.gateway.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * <p>
 * 滑动窗口取最长字符串
 * <p>
 * 收获:
 * 1.我是傻逼
 * 2.取子串可以考虑用滑动窗口
 * 3.遍历可以看做游标移动 可以想象多个游标
 * 4.需要足够全面的测试数据
 * 5.可以放心大胆地忽略遍历过的数据
 */
public class Puzzle3 {

	public static void main(String[] args) {
		Puzzle3 puzzle3 = new Puzzle3();
		System.out.println(puzzle3.toolVersion("idea取消debug自动弹出toolwindow"));
	}

	private int myVersion(String s) {
		// TODO: 2022/4/24 zylTodo 错误1: 输入没考虑全面 漏了只有一个字符的情况
		// TODO: 2022/4/24 zylTodo 错误2: 根本不是漏了一个字符的问题 没重复的情况就没考虑到!
		if (null == s || s.length() == 0) {
			return 0;
		}
		int begin = 0, end = 0;
		int result = 0;
		int maxBegin = 0, maxEnd = 0;
		Map<Character, Integer> map = new HashMap<>();
		char[] chars = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			maxBegin = begin;
			maxEnd = end;
			Integer p = map.get(chars[i]);
			// 如果重复了
			if (null != p && begin <= p && p <= end) {
				// 如果比结果大
				if (result < end - begin) {
					result = end - begin;
					maxBegin = begin;
					maxEnd = end;
				}
				begin = p + 1;
			}
			// TODO: 2022/4/24 zylTodo 错误3: 不是每次不重复就递增 而是新子串的长度大于之前记录时才递增!
			// TODO: 2022/4/24 zylTodo 错误4: 等于的时候也要递增!
			else if (end - begin >= result) {
				result++;
			}
			map.put(chars[i], i);
			end++;
		}
		System.out.println(maxBegin);
		System.out.println(maxEnd);
		System.out.println(s.substring(maxBegin, maxEnd));
		return result;
	}

	private int commitVersion(String s) {
		if (null == s || s.length() == 0) {
			return 0;
		}
		int begin = 0, end = 0;
		int result = 0;
		Map<Character, Integer> map = new HashMap<>();
		char[] chars = s.toCharArray();

		for (int i = 0; i < s.length(); i++) {
			Integer p = map.get(chars[i]);
			if (null != p && begin <= p && p < end) {
				if (result < end - begin) {
					result = end - begin;
				}
				begin = p + 1;
			} else if (end - begin >= result) {
				result++;
			}
			map.put(chars[i], i);
			end++;
		}
		return result;
	}

	private int bestVersion(String s) {
		// 记录字符上一次出现的位置 key是字符ASCII value是该字符最后一次出现的位置下标
		int[] last = new int[128];
		for (int i = 0; i < 128; i++) {
			last[i] = -1;
		}
		int n = s.length();

		int result = 0;
		int start = 0; // 窗口开始位置
		for (int i = 0; i < n; i++) {
			// 每个字符取其ASCII码序号
			int index = s.charAt(i);
			// 如果last[index] + 1 > start 表示当前index的位置出现了和start位置相同的字符 就需要将start置为index的下一个字符下标 因为前面的都遍历过了
			start = Math.max(start, last[index] + 1);
			// 当前位置子串长度和上一次子串长度取大者
			result = Math.max(result, i - start + 1);
			// ASCII数组字符位置值更新为上次出现的下标
			last[index] = i;
		}

		return result;
	}

	private int toolVersion(String s) {
		if (null == s) {
			return 0;
		}
		// 初始化用于记录上次位置的map
		Map<Character, Integer> map = new HashMap<>();
		// 初始化子串开始游标和结果
		int start = 0, result = 0;
		int bestStart = 0;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			Integer lastIndex = map.get(chars[i]);
			// 当前字符没有重复记录或者子串开始下标大于该字符上次重复的加入子串
			if (lastIndex == null || start > lastIndex) {
				if (i - start + 1 > result) {
					bestStart = start;
				}
				result = Math.max(result, i - start + 1);
			} else {
				start = Math.max(start, lastIndex + 1);
			}
			map.put(chars[i], i);
		}
		System.out.println(s.substring(bestStart, bestStart + result));
		return result;
	}

}
