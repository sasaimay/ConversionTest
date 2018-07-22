package to.kit.may.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LangueDuChatTest {
	private static final String HALF_SYMBOL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	private static final String FULL_SYMBOL = "！”＃＄％＆’（）＊＋，－．／：；＜＝＞？＠［￥］＾＿‘｛｜｝￣";
	private static final String HALF_DIGIT = "0123456789";
	private static final String FULL_DIGIT = "０１２３４５６７８９";
	private static final String HALF_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String FULL_UPPER = "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ";
	private static final String HALF_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String FULL_LOWER = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
	private static final String HALF_KANA = "ｧｱｰｨｲｩｳｪｴｫｵｶｶﾞｷｷﾞｸｸﾞｹｹﾞｺｺﾞｻｻﾞｼｼﾞｽｽﾞｾｾﾞｿｿﾞﾀﾀﾞﾁﾁﾞｯﾂﾂﾞﾃﾃﾞﾄﾄﾞﾅﾆﾇﾈﾉﾊﾊﾞﾊﾟﾋﾋﾞﾋﾟﾌﾌﾞﾌﾟﾍﾍﾞﾍﾟﾎﾎﾞﾎﾟﾏﾐﾑﾒﾓｬﾔｭﾕｮﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞ";
	private static final String FULL_KATAKANA = "ァアーィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロワワイエヲンヴ";
	private static final String FULL_HIRAGANA = "ぁあーぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろわわいえをんゔ";
	private static final String EMPTY = "";
	private static final String OTHER = "" + ('ぁ' - 1);
	private static final String PC = "PC:ﾊﾟ-ｿﾅﾙ･ｺﾝﾋﾟｭｰﾀｰ";
	private static final String NEKO = "こんなに寝ていて勤まるものなら猫にでも出来ぬ事はないと。";
	private static final String ROMAN = "wagahaiHAtampakuWOaisurutyazintekinekodearu。";
	private static final String ROMAN_NYA = "ippainyanko";
	private final LangueDuChat chatEn = new LangueDuChat();
	private final LangueDuChat chatJa = new LangueDuChat(LangueDuChat.JaChopBefore.and((ch, type, lastType) -> true));
	private final LangueDuChat chatJaR = new LangueDuChat(LangueDuChat.JaChopBefore, LangueDuChat.JaChopAfter);

	@Test
	void testToHalfDigit() {
		var halfDigit = this.chatEn.filter(HALF_DIGIT, JaFilterType.toHalfDigit);
		var fullDigit = this.chatEn.filter(FULL_DIGIT, JaFilterType.toHalfDigit);
		var fullSymbol = this.chatEn.filter(FULL_SYMBOL, JaFilterType.toHalfDigit);

		assertEquals(EMPTY, this.chatEn.filter(EMPTY));
		assertEquals(HALF_DIGIT, halfDigit);
		assertEquals(HALF_DIGIT, fullDigit);
		assertEquals(FULL_SYMBOL, fullSymbol);
	}

	@Test
	void testToFullDigit() {
		var halfDigit = this.chatJa.filter(HALF_DIGIT, JaFilterType.toFullDigit);
		var fullDigit = this.chatJa.filter(FULL_DIGIT, JaFilterType.toFullDigit);
		var halfSymbol = this.chatJa.filter(HALF_SYMBOL, JaFilterType.toFullDigit);

		assertEquals(FULL_DIGIT, halfDigit);
		assertEquals(FULL_DIGIT, fullDigit);
		assertEquals(HALF_SYMBOL, halfSymbol);
	}

	@Test
	void testToHalfAlpha() {
		var halfUpper = this.chatJa.filter(HALF_UPPER, JaFilterType.toHalfAlpha);
		var fullUpper = this.chatJa.filter(FULL_UPPER, JaFilterType.toHalfAlpha);
		var halfLower = this.chatJa.filter(HALF_LOWER, JaFilterType.toHalfAlpha);
		var fullLower = this.chatJa.filter(FULL_LOWER, JaFilterType.toHalfAlpha);
		var fullSymbol = this.chatJa.filter(FULL_SYMBOL, JaFilterType.toHalfAlpha);

		assertEquals(HALF_UPPER, halfUpper);
		assertEquals(HALF_UPPER, fullUpper);
		assertEquals(HALF_LOWER, halfLower);
		assertEquals(HALF_LOWER, fullLower);
		assertEquals(FULL_SYMBOL, fullSymbol);
	}

	@Test
	void testToFullAlpha() {
		var halfUpper = this.chatJa.filter(HALF_UPPER, JaFilterType.toFullAlpha);
		var fullUpper = this.chatJa.filter(FULL_UPPER, JaFilterType.toFullAlpha);
		var halfLower = this.chatJa.filter(HALF_LOWER, JaFilterType.toFullAlpha);
		var fullLower = this.chatJa.filter(FULL_LOWER, JaFilterType.toFullAlpha);
		var halfSymbol = this.chatJa.filter(HALF_SYMBOL, JaFilterType.toFullAlpha);

		assertEquals(FULL_UPPER, halfUpper);
		assertEquals(FULL_UPPER, fullUpper);
		assertEquals(FULL_LOWER, halfLower);
		assertEquals(FULL_LOWER, fullLower);
		assertEquals(HALF_SYMBOL, halfSymbol);
	}

	@Test
	void testToHalfKana() {
		var fullKatakana = this.chatJa.filter(FULL_KATAKANA, JaFilterType.toHalfKana);
		var fullHiragana = this.chatJa.filter(FULL_HIRAGANA, JaFilterType.toHalfKana);
		var other = this.chatJa.filter(OTHER, JaFilterType.toHalfKana);
		var pc = this.chatJa.filter("パーソナル・コンピューター", JaFilterType.toHalfKana);

		assertEquals(HALF_KANA, fullKatakana);
		assertEquals(HALF_KANA, fullHiragana);
		assertEquals(OTHER, other);
		assertEquals("ﾊﾟｰｿﾅﾙ･ｺﾝﾋﾟｭｰﾀｰ", pc);
	}

	@Test
	void testToFullHiragana() {
		var halfKana = this.chatJaR.filter(HALF_KANA, JaFilterType.toFullHiragana);
		var katakana = this.chatJaR.filter(FULL_KATAKANA, JaFilterType.toFullHiragana);
		var pcHira = this.chatJaR.filter(PC, JaFilterType.toFullHiragana);
		var alphaToH = this.chatJaR.filter(HALF_UPPER, JaFilterType.toFullHiragana);
		var romanToH = this.chatJaR.filter(ROMAN, JaFilterType.toFullHiragana);
		var nya = this.chatJaR.filter(ROMAN_NYA, JaFilterType.toFullHiragana);

		assertEquals(FULL_HIRAGANA, halfKana);
		assertEquals(FULL_HIRAGANA, katakana);
		assertEquals("PC:ぱーそなる・こんぴゅーたー", pcHira);
		assertEquals("あBCDEFGHIJKLMNOPQRSTUVWXYZ", alphaToH);
		assertEquals("わがはいはたんぱくをあいするちゃじんてきねこである。", romanToH);
		assertEquals("いっぱいにゃんこ", nya);
	}

	@Test
	void testToFullKatakana() {
		var halfKana = this.chatJaR.filter(HALF_KANA, JaFilterType.toFullKatakana);
		var hiragana = this.chatJaR.filter(FULL_HIRAGANA, JaFilterType.toFullKatakana);
		var pcKata = this.chatJaR.filter(PC, JaFilterType.toFullKatakana);
		var alphaToK = this.chatJaR.filter(HALF_UPPER, JaFilterType.toFullKatakana);
		var romanToK = this.chatJaR.filter(ROMAN, JaFilterType.toFullKatakana);
		var nya = this.chatJaR.filter(ROMAN_NYA, JaFilterType.toFullKatakana);

		assertEquals(FULL_KATAKANA, halfKana);
		assertEquals(FULL_KATAKANA, hiragana);
		assertEquals("PC:パーソナル・コンピューター", pcKata);
		assertEquals("アBCDEFGHIJKLMNOPQRSTUVWXYZ", alphaToK);
		assertEquals("ワガハイハタンパクヲアイスルチャジンテキネコデアル。", romanToK);
		assertEquals("イッパイニャンコ", nya);
	}

	@Test
	void testToNeko() {
		var neko = this.chatJa.filter(NEKO, JaFilterType.toNeko);
		var pc = this.chatJa.filter(PC, JaFilterType.toFullAlpha, JaFilterType.toFullHiragana, JaFilterType.toNeko);

		assertEquals("こんにゃに寝ていて勤まるもにょにゃら猫にでも出来にゅ事はにゃいと。", neko);
		assertEquals("ＰＣ:ぱーそにゃる・こんぴゅーたー", pc);
	}
}
