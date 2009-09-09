package comfort.client.ui.components;

import comfort.client.ui.interfaces.IView;
import interfaces.IEntity;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Michael Morozov
 * Date: 19.12.2007
 * Time: 0:12:34
 */
public class EntityGrid extends JPanel {
    private JTable table;
    private JTabbedPane tabs;
    private IView view;
    private List<IEntity> entities;
    private int maxRowCount;
    private ChangeListener tabChangeListener;
    private boolean showHeaders;
    private int tabsCount;
    private TableCellRenderer cellRenderer;
    private int lastTabCount;
    private List<SelectEventListener> listeners;

    public void removeSelectEventListener(SelectEventListener listener) {
        listeners.remove(listener);
    }

    public void addSelectEventListener(SelectEventListener listener) {
        listeners.add(listener);
    }

    private void fireSelectChange() {
        for (SelectEventListener listener : listeners) {
            listener.selectChange();
        }
    }

    private class EntityCellRenderer extends DefaultTableCellRenderer {
        private Color evenBackColor;
        private Color evenForeColor;
        private Color oddBackColor;
        private Color oddForeColor;
        private boolean isUseParityColor;

        public EntityCellRenderer() {
            evenBackColor = UIManager.getColor("Table.evenRowBackground");
            evenForeColor = UIManager.getColor("Table.evenRowForeground");
            oddBackColor = UIManager.getColor("Table.oddRowBackground");
            oddForeColor = UIManager.getColor("Table.oddRowForeground");
            isUseParityColor =
                    evenBackColor != null && evenForeColor != null && oddBackColor != null && oddForeColor != null;

        }

        // implements javax.swing.table.TableCellRenderer
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
            if (!isSelected && !hasFocus && isUseParityColor) {
                Color fg = null;
                Color bg = null;
                if (row % 2 == 0) {
                    bg = evenBackColor;
                    fg = evenForeColor;
                } else {
                    bg = oddBackColor;
                    fg = oddForeColor;
                }

                component.setBackground(bg);
                component.setForeground(fg);
                ((JLabel) component).setText(value.toString());
            }

