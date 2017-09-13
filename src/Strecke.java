public class Strecke {
    Punkt p1;
    Punkt p2;
    double m;
    double b;
    double[] defbereich;
    double[] wertebereich;

    public Strecke(Punkt p1, Punkt p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.defbereich = new double[2];
        this.wertebereich = new double[2];
        if (p1.y > p2.y) {
            wertebereich[0] = p2.y;
            wertebereich[1] = p1.y;
        } else {
            wertebereich[1] = p2.y;
            wertebereich[0] = p1.y;
        }
    }

    public Strecke(Schnittpunkt p1, Schnittpunkt p2) {
        this.p1 = new Punkt(p1.x, p1.y);
        this.p2 = new Punkt(p2.x, p2.y);
        this.defbereich = new double[2];
        this.wertebereich = new double[2];
        if (p1.y > p2.y) {
            wertebereich[0] = p2.y;
            wertebereich[1] = p1.y;
        } else {
            wertebereich[1] = p2.y;
            wertebereich[0] = p1.y;
        }
    }

    @Override
    public String toString() {
        String s = "Strecke:\n " + "Punkt1: (" + p1.x + "," + p1.y + ")\n Punkt2: (" + p2.x + "," + p2.y + ")";
        s += "\n f(x)=" + m + "*x+" + b;
        s += "\n Definitionsbereich: " + defbereich[0] + "," + defbereich[1];
        return s;
    }

    public Punkt schneidet(Strecke s2) {
        System.out.println("__________________________________________________\n\n");
        if (this.m == s2.m) {
            //System.out.println("HILFE");
            System.out.println("Steigung gleich");
            return null;
        }
        System.out.println(this.toString());
        System.out.println(s2.toString());
        if (this.m == Double.POSITIVE_INFINITY || s2.m == Double.POSITIVE_INFINITY) {
            if (this.m == Double.POSITIVE_INFINITY) {

                double yschnitt = s2.m * this.p1.x + s2.b;
                double xschnitt = (yschnitt - s2.b) / (s2.m);
                ;
                if (s2.m == 0) {
                    xschnitt = this.defbereich[0];
                }
                System.out.println("Xschnitt: " + xschnitt);
                System.out.println("yschnitt: " + yschnitt);
                if (s2.imDefBereich(xschnitt) && this.imWerteBereich(yschnitt)) {
                    System.out.println("Im Def-Bereich");
                    return new Punkt(xschnitt, yschnitt);
                }
            } else {
                double yschnitt = this.m * s2.p1.x + this.b;
                double xschnitt = (yschnitt - this.b) / (this.m);
                if (this.m == 0) {
                    xschnitt = s2.defbereich[0];
                }
                System.out.println("xschnitt: " + xschnitt);
                System.out.println("yschnitt: " + yschnitt);
                if (imDefBereich(xschnitt) && s2.imWerteBereich(yschnitt)) {
                    System.out.println("Im Def-Bereich");
                    return new Punkt(xschnitt, yschnitt);
                }
            }
        } else {
            double xschnitt = (this.b - s2.b) / (s2.m - this.m);
            System.out.println("Xschnitt: " + xschnitt);
            double yschnitt = this.m * xschnitt + this.b;
            System.out.println("Yscnintt: " + yschnitt);
            if (imDefBereich(xschnitt) && s2.imWerteBereich(yschnitt)) {
                System.out.println("Im Def-Bereich.");
                return new Punkt(xschnitt, yschnitt);
            }

        }
        return null;
    }

    public boolean imDefBereich(double test) {
        return test >= defbereich[0] && test <= defbereich[1];
    }

    public boolean imWerteBereich(double test) {
        return test >= wertebereich[0] && test <= wertebereich[1];
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Strecke) {
            Strecke s = (Strecke) o;
            return this.p1.x == s.p1.x && this.p1.y == s.p1.y && this.p2.x == s.p2.x && this.p2.y == s.p2.y;
        } else {
            return false;
        }
    }

    public double getLength() {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
