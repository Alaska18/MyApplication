package com.example.myapplication;

public class querysnapshot {
    String mcomponent;
    int mvolatge;
    int mcurrent;
    int mpower;
    public querysnapshot() {
    }
    public void setcomponent(String component)
    {
        mcomponent = component;
    }
    public void setpower( int Power)
    {
       mpower = Power;
    }
    public void setvoltage(int voltage)
    {
        mvolatge = voltage;
    }
    public void setcurrent(int Current)
    {
      mcurrent = Current;
    }
    public String getcomponent()
    {
        return mcomponent;
    }
    public int getCurrent()
    {
        return mcurrent;
    }
    public int getVoltage()
    {
        return mvolatge;
    }
    public int getpower()
    {
        return mpower;
    }
    public void setEnergy(String component, int Voltage, int current, int power)
    {
        mcomponent = component;
        mcurrent = current;
        mvolatge = Voltage;
        mpower = power;
    }
    public void setEnergytwo(String component, int Voltage, int current, int power)
    {
        mcomponent = component;
        mcurrent = current;
        mvolatge = Voltage;
        mpower = power;
    }
}
