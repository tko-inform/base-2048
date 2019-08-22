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

    @Test
    public void readMeTest() {
        final String input = "003c176e659bea0f29a3e9bf7880c112b1b31b4dc826268187";
        final byte[] bytes;
        try {
            bytes = Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        assertEquals(
                "的 和 暗 磁 集 捐 区 纱 悟 饿 表 瓶 恩 脚 太 亏 质 匀 容",
                Base2048.CHINESE_SIMPLIFIED.encode(bytes)
        );

        assertEquals(
                "的 和 暗 磁 集 捐 區 紗 悟 餓 表 瓶 恩 腳 太 虧 質 勻 容",
                Base2048.CHINESE_TRADITIONAL.encode(bytes)
        );

        assertEquals(
                "abandon abstract load hover coast whisper bundle olive visit worth avoid scissors night holiday custom symptom basic old couch",
                Base2048.ENGLISH.encode(bytes)
        );

        assertEquals(
                "abaisser aboyer insecte fureur cercle vidéo bistouri mérite utile volaille appuyer prétexte manquant frénésie concert siffler asservir mercredi chute",
                Base2048.FRENCH.encode(bytes)
        );

        assertEquals(
                "abaco abrogato monsone lacuna citrico vincitore bisturi parvenza vanitoso zattera arazzo satira ottagono irrorare dado stiletto asola partire continuo",
                Base2048.ITALIAN.encode(bytes)
        );

        assertEquals(
                "あいこくしん あける そんぞく すうせん かまぼこ るいじ えんとつ つとめる よそく ろせん いよく はえる ちひょう しんちく きみつ ほきょう うきわ つつむ きくらげ",
                Base2048.JAPANESE.encode(bytes)
        );

        assertEquals(
                "ábaco abrazo loción hueco catre vil bicho nevera vajilla yacer aprender rama mula hocico colmo sudor arroz nevar chuleta",
                Base2048.SPANISH.encode(bytes)
        );
    }

    private void shouldEncodeAndDecode(final Base2048 solver,
                                       final String hexInput,
                                       final String base2048Input) {

        final byte[] binaryDataInput;
        try {
            binaryDataInput = Hex.decodeHex(hexInput.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }

        final String encodedBase2048 = solver.encode(binaryDataInput);
        assertEquals(base2048Input, encodedBase2048);

        final byte[] decodedBinaryData;
        try {
            decodedBinaryData = solver.decode(base2048Input);
        } catch (IllegalBaseCodeException e) {
            throw new RuntimeException(e);
        }

        assertArrayEquals(binaryDataInput, decodedBinaryData);
    }
}