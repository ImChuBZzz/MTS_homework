package config;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/.properties")
public interface ProjectConfig extends Config {
    @Key("start_url")
    @DefaultValue(value = "https://www.ya.ru")
    String baseUrl();
}
