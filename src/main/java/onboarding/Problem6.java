package onboarding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Problem6 {
	public static List<String> solution(List<List<String>> forms) {
		Set<String> blacklist = new HashSet<>();
		Set<String> answer = new TreeSet<>();
		List<User> userList = forms.stream()
				.map(User::new)
				.collect(Collectors.toList());

		for (User user : userList) {
			for (String blackName : blacklist) {
				if (user.checkDuplicate(blackName)) {
					for (User targetUser : userList) {
						if (targetUser.checkDuplicate(blackName)) {
							answer.add(targetUser.email);
						}
					}
				}
			}

			blacklist.addAll(user.getDuplicateNicknameTokens());
		}

		return List.copyOf(answer);
	}

	static class User {
		private String email;
		private String nickname;

		public User(List<String> form) {
			this.email = form.get(0);
			this.nickname = form.get(1);
		}

		public Set<String> getDuplicateNicknameTokens() {
			Set<String> duplicateTokens = new HashSet<>();
			for (int i = 1; i < nickname.length(); i++) {
				int ptr1 = 0;
				int ptr2 = i + 1;
				while (ptr2 <= nickname.length()) {
					duplicateTokens.add(nickname.substring(ptr1, ptr2));
					ptr1++;
					ptr2++;
				}
			}
			return duplicateTokens;
		}

		public boolean checkDuplicate(String token) {
			return nickname.contains(token);
		}
	}
}