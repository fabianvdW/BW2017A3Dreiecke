
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class  GUI extends JPanel {
    private static int width;
    private static int height;
    private ArrayList<Strecke> strecken;
    private ArrayList<Schnittpunkt> schnittpunkte;
    public GUI(ArrayList<Strecke> strecken,ArrayList<Schnittpunkt> schnittpunkte){
        this.strecken=strecken;
        this.schnittpunkte=schnittpunkte;
    }
    @Override
    public void paintComponent(Graphics g) {
        for(Strecke s: strecken){
            g.drawLine((int)s.p1.x+400,(int)s.p1.y+400,(int)s.p2.x+400,(int)s.p2.y+400);
        }
        g.setColor(Color.RED);
        for(Schnittpunkt s: schnittpunkte){
            g.drawOval((int) s.x + 398, (int) s.y + 398, 5, 5);

        }
        g.setColor(Color.BLACK);

    }

    public static void startBaum(ArrayList<Strecke> strecken,ArrayList<Schnittpunkt> schnittpunkte) {
        //Sort Baum
        JFrame jFrame = new JFrame();
        jFrame.add(new GUI(strecken,schnittpunkte));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)screenSize.getWidth();
        height =(int) screenSize.getHeight();
        jFrame.setSize((int)width, (int)height);
        jFrame.setVisible(true);
    }

}
