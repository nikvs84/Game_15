
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 05.08.2006
 * Time: 10:48:14
 * To change this template use File | Settings | File Templates.
 */
public class SampleFrame extends Frame implements Runnable {
    public String msg="";
    Thread t=null;
    int state;
    boolean  stopFlag;

    SampleFrame(String title) {
        super(title);

        setBackground(Color.black);
        setForeground(Color.cyan);

        setFont(new Font("SainsSerif", Font.BOLD, 24));

        t = new Thread(this);
        stopFlag = false;
        t.start();
        

        MyWindowAdapter adapter=new MyWindowAdapter(this);
        addWindowListener(adapter);
    }

    public void init() {
        setBackground(Color.black);
        setForeground(Color.cyan);
    }


    public void paint(Graphics g) {
        g.drawString(msg,30,65);
    }

    public void start() {
        t = new Thread(this);
        stopFlag = false;
        t.start();
    }

    public void run() {
        char ch;

        for (; ;) {
            try {
                repaint();
                Thread.sleep(250);
                ch = msg.charAt(0);
                msg = msg.substring(1, msg.length());
                msg += ch;
                if (stopFlag) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public void stop() {
        stopFlag = true;
        t = null;
    }
}
