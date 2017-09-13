import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Strecke> strecken = leseStreckenEin("dreiecke7.txt");
        for (int i = 0; i < strecken.size(); i++) {
            Strecke strecke = strecken.get(i);
            setFunctionParameters(strecke);
            //System.out.println(strecke.toString());//DEBUG

        }
        System.out.println("Anzahl Strecken: " + strecken.size());
        ArrayList<Schnittpunkt> schnittpunkte = berechneSchnittpunkte(strecken);
        System.out.println("Anzahl Schnittpunkte: " + schnittpunkte.size());
        for (Schnittpunkt s : schnittpunkte) {
            if ((s.y + "").equals("NaN")) {
                System.out.println(s.s1.toString());
                System.out.println(s.s2.toString());
            }
            /*if(s.x>100 && s.y>10) {
                System.out.println(s.toString());
            }*/

        }
        ArrayList<Dreieck> dreiecke = berechneDreiecke(schnittpunkte);
        System.out.println("Anzahl Dreiecke: " + dreiecke.size());
        for (Dreieck d : dreiecke) {
            System.out.println("(" + d.p1.x + "|" + d.p1.y + ")-(" + d.p2.x + "|" + d.p2.y + ")-(" + d.p3.x + "|" + d.p3.y + ")");
        }
        GUI.startBaum(strecken, schnittpunkte, dreiecke);

    }

    public static boolean isValidesDreieck(Schnittpunkt s1, Schnittpunkt s2, Schnittpunkt s3) {
        //Strecke die s1 mit s2 gemeinsam hat
        Strecke s1s2 = null;
        Strecke s1s2Complete = null;
        if (s1.getS1().equals(s2.getS1()) || s1.getS1().equals(s2.getS2())) {
            s1s2 = new Strecke(s1, s2);
            s1s2Complete = s1.getS1();
        } else if (s1.getS2().equals(s2.getS1()) || s1.getS2().equals(s2.getS2())) {
            s1s2 = new Strecke(s1, s2);
            s1s2Complete = s1.getS2();
        }
        if (s1s2 == null) return false;
        Strecke s1s3 = null;
        Strecke s1s3Complete = null;
        if (s1.getS1().equals(s3.getS1()) || s1.getS1().equals(s3.getS2())) {
            s1s3 = new Strecke(s1, s3);
            s1s3Complete = s1.getS1();
        } else if (s1.getS2().equals(s3.getS1()) || s1.getS2().equals(s3.getS2())) {
            s1s3 = new Strecke(s1, s3);
            s1s3Complete = s1.getS2();
        }
        if (s1s3 == null) return false;
        Strecke s2s3 = null;
        Strecke s2s3Complete = null;
        if (s2.getS1().equals(s3.getS1()) || s2.getS1().equals(s3.getS2())) {
            s2s3 = new Strecke(s2, s3);
            s2s3Complete = s2.getS1();
        } else if (s2.getS2().equals(s3.getS1()) || s2.getS2().equals(s3.getS2())) {
            s2s3 = new Strecke(s2, s3);
            s2s3Complete = s2.getS2();
        }
        if (s2s3 == null || s1s2Complete.equals(s1s3Complete) || s1s2Complete.equals(s2s3Complete) || s1s3Complete.equals(s2s3Complete))
            return false;
        double a = s1s2.getLength();
        double b = s1s3.getLength();
        double c = s2s3.getLength();
        System.out.println("A: " + a + " B: " + b + " C: " + c);
        double winkela = Math.acos((b * b + c * c - a * a) / (2 * b * c)) * (180 / Math.PI);
        double winkelb = Math.acos((a * a + c * c - b * b) / (2 * a * c)) * (180 / Math.PI);
        double winkelc = Math.acos((b * b + a * a - c * c) / (2 * b * a)) * (180 / Math.PI);
        System.out.println("Winkela: " + winkela + " Winkelb: " + winkelb + " WinkelC: " + winkelc);
        System.out.println("IWS: " + (winkela + winkelb + winkelc));
        //return winkela+winkelb+winkelc==180;
        return winkela + winkelb + winkelc >= 179.9 && winkela + winkelb + winkelc <= 180.1;
    }

    public static ArrayList<Dreieck> berechneDreiecke(ArrayList<Schnittpunkt> schnittpunkte) {
        ArrayList<Dreieck> dreiecke = new ArrayList<Dreieck>();
        A:
        for (int i = 0; i < schnittpunkte.size(); i++) {
            Schnittpunkt s1 = schnittpunkte.get(i);
            B:
            for (int j = i + 1; j < schnittpunkte.size(); j++) {
                if (j == i) continue B;
                Schnittpunkt s2 = schnittpunkte.get(j);
                C:
                for (int k = j + 1; k < schnittpunkte.size(); k++) {
                    if (k == i || k == j) continue C;
                    Schnittpunkt s3 = schnittpunkte.get(k);
                    if (!s1.equals(s2) && !s2.equals(s3) && !s1.equals(s3)) {
                        if (isValidesDreieck(s1, s2, s3)) {
                            dreiecke.add(new Dreieck(s1, s2, s3));
                        }
                    }

                }

            }


        }
        return dreiecke;
    }

    public static ArrayList<Schnittpunkt> berechneSchnittpunkte(ArrayList<Strecke> strecken) {
        ArrayList<Schnittpunkt> schnittpunkte = new ArrayList<Schnittpunkt>();
        for (int i = 0; i < strecken.size(); i++) {
            Strecke strecke = strecken.get(i);
            for (int j = i + 1; j < strecken.size(); j++) {
                Strecke strecke1 = strecken.get(j);
                Punkt p = strecke.schneidet(strecke1);
                if (p != null) {
                    Schnittpunkt schnittpunkt = new Schnittpunkt(p.x, p.y, strecke, strecke1);
                    schnittpunkte.add(schnittpunkt);

                }

            }
        }
        return schnittpunkte;
    }

    public static ArrayList<Strecke> leseStreckenEin(String path) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            ArrayList<Strecke> daten = new ArrayList<Strecke>();
            String dreiecke = in.readLine();
            //System.out.println("dreicke: "+dreiecke);
            dreiecke = dreiecke.substring(1);//Weil der erste Charakter immer so ein ' ist aus Gründen
            //System.out.println("dreicke: "+dreiecke);
            int anzDreiecke = Integer.parseInt(dreiecke);
            String line = "";
            for (int i = 0; i < anzDreiecke; i++) {
                ArrayList<Double> zahlen = new ArrayList<Double>();
                line = in.readLine();
                String zahl = "";
                char[] chars = line.toCharArray();
                for (char c : chars) {
                    if (c == ' ') {
                        zahlen.add(Double.parseDouble(zahl));
                        zahl = "";
                    } else {
                        zahl += c;
                    }
                }
                zahlen.add(Double.parseDouble(zahl));
                Punkt p1 = new Punkt(zahlen.get(0), zahlen.get(1));
                Punkt p2 = new Punkt(zahlen.get(2), zahlen.get(3));
                Strecke s = new Strecke(p1, p2);
                daten.add(s);
            }
            in.close();
            return daten;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no such file");
            System.exit(0);
        }
        return null;
    }

    public static void setFunctionParameters(Strecke s) {
        Punkt p1 = s.p1;
        Punkt p2 = s.p2;
        double steigung = (p1.y - p2.y) / (p1.x - p2.x);
        double ordinatenabschnitt = p1.y - (steigung * p1.x);
        if (p1.x - p2.x == 0) {
            steigung = Double.POSITIVE_INFINITY;
        }
        s.m = steigung;
        s.b = ordinatenabschnitt;
        double[] defb = new double[2];
        if (p1.x < p2.x) {
            defb[0] = p1.x;
            defb[1] = p2.x;
        } else {
            defb[0] = p2.x;
            defb[1] = p1.x;
        }
        s.defbereich = defb;
    }

}
