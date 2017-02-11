/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JProgressBar;
import javax.swing.Painter;

public class MyPainter implements Painter<JProgressBar> {

    private final Color color;

    public MyPainter(Color c1) {
        this.color = c1;
    }

    @Override
    public void paint(Graphics2D gd, JProgressBar t, int width, int height) {
        gd.setColor(color);
        gd.fillRect(0, 0, width, height);
    }
}
