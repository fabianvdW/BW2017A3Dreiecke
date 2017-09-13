
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {
    private static int width;
    private static int height;
    private ArrayList<Strecke> strecken;
    private ArrayList<Schnittpunkt> schnittpunkte;
    private ArrayList<Dreieck> dreiecke;

    public GUI(ArrayList<Strecke> strecken, ArrayList<Schnittpunkt> schnittpunkte, ArrayList<Dreieck> dreiecke) {
        this.strecken = strecken;
        this.schnittpunkte = schnittpunkte;
        this.dreiecke = dreiecke;
    }

    @Override
    public void paintComponent(Graphics g) {
        //int k=1;
        for (Strecke s : strecken) {
            //System.out.println(k++);
            g.drawLine((int) s.p1.x + 400, (int) s.p1.y + 400, (int) s.p2.x + 400, (int) s.p2.y + 400);
        }
        g.setColor(Color.RED);
        HashMap<Double, Double> coords = new HashMap<Double, Double>();
        for (Schnittpunkt s : schnittpunkte) {
            for (Double key : coords.keySet()) {
                if (s.x == key && coords.get(key) == s.y) {
                    System.out.println("WOWOWOW");
                    g.setColor(Color.GREEN);
                }
            }
            coords.put(s.x, s.y);
            g.drawOval((int) s.x + 398, (int) s.y + 398, 5, 5);
            g.setColor(Color.RED);

        }
        g.setColor(Color.BLACK);
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(new Color(0, 0, 255));
        colors.add(new Color(0, 255, 0));
        colors.add(new Color(255, 0, 0));
        colors.add(new Color(0, 0, 127));
        colors.add(new Color(0, 127, 0));
        colors.add(new Color(127, 0, 0));
        colors.add(new Color(0, 255, 255));
        colors.add(new Color(255, 0, 255));
        colors.add(new Color(255, 255, 0));
        colors.add(new Color(0, 127, 255));
        colors.add(new Color(127, 0, 255));
        colors.add(new Color(0, 255, 127));
        colors.add(new Color(127, 255, 0));
        colors.add(new Color(255, 0, 127));
        colors.add(new Color(255, 127, 0));
        int index = 0;
        for (Dreieck d : dreiecke) {
            if (index == 15) index = 0;
            g.setColor(colors.get(index));
            g.drawLine((int) (d.p1.x + 800), (int) (d.p1.y + 400), (int) (d.p2.x + 800), (int) (d.p2.y + 400));
            g.drawLine((int) (d.p1.x + 800), (int) (d.p1.y + 400), (int) (d.p3.x + 800), (int) (d.p3.y + 400));
            g.drawLine((int) (d.p3.x + 800), (int) (d.p3.y + 400), (int) (d.p2.x + 800), (int) (d.p2.y + 400));
            index++;
        }

    }

    public static void startBaum(ArrayList<Strecke> strecken, ArrayList<Schnittpunkt> schnittpunkte, ArrayList<Dreieck> dreiecke) {
        //Sort Baum
        JFrame jFrame = new JFrame();
        jFrame.add(new GUI(strecken, schnittpunkte, dreiecke));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        jFrame.setSize((int) width, (int) height);
        jFrame.setVisible(true);
    }

}
