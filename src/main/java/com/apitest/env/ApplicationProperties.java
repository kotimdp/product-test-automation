package com.apitest.env;

import com.apitest.utilities.ApplicationConstants;
import com.apitest.utilities.FileOperations;
import com.apitest.utilities.FilePathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class ApplicationProperties {

    private final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

    private String baseURL;


    private Map<String, String> additionalProps = null;

    protected ApplicationProperties() {
        additionalProps = new HashMap<>(5);
    }

    protected void loadProperties() {
        FilePathBuilder fpb = new FilePathBuilder(ApplicationConstants.ENVIORNMENT_PROPS);
        fpb.setParentDirectory(ApplicationConstants.PROPERTIES_DIRECTORY);

        String envProps = fpb.getFilePath();
        logger.debug("Environment Properties Path {}", envProps);

        FileOperations fileOps = new FileOperations(new File(envProps));
        Map<String, String> props = fileOps.getPropValuesInMap();

        if (props == null) {
            logger.error("Failed to read the properties for the application from resouce: " + envProps);
            return;
        }

        for (Map.Entry<String, String> entry : props.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (ApplicationConstants.APPLICATION_BASE_URL.equals(key))
                setBaseURL(value);
            else
                setProperty(key, value);
        }
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }


    private void setProperty(String key, String value) {
        additionalProps.put(key, value);
    }

    public String getPropertyValue(String key) {
        return additionalProps.get(key);
    }
}