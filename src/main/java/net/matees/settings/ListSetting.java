package net.matees.settings;

import java.util.List;

public abstract class ListSetting extends Setting<List>{
    @Override
    public void setIntValue(int value) {  }

    @Override
    public void setBooleanValue(boolean value) { }

    @Override
    public void setStringValue(String value) { }

    @Override
    public void setListValue(List value) { setSetting(value);}

}
