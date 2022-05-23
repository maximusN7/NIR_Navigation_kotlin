package com.MaxEle.maximarius.nir_navigation.newpack;

public class RadRazv extends MyBigClass{

    public RadRazv(int v, byte x) {
        this.V = v;
        this.x = x;
    }

    int V;
    byte x;

    @Override
    public Double calculate(){
        return V*V*10*10/36/36/g/Math.tan(Math.toRadians(x))/1000;
    }

}
