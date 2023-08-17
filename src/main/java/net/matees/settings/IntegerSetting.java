package net.matees.settings;

import java.util.List;

public abstract class IntegerSetting extends Setting<Integer>{
    @Override
    public void setIntValue(int value) { setSetting(value); System.out.println(value); }

    @Override
    public void setBooleanValue(boolean value) { }

    @Override
    public void setStringValue(String value) { }

    @Override
    public void setListValue(List value) { }
}
