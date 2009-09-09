package comfort.client.ui.components;

import interfaces.IGoods;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 19.12.2007
 * Time: 0:12:34
 */
public class GoodsGrid extends JPanel {
    private static String[] columnNames;
    private static double[] columnSize;

    private JTable grid;
    private List<IGoods> goods;


    {
    }

    public GoodsGrid() {
        super();
        initUI();
    }

    public void initUI(){
        this.setLayout(new BorderLayout());
        grid = new JTable(new String[][]{{"hjewwe"}, {"wehwejhj"}}, new String[]{"name"});
        grid.setRowHeight(0, 50);
        add(grid.getTableHeader(), BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
    }

    
}
