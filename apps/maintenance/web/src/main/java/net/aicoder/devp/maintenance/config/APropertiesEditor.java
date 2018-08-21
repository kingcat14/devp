package net.aicoder.devp.maintenance.config;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.sql.Timestamp;

public class APropertiesEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {



    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

}
