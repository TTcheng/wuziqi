package yuecheng.wuziqi;

import javax.swing.*;

import yuecheng.wuziqi.component.CheckerPanel;
import yuecheng.wuziqi.entity.CheckerGrid;

@SuppressWarnings("all")
public class Main {

    public static final Integer BOARD_WIDTH = 600;

    public static void main(String[] args) {
        JFrame checkerWindow = new JFrame();
        checkerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CheckerGrid checkerGrid = new CheckerGrid(20, 20);
        CheckerPanel checkerPanel = new CheckerPanel(checkerGrid);
        checkerWindow.add(checkerPanel);
        checkerPanel.setSize(BOARD_WIDTH, BOARD_WIDTH);
        checkerWindow.setSize(BOARD_WIDTH + 200, BOARD_WIDTH);
        checkerWindow.setVisible(true);
    }
}
