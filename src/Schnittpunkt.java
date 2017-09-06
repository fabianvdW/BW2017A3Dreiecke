public class Schnittpunkt  {
    double x;
    double y;
    Strecke s1;
    Strecke s2;
    public Schnittpunkt(double x, double y, Strecke s1, Strecke s2){
        this.x=x;
        this.y=y;
        this.s1=s1;
        this.s2=s2;
    }
    @Override
    public String toString(){
        String s=s1.toString()+"\n"+s2.toString()+"\n"+"Schnittpunkt: ("+x+","+y+")";
        //String s="Schnittpunkt: ("+x+","+y+")";
        return  s;
    }
}
