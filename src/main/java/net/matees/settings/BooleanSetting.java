package net.matees.settings;

import java.util.List;

public abstract class BooleanSetting extends Setting<Boolean> {
    @Override
    public void setIntValue(int value) {
    }

    @Override
    public void setBooleanValue(boolean value) {
        setSetting(value);
    }

    @Override
    public void setStringValue(String value) {
    }

    @Override
    public void setListValue(List value) {
    }

}
