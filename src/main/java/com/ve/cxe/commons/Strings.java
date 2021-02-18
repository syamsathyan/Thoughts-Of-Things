package com.ve.cxe.commons;

import java.text.Normalizer;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringJoiner;
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
    public static final String HYPHEN = "-";
    public static final int INDEX_NOT_FOUND = -1;
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
     * @param ('ss101y@att.com')
     * @return S*****@att.com
     */
    public static String capitalizeFirstLetter(String variable) {
        if (variable != null && variable.length() > 1) {
            return String.valueOf(Character.toUpperCase(variable.charAt(0))).concat(variable.substring(1));
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
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     * <p>
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     * <p>
     * <p>If nothing is found, the string input is returned.</p>
     * <p>
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     * <code>null</code> if null String input
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY_STRING;
        }
        int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static final String joinFast(String... val) {
        StringBuilder builder = new StringBuilder();
        for (String i : val) {
            builder.append(i);
        }
        return builder.toString();
    }

    /**
     * Gets the substring after the first occurrence of a separator. The separator is not returned.
     * A null string input will return null. An empty ("") string input will return the empty string. A null separator will return the empty string if the input string is not null.
     * If nothing is found, the empty string is returned.
     * <p>
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str       the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     * <code>null</code> if null String input
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY_STRING;
        }
        int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }
        return str.substring(pos + separator.length());
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
        StringBuilder buf = new StringBuilder(); // Java default is 16, probably too small
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


    public static final boolean isEmpty(String input) {
        return (input == null || input.isEmpty());
    }

    public static final String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static final String joinFill(String string, String fillCharacter, int count) {
        String filled = fill(fillCharacter, count);
        return string.concat(filled);
    }

    /**
     * @param startString   - starting string to be appended
     * @param fillCharacter - character to fill with
     * @param count         - the number of times the character is to be filled
     * @param endString     - the ending string to be appended
     * @return
     */
    public static final String joinFill(String startString, String fillCharacter, int count, String endString) {
        String filled = fill(fillCharacter, count);
        StringBuilder builder = new StringBuilder(startString);
        builder.append(filled);
        builder.append(endString);
        return builder.toString();
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
        String filled = fill(fillCharacter, count);
        StringBuilder builder = new StringBuilder(filled);
        builder.append(endString);
        return builder.toString();
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
        StringBuilder builder = new StringBuilder(startString);
        builder.append(filled);
        builder.append(endString);
        return builder.toString();
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
     *
     * @param seperator
     * @param array
     * @return
     */
    private static final Pattern SPACES_OR_EMPTY = Pattern.compile(" *");

    public static String implode(String separator, String... data) {
        StringJoiner sb = new StringJoiner(separator);
        for (String token : data) {
            if (!SPACES_OR_EMPTY.matcher(token).matches()) {
                sb.add(token);
            }
        }
        return sb.toString();
    }
}
