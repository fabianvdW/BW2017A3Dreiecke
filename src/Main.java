import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Strecke> strecken= leseStreckenEin("dreiecke1.txt");
        for (int i = 0; i < strecken.size(); i++) {
            Strecke strecke =  strecken.get(i);
            setFunctionParameters(strecke);
            //System.out.println(strecke.toString());//DEBUG

        }
        ArrayList<Schnittpunkt> schnittpunkte= berechneSchnittpunkte(strecken);
        for(Schnittpunkt s: schnittpunkte){
            if((s.y+"").equals("NaN")){
                System.out.println(s.s1.toString());
                System.out.println(s.s2.toString());
            }
            /*if(s.x>100 && s.y>10) {
                System.out.println(s.toString());
            }*/

        }
        System.out.println(schnittpunkte.size());
        GUI.startBaum(strecken,schnittpunkte);

    }
    public static ArrayList<Schnittpunkt> berechneSchnittpunkte(ArrayList<Strecke> strecken){
        ArrayList<Schnittpunkt> schnittpunkte= new ArrayList<Schnittpunkt>();
        for (int i = 0; i < strecken.size(); i++) {
            Strecke strecke =  strecken.get(i);
            for (int j = i+1; j < strecken.size(); j++) {
                Strecke strecke1 =  strecken.get(j);
                Punkt p= strecke.schneidet(strecke1);
                if(p!=null){
                    schnittpunkte.add(new Schnittpunkt(p.x,p.y,strecke,strecke1));
                }

            }
        }
        return schnittpunkte;
    }
    public static ArrayList<Strecke> leseStreckenEin(String path){
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            ArrayList<Strecke> daten= new ArrayList<Strecke>();
            String dreiecke = in.readLine();
            dreiecke=dreiecke.substring(1);//Weil der erste Charakter immer so ein ' ist aus Gründen
            int anzDreiecke=Integer.parseInt(dreiecke);
            String line="";
            for (int i = 0; i <anzDreiecke ; i++) {
                ArrayList<Double> zahlen= new ArrayList<Double>();
                line=in.readLine();
                String zahl="";
                char[] chars =line.toCharArray();
                for(char c: chars) {
                    if(c==' ') {
                        zahlen.add(Double.parseDouble(zahl));
                        zahl="";
                    }else {
                        zahl+=c;
                    }
                }
                zahlen.add(Double.parseDouble(zahl));
                Punkt p1= new Punkt(zahlen.get(0),zahlen.get(1));
                Punkt p2= new Punkt(zahlen.get(2),zahlen.get(3));
                Strecke s= new Strecke(p1,p2);
                daten.add(s);
            }
            in.close();
            return daten;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("no such file");
            System.exit(0);
        }
        return null;
    }
    public static  void setFunctionParameters(Strecke s){
        Punkt p1=s.p1;
        Punkt p2=s.p2;
        double  steigung = (p1.y-p2.y)/(p1.x-p2.x);
        double ordinatenabschnitt = p1.y-(steigung*p1.x);
        if(p1.x-p2.x==0){
            steigung=Double.POSITIVE_INFINITY;
        }
        s.m=steigung;
        s.b=ordinatenabschnitt;
        double[] defb= new double[2];
        if(p1.x<p2.x) {
            defb[0] = p1.x;
            defb[1] = p2.x;
        }else{
            defb[0]=p2.x;
            defb[1]=p1.x;
        }
        s.defbereich=defb;
    }

}
