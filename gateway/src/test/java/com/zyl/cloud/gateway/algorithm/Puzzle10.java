package com.zyl.cloud.gateway.algorithm;

/**
 * 正则表达式匹配
 * <p>
 * s = "aa", p = "a" => false
 * s = "aa", p = "a*" => true
 * s = "ab", p = ".*" => true
 * s = "ab", p = "*b" => true
 * s = "ab", p = "*a" => false
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class Puzzle10 {

	// TODO: 2022/4/28 zylTodo 优先级思路 分隔.和* 高优先级快速失败
	// TODO: 2022/4/28 zylTodo 1. split[].size=1 直接判断s和p是否相同 不同失败
	// TODO: 2022/4/28 zylTodo 2. split[0]!=.||* 判断开头相同长度是否相同 不同失败 相同判断split[size-1]!=.||* 判断结尾相同长度是否相同 不同失败
	// TODO: 2022/4/28 zylTodo 3. 如果split[i]=="" 直接跳过
	// TODO: 2022/4/28 zylTodo 4. 如果split[i]==. s++
	// TODO: 2022/4/28 zylTodo 5. 如果split[i]==* 直接跳过
	// TODO: 2022/4/28 zylTodo 6. 如果split[i]==? 从i开始找匹配子串
	public static void main(String[] args) {
		Puzzle10 puzzle10 = new Puzzle10();
		// System.out.println(puzzle10.myVersion("123512341236", "*123*1234*"));
	}

	public boolean splitVersion(String s, String p) {
		int sIndex = 0;
		String[] starSplit = p.split("\\*", -1);
		for (String starSegment : starSplit) {
			String remainS = s.substring(sIndex);
			// 包含.
			if (starSegment.contains(".")) {
				String[] dotSplit = starSegment.split("\\.", -1);
				if (starSegment.length() > remainS.length()) {
					return false;
				}
				for (String dotSegment : dotSplit) {
					if (!remainS.contains(dotSegment)) {
						return false;
					}
				}
			}
			// 不包含.
			else {
				if (!remainS.contains(starSegment)) {
					System.out.println(sIndex);
					return false;
				} else {
					sIndex = s.indexOf(starSegment, sIndex);
				}
			}
		}
		return true;
	}

	/**
	 * 两个游标分别指向s[0]p[0] 终点是s和p都走完
	 * <p>
	 * 0.快速失败
	 * 1.如果当前都是字符且不相等 直接false
	 * 2.如果p[pIndex]是. s[sIndex]++
	 * 3.如果p[pIndex]是* s[sIndex]不变
	 * <p>
	 * 什么时候afterStar重置为false
	 * 如果当前是* 看"".equals(wholeSubStr)
	 * 如果true(最近一次出现的不是*)
	 * afterStar=true
	 * pIndex++
	 * continue
	 * 如果false(代表之前最近一次出现的符号是*)
	 * 判断字符串是否包含s.subString(sIndex).contains(wholeSubStr)
	 * true
	 * s重置索引为sIndex+s.subString(sIndex).contains(wholeSubStr)+wholeSubStr.length
	 * wholeSubStr重置为""
	 * pIndex++
	 * continue
	 * false
	 * 返回false
	 * 如果当前是. 看"".equals(wholeSubStr)
	 * 如果true(最近一次出现的不是*)
	 * pIndex++
	 * sIndex++
	 * continue
	 * 如果false(代表之前最近一次出现的符号是*)
	 * 判断字符串是否包含s.subString(sIndex).contains(wholeSubStr)
	 * true
	 * s重置索引为sIndex+s.subString(sIndex).contains(wholeSubStr)+wholeSubStr.length
	 * wholeSubStr重置为""
	 * pIndex++
	 * continue
	 * false
	 * 返回false
	 */
	public boolean myVersion(String s, String p) {

		char p0 = p.charAt(0);
		char pe = p.charAt(p.length() - 1);
		// 快速失败
		if (((p0 != '.' && p0 != '*') && p0 != s.charAt(0)) || ((pe != '.' && pe != '*') && pe != s.charAt(s.length() - 1))) {
			return false;
		}

		int sIndex = 0, pIndex = 0;
		String wholeSubStr = "";
		boolean afterStar = false;
		while (sIndex < s.length() || pIndex < p.length()) {
			String sCurrent = sIndex == s.length() ? "" : s.substring(sIndex, sIndex + 1);
			String pCurrent = pIndex == p.length() ? "" : p.substring(pIndex, pIndex + 1);
			if ("*".equals(pCurrent)) {
				if (noHoldStr(s, sIndex, wholeSubStr)) {
					return false;
				}
				pIndex = Math.min(pIndex + wholeSubStr.length(), p.length());
				wholeSubStr = "";
				afterStar = true;
				continue;
			}
			if (afterStar) {
				wholeSubStr += p.substring(pIndex, pIndex + 1);
			}
			if (!sCurrent.equals(pCurrent)) {
				return false;
			}

			if (!pCurrent.equals("*")) {
				sIndex = Math.min(sIndex + 1, s.length());
			}
			pIndex = Math.min(pIndex + 1, p.length());
		}

		return true;

		//
		// char p0 = p.charAt(0);
		// char pe = p.charAt(p.length() - 1);
		// if (((p0 != '.' && p0 != '*') && p0 != s.charAt(0)) || ((pe != '.' && pe != '*') && pe != s.charAt(s.length() - 1))) {
		// 	return false;
		// }
		//
		// // 原始字符串下标
		// int sIndex = 0;
		// // 正则字符串下标
		// int pIndex = 0;
		// // 下一个.下标
		// int dotIndex = 0;
		// // 下一个*下标
		// int starIndex = 0;
		// // 结果
		// boolean result = false;
		// // 如果还有剩余.或*存在
		// while (sIndex < s.length()) {
		// 	int tempDotIndex = p.indexOf(".", dotIndex + 1);
		// 	int tempStarIndex = p.indexOf("*", starIndex + 1);
		//
		// 	if (tempDotIndex == -1 && tempStarIndex == -1) {
		// 		return true;
		// 	}
		//
		// 	// 下一个符号是.
		// 	if (tempDotIndex < tempStarIndex && -1 != tempDotIndex) {
		// 		if (!s.substring(sIndex, tempDotIndex - dotIndex).equals(p.substring(pIndex, tempDotIndex - dotIndex))) {
		// 			return false;
		// 		}
		// 		sIndex += tempDotIndex + 1;
		// 		dotIndex += tempDotIndex + 1;
		// 		pIndex += dotIndex;
		// 	}
		// 	// 下一个符号是*
		// 	else if ((tempDotIndex == -1 || tempDotIndex > tempStarIndex) && -1 != tempStarIndex) {
		// 		if (!s.substring(sIndex, tempStarIndex - starIndex).equals(p.substring(pIndex, tempStarIndex - starIndex))) {
		// 			return false;
		// 		}
		// 		starIndex = tempStarIndex;
		// 		pIndex += starIndex;
		// 	}
		// }
		// return result;
	}

	private boolean noHoldStr(String s, int sIndex, String wholeSubStr) {
		return (!"".equals(wholeSubStr)) && !s.substring(sIndex).contains(wholeSubStr);
	}

}
