package com.MaxEle.maximarius.nir_navigation.newpack;

public class YV_p extends MyBigClass {
    public YV_p(int Ben, int ZMPY) {
        this.Ben = Ben;
        this.ZMPY = ZMPY;
        YV = Ben - ZMPY;
        if (YV>180){YV=YV-360;}
        if (YV<=-180){YV=YV+360;}
    }

    int Ben;
    int ZMPY;
    int YV;

    @Override
    public Integer calculate(){
        return YV;
    }
}
