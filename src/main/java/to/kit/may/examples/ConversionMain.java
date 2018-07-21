package to.kit.may.examples;

import java.text.Normalizer;
import java.util.Locale;

import to.kit.may.lang.LangueDuChat;

public class ConversionMain {
	private static final String CHOP_TEST_EN = "This cookie is the shape of a cat's tongue.";
	private static final String CHOP_TEST_JA = "ﾗﾝｸﾞ･ﾄﾞ･ｼｬ(フランス語：Langue de chat)ﾊﾟﾋﾟﾌﾟひらがなカタカナａｂｃＡＢＣ";
	private static final String HALF_KANA = "ｧｱｰｨｲｩｳｪｴｫｵｶｶﾞｷｷﾞｸｸﾞｹｹﾞｺｺﾞｻｻﾞｼｼﾞｽｽﾞｾｾﾞｿｿﾞﾀﾀﾞﾁﾁﾞｯﾂﾂﾞﾃﾃﾞﾄﾄﾞﾅﾆﾇﾈﾉﾊﾊﾞﾊﾟﾋﾋﾞﾋﾟﾌﾌﾞﾌﾟﾍﾍﾞﾍﾟﾎﾎﾞﾎﾟﾏﾐﾑﾒﾓｬﾔｭﾕｮﾖﾗﾘﾙﾚﾛﾜﾜｲｴｦﾝｳﾞ";

	private void execute() {
		var text = "012０１２＊＋，－．／abcａｂｃあぁいぃうぅえぇおぉｱｲｳｴｵｶﾞｷﾞｸﾞｹﾞｺﾞ";
		var upper = text.toUpperCase(Locale.JAPANESE);
		var norm = Normalizer.normalize(text, Normalizer.Form.NFKC);
		var chat = new LangueDuChat(LangueDuChat.JaSplitPredicate);

		System.out.println(upper);
		System.out.println(Character.toUpperCase('ａ'));
		System.out.println(norm);
		System.out.println(String.join("|", chat.chop(CHOP_TEST_EN)));
		System.out.println(String.join("|", chat.chop(CHOP_TEST_JA)));
		System.out.println(String.join("|", chat.chop(HALF_KANA)));
	}

	public static void main(String[] args) {
		var app = new ConversionMain();

		app.execute();
	}
}
