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
	private static final String OTHER = "" + ('ぁ' - 1);
	private static final String PC = "PC:ﾊﾟ-ｿﾅﾙ･ｺﾝﾋﾟｭｰﾀｰ";
	private static final String NEKO = "こんなに寝ていて勤まるものなら猫にでも出来ぬ事はないと。";
	private final LangueDuChat chat = new LangueDuChat(LangueDuChat.JaSplitPredicate);

	@Test
	void testToHalfDigit() {
		var halfDigit = this.chat.filter(HALF_DIGIT, JaFilterType.toHalfDigit);
		var fullDigit = this.chat.filter(FULL_DIGIT, JaFilterType.toHalfDigit);
		var fullSymbol = this.chat.filter(FULL_SYMBOL, JaFilterType.toHalfDigit);

		assertEquals(HALF_DIGIT, halfDigit);
		assertEquals(HALF_DIGIT, fullDigit);
		assertEquals(FULL_SYMBOL, fullSymbol);
	}

	@Test
	void testToFullDigit() {
		var halfDigit = this.chat.filter(HALF_DIGIT, JaFilterType.toFullDigit);
		var fullDigit = this.chat.filter(FULL_DIGIT, JaFilterType.toFullDigit);
		var halfSymbol = this.chat.filter(HALF_SYMBOL, JaFilterType.toFullDigit);

		assertEquals(FULL_DIGIT, halfDigit);
		assertEquals(FULL_DIGIT, fullDigit);
		assertEquals(HALF_SYMBOL, halfSymbol);
	}

	@Test
	void testToHalfAlpha() {
		var halfUpper = this.chat.filter(HALF_UPPER, JaFilterType.toHalfAlpha);
		var fullUpper = this.chat.filter(FULL_UPPER, JaFilterType.toHalfAlpha);
		var halfLower = this.chat.filter(HALF_LOWER, JaFilterType.toHalfAlpha);
		var fullLower = this.chat.filter(FULL_LOWER, JaFilterType.toHalfAlpha);
		var fullSymbol = this.chat.filter(FULL_SYMBOL, JaFilterType.toHalfAlpha);

		assertEquals(HALF_UPPER, halfUpper);
		assertEquals(HALF_UPPER, fullUpper);
		assertEquals(HALF_LOWER, halfLower);
		assertEquals(HALF_LOWER, fullLower);
		assertEquals(FULL_SYMBOL, fullSymbol);
	}

	@Test
	void testToFullAlpha() {
		var halfUpper = this.chat.filter(HALF_UPPER, JaFilterType.toFullAlpha);
		var fullUpper = this.chat.filter(FULL_UPPER, JaFilterType.toFullAlpha);
		var halfLower = this.chat.filter(HALF_LOWER, JaFilterType.toFullAlpha);
		var fullLower = this.chat.filter(FULL_LOWER, JaFilterType.toFullAlpha);
		var halfSymbol = this.chat.filter(HALF_SYMBOL, JaFilterType.toFullAlpha);

		assertEquals(FULL_UPPER, halfUpper);
		assertEquals(FULL_UPPER, fullUpper);
		assertEquals(FULL_LOWER, halfLower);
		assertEquals(FULL_LOWER, fullLower);
		assertEquals(HALF_SYMBOL, halfSymbol);
	}

	@Test
	void testToHalfKana() {
		var fullKatakana = this.chat.filter(FULL_KATAKANA, JaFilterType.toHalfKana);
		var fullHiragana = this.chat.filter(FULL_HIRAGANA, JaFilterType.toHalfKana);
		var other = this.chat.filter(OTHER, JaFilterType.toHalfKana);
		var pc = this.chat.filter("パーソナル・コンピューター", JaFilterType.toHalfKana);

		assertEquals(HALF_KANA, fullKatakana);
		assertEquals(HALF_KANA, fullHiragana);
		assertEquals(OTHER, other);
		assertEquals("ﾊﾟｰｿﾅﾙ･ｺﾝﾋﾟｭｰﾀｰ", pc);
	}

	@Test
	void testToFullKana() {
		var halfKanaToH = this.chat.filter(HALF_KANA, JaFilterType.toFullHiragana);
		var halfKanaToK = this.chat.filter(HALF_KANA, JaFilterType.toFullKatakana);
		var pcHira = this.chat.filter(PC, JaFilterType.toFullHiragana);
		var pcKata = this.chat.filter(PC, JaFilterType.toFullKatakana);

		assertEquals(FULL_HIRAGANA, halfKanaToH);
		assertEquals(FULL_KATAKANA, halfKanaToK);
		assertEquals("PC:ぱーそなる・こんぴゅーたー", pcHira);
		assertEquals("PC:パーソナル・コンピューター", pcKata);
	}

	@Test
	void testToNeko() {
		var neko = this.chat.filter(NEKO, JaFilterType.toNeko);

		assertEquals("こんにゃに寝ていて勤まるもにょにゃら猫にでも出来にゅ事はにゃいと。", neko);
	}
}
