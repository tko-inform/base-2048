package safezero.base2048;

import java.util.Arrays;

public final class Base2048 {

    public static final Base2048 ENGLISH = new Base2048(English.WORDS);
    public static final Base2048 JAPANESE = new Base2048(Japanese.WORDS);
    public static final Base2048 FRENCH = new Base2048(French.WORDS);
    public static final Base2048 ITALIAN = new Base2048(Italian.WORDS);
    public static final Base2048 SPANISH = new Base2048(null);

    public static final Base2048 CHINESE_SIMPLIFIED =
            new Base2048(ChineseSimplified.WORDS);

    public static final Base2048 CHINESE_TRADITIONAL =
            new Base2048(ChineseTraditional.WORDS);

    private final String[] words;

    private Base2048(final String[] words) {
        assert 2048 == words.length;
        this.words = Arrays.copyOf(words, 2048);
    }

    public String encode(byte[] binaryData) {
        return null;
    }

    public byte[] decode(String base2048Input) {
        return new byte[0];
    }
}
