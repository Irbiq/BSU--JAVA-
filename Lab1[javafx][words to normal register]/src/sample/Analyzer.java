package sample;

public class Analyzer {

    public static String analyze(String str) {
        String[] strings = str.split("(?=[^a-zA-Zа-яА-Я]+)|(?<=[^a-zA-Zа-яА-Я]+)");//look ahead look before
        str = null;
        int uCount = 0;
        char ch;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strings.length; ++i) {
            uCount = 0;
            if (strings[i].length() == 1) {
                sb.append(strings[i]);
                continue;
            }
            for (int j = 0; j < strings[i].length(); j++) {
                ch = strings[i].charAt(j);
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'А' && ch <= 'Я')) {
                    uCount++;
                }
            }
            if (uCount == strings[i].length() || uCount == 0) {
                sb.append(strings[i]);
                continue;
            }
            sb.append(strings[i].charAt(0));
            sb.append(strings[i].substring(1).toLowerCase());

        }
        return sb.toString();
    }
}
