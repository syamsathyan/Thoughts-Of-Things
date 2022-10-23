package com.siot.commons.util;

import java.math.BigInteger;

public class BigIntegerUtils {

    public static BigInteger concat(BigInteger x, BigInteger y) {
        int ndigits = y.bitLength() * 3 / 10; // Guessed number of digits using 2^10 ≈ 10^3.
        BigInteger pow10 = BigInteger.TEN.pow(ndigits);
        while (pow10.compareTo(y) > 0) {
            pow10 = pow10.divide(BigInteger.TEN);
        }
        while (pow10.compareTo(y) <= 0) {
            pow10 = pow10.multiply(BigInteger.TEN);
        }
        // Cheating: int ndigits = y.toString().length();
        return x.multiply(pow10).add(y);
    }
}
