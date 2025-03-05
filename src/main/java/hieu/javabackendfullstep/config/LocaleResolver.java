package hieu.javabackendfullstep.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        return StringUtils.hasLength(language) ?
                Locale.lookup(Locale.LanguageRange.parse(language), List.of(new Locale("en"), new Locale("fr"),new Locale("vi")))
                : Locale.getDefault();
    }
}
