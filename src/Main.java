import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        ArrayList<Strecke> strecken= leseStreckenEin("dreiecke1.txt");
        for (int i = 0; i < strecken.size(); i++) {
            Strecke strecke =  strecken.get(i);
            System.out.println(strecke.toString());

        }
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
                Punkt p1= new Punkt(zahlen.get(0),zahlen.get(1));
                Punkt p2= new Punkt(zahlen.get(2),zahlen.get(3));
                Strecke s= new Strecke(p1,p2);
                daten.add(s);
            }
            in.close();
            return daten;
        }catch(Exception e){
            System.out.println("no such file");
            System.exit(0);
        }
        return null;
    }

}
