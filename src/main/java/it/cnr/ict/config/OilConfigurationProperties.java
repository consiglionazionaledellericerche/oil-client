package it.cnr.ict.config;

import it.cnr.ict.domain.Category;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "oil")
public class OilConfigurationProperties {

    private String url;
    private String username;
    private String password;
    private String instance;
    private List<Category> acceptcategories;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public List<Category> getAcceptcategories() {
        return acceptcategories;
    }

    public void setAcceptcategories(List<Category> acceptcategories) {
        this.acceptcategories = acceptcategories;
    }
}
