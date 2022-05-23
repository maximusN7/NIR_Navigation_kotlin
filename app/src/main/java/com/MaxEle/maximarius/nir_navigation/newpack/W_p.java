package com.MaxEle.maximarius.nir_navigation.newpack;

public class W_p extends MyBigClass {
    public W_p(int V, int U,int YV, double YS) {
        this.V = V;
        this.U = U;
        this.YV = YV;
        this.YS = YS;
    }

    int V;
    int U;
    int YV;
    double YS;

    @Override
    public Double calculate(){
        if (YV==0){
            return (double)V+U;
        }else {
            if (YV == 180) {
                return (double)V-U;
            } else {
                return V * Math.sin(Math.toRadians(YV + YS)) / Math.sin(Math.toRadians(YV));
            }
        }
    }
}
