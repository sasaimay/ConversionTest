package to.kit.may.examples;

import java.text.Normalizer;
import java.util.Locale;

public class ConversionMain {
	private void execute() {
		var text = "012０１２＊＋，－．／abcａｂｃあぁいぃうぅえぇおぉｱｲｳｴｵｶﾞｷﾞｸﾞｹﾞｺﾞ";
		var upper = text.toUpperCase(Locale.JAPANESE);
		var norm = Normalizer.normalize(text, Normalizer.Form.NFKC);

		System.out.println(upper);
		System.out.println(norm);
	}

	public static void main(String[] args) {
		var app = new ConversionMain();

		app.execute();
	}
}
