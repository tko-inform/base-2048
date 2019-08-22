package safezero.base2048;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import static org.junit.Assert.*;

public class Base2048Test {

    @Test
    public void english() {
        Base2048 instance = Base2048.ENGLISH;
        shouldEncodeAndDecode(instance, "00", "abandon");
        shouldEncodeAndDecode(instance, "01", "ability");
        shouldEncodeAndDecode(instance, "07ff", "zoo");
        shouldEncodeAndDecode(instance, "0800", "ability abandon");
        shouldEncodeAndDecode(instance, "3fffff", "zoo zoo");
    }

    @Test
    public void japanese() {
        Base2048 instance = Base2048.JAPANESE;
        shouldEncodeAndDecode(instance, "00", "あいこくしん");
        shouldEncodeAndDecode(instance, "01", "あいさつ");
        shouldEncodeAndDecode(instance, "07ff", "われる");
        shouldEncodeAndDecode(instance, "0800", "あいさつ あいこくしん");
        shouldEncodeAndDecode(instance, "3fffff", "われる われる");
    }

    @Test
    public void french() {
        Base2048 instance = Base2048.FRENCH;
        shouldEncodeAndDecode(instance, "00", "abaisser");
        shouldEncodeAndDecode(instance, "01", "abandon");
        shouldEncodeAndDecode(instance, "07ff", "zoologie");
        shouldEncodeAndDecode(instance, "0800", "abandon abaisser");
        shouldEncodeAndDecode(instance, "3fffff", "zoologie zoologie");
    }

    @Test
    public void italian() {
        Base2048 instance = Base2048.ITALIAN;
        shouldEncodeAndDecode(instance, "00", "abaco");
        shouldEncodeAndDecode(instance, "01", "abbaglio");
        shouldEncodeAndDecode(instance, "07ff", "zuppa");
        shouldEncodeAndDecode(instance, "0800", "abbaglio abaco");
        shouldEncodeAndDecode(instance, "3fffff", "zuppa zuppa");
    }

    @Test
    public void spanish() {
        Base2048 instance = Base2048.SPANISH;
        shouldEncodeAndDecode(instance, "00", "ábaco");
        shouldEncodeAndDecode(instance, "01", "abdomen");
        shouldEncodeAndDecode(instance, "07ff", "zurdo");
        shouldEncodeAndDecode(instance, "0800", "abdomen ábaco");
        shouldEncodeAndDecode(instance, "3fffff", "zurdo zurdo");
    }

    @Test
    public void chineseSimplified() {
        Base2048 instance = Base2048.CHINESE_SIMPLIFIED;
        shouldEncodeAndDecode(instance, "00", "的");
        shouldEncodeAndDecode(instance, "01", "一");
        shouldEncodeAndDecode(instance, "07ff", "歇");
        shouldEncodeAndDecode(instance, "0800", "一 的");
        shouldEncodeAndDecode(instance, "3fffff", "歇 歇");
    }

    @Test
    public void chineseTraditional() {
        Base2048 instance = Base2048.CHINESE_TRADITIONAL;
        shouldEncodeAndDecode(instance, "00", "的");
        shouldEncodeAndDecode(instance, "01", "一");
        shouldEncodeAndDecode(instance, "07ff", "歇");
        shouldEncodeAndDecode(instance, "0800", "一 的");
        shouldEncodeAndDecode(instance, "3fffff", "歇 歇");
    }

    private void shouldEncodeAndDecode(Base2048 solver, final String hexInput, final String base2048Input) {

        final byte[] binaryDataInput;
        try {
            binaryDataInput = Hex.decodeHex(hexInput.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        final String encodedBase2048 = solver.encode(binaryDataInput);
        assertEquals(base2048Input, encodedBase2048);

        final byte[] decodedBinaryData = solver.decode(base2048Input);
        assertArrayEquals(binaryDataInput, decodedBinaryData);
    }
}