package com.zyl.cloud.gateway.algorithm;

public class Puzzle9 {

	public static void main(String[] args) {
		Puzzle9 puzzle9 = new Puzzle9();
		int x = -121;
		System.out.println(puzzle9.betterVersion(x));
	}

	public boolean betterVersion(int x) {
		if ((0 != x && x % 10 == 0) || x < 0) {
			return false;
		}
		int origin = x;
		long n = 0;
		while (x != 0) {
			n = n * 10 + x % 10;
			x = x / 10;
		}
		int result = (int) n == n ? (int) n : 0;
		return result == origin;
	}

}
