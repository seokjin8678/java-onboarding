package onboarding;

import java.util.List;
import java.util.NoSuchElementException;

class Problem1 {
	public static int solution(List<Integer> pobi, List<Integer> crong) {
		if (checkIrregularPages(pobi) || checkIrregularPages(crong)) {
			return -1;
		}

		Integer pobiMaxPage = pobi.stream()
				.map(Problem1::getMaxPage)
				.max(Integer::compareTo)
				.orElseThrow(NoSuchElementException::new);

		Integer crongMaxPage = crong.stream()
				.map(Problem1::getMaxPage)
				.max(Integer::compareTo)
				.orElseThrow(NoSuchElementException::new);

		return compareMaxPage(pobiMaxPage, crongMaxPage);
	}

	private static boolean checkIrregularPages(List<Integer> pages) {
		if (pages.get(0) == null || pages.get(1) == null) {
			return true;
		}
		return pages.get(0) - pages.get(1) != -1;
	}

	private static int getMaxPage(Integer page) {
		int sum = 0;
		int mul = 1;
		int temp;
		while (page > 0) {
			temp = page % 10;
			page /= 10;
			sum += temp;
			mul *= temp;
		}
		return Math.max(sum, mul);
	}

	private static int compareMaxPage(Integer pobiPage, Integer crongPage) {
		if (pobiPage > crongPage) {
			return 1;
		}
		if (pobiPage < crongPage) {
			return 2;
		}
		return 0;
	}
}