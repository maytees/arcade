package net.matees;

public interface Setting<T> {

    public String getName();

    // Default
    public T getSetting();

    public void setSetting(T setting);
}
