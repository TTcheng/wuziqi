package yuecheng.wuziqi.entity;


/**
 * @author yuecheng 201717020079 2020/3/21 上午11:40
 */
public class CheckerGrid {
    private Integer columns;
    private Integer rows;

    private Box[][] grid;

    public CheckerGrid(Integer columns, Integer rows) {
        this.columns = columns;
        this.rows = rows;
        init();
    }

    private void init(){
        grid = new Box[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Box.EMPTY;
            }
        }
    }

    public static final int WIN_NUM = 5;

    public Boolean dropBox(Box box, int x, int y) {
        grid[x][y] = box;
        // 横向查找
        int lianzi = 1;
        for (int i = x + 1; i < columns; i++) {
            if (grid[i][y] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (grid[i][y] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        if (lianzi >= WIN_NUM) {
            return true;
        }
        // 纵向查找
        lianzi = 1;
        for (int i = y + 1; i < rows; i++) {
            if (grid[x][i] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        for (int i = y - 1; i >= 0; i--) {
            if (grid[x][i] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        if (lianzi >= WIN_NUM) {
            return true;
        }
        // 正斜
        lianzi = 1;
        for (int i = x + 1, j = y + 1; i < columns && j < rows; i++, j++) {
            if (grid[i][j] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        if (lianzi >= WIN_NUM) {
            return true;
        }
        // 反斜
        lianzi = 1;
        for (int i = x + 1, j = y - 1; i < columns && j >= 0; i++, j--) {
            if (grid[i][j] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < rows; i--, j++) {
            if (grid[i][j] == box) {
                lianzi++;
            } else {
                break;
            }
        }
        if (lianzi >= WIN_NUM) {
            return true;
        }
        return false;
    }

    public Box[][] getGrid() {
        return grid;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void reset() {
        init();
    }
}
