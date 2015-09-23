package com.cxe.commons;

import java.text.Normalizer;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author /Syam Sathyan
 */
public class Strings {

    public static final String FILE_SEPERATOR;
    public static final String EMPTY_STRING = "";
    public static final String UNDERSCORE = "_";
    public static final String SPACE = " ";
    public static final String SLASH = "/";
    public static final String MODULUS = "%";
    public static final String EQUALS = "=";
    public static final String COMMA = ",";
    public static final String STARTING_ROUND = "(";
    public static final String ENDING_ROUND = ")";
    public static final String DOT = ".";
    //Patterns
    public static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    public static final String ALPHA_NUMERIC_PATTERN = "[a-zA-Z0-9]+";
    private static final Pattern alphaNumericPattern = Pattern.compile(ALPHA_NUMERIC_PATTERN);
    public static final String NUMERIC_PATTERN = "[^0-9]";
    private static final Pattern numericPattern = Pattern.compile(NUMERIC_PATTERN);

    static {
        FILE_SEPERATOR = System.getProperty("file.separator");

    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        return NONLATIN.matcher(Normalizer.normalize(WHITESPACE.matcher(input).replaceAll("-"), Normalizer.Form.NFD)).replaceAll("").toLowerCase(Locale.ENGLISH);
    }

    public static final boolean isAlphaNumeric(String string) {
        if (string == null) {
            return false;
        }
        Matcher matcher = alphaNumericPattern.matcher(string);
        return matcher.matches();
    }

