package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {

    Font f = new Font("Comic Sans MS", Font.PLAIN, 70);

    public Menu() {
        // Définition des propriétés de la fenêtre

        setTitle("Sudoku");
        setSize(500, 400);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            // On veut demander la confirmation afin de pouvoir quitter le jeu ou pas 
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(Menu.this, "Souhaites-tu quitter mon jeu ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 0, 20)); // 3 lignes, 1 colonne, espacement vertical de 20
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Sudoku");
        titleLabel.setFont(f);
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel);

        //bouton "Jouer"
        JButton jouerButton = new JButton("Jouer");
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Changement du panneau lorsque le bouton "Jouer" est cliqué
                changerPanneau();
            }
        });
        mainPanel.add(jouerButton);

        //bouton "Quitter"
        JButton quitterButton = new JButton("Quitter");
        quitterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(Menu.this, "Souhaites-tu quitter mon jeu ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                        }
                    });
                }
            }
        });
        mainPanel.add(quitterButton);

        add(mainPanel);
        setVisible(true);
    }

    private void changerPanneau() {
        getContentPane().removeAll();
        JPanel nouvJPanel = new JPanel();
        nouvJPanel.setLayout(new BorderLayout());
        nouvJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titreLabel = new JLabel("Choix du Niveau");
        titreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titreLabel.setHorizontalAlignment(JLabel.CENTER);
        nouvJPanel.add(titreLabel, BorderLayout.NORTH);

        // Ajout liste de boutons
        JPanel boutonsPanel = new JPanel(new GridLayout(4, 1, 0, 10));

        // Bouton "Facile"
        JButton facileButton = new JButton("Facile");

        facileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameGUI(1);
                dispose();
            }
        });

        boutonsPanel.add(facileButton);

        // Bouton "Moyen"
        JButton moyenButton = new JButton("Moyen");

        moyenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameGUI(2);
                dispose();
            }
        });

        boutonsPanel.add(moyenButton);

        // Bouton "Difficile"
        JButton difficileButton = new JButton("Difficile");
        difficileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameGUI(3);
                dispose();
            }
        });
        boutonsPanel.add(difficileButton);

        // Bouton "Extrême"
        JButton extremeButton = new JButton("Extrême");
        extremeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameGUI(4);
                dispose();
            }
        });
        boutonsPanel.add(extremeButton);

        nouvJPanel.add(boutonsPanel, BorderLayout.CENTER);
        getContentPane().add(nouvJPanel);
        revalidate();
        repaint();
    }

}
