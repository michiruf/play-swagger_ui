package models.swagger_ui;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import play.Logger;
import play.Play;
import play.mvc.Http;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Michael Ruf
 * @since 2015-05-16
 */
@SuppressWarnings("unused")
public class SwaggerUIConfiguration {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    private static SwaggerUIConfiguration instance;

    public static SwaggerUIConfiguration get() {
        if (instance == null) {
            synchronized (SwaggerUIConfiguration.class) {
                if (instance == null) {
                    instance = new SwaggerUIConfiguration();
                }
            }
        }
        return instance;
    }

    private Configuration configuration;

    private SwaggerUIConfiguration() {
        try {
            configuration = mapper.readValue(new FileInputStream(
                    Play.getFile("conf/swagger_ui.yml")), Configuration.class);
        } catch (FileNotFoundException e) {
            Logger.error("File conf/swagger_ui.yml could not be found. Swagger UI running in default mode.");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Quick and dirty
        if (configuration == null) {
            configuration = new Configuration();
        }
    }

    public boolean getShowUrlInput() {
        return configuration.showUrlInput;
    }

    public String getScheme() {
        return configuration.scheme;
    }

    public String getHost() {
        if (configuration.host != null) {
            return configuration.host;
        }
        return Http.Request.current().host;
    }

    public String getBasePath() {
        return configuration.basePath;
    }

    public String getSwaggerFilePath() {
        return configuration.swaggerFilePath;
    }

    public String getUrl() {
        // Again quick and dirty
        return getScheme() + "://" + getHost() + getBasePath() + "/" + getSwaggerFilePath();
    }

    public static final class Configuration {

        public boolean showUrlInput = true;

        public String scheme = "http";
        public String host;
        public String basePath = "";
        public String swaggerFilePath = "swagger.json";

    }

}
