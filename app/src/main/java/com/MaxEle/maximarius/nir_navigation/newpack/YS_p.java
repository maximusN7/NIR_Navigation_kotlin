package com.MaxEle.maximarius.nir_navigation.newpack;

public class YS_p extends MyBigClass {
    public YS_p(int U, int V, int YV) {
        this.U = U;
        this.V = V;
        this.YV = YV;
    }

    int U;
    int V;
    int YV;

    @Override
    public Double calculate(){
        return Math.toDegrees(Math.asin(U*Math.sin(Math.toRadians(YV))/V));
    }
}
