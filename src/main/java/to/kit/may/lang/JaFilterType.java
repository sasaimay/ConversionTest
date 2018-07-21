package to.kit.may.lang;

import java.util.function.UnaryOperator;

/**
 * Japanese filter type.
 * @author May Sasai
 */
public enum JaFilterType implements FilterType, JaFilter {
	/** 半角数字に変換. */
	toHalfDigit(text -> {
		var buff = new StringBuilder();

		text.chars()
				.map(i -> '０' <= i && i <= '９' ? '0' + i - '０' : i)
				.forEach(i -> buff.append((char)i));
		return buff.toString();
	}),
	/** 全角数字に変換. */
	toFullDigit(text -> {
		var buff = new StringBuilder();

		text.chars()
				.map(i -> '0' <= i && i <= '9' ? '０' + i - '0' : i)
				.forEach(i -> buff.append((char)i));
		return buff.toString();
	}),
	/** 半角アルファベットに変換. */
	toHalfAlpha(text -> {
		var buff = new StringBuilder();

		text.chars()
				.map(i -> 'Ａ' <= i && i <= 'Ｚ' ? 'A' + i - 'Ａ' : ('ａ' <= i && i <= 'ｚ' ? 'a' + i - 'ａ' : i))
				.forEach(i -> buff.append((char) i));
		return buff.toString();
	}),
	/** 全角アルファベットに変換. */
	toFullAlpha(text -> {
		var buff = new StringBuilder();

		text.chars()
				.map(i -> 'A' <= i && i <= 'Z' ? 'Ａ' + i - 'A' : ('a' <= i && i <= 'z' ? 'ａ' + i - 'a' : i))
				.forEach(i -> buff.append((char) i));
		return buff.toString();
	}),
	/** 半角ｶﾅに変換. */
	toHalfKana(text -> {
		var buff = new StringBuilder();

		text.chars()
				.map(i -> 'ー' == i ? 'ｰ' : ('・' == i ? '･' : i))
				.forEach(i -> {
					var ix = -1;

					if ('ァ' <= i && i <= 'ヴ') {
						ix = (i - 'ァ') * 2;
					} else if ('ぁ' <= i && i <= 'ゔ') {
						ix = (i - 'ぁ') * 2;
					}
					if (ix != -1) {
						var half = HALF_KANA.charAt(ix);
						var sonant = HALF_KANA.charAt(ix + 1);

						buff.append(half);
						if (!Character.isWhitespace(sonant)) {
							buff.append(sonant);
						}
					} else {
						buff.append((char) i);
					}
				});
		return buff.toString();
	}),
	/** 全角ひらがなに変換. */
	toFullHiragana(text -> {
		if ("ｰ".equals(text) || "-".equals(text)) {
			return "ー";
		}
		if ("･".equals(text)) {
			return "・";
		}
		if ("ﾜ".equals(text)) {
			return "わ";
		}
		var ix = HALF_KANA.indexOf(text);

		if (ix != -1) {
			return String.valueOf((char) ('ぁ' + ix / 2));
		}
		return text;
	}),
	/** 全角カタカナに変換. */
	toFullKatakana(text -> {
		if ("ｰ".equals(text) || "-".equals(text)) {
			return "ー";
		}
		if ("･".equals(text)) {
			return "・";
		}
		if ("ﾜ".equals(text)) {
			return "ワ";
		}
		var ix = HALF_KANA.indexOf(text);

		if (ix != -1) {
			return String.valueOf((char) ('ァ' + ix / 2));
		}
		return text;
	}),
	/** ねこに変換. */
	toNeko(text -> {
		var buff = new StringBuilder();

		text.chars()
				.forEach(i -> {
					if ('な' == i) {
						buff.append("にゃ");
					} else if ('ぬ' == i) {
						buff.append("にゅ");
					} else if ('の' == i) {
						buff.append("にょ");
					} else {
						buff.append((char) i);
					}
				});
		return buff.toString();
	}),
	;
	UnaryOperator<String> filter;

	JaFilterType(UnaryOperator<String> filter) {
		this.filter = filter;
	}

	@Override
	public String apply(String text) {
		return this.filter.apply(text);
	}
}
