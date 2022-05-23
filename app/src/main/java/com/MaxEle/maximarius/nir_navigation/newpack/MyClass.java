package com.MaxEle.maximarius.nir_navigation.newpack;


public class MyClass{
    Double g = 9.80665;
    Double Pi = 3.14159;

    public boolean nesush1=false;
    public boolean nesush2=false;
    public boolean nesush3=false;
    public boolean nesush4=false;
    public boolean nesush5=false;
    public boolean nesush6=false;
    public boolean nesush7=false;
    public boolean nesush8=false;

    private void ClearFlag(){
        nesush1=false;
        nesush2=false;
        nesush3=false;
        nesush4=false;
        nesush5=false;
        nesush6=false;
        nesush7=false;
        nesush8=false;
    }

    public Integer V;
    public Byte x;
    public Double RadRazv(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;
        }
        if (x==null){
            x=(byte)(5+(int)(Math.random()*40));
            nesush2=true;
        }
        Rad = V*V*10*10/36/36/g/Math.tan(Math.toRadians(x))/1000;
        return Rad;
    }
    public Integer YR;
    public Double Rad;

    public Double VremyaRazvor(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;
        }
        if (Rad==null){
            Rad=(double)((10+(int)(Math.random()*50)))/10;
            nesush2=true;
        }
        if (YR==null){
            YR=1+(int)(Math.random()*180);
            nesush3=true;
        }
        return Pi*Rad*100*YR*36/180/V;
    }

    public Double LYRRazvr(){
        ClearFlag();
        if (Rad==null){
            Rad=(double)((10+(int)(Math.random()*50)))/10;
            nesush1=true;

        }
        if (YR==null){
            YR=1+(int)(Math.random()*180);
            nesush2=true;

        }
        return Rad*Math.tan(Math.toRadians(YR/2));
    }

    public Double W;
    public Double YV;
    public Double YS;
    public Double W_p(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;
        }
        if (YV==null){
            YV=(double)((-179)+(int)(Math.random()*360));
            nesush2=true;
        }
        if (YS==null){
            YS=(double)((-15)+(int)(Math.random()*31));
            nesush3=true;
        }
        W=V*Math.sin(Math.toRadians(YV+YS))/Math.sin(Math.toRadians(YV));
        return W;
    }

    public Double U;
    public Double YS_p(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;
        }
        if (YV==null){
            YV=(double)((-179)+(int)(Math.random()*360));
            nesush2=true;
        }
        if (U==null){
            U=(double)(int)(Math.random()*200);
            nesush3=true;
        }
        YS=Math.toDegrees(Math.asin(U*Math.sin(Math.toRadians(YV))/V));
        YS = makeYgol180_180(YS);
        return YS;
    }

    public Integer Bem;
    public Integer dM;
    public Integer Ben_p(){
        ClearFlag();
        if (Bem==null){
            Bem=(int)(Math.random()*360);
            nesush1=true;

        }
        if (dM==null){
            dM=(-15)+(int)(Math.random()*31);
            nesush2=true;

        }
        Ben = Bem-dM;
        if (Ben>=180){Ben=Ben-180;}else{Ben=Ben+180;}
        Ben = (int)makeYgol0_360(Ben);
        return Ben;
    }
    public Integer MK;
    public Integer Ben_p2(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;
        }
        if (W==null){
            W=(double)(100+(int)(Math.random()*900));
            nesush2=true;
        }
        if (Bem==null){
            Bem=(int)(Math.random()*360);
            nesush3=true;
        }
        if (dM==null){
            dM=(-15)+(int)(Math.random()*31);
            nesush4=true;
        }
        if (MK==null){
            MK=(int)(Math.random()*360);
            nesush5=true;
        }
        if (YV==null){
            YV=(double)((-179)+(int)(Math.random()*360));
            nesush6=true;
        }
        if (YS==null){
            YS=(double)((-15)+(int)(Math.random()*31));
            nesush7=true;
        }
        if (W>=V){
            Ben=(int)(MK+YS+YV);
        }
        else{
            Ben=(int)(MK+YS-YV);
            if (Ben>=180){
                Ben=Ben-180;
            }
            else
            {
                Ben=Ben+180;
            }
        }
        Ben = (int)makeYgol0_360(Ben);
        return Ben;
    }
    public Integer Bem_p(){
        ClearFlag();
        if (Ben==null){
            Ben=(int)(Math.random()*360);
            nesush1=true;

        }
        if (dM==null){
            dM=(-15)+(int)(Math.random()*31);
            nesush2=true;

        }
        Bem=Ben+dM;
        if (Bem<180) {
            Bem = Bem + 180;
        }
        else {
            Bem = Bem - 180;
        }
        Bem = (int)makeYgol0_360(Bem);
        return Bem;
    }

    public Integer Ben;
    public Integer ZMPY;
    public Double YV_p(){
        ClearFlag();
        if (Ben==null){
            Ben=(int)(Math.random()*360);
            nesush1=true;

        }
        if (ZMPY==null){
            ZMPY=(int)(Math.random()*360);
            nesush2=true;

        }
        YV = (double)(Ben - ZMPY);
        YV = makeYgol180_180(YV);
        return YV;
    }

    public Double U_p(){
        ClearFlag();
        if (V==null){
            V=100+(int)(Math.random()*800);
            nesush1=true;

        }
        if (W==null){
            W=(double)(100+(int)(Math.random()*900));
            nesush2=true;

        }
        if (YS==null){
            YS=(double)((-15)+(int)(Math.random()*31));
            nesush3=true;

        }
        U=Math.sqrt(V*V+W*W-2*V*W*Math.cos(Math.toRadians(YS)));
        return U;
    }

    public double makeYgol0_360(double x){
        do {
            if (x>=360){
                x=x-360;
            }} while(x>=360);
        do {
            if (x<0){
                x=x+360;
            }} while(x<0);
        return x;
    }
    public double makeYgol180_180(double x){
        if (x>180){
            x=x-360;
        }
        if (x<-180){
            x=x+360;
        }
        return x;
    }

    int[][] arrShat = {{0,300,400,500,600,700,800,900},{2000,1,2,3,4,7,9,10},{4000,2,4,6,10,16,23,24},{6000,3,6,11,18,27,39,40},{8000,4,9,17,28,41,53,54},{10000,6,13,24,40,56,80,81},{12000,9,19,34,56,78,98,99},{14000,12,25,48,73,97,118,119}};
    public int CoafShatia(int V,int H){
        int Shat = 0;
        int Ig=0;
        int Jg=0;
        double A;

        for (int i=0;i<7;i++){
            if (H>=arrShat[i][0]){Ig=i;}
        }
        for (int i=0;i<7;i++){
            if (V>=arrShat[0][i]){Jg=i;}
        }
        if ((Ig==0)||(Jg==0)){
            Shat=0;
        }else{
            A=(((double)arrShat[Ig][Jg]*(arrShat[0][Jg+1]-V)*(arrShat[Ig+1][0]-H)/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig][Jg+1]*(V-arrShat[0][Jg])*(arrShat[Ig+1][0]-H)/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig+1][Jg]*(arrShat[0][Jg+1]-V)*(H-arrShat[Ig][0])/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0]))+((double)arrShat[Ig+1][Jg+1]*(V-arrShat[0][Jg])*(H-arrShat[Ig][0])/(arrShat[0][Jg+1]-arrShat[0][Jg])/(arrShat[Ig+1][0]-arrShat[Ig][0])));
            Shat=(int)A;
            Shat=(-1)*Shat;
        }
        return Shat;
    }
}
