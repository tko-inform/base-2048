/**
 * base-x encoding
 * 
 * Forked from https://github.com/safezero/base-x-array
 * That forked from https://github.com/cryptocoinjs/base-x
 * That forked from https://github.com/cryptocoinjs/bs58
 *
 * Originally written by Mike Hearn for BitcoinJ
 * Copyright (c) 2011 Google Inc
 * Ported to JavaScript by Stefan Thomas
 * Merged Buffer refactorings from base58-native by Stephen Pair
 * Copyright (c) 2013 BitPay Inc
 */

package safezero.base2048;

import java.util.*;

final class BaseX {

    public final String[] alphabet;
    public final int base;
    private final String leader;

    private final Map<String, Integer> alphabetMap = new HashMap<>();

    public BaseX(final String[] alphabet) {
        this.alphabet = alphabet;
        this.base = alphabet.length;
        this.leader = alphabet[0];

        for (int i = 0; i < alphabet.length; i++) {
            alphabetMap.put(alphabet[i], i);
        }
    }

    public String encode(final byte[] source) {
        if (source.length == 0) {
            return "";
        }

        List<Integer> digits = new ArrayList<>();
        digits.add(0);

        for (int i = 0; i < source.length; ++i) {
            int carry = source[i] & 0xff;

            for (int j = 0; j < digits.size(); ++j) {
                carry += digits.get(j) << 8;
                digits.set(j, carry % base);
                carry = (carry / base) | 0;
            }

            while (carry > 0) {
                digits.add(carry % base);
                carry = (carry / base) | 0;
            }
        }

        List<String> letters = new ArrayList<>();

        // deal with leading zeros
        for (int k = 0; source[k] == 0 && k < source.length - 1; ++k) {
            letters.add(alphabet[0]);
        }

        // convert digits to a string
        for (int q = digits.size() - 1; q >= 0; --q) {
            letters.add(alphabet[digits.get(q)]);
        }

        return String.join(" ", letters);
    }

    public byte[] decode(String input) throws IllegalBaseCodeException {
        if (input.length() == 0) {
            return new byte[0];
        }

        String[] letters = input.split("\\s+");

        List<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 0);

        for (int i = 0; i < letters.length; i++) {
            Integer value = alphabetMap.get(letters[i]);
            if (null == value) {
                throw new IllegalBaseCodeException(letters[i]);
            }

            int carry = value;
            for (int j = 0; j < bytes.size(); ++j) {
                carry += unsignedByteToInt(bytes.get(j)) * base;
                bytes.set(j, (byte) (carry & 0xff));
                carry >>= 8;
            }

            while (carry > 0) {
                bytes.add((byte) (carry & 0xff));
                carry >>= 8;
            }
        }

        // deal with leading zeros
        for (int k = 0; leader.equals(letters[k]) && k < letters.length - 1; ++k) {
            bytes.add((byte) 0);
        }

        Collections.reverse(bytes);
        return toArray(bytes);
    }

    private static byte[] toArray(List<Byte> bytes) {
        byte[] result = new byte[bytes.size()];

        int i = 0;
        for (Byte b : bytes) {
            result[i] = b;
            i++;
        }

        return result;
    }

    private int unsignedByteToInt(byte b) {
        return b & 0xff;
    }
}
