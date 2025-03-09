/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ronna
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KontakManager {
    private static final String TITLE = "Pengelolaan Daftar Kontak";
    private static ArrayList<Kontak> daftarKontak = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Mengatur warna latar belakang
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Komponen
        JTextField namaField = new JTextField(15);
        JTextField nomorField = new JTextField(15);
        JTextArea kontakArea = new JTextArea(10, 30);
        kontakArea.setEditable(false);
        kontakArea.setLineWrap(true);
        kontakArea.setWrapStyleWord(true);
        kontakArea.setBackground(new Color(255, 255, 255));
        kontakArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JButton tambahButton = new JButton("Tambah Kontak");
        JButton hapusButton = new JButton("Hapus Kontak");

        // Mengatur GridBagConstraints untuk komponen
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Nama:"), gbc);

        gbc.gridx = 1;
        frame.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Nomor Telepon:"), gbc);

        gbc.gridx = 1;
        frame.add(nomorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Menggabungkan kolom
        frame.add(tambahButton, gbc);

        gbc.gridy = 3;
        frame.add(hapusButton, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2; // Menggabungkan kolom
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(new JScrollPane(kontakArea), gbc);

        // Action Listener untuk tombol
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaField.getText();
                String nomor = nomorField.getText();
                if (!nama.isEmpty() && !nomor.isEmpty()) {
                    daftarKontak.add(new Kontak(nama, nomor));
                    updateKontakArea(kontakArea);
                    namaField.setText("");
                    nomorField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Nama dan Nomor Telepon tidak boleh kosong!");
                }
            }
        });

        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = namaField.getText();
                if (!nama.isEmpty()) {
                    daftarKontak.removeIf(kontak -> kontak.getNama().equalsIgnoreCase(nama));
                    updateKontakArea(kontakArea);
                    namaField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Masukkan nama kontak yang ingin dihapus!");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void updateKontakArea(JTextArea kontakArea) {
        kontakArea.setText("");
        for (Kontak kontak : daftarKontak) {
            kontakArea.append(kontak.toString() + "\n");
        }
    }
}
