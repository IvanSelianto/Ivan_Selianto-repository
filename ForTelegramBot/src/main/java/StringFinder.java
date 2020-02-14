import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFinder {
    public String find(Element element, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element.toString());
        matcher.find();
        try {
            return matcher.group(1);
        }catch ( java.lang.IllegalStateException e){
            return "0";
        }


    }
}
