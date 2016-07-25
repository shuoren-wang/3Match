package display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shuorenwang on 2016-07-20.
 */
public class Display extends JFrame{
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        super(title);
        this.width=width;
        this.height=height;

        createDisplay();
    }

    private void createDisplay(){

        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        canvas=new Canvas();
        canvas.setSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        add(canvas);
        pack();
    }


    public Canvas getCanvas() {
        return canvas;
    }
}
