package back.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
@Value("${upload.path]")
String filepath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
                registry.addResourceHandler("/store/posters/**")
                        .addResourceLocations("file://" +
                                filepath+"/");
    }
}