            return component;
        }

    }

    /**
     * РњРѕРґРµР»СЊ РґР»СЏ С‚Р°Р±Р»РёС†С‹
     */
    private class TableGridModel extends AbstractTableModel {


        public String getColumnName(int column) {
            return view != null ? view.get(column).getName() : null;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        /**
         * Р’С‹С‡РёСЃР»СЏРµРј СЂРµР°Р»СЊРЅС‹Р№ РёРЅРґРµРєСЃ РґР»СЏ РґР°РЅРЅС‹С…
         */
        private int getRealDataIndex(int rowIndex) {
            int tabIndex = tabs.getSelectedIndex() > 0 ? tabs.getSelectedIndex() : 0;
            return tabIndex * maxRowCount + rowIndex;
        }

        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РєРѕР»-РІРѕ СЃС‚СЂРѕРє РІ С‚Р°Р±Р»РёС†Рµ
         *
         * @return
         */
        public int getRowCount() {
            int size = view != null ? entities.size() : 0;
            int remain = size - maxRowCount * (tabs.getTabCount() - 1);
            return tabs.getSelectedIndex() == (tabs.getTabCount() - 1) ? remain : maxRowCount;
        }

        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РєРѕР»-РІРѕ РєРѕР»РѕРЅРѕРє РІ С‚Р°Р±Р»РёС†Рµ
         *
         * @return
         */
        public int getColumnCount() {
            return view != null ? view.getCount() : 0;
        }

        /**
         * Р’РѕР·РІСЂР°С‰Р°РµС‚ РѕС‚С„РѕСЂРјР°С‚РёСЂРѕРІР°РЅРЅСѓСЋ СЃС‚СЂРѕРєСѓ РґР»СЏ РѕС‚РѕР±СЂР°Р¶РµРЅРёСЏ РґР°РЅРЅС‹С… РІ СЏС‡РµР№РєРё С‚Р°Р±Р»РёС†С‹
         *
         * @param rowIndex
         * @param columnIndex
         * @return
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
            return entities != null && view != null ?
                    view.getString(entities.get(getRealDataIndex(rowIndex)), view.get(columnIndex)) : null;
        }
    }

    private boolean changeSelectionRow(boolean isDown) {
        if (table.getSelectedRow() == 0 || table.getSelectedRow() == table.getRowCount() - 1) {
            int tabIndex = tabs.getSelectedIndex();
            if (isDown)
                tabIndex++;
            else
                tabIndex--;

            if (tabIndex > -1 && tabIndex < tabs.getTabCount()) {
                // РџРµСЂРµРєР»СЋС‡Р°РµРј Р·Р°РєР»Р°РґРєСѓ
                tabs.setSelectedIndex(tabIndex);
                // РЎС‚СЂРѕРєР° РєРѕС‚РѕСЂР°СЏ Р±СѓРґРµС‚ РІС‹Р±СЂР°РЅР°
                int prefferedRow = !isDown ? table.getRowCount() - 1 : 0;
                // РЎРјРµРЅР° СЃС‚СЂРѕРєРё
                table.changeSelection(prefferedRow, 0, true, false);
                return true;

            }
        }
        return false;

    }

    private void createTable() {
        table = new JTable();

        table.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // РЎРєР°РЅ РєРѕРґ РєР»Р°РІРёС€Рё
                int code = e.getKeyCode();
                // РўРµРєСѓС‰РёР№ РёРЅРґРµРєСЃ
                int tabIndex = tabs.getSelectedIndex();
                boolean selectLast = false;
                switch (code) {
                    // РЎС‚СЂРµР»РєР° РІРЅРёР·
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_KP_DOWN:
                        if (changeSelectionRow(true)) e.setKeyCode(0);
                        break;
                        // РЎС‚СЂРµР»РєР° РІРІРµСЂС…
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_KP_UP:
                        if (changeSelectionRow(false)) e.setKeyCode(0);
                        break;
                        // Р”СЂСѓРіРёРµ РєР»Р°РІРёС€Рё РЅРµ РѕР±СЂР°Р±Р°С‚С‹РІР°РµРј
                    default:
                        return;

                }
            }

        });
        table.addMouseWheelListener(new MouseWheelListener() {

            public void mouseWheelMoved(MouseWheelEvent e) {
                int select = table.getSelectedRow();
                if (e.getWheelRotation() > 0)
                    select++;
                else
                    select--;

                if (select > -1 && select < table.getRowCount())
                    table.changeSelection(select, 0, true, false);
                else
                    changeSelectionRow(e.getWheelRotation() > 0);

            }

        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                fireSelectChange();
            }

        });

        table.setPreferredSize(new Dimension(0, 0));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellRenderer = new EntityCellRenderer();
        table.setDefaultRenderer(Object.class, cellRenderer);
        table.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                // Р—Р°С‰РёС‚Р° РѕС‚ СЃРјРµРЅС‹ Render
                if (evt.getPropertyName() == "UI")
                    table.setDefaultRenderer(Object.class, cellRenderer);
//                System.out.println("evt.getPropertyName() = " + evt.getPropertyName());
            }
        });
    }

    private void createTabs() {
        tabs = new JTabbedPane();
        tabs.setTabPlacement(JTabbedPane.BOTTOM);
        tabs.setPreferredSize(new Dimension(0, 20));
        tabChangeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                updateTable();
            }
        };
        tabs.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                // Р—Р°С‰РёС‚Р° РѕС‚ СЃРјРµРЅС‹ PreferedSize
                if (evt.getPropertyName() == "UI")
                    tabs.setPreferredSize(new Dimension(0, 20));
//                System.out.println("evt.getPropertyName() = " + evt.getPropertyName());
            }
        });
        tabs.addChangeListener(tabChangeListener);

    }

    /**
     * РЎРѕР·РґР°РЅРёРµ РІРЅСѓС‚СЂРµРЅРЅРёС… РєРѕРјРїРѕРЅРµРЅС‚РѕРІ
     */
    private void createComponents() {
        listeners = new ArrayList<SelectEventListener>();
        setPreferredSize(new Dimension(0, 0));
        setLayout(new BorderLayout());
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                refreshGrid();
            }

        });

        // РЎРѕР·РґР°РЅРёРµ Рё РёРЅРёС†РёР°Р»РёР·Р°С†РёСЏ РєРѕРјРїРѕРЅРµРЅС‚РѕРІ
        createTable();
        createTabs();
        // Р”РѕР±Р°РІР»РµРЅРёРµ РІСЃРµС… РєРѕРјРїРѕРЅРµС‚РѕРІ
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(tabs, BorderLayout.SOUTH);
    }

    public EntityGrid() {
        super();
        createComponents();

    }

    /**
     * РћР±РЅРѕРІР»РµРЅРёРµ С‚Р°Р±Р»РёС†С‹
     */
    private void updateTable() {
        table.repaint();
    }

    /**
     * РћР±РЅРѕРІР»РµРЅРёРµ Р·Р°РєР»Р°РґРѕРє
     */
    private void updateTabs() {
        if (lastTabCount == tabsCount) return;
        int selected = Math.max(tabs.getSelectedIndex(), 0);
        tabs.removeChangeListener(tabChangeListener);
        tabs.removeAll();
        for (int i = 0; i < tabsCount; i++)
            tabs.addTab(String.valueOf(i), null);
        tabs.addChangeListener(tabChangeListener);
        if (tabs.getTabCount() > 0) {
            selected = selected < tabs.getTabCount() ? selected : 0;
            tabs.setSelectedIndex(selected);
        }
    }

    /**
     * Р’С‹С‡РёСЃР»СЏРµС‚ РІСЃРµ РЅРµРѕР±С…РѕРґРёРјС‹Рµ РїР°СЂР°РјРµС‚СЂС‹ РґР»СЏ С„СѓРЅРєС†РёРѕРЅРёСЂРѕРІР°РЅРёСЏ
     */

    private void calcGrid() {
        double height = table.getSize().getHeight();
        double rowHeight = table.getRowHeight();
        maxRowCount = (view != null) && (entities.size() > 0) && (rowHeight > 0) ? (int) Math.floor(height / rowHeight) : 0;
        lastTabCount = tabsCount;
        tabsCount = (view != null) && (maxRowCount > 0) && (entities.size() > 0) ? (int) Math.floor((entities.size() - 1) / maxRowCount) + 1 : 0;
    }

    /**
     * РћР±РЅРѕРІР»РµРЅРёРµ РєРѕРјРїРѕРЅРµРЅС‚Р°
     */
    public void refreshGrid() {
        calcGrid();
        updateTabs();
        updateTable();
    }

    public boolean isShowHeaders() {
        return showHeaders;
    }

    public void setShowHeaders(boolean showHeaders) {
        this.showHeaders = showHeaders;
    }


    public IView getView() {
        return view;
    }

    public void setView(IView view) {
        this.view = view;
    }


    public List<IEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<IEntity> entities) {
        this.entities = entities;
    }

    public void updateGrid() {
        table.setModel(new TableGridModel());
    }
}
