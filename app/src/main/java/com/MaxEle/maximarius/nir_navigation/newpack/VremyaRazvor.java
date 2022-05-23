package com.MaxEle.maximarius.nir_navigation.newpack;

public class VremyaRazvor extends MyBigClass {

    public VremyaRazvor(double Rad,int YR,int V) {
        this.V =V;
        this.YR = YR;
        this.Rad = Rad;
    }

    int V;
    int YR;
    double Rad;

    @Override
    public Double calculate(){
        return Pi*Rad*100*YR*36/180/V;
    }


}
