import java.util.ArrayList;

public class Dreieck {
    Schnittpunkt p1;
    Schnittpunkt p2;
    Schnittpunkt p3;

    public Dreieck(Schnittpunkt p1, Schnittpunkt p2, Schnittpunkt p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    @Override
    public String toString(){
        String s="";
        ArrayList<Schnittpunkt> links= new ArrayList<Schnittpunkt>();
        links.add(p1);
        links.add(p2);
        links.add(p3);
        Schnittpunkt[] xwerte= new Schnittpunkt[3];
        for(int i=0;i<3;i++){
            Schnittpunkt linkesterPunkt = links.get(0);
            for(int k=1;k<links.size();k++){
                Schnittpunkt test=links.get(k);
                if(test.x<linkesterPunkt.x){
                    linkesterPunkt=test;
                }else if(test.x==linkesterPunkt.x){
                    if(test.y<linkesterPunkt.y){
                        linkesterPunkt=test;
                    }
                }
            }
            links.remove(linkesterPunkt);
            xwerte[i]=linkesterPunkt;
        }
        s+=(Math.round(xwerte[0].x* 100) / 100.0)+" " + (Math.round(xwerte[0].y* 100) / 100.0)+" ";
        s+=(Math.round(xwerte[2].x* 100) / 100.0)+" " + (Math.round(xwerte[2].y* 100) / 100.0)+" ";
        s+=(Math.round(xwerte[1].x* 100) / 100.0)+" " + (Math.round(xwerte[1].y* 100) / 100.0)+" ";
        return s;
    }
}