    public static final boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        Matcher matcher = numericPattern.matcher(string);
        return matcher.matches();
    }

    public static final boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param (eg:ss101y@att.com)
     * @return S*****@att.com
     */
    public static String capitalizeFirstLetter(String variable) {
        if (variable != null && variable.length() > 1) {
            return Strings.joinFast(String.valueOf(Character.toUpperCase(variable.charAt(0))), variable.substring(1));
        } else {
            return variable;
        }
    }

    /**
     * @param email (eg:ss101y@att.com)
     * @return s*****@att.com
     */
    public static String concealEmail(String email, String mask) {
        int indexAt = email.indexOf("@");
        int length = email.length();
        //Find the remaining after @
        //email.substring(indexAt);
        return Strings.joinFill(String.valueOf(email.charAt(0)), mask == null ? "x" : mask, indexAt - 1, email.substring(indexAt, length));
    }

    /**
     * @return U0001055xxxxxx
     * @params {content:U0001055UN6TYI, concealCount: 6, tailMode, true, concealCharacter: null}
     */
    public static String concealCharacters(String content, int concealCount, boolean tailMode, String mask) {
        if (tailMode) {
            int index = content.length() - concealCount;
            String conceal = content.substring(index, content.length());
            return Strings.joinFill(content.substring(0, index), mask == null ? "x" : mask, conceal.length());
        } else {
            String pre = content.substring(0, concealCount);
            return Strings.joinFill(mask == null ? "x" : mask, pre.length(), content.substring(concealCount, content.length()));
        }
    }

    /**
     * @param phone (eg:+4046626413) / SAFE FOR any input with or without +
     * @return 4046626413
     */
    public static String removeSymbolIfExists(String symbol, String phone) {
        if (phone == null) {
            return null;
        }
        int plusIndex = phone.indexOf(symbol);
        if (plusIndex != -1) {
            phone = phone.substring(plusIndex + 1);
        }
        return phone;
    }

    /**
     * <p>
     * Joins the elements of the provided {@code Iterator} into a single String
     * containing the provided elements.</p>
     * <p/>
     * <p>
     * No delimiter is added before or after the list. A {@code null} separator
     * is the same as an empty String ("").</p>
     * <p/>
     * <p>
     * See the examples here: {join(Object[], String)}. </p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be
     *                  null
     * @param separator the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null iterator input
     */
    public static String join(Iterator<?> iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY_STRING;
        }

        if (!iterator.hasNext()) {
            return EMPTY_STRING;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // two or more elements
        FastStringBuilder buf = new FastStringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @return
     */
    @Deprecated
    public static final String join(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2);
        builder.append(s1, l1);
        builder.append(s2, l2);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @return
     */
    public static final String joinFast(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int count = 0;
        char value[] = new char[l1 + l2];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    @Deprecated
    public static final String join(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static final String joinFast(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @return
     */
    @Deprecated
    public static final String join(String s1, String s2, String s3, String s4) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3 + l4);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        builder.append(s4, l4);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @return
     */
    public static final String joinFast(String s1, String s2, String s3, String s4) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3 + l4];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        s4.getChars(0, l4, value, count);
        count += l4;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @return
     */
    @Deprecated
    public static final String join(String s1, String s2, String s3, String s4, String s5) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3 + l4 + l5);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        builder.append(s4, l4);
        builder.append(s5, l5);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @return
     */
    public static final String joinFast(String s1, String s2, String s3, String s4, String s5) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3 + l4 + l5];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        s4.getChars(0, l4, value, count);
        count += l4;

        s5.getChars(0, l5, value, count);
        count += l5;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @return combined
     */
    @Deprecated
    public static final String join(String s1, String s2, String s3, String s4, String s5, String s6) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3 + l4 + l5 + l6);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        builder.append(s4, l4);
        builder.append(s5, l5);
        builder.append(s6, l6);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @return combined
     */
    public static final String joinFast(String s1, String s2, String s3, String s4, String s5, String s6) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3 + l4 + l5 + l6];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        s4.getChars(0, l4, value, count);
        count += l4;

        s5.getChars(0, l5, value, count);
        count += l5;

        s6.getChars(0, l6, value, count);
        count += l6;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @param s7
     * @return combined
     */
    @Deprecated
    public static final String join(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        int l7 = s7.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3 + l4 + l5 + l6 + l7);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        builder.append(s4, l4);
        builder.append(s5, l5);
        builder.append(s6, l6);
        builder.append(s7, l7);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @param s7
     * @return combined
     */
    public static final String joinFast(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        int l7 = s7.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3 + l4 + l5 + l6 + l7];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        s4.getChars(0, l4, value, count);
        count += l4;

        s5.getChars(0, l5, value, count);
        count += l5;

        s6.getChars(0, l6, value, count);
        count += l6;

        s7.getChars(0, l7, value, count);
        count += l7;

        return new String(value, 0, count);
    }

    /**
     * No Null Check, This method creates a FixedFastStringBuilder with the
     * combined length and appends the two strings and returns.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @param s7
     * @param s8
     * @return
     */
    public static final String join(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        int l7 = s7.length();
        int l8 = s8.length();
        FixedFastStringBuilder builder = new FixedFastStringBuilder(l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8);
        builder.append(s1, l1);
        builder.append(s2, l2);
        builder.append(s3, l3);
        builder.append(s4, l4);
        builder.append(s5, l5);
        builder.append(s6, l6);
        builder.append(s7, l7);
        builder.append(s8, l8);
        return builder.toString();
    }

    /**
     * No Null Check, This method creates manages appends using character array with combined length and hence light and fast.
     *
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @param s5
     * @param s6
     * @param s7
     * @param s8
     * @return combined
     */
    public static final String joinFast(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        int l4 = s4.length();
        int l5 = s5.length();
        int l6 = s6.length();
        int l7 = s7.length();
        int l8 = s8.length();
        int count = 0;
        char value[] = new char[l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8];

        s1.getChars(0, l1, value, count);
        count += l1;

        s2.getChars(0, l2, value, count);
        count += l2;

        s3.getChars(0, l3, value, count);
        count += l3;

        s4.getChars(0, l4, value, count);
        count += l4;

        s5.getChars(0, l5, value, count);
        count += l5;

        s6.getChars(0, l6, value, count);
        count += l6;

        s7.getChars(0, l7, value, count);
        count += l7;

        s8.getChars(0, l8, value, count);
        count += l8;

        return new String(value, 0, count);
    }

    public static final boolean isEmpty(String input) {
        return (input == null || input.isEmpty());
    }

    public static final String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static final String joinFill(String string, String fillCharacter, int count) {
        //Create the fill
        int fillCharacterLength = fillCharacter.length();
        char value[] = new char[fillCharacterLength * count];
        for (int i = 0; i < count; i++) {
            fillCharacter.getChars(0, fillCharacterLength, value, i);
        }
        String filled = new String(value, 0, count);
        return joinFast(string, filled);
    }

    /**
     * @param startString   - starting string to be appended
     * @param fillCharacter - character to fill with
     * @param count         - the number of times the character is to be filled
     * @param endString     - the ending string to be appended
     * @return
     */
    public static final String joinFill(String startString, String fillCharacter, int count, String endString) {
        //Create the fill
        int fillCharacterLength = fillCharacter.length();
        char value[] = new char[fillCharacterLength * count];
        for (int i = 0; i < count; i++) {
            fillCharacter.getChars(0, fillCharacterLength, value, i);
        }
        String filled = new String(value, 0, count);
        return joinFast(startString, filled, endString);
    }

    /**
     * @param startString   - starting string to be appended
     * @param fillCharacter - character to fill with
     * @param count         - the number of times the character is to be filled
     * @param endString     - the ending string to be appended
     * @return
     */
    public static final String joinFill(String fillCharacter, int count, String endString) {
        //Create the fill
        int fillCharacterLength = fillCharacter.length();
        char value[] = new char[fillCharacterLength * count];
        for (int i = 0; i < count; i++) {
            fillCharacter.getChars(0, fillCharacterLength, value, i);
        }
        String filled = new String(value, 0, count);
        return joinFast(filled, endString);
    }

    /**
     * @param startString   - starting string to be appended
     * @param fillCharacter - character to fill with
     * @param count         - the number of times the character is to be filled
     * @param endString     - the ending string to be appended
     * @param seperator     - the separator character to be inserted in-between the
     *                      fill character
     * @return returns the whole String reduced by 1 from the end to avoid a
     * trailing seperator character
     */
    public static final String joinFill(String startString, String fillCharacter, char seperator, int count, String endString) {
        //Create the fill
        String filled = fill(fillCharacter, seperator, count);
        return joinFast(startString, filled, endString);
    }

    public static final String fill(String fillCharacter, char seperator, int count) {
        int fillCharacterLength = fillCharacter.length();
        char value[] = new char[(fillCharacterLength * count) + count];
        count = value.length;
        for (int i = 0; i < count; i++) {
            fillCharacter.getChars(0, fillCharacterLength, value, i);
            i++;
            value[i] = seperator;
        }
        return new String(value, 0, count - 1); // remove the last seperator from behind
    }

    /**
     * @param fillCharacter - character to fill with
     * @param count         - the number of times the character is to be filled
     * @return
     */
    public static final String fill(String fillCharacter, int count) {
        int fillCharacterLength = fillCharacter.length();
        char value[] = new char[fillCharacterLength * count];
        for (int i = 0; i < count; i++) {
            fillCharacter.getChars(0, fillCharacterLength, value, i);
        }
        return new String(value, 0, count);
    }

    /**
     * PHP Style implode
     * @param seperator
     * @param array
     * @return
     */
    public static final String implode(char seperator, String array[]) {
        FastStringBuilder builder = new FastStringBuilder();
        for (String s : array) {
            builder.append(s);
            builder.append(seperator);
        }
        return builder.toString(1);
    }
}
