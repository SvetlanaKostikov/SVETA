import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class correctBrackets {
    public static void main(String[] args) {
        String s = "(()";
        String v = ")()())";
        String d = ")(()())";
        String e = ")(";
        String m = "())(()())(()";

        createCorrectBrackets(m);

    }

    public static void createCorrectBrackets(String input) {
        StringBuffer brackets = new StringBuffer();
        Pattern pattern = Pattern.compile("\\(\\)|\\(\\([\\W*]*\\)\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            brackets.append(matcher.group());
        }
        System.out.println(brackets.length() + " - " + brackets);
    }
}

