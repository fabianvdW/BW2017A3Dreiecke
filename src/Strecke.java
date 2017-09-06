public class Strecke {
    Punkt p1;
    Punkt p2;
    double m;
    double b;
    double[] defbereich;
    public Strecke(Punkt p1, Punkt p2){
        this.p1=p1;
        this.p2=p2;
        this.defbereich= new double[2];
    }
    @Override
    public String toString(){
        String s="Strecke: "+"Punkt1: ("+p1.x+","+p1.y+")\n Punkt2: ("+p2.x+","+p2.y+")";
        return s;
    }
}
