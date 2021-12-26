import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JPanel;

public class board extends JPanel {
    int numColumns = 8;
    int numRows = 8;
    int height = 400;
    int width = 400;
    int cellSizeX = height / numRows;
    int cellSizeY = width / numColumns;
    Image[] img;
    Graphics g;
    Color c;
    cell cellObj = new cell(numRows, numColumns);

    public board() {
        initBoard();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(400, 400));
        img = new Image[13];
        addMouseListener(new MinesAdapter());
        newGame();
    }

    private void newGame() {
        this.c = Color.YELLOW;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int runningnum = 0;
        for (int i = 0; i < this.numColumns; i++) {
            for (int k = 0; k < this.numRows; k++) {
                runningnum++;
                if (cellObj.getCell()[i][k] == true) {
                    if (cellObj.getBomb()[i][k] == true) {
                        g.setColor(Color.BLACK);
                        System.exit(0);
                    } else {
                        g.setColor(Color.LIGHT_GRAY);
                    }

                } else if (cellObj.getFlag()[i][k] == true) {
                    g.setColor(Color.YELLOW);

                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect((this.width / this.numColumns) * i, (this.height / this.numRows) * k, this.cellSizeX,
                        this.cellSizeY);

                if (cellObj.getCell()[i][k] == true && cellObj.getBomb()[i][k] == false) {
                    String s = String.valueOf(cellObj.getDistance()[runningnum - 1]);
                    Color cc = new Color(0, 100, 0);
                    AttributedString as = new AttributedString(s);
                    as.addAttribute(TextAttribute.FOREGROUND, cc);
                    as.addAttribute(TextAttribute.WEIGHT, 2);
                    as.addAttribute(TextAttribute.SIZE, 20);
                    g.drawString(as.getIterator(), ((this.width / this.numColumns) * i) + 15,
                            ((this.height / this.numRows) * k) + 30);
                }
            }
        }
        for (int i = 0; i < this.numColumns; i++) {
            for (int k = 0; k < this.numRows; k++) {
                g.setColor(Color.BLACK);
                g.drawRect((this.width / this.numColumns) * i, (this.height / this.numRows) * k, this.cellSizeX,
                        this.cellSizeY);
            }
        }
    }

    private class MinesAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int cCol = x / cellSizeX;
            int cRow = y / cellSizeY;
            System.out.println(cCol);
            System.out.println(cRow);
            if (e.getButton() == MouseEvent.BUTTON1) {
                // select
                cellObj.setCell(cCol, cRow);
                repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // flag
                cellObj.setFlag(cCol, cRow);
                repaint();
            } else if (e.getButton() == MouseEvent.BUTTON2) {
                cellObj.doRealAuto();
                repaint();
            }
        }
    }
}
