package c24.thriftshop.webspring.domain;

public class Converter {
    public String byteToHex(final byte num) {
        final char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public String bytesToHex(final byte[] num) {
        String out = "";
        for (int i = 0; i < num.length; ++i) {
            final char[] hexDigits = new char[2];
            hexDigits[0] = Character.forDigit((num[i] >> 4) & 0xF, 16);
            hexDigits[1] = Character.forDigit((num[i] & 0xF), 16);
            out += new String(hexDigits);
        }
        return out;
    }

    public byte hexToByte(final String hexString) {
        final int firstDigit = toDigit(hexString.charAt(0));
        final int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private int toDigit(final char hexChar) {
        final int digit = Character.digit(hexChar, 16);
        if (digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: " + hexChar);
        }
        return digit;
    }
}
