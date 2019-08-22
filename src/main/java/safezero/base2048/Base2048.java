package safezero.base2048;

import java.util.Arrays;

public final class Base2048 {

    public static final Base2048 ENGLISH = new Base2048(English.WORDS);
    public static final Base2048 JAPANESE = new Base2048(Japanese.WORDS);
    public static final Base2048 FRENCH = new Base2048(French.WORDS);
    public static final Base2048 ITALIAN = new Base2048(Italian.WORDS);
    public static final Base2048 SPANISH = new Base2048(Spanish.WORDS);

    public static final Base2048 CHINESE_SIMPLIFIED =
            new Base2048(ChineseSimplified.WORDS);

    public static final Base2048 CHINESE_TRADITIONAL =
            new Base2048(ChineseTraditional.WORDS);

    private final BaseX baseX;

    private Base2048(final String[] words) {
        assert 2048 == words.length;
        this.baseX = new BaseX(Arrays.copyOf(words, 2048));
    }

    public String encode(byte[] binaryData) {
        return baseX.encode(binaryData);
    }

    public byte[] decode(String base2048Input) throws IllegalBaseCodeException {
        return baseX.decode(base2048Input);
    }
}
