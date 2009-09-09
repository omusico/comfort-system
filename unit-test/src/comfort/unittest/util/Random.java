package comfort.unittest.util;

public class Random {
    private static java.util.Random random = new java.util.Random();

    public static Object randomOptions(Object[] options) {
        int i = random.nextInt(options.length);
        return options[i];
    }

    public static String randomString() {
        return randomString(255);
    }

    public static String randomString(int maxLength) {
        return randomString(maxLength, true);
    }

    public static String randomString(int maxLength, boolean allowEmpty) {
        int l = random.nextInt(maxLength + 1);
        if (!allowEmpty && l == 0) l++;
        String s = "";
        for (int i = 0; i < l; i++) {
            s += random.nextInt(10);
        }
        return s;
    }

}