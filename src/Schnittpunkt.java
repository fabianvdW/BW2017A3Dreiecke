public class Schnittpunkt  {
    double x;
    double y;
    Strecke s1;
    Strecke s2;
    public Schnittpunkt(double x, double y, Strecke s1, Strecke s2){
        //this.x=x;
        //this.y=y;
        this.x=Math.round(x*1000)/1000;
        this.y=Math.round(y*1000)/1000;
        this.s1=s1;
        this.s2=s2;
    }

    public Strecke getS2() {
        return this.s2;
    }

    public Strecke getS1(){
        return this.s1;
    }
    @Override
    public String toString(){
        String s=s1.toString()+"\n"+s2.toString()+"\n"+"Schnittpunkt: ("+x+","+y+")";
        //String s="Schnittpunkt: ("+x+","+y+")";
        return  s;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof  Schnittpunkt){
            Schnittpunkt s= (Schnittpunkt)o;
            return s.x== this.x && s.y==this.y;
        }
        return false;
    }
}
