package com.gj;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SpringPropertiesUtil  extends PropertyPlaceholderConfigurer{


    public static Map<String, String> propertiesMap;
   
    private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        super.setSystemPropertiesMode(systemPropertiesMode);
        springSystemPropertiesMode = systemPropertiesMode;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = beanFactory.resolveEmbeddedValue(DEFAULT_PLACEHOLDER_PREFIX + keyStr.trim() + DEFAULT_PLACEHOLDER_SUFFIX);
            propertiesMap.put(keyStr, valueStr);
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }

	public static Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	public static void setPropertiesMap(Map<String, String> propertiesMap) {
		SpringPropertiesUtil.propertiesMap = propertiesMap;
	}


}
