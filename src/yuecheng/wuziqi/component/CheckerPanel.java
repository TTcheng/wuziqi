package yuecheng.wuziqi.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import yuecheng.wuziqi.entity.Box;
import yuecheng.wuziqi.entity.CheckerGrid;

/**
 * @author yuecheng 201717020079 2020/3/21 下午12:02
 */
@SuppressWarnings("all")
public class CheckerPanel extends JPanel implements MouseListener {

    /**
     * 棋盘颜色
     */
    public static final Color BOARD_COLOR = Color.ORANGE;
    /**
     * 黑子颜色
     */
    public static final Color CHECKER_PIECE_COLOR_BLACK = Color.BLACK;
    /**
     * 白子颜色
     */
    public static final Color CHECKER_PIECE_COLOR_WHITE = Color.WHITE;

    private CheckerGrid checkerGrid;

    private Color curColor = Color.BLACK;

    private int boardLength;
    private int rowLines;
    private int colLines;
    private int perWidth;
    private int perHeight;

    public CheckerPanel(CheckerGrid checkerGrid) {
        this.checkerGrid = checkerGrid;
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        init(g);
        redraw(checkerGrid);
    }

    public void init(Graphics graphics) {
        setBackground(BOARD_COLOR);
        // 清空
        int height = getHeight();
        int width = getWidth();
        boardLength = Math.min(height, width);
        //graphics.clearRect(0, 0, width, height);
        // 画表格
        graphics.setColor(Color.BLACK);
        colLines = checkerGrid.getColumns() + 2;
        rowLines = checkerGrid.getRows() + 1;
        perWidth = boardLength / checkerGrid.getColumns();
        perHeight = boardLength / checkerGrid.getRows();
        // 画竖线
        for (int i = 0; i < colLines; i++) {
            graphics.drawLine(perHeight * i, 0, perHeight * i, perWidth * colLines);
        }
        // 画横线
        for (int i = 0; i < rowLines; i++) {
            graphics.drawLine(0, perWidth * i, perHeight * rowLines, perWidth * i);
        }
    }

    public void refresh(CheckerGrid checkerGrid) {
        new Thread(() -> redraw(checkerGrid)).start();
    }

    private void redraw(CheckerGrid checkerGrid) {
        Box[][] grid = checkerGrid.getGrid();
        for (int i = 0; i < checkerGrid.getRows(); i++) {
            for (int j = 0; j < checkerGrid.getColumns(); j++) {
                if (grid[i][j] == Box.BLACK) {
                    drawBox(CHECKER_PIECE_COLOR_BLACK, perWidth * i, perHeight * j);
                }
                if (grid[i][j] == Box.WHITE) {
                    drawBox(CHECKER_PIECE_COLOR_WHITE, perWidth * i, perHeight * j);
                }
            }
        }
    }

    private void drawBox(Color color, int x, int y) {
        Graphics graphics = getGraphics();
        graphics.setColor(color);
        graphics.fillOval(x, y, perWidth, perHeight);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int fixedX = x / perWidth;
        int fixedY = y / perHeight;
        drawBox(curColor, fixedX * perWidth, fixedY * perHeight);
        curColor = curColor == CHECKER_PIECE_COLOR_BLACK ? CHECKER_PIECE_COLOR_WHITE : CHECKER_PIECE_COLOR_BLACK;
        Boolean win = checkerGrid.dropBox(curColor == CHECKER_PIECE_COLOR_BLACK ? Box.WHITE : Box.BLACK, fixedX, fixedY);
        if (win) {
            JOptionPane.showMessageDialog(getParent(), curColor == CHECKER_PIECE_COLOR_BLACK ? "白方胜！" : "黑方胜！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
