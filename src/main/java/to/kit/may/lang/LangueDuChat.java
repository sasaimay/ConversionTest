package to.kit.may.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * La langue du chat.
 * @author May Sasai
 */
public class LangueDuChat {
	private final Chopper chopBefore;
	private final Chopper chopAfter;

	public static final Chopper NotChop = (ch, type, lastType) -> false;

	public static final Chopper DefaultChopBefore = (ch, type, lastType) -> {
		return type != lastType && type != Character.MODIFIER_LETTER;
	};

	public static final Chopper JaChopBefore = DefaultChopBefore.or((ch, type, lastType) -> {
		return 'ｦ' <= ch && ch <= 'ﾝ';
	});

	public static final Chopper JaChopAfter = (ch, type, lastType) -> {
		var lower = Character.toLowerCase(ch);

		return 'a' == lower || 'e' == lower || 'i' == lower || 'o' == lower || 'u' == lower;
	};

	public List<String> chop(final String text) {
		var list = new ArrayList<String>();
		var buff = new StringBuilder();
		var lastType = 0;

		for (var ch : text.toCharArray()) {
			var lower = Character.toLowerCase(ch);
			var type = Character.getType(lower);
			var chopBefore = this.chopBefore.test(ch, type, lastType);
			var chopAfter = this.chopAfter.test(ch, type, lastType);

			if (chopBefore && 0 < buff.length()) {
				list.add(buff.toString());
				buff.delete(0, buff.length());
			}
			buff.append(ch);
			if (chopAfter) {
				list.add(buff.toString());
				buff.delete(0, buff.length());
			}
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

	public LangueDuChat(Chopper chopBefore, Chopper chopAfter) {
		this.chopBefore = chopBefore;
		this.chopAfter = chopAfter;
	}

	public LangueDuChat(Chopper chopBefore) {
		this.chopBefore = chopBefore;
		this.chopAfter = NotChop;
	}

	public LangueDuChat() {
		this.chopBefore = DefaultChopBefore;
		this.chopAfter = NotChop;
	}
}
