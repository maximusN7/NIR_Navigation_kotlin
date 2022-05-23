package com.MaxEle.maximarius.nir_navigation.newpack;

public class U_p extends MyBigClass {
    public U_p(int W, int V, double YS) {
        this.W = W;
        this.V = V;
        this.YS = YS;
    }

    int W;
    int V;
    double YS;

    @Override
    public Double calculate(){
        return Math.sqrt(V*V+W*W-2*V*W*Math.cos(Math.toRadians(YS)));
    }
}
