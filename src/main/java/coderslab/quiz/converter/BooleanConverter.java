package coderslab.quiz.converter;

import org.springframework.core.convert.converter.Converter;

//@Component
public class BooleanConverter implements Converter<String,Boolean> {
    @Override
    public Boolean convert(String s) {
        if (s.equals("true"))
            return true;
        else
            return false;
    }
}
