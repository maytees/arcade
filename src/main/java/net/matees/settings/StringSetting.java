package net.matees.settings;

import java.util.List;

public abstract class StringSetting extends Setting<String> {
    @Override
    public void setIntValue(int value) {  }

    @Override
    public void setBooleanValue(boolean value) { }

    @Override
    public void setStringValue(String value) { setSetting(value); }

    @Override
    public void setListValue(List value) { }

}
