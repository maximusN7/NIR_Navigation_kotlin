package com.MaxEle.maximarius.nir_navigation.newpack;

public class Ben_p extends MyBigClass {

    public Ben_p(int Bem, int dM) {
        this.Bem = Bem;
        this.dM = dM;
        Ben = Bem-dM;
        if (Ben>=180){Ben=Ben-180;}else{Ben=Ben+180;}
    }

    int Bem;
    int dM;
    int Ben;

    @Override
    public Integer calculate(){
        return Ben;
    }
}
