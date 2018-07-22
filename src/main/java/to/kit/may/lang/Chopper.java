package to.kit.may.lang;

@FunctionalInterface
public interface Chopper {
	boolean test(char ch, int type, int lastType);

	default Chopper and(Chopper other) {
		return (c, t, l) -> test(c, t, l) && other.test(c, t, l);
	}

	default Chopper or(Chopper other) {
		return (c, t, l) -> test(c, t, l) || other.test(c, t, l);
	}
}
