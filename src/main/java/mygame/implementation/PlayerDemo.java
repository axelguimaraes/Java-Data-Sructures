package mygame.implementation;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerDemo extends JFrame{
    private JPanel panelMain;
    private JTextField txtName;
    private JButton btnClick;
    private JButton registerPlayer;
    private JButton selectPlayer;
    private JTextPane showPlayersNames;

    public JTextField getTxtName() {
        return txtName;
    }

    public PlayerDemo(){

        super("Player Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // código a ser executado quando o botão é clicado
            }
        });

        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = txtName.getText();
                showPlayersNames.setText(texto);
            }
        });


        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = txtName.getText();
                if (!showPlayersNames.getText().contains(texto)) {
                    showPlayersNames.setText(showPlayersNames.getText() + texto + "\n");
                }
            }
        });

    }

    public static void main(String[] args){
        PlayerDemo player = new PlayerDemo();
        player.setContentPane(player.panelMain);
        player.setVisible(true);
    }
}
