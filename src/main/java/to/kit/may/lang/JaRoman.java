package to.kit.may.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class JaRoman {
	private Map<String, String[]> consonantMap;

	public UnaryOperator<String> toHiragana = text -> {
		var len = text.length();
		var upper = text.toUpperCase();
		var first = upper.charAt(0);
		var second = 1 < len ? upper.charAt(1) : '\0';
		// Vowel and consonant
		var key = upper.substring(0, len - 1);
		var vowel = upper.substring(len - 1);
		var ext = "";

		if (len == 1) {
			key = "A";
		} else if (!this.consonantMap.containsKey(key) && 2 < len) {
			if ('Y' == second) {
				ext = this.consonantMap.get(String.valueOf(first))[1];
				key = "y";
			} else if ('M' == first || 'N' == first) {
				ext = "ん";
				key = key.substring(1);
			} else if (first == second) {
				ext = "っ";
				key = key.substring(1);
			}
		}
		var row = this.consonantMap.get(key);

		if (row != null) {
			var ch = vowel.charAt(0);
			var ix = 'A' == ch ? 0 : 'I' == ch ? 1 : 'U' == ch ? 2 : 'E' == ch ? 3 : 'O' == ch ? 4 : -1;

			if (ix != -1) {
				return ext + row[ix];
			}
		}
		return text;
	};

	private void initMap() {
		var map = new HashMap<String, String[]>();

		map.put("A", new String[] { "あ", "い", "う", "え", "お" });
		map.put("B", new String[] { "ば", "び", "ぶ", "べ", "ぼ" });
		map.put("C", new String[] { "か", "し", "く", "せ", "こ" });
		map.put("CH", new String[] { "ちゃ", "ち", "ちゅ", "ちぇ", "ちょ" });
		map.put("D", new String[] { "だ", "ぢ", "づ", "で", "ど" });
		map.put("F", new String[] { "ふぁ", "ふぃ", "ふ", "ふぇ", "ふぉ" });
		map.put("G", new String[] { "が", "ぎ", "ぐ", "げ", "ご" });
		map.put("H", new String[] { "は", "ひ", "ふ", "へ", "ほ" });
		map.put("J", new String[] { "じゃ", "じ", "じゅ", "じぇ", "じょ" });
		map.put("K", new String[] { "か", "き", "く", "け", "こ" });
		map.put("L", new String[] { "ぁ", "ぃ", "ぅ", "ぇ", "ぉ" });
		map.put("LY", new String[] { "ゃ", "ぃ", "ゅ", "ぇ", "ょ" });
		map.put("LW", new String[] { "ゎ", "", "", "", "" });
		map.put("M", new String[] { "ま", "み", "む", "め", "も" });
		map.put("N", new String[] { "な", "に", "ぬ", "ね", "の" });
		map.put("P", new String[] { "ぱ", "ぴ", "ぷ", "ぺ", "ぽ" });
		map.put("Q", new String[] { "くぁ", "くぃ", "く", "くぇ", "くぉ" });
		map.put("R", new String[] { "ら", "り", "る", "れ", "ろ" });
		map.put("S", new String[] { "さ", "し", "す", "せ", "そ" });
		map.put("SH", new String[] { "しゃ", "し", "しゅ", "しぇ", "しょ" });
		map.put("T", new String[] { "た", "ち", "つ", "て", "と" });
		map.put("V", new String[] { "ゔぁ", "ゔぃ", "ゔ", "ゔぇ", "ゔぉ" });
		map.put("W", new String[] { "わ", "ゐ", "う", "ゑ", "を" });
		map.put("X", new String[] { "ぁ", "ぃ", "ぅ", "ぇ", "ぉ" });
		map.put("Y", new String[] { "や", "ゐ", "ゆ", "ゑ", "よ" });
		map.put("Z", new String[] { "ざ", "じ", "ず", "ぜ", "ぞ" });
		map.put("y", new String[] { "ゃ", "ぃ", "ゅ", "ぇ", "ょ" });
		this.consonantMap = Map.copyOf(map);
	}

	public JaRoman() {
		initMap();
	}
}
