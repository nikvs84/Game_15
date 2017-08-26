
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
//import java.lang.Math;

/**
 * Created by IntelliJ IDEA.
 * User: �������������
 * Date: 06.08.2006
 * Time: 16:52:30
 * To change this template use File | Settings | File Templates.
 */
public class GridLayoutDemo extends Applet implements ActionListener {
    static final int n=4;
    SampleFrame f;
    long counter;
    int slow=0, slown=1;
    int speed=2;
    public int PosEmptyX, PosEmptyY;
    Button[] Item = new Button[20];
    int[] ButtonGrid = new int[20];
    double bound=0.066666;
    public void init() {
        RandIndex();
        int k=1;
        setLayout(new GridLayout(n, n));
        setFont(new Font("SainsSerif", Font.BOLD, 24));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                k = i * n + j;
                if (k > 0) {
                    Item[k] = (Button) add(new Button("" + ButtonGrid[k]));
                    Item[k].addActionListener(this);

                }
            }
        }
/*
        PosEmptyX=Item[15].getX()+Item[15].getWidth();
        System.out.println("PosEmptyX= " + PosEmptyX);
        PosEmptyY=Item[15].getY();
*/

    }

    public void start() {
        repaint();
        PosEmptyX=Item[15].getX()+Item[15].getWidth();
//        System.out.println("PosEmptyX= " + PosEmptyX);
        PosEmptyY=Item[15].getY();
/*        String param;


        param=getParameter("speed");
        try {
            if (param!=null) {
                speed=Integer.parseInt(param);
            }
        } catch (NumberFormatException e) {
            speed=2;  //To change body of catch statement use File | Settings | File Templates.
        }

        param=getParameter("slow");
        try {
            if (param!=null) {
                slown=Integer.parseInt(param);
            }
        } catch (NumberFormatException e) {
            slown=1;  //To change body of catch statement use File | Settings | File Templates.
        }
*/


    }

    public void actionPerformed(ActionEvent e) {
        ++counter;
        int c = 1;
        for (int i = 1; i < 16; i++) {
            if (Item[i] == e.getSource()) {
                c = i;
            }
        }
//        System.out.println("c= " + c);
        ButtonMover(c, PosEmptyX, PosEmptyY);
        if (isComplete()) {
//            System.out.println("Complete!!!");
            f = new SampleFrame("����������!");
            f.msg = "    ����������� ������� �� " + counter + " �����   ";
            f.setSize(500,110);
            f.setVisible(true);
            counter=0;
        }
    }

    public void stop() {
        try {
            f.setVisible(false);
        } catch (Exception e) {
           //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public int getRand() {
        double f = Math.random();
        int i=1;
        if (f < bound) {
            i = 1;
        } else {
            if (f < bound + bound) {
                i = 2;
            } else {
                if (f < 3 * bound) {
                    i = 3;
                } else {
                    if (f < 4 * bound) {
                        i = 4;
                    } else {
                        if (f < 5 * bound) {
                            i = 5;
                        } else {
                            if (f < 6 * bound) {
                                i = 6;
                            } else {
                                if (f < 7 * bound) {
                                    i = 7;
                                } else {
                                    if (f < 8 * bound) {
                                        i = 8;
                                    } else {
                                        if (f < 9 * bound) {
                                            i = 9;
                                        } else {
                                            if (f < 10 * bound) {
                                                i = 10;
                                            } else {
                                                if (f < 11 * bound) {
                                                    i = 11;
                                                } else {
                                                    if (f < 12 * bound) {
                                                        i = 12;
                                                    } else {
                                                        if (f < 13 * bound) {
                                                            i=13;
                                                        } else {
                                                            if (f < 14 * bound) {
                                                                i = 14;
                                                            } else {
                                                                if (f < 15 * bound) {
                                                                    i = 15;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    public void RandIndex() {
        int c=1;
        boolean flag=true;
        ButtonGrid[0] = 0;
        ButtonGrid[1] = getRand();
/*
        System.out.println("ButtonGrid[" + 1 + "]= " + ButtonGrid[1]);
        System.out.println("---------------------------------------");
*/
        for (int j = 2; j < 16; j++) {
            do {
                flag = false;
                c = getRand();
//                System.out.println("c= " + c);
                for (int k = 1; k < j; k++) {
                    if (c == ButtonGrid[k]) {
                        flag = true;
                    }
                }
//                System.out.println("flag= " + flag);
            } while(flag);

                ButtonGrid[j] = c;

/*
            System.out.println("ButtonGrid[" + j + "]= " + ButtonGrid[j]);
            System.out.println("---------------------------------------");
*/
        }
/*
        for (c = 0; c < 16; c++) {
            ButtonGrid[c] = c;
        }
*/
    }

    public void ButtonMover(int MovedButtonIndex, int PosEmpX, int PosEmpY) {
        int wb = Item[MovedButtonIndex].getWidth(), hb = Item[MovedButtonIndex].getHeight(), x = Item[MovedButtonIndex].getX(), y = Item[MovedButtonIndex].getY();
        /*System.out.println("x= " + x);
        System.out.println("PosEmptyX= " + PosEmptyX);
//        add(new Button("16"));*/

        if ((x == PosEmpX - wb) & (y == PosEmpY)) {
            ItemMover(MovedButtonIndex, PosEmpX, PosEmpY);
            for (int i = 1; i <= wb; i += speed) {
                /*for (int j = 1; j <= hb; j++)*/ {
                    try {
                        Thread.sleep(slow, slown);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    Item[MovedButtonIndex].setBounds(x + i, y, wb, hb);

                }
            }
            Item[MovedButtonIndex].setBounds(PosEmpX, PosEmpY, wb, hb);
            PosEmptyX = x;
            PosEmptyY = y;
//        remove(Item[1]);//To change body of implemented methods use File | Settings | File Templates.
        } else {
            if ((x == PosEmpX + wb) & (y == PosEmpY)) {
                ItemMover(MovedButtonIndex, PosEmpX, PosEmpY);
                for (int i = 1; i <= wb; i+=speed) {
                    try {
                        Thread.sleep(slow, slown);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    Item[MovedButtonIndex].setBounds(x - i, y, wb, hb);

                }
                Item[MovedButtonIndex].setBounds(PosEmpX, PosEmpY, wb, hb);
                PosEmptyX = x;
                PosEmptyY = y;
            } else {
                if ((y == PosEmpY - hb) & (x == PosEmpX)) {
                    ItemMover(MovedButtonIndex, PosEmpX, PosEmpY);
                    for (int i = 1; i <= hb; i+=speed) {
                        /*for (int j = 1; j <= hb; j++)*/ {
                            try {
                                Thread.sleep(slow, slown);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            Item[MovedButtonIndex].setBounds(x, y + i, wb, hb);

                        }
                    }
                    Item[MovedButtonIndex].setBounds(PosEmpX, PosEmpY, wb, hb);
                    PosEmptyX = x;
                    PosEmptyY = y;
//        remove(Item[1]);//To change body of implemented methods use File | Settings | File Templates.
                } else {
                    if ((y == PosEmpY + hb) & (x == PosEmpX)) {
                        ItemMover(MovedButtonIndex, PosEmpX, PosEmpY);
                        for (int i = 1; i <= hb; i+=speed) {
                            /*for (int j = 1; j <= hb; j++)*/ {
                                try {
                                    Thread.sleep(slow, slown);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                }
                                Item[MovedButtonIndex].setBounds(x, y - i, wb, hb);

                            }
                        }
                        Item[MovedButtonIndex].setBounds(PosEmpX, PosEmpY, wb, hb);
                        PosEmptyX = x;
                        PosEmptyY = y;
//        remove(Item[1]);//To change body of implemented methods use File | Settings | File Templates.
                    }
                }
            }
        }
/*
        x=Item[MovedButtonIndex].getX();
        System.out.println("x= " + x);
*/

/*
        System.out.println("PosEmptyX= " + PosEmptyX);
        System.out.println("PosEmptyY= " + PosEmptyY);
*/
    }

    public void ItemMover(int index, int Px, int Py) {
        int Temp = -1;
        int wb = Item[index].getWidth(), hb = Item[index].getHeight(), x = Item[index].getX(), y = Item[index].getY();
        int NewM = (int) (Math.floor((x + wb) / wb) + 4 * Math.floor(y / hb));
//        System.out.println("Math.floor((x) / wb) = " + Math.floor((x + wb) / wb));
        int NewB = (int) (Math.floor((Px + wb) / wb) + 4 * Math.floor(Py / hb));
//        System.out.println("Math.floor((Px) / wb) = " + Math.floor((Px + wb) / wb));
//        System.out.println("Button [" + NewM + "] = " + ButtonGrid[NewM]);
//        System.out.println("Emp - [" + NewB + "] = " + ButtonGrid[NewB]);
        Temp = ButtonGrid[NewM];
//        System.out.println("Temp= " + Temp);
        ButtonGrid[NewM] = 17;
        ButtonGrid[NewB] = Temp;
/*
        System.out.println("-------------------");
        System.out.println("Button [" + NewM + "] = " + ButtonGrid[NewM]);
        System.out.println("Emp - [" + NewB + "] = " + ButtonGrid[NewB]);
        System.out.println("==========================================");
*/

    }

    public boolean isComplete() {
/*
        for (int i = 0; i < 17; i++) {
            System.out.println("Buttongrid[" + i + "]= " + ButtonGrid[i]);
        }
*/
        boolean flag = true;
        int k = 0;
        do {
            if (ButtonGrid[k] > ButtonGrid[++k]) {
                flag = false;
//                System.out.println("flag= " + flag);
//                System.out.println("k= " + k);
                break;
            }
        } while (k < 16);

        return flag;

    }

}
