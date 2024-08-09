package src.view;

import src.Model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class GameGUI extends JFrame {
    private Grid grid;
    final JTextField[][] gridP;
    private int game = 0;

    public GameGUI(int diff) {
        this.grid = new Grid();
        this.grid.generateGRID(diff);

        setLayout(new GridLayout(9, 9));
        gridP = new JTextField[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                gridP[row][col] = new JTextField();
                if (row < 3 && col < 3 || row > 5 && col > 5 || row < 3 && col > 5 || row > 5 && col < 3
                        || row > 2 && row < 6 && col > 2 && col < 6) {
                    gridP[row][col].setBorder(BorderFactory.createLineBorder(Color.ORANGE));

                } else {
                    gridP[row][col].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                }
                gridP[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                this.add(gridP[row][col]);

                final int currentRow = row;
                final int currentCol = col;
                gridP[row][col].getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        valueChanged(currentRow, currentCol);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        valueChanged(currentRow, currentCol);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        valueChanged(currentRow, currentCol);
                    }
                });
            }
        }

        setInitialValues(grid.getgrid());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        pack(); // Taille préférée
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        this.game = 1;
    }

    public void setInitialValues(int[][] initialValues) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (initialValues[row][col] != 0) {
                    gridP[row][col].setText(String.valueOf(initialValues[row][col]));
                    gridP[row][col].setEditable(false); // Empêcher la modification des valeurs initiales
                    gridP[row][col].setForeground(Color.RED);

                }
            }
        }
    }

    private void valueChanged(int row, int col) {
        String text = gridP[row][col].getText();
        if (text.isEmpty()) {
            this.grid.setElement(row, col, 0);

        } else {
            try {
                int value = Integer.parseInt(text);
                if (value > 9 || value < 1) {
                    JOptionPane.showMessageDialog(this, "Entrée non valide chiffre non compris entre 0 et 9  ",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    SwingUtilities.invokeLater(() -> {
                        this.grid.setElement(row, col, 0);
                        gridP[row][col].setText("");
                    });
                } else {
                    if (game == 1) {
                        if (!this.grid.setElementInGrid(row, col, value)) {
                            JOptionPane.showMessageDialog(this, "Ton chiffre est en conflit avec un autre",
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                            SwingUtilities.invokeLater(() -> {
                                this.grid.setElement(row, col, 0);
                                gridP[row][col].setText("");
                            });
                        } else {
                            if (this.grid.IsWin()) {
                                JOptionPane.showMessageDialog(this, "Félicitations !",
                                        "Félicitations", JOptionPane.ERROR_MESSAGE);
                                int choice = JOptionPane.showConfirmDialog(GameGUI.this,
                                        "Souhaites-tu recommencer une autre partie ?",
                                        "Recommencer ?", JOptionPane.YES_NO_OPTION);
                                if (choice == JOptionPane.YES_OPTION) {
                                    new Menu();
                                    dispose();
                                } else {
                                    dispose();
                                }

                            }
                        }

                    }
                }

            } catch (NumberFormatException e) {
                // Gérer le cas où le texte n'est pas un entier valide

                JOptionPane.showMessageDialog(this, "Entrée non valide seul les chiffres sont autorisés ",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                SwingUtilities.invokeLater(() -> {
                    gridP[row][col].setText("");
                });

            }
        }
    }

}
