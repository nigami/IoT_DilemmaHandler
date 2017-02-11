/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.badMoodDay;

/**
 *
 * @author ilanigam17
 */
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.FullScreenHandler;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

/**
 * Demonstrates how to switch between fullscreen mode and window mode.
 */
public class FullScreenVideo {

    public static void showVideo(String url) {
        Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(browserView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.setFullScreenHandler(new MyFullScreenHandler(frame, browserView));

        browser.loadURL(url);

    }

    private static class MyFullScreenHandler implements FullScreenHandler {

        private final JFrame parentFrame;
        private final BrowserView browserView;
        private final JFrame frame;

        private MyFullScreenHandler(JFrame parentFrame, BrowserView browserView) {
            this.parentFrame = parentFrame;
            this.browserView = browserView;
            this.frame = createFrame();
        }

        private static JFrame createFrame() {
            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            return frame;
        }

        @Override
        public void onFullScreenEnter() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    parentFrame.getContentPane().remove(browserView);
                    frame.getContentPane().add(browserView);
                    frame.setVisible(true);
                    parentFrame.setVisible(false);
                }
            });
        }

        @Override
        public void onFullScreenExit() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.getContentPane().remove(browserView);
                    parentFrame.getContentPane().add(browserView);
                    parentFrame.setVisible(true);
                    frame.setVisible(false);
                }
            });
        }
    }
}
