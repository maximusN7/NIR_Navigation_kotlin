package com.MaxEle.maximarius.nir_navigation.newpack;

public class LYRRazvr extends MyBigClass {
    public LYRRazvr(double Rad,int YR,int V) {
        this.V = V;
        this.YR = YR;
        this.Rad = Rad;
    }

    int V;
    int YR;
    double Rad;

    @Override
    public Double calculate(){
        return Rad*Math.tan(Math.toRadians(YR/2));
    }
}
