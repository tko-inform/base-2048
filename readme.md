base-2048
====

JavaScript component to compute base 2048 encodings.

Install
-------

    <dependency>
        <groupId>io.github.safezero</groupId>
        <artifactId>base-2048</artifactId>
        <version>0.0.1</version>
    </dependency>

Languages
-------
1. `chineseSimplified`
2. `chineseTraditional`
3. `english`
4. `french`
5. `italian`
6. `japanese`
7. `spanish`

API
---

### String encode(byte[] input)

```java
import safezero.base2048.Base2048;
import org.apache.commons.codec.binary.Hex;

String input = "003c176e659bea0f29a3e9bf7880c112b1b31b4dc826268187";
byte[] bytes;
try {
    bytes = Hex.decodeHex(input.toCharArray());
} catch (DecoderException e) {
    throw new RuntimeException(e);
}

Base2048.CHINESE_SIMPLIFIED.encode(bytes);
// => 的 和 暗 磁 集 捐 区 纱 悟 饿 表 瓶 恩 脚 太 亏 质 匀 容

Base2048.CHINESE_TRADITIONAL.encode(bytes);
// => 的 和 暗 磁 集 捐 區 紗 悟 餓 表 瓶 恩 腳 太 虧 質 勻 容

Base2048.ENGLISH.encode(bytes);
// => abandon abstract load hover coast whisper bundle olive visit worth avoid scissors night holiday custom symptom basic old couch

Base2048.FRENCH.encode(bytes);
// => abaisser aboyer insecte fureur cercle vidéo bistouri mérite utile volaille appuyer prétexte manquant frénésie concert siffler asservir mercredi chute

Base2048.ITALIAN.encode(bytes);
// => abaco abrogato monsone lacuna citrico vincitore bisturi parvenza vanitoso zattera arazzo satira ottagono irrorare dado stiletto asola partire continuo

Base2048.JAPANESE.encode(bytes);
// => あいこくしん あける そんぞく すうせん かまぼこ るいじ えんとつ つとめる よそく ろせん いよく はえる ちひょう しんちく きみつ ほきょう うきわ つつむ きくらげ

Base2048.SPANISH.encode(bytes);
// => ábaco abrazo loción hueco catre vil bicho nevera vajilla yacer aprender rama mula hocico colmo sudor arroz nevar chuleta
```


### byte[] decode(String input)

```java
final String address = "的 和 暗 磁 集 捐 区 纱 悟 饿 表 瓶 恩 脚 太 亏 质 匀 容";

final byte[] bytes;
try {
    bytes = Base2048.CHINESE_SIMPLIFIED.decode(address);
} catch (IllegalBaseCodeException e) {
    throw new RuntimeException(e);
}

Hex.encodeHexString(bytes);
// => 003c176e659bea0f29a3e9bf7880c112b1b31b4dc826268187
```
