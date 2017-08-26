
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 05.08.2006
 * Time: 10:49:45
 * To change this template use File | Settings | File Templates.
 */
public class MyWindowAdapter extends WindowAdapter {
    SampleFrame sampleFrame;
    public MyWindowAdapter(SampleFrame sampleFrame) {
        this.sampleFrame=sampleFrame;
    }

    public void windowClosing(WindowEvent we) {
        sampleFrame.setVisible(false);
    }
}
