package to.kit.may.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * La langue du chat.
 * @author May Sasai
 */
public class LangueDuChat {
	private final Predicate<int[]> splitPredicate;

	public static final Predicate<int[]> DefaultSplitPredicate = i -> {
		var type = i[1];
		var lastType = i[2];

		return type != lastType && type != Character.MODIFIER_LETTER;
	};

	public static final Predicate<int[]> JaSplitPredicate = DefaultSplitPredicate.or(i -> {
		var ch = i[0];

		return 'ｦ' <= ch && ch <= 'ﾝ';
	});

	public List<String> chop(final String text) {
		var list = new ArrayList<String>();
		var buff = new StringBuilder();
		var lastType = 0;

		for (var ch : text.toCharArray()) {
			var lower = Character.toLowerCase(ch);
			var type = Character.getType(lower);
			var split = this.splitPredicate.test(new int[] { ch, type, lastType });

			if (split && 0 < buff.length()) {
				list.add(buff.toString());
				buff.delete(0, buff.length());
			}
			buff.append(ch);
			lastType = type;
		}
		if (0 < buff.length()) {
			list.add(buff.toString());
		}
		return list;
	}

	public String filter(final String text, final FilterType... filters) {
		var result = new StringBuilder();

		chop(text).forEach(s -> {
			var str = s;

			for (var f : filters) {
				str = f.apply(str);
			}
			result.append(str);
		});
		return result.toString();
	}

	public LangueDuChat(Predicate<int[]> predicate) {
		this.splitPredicate = predicate;
	}

	public LangueDuChat() {
		this.splitPredicate = DefaultSplitPredicate;
	}
}
