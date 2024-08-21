import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormStockPrice extends JFrame {
    private JButton selectFileButton;
    private JButton SmaButton;
    private JPanel panel1;
    private JButton EmaButton;
    private JButton WmaButton;
    private JTextArea textArea1;
    private File file;
    private boolean fileSelected = false;
    private File selectedFile;

    public FormStockPrice() {
        add(panel1);
        setSize(600, 600);
        setTitle("Tahmin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea1);
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        SmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected) {
                    FileRead fileRead = new FileRead(file);
                    fileRead.readAllData();
                    MovingAverage movingAverage = new MovingAverage(fileRead);
                    double guessSma = movingAverage.CalculateSma();
                    JOptionPane.showMessageDialog(null, "Basit Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessSma);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir dosya seçin.");
                }
            }
        });
        WmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected) {
                    FileRead fileRead = new FileRead(file);
                    fileRead.readAllData();
                    MovingAverage movingAverage = new MovingAverage(fileRead);
                    double guessWma = movingAverage.CalculateWma();
                    JOptionPane.showMessageDialog(null, "Ağırlıklı Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessWma);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir dosya seçin.");
                }

            }
        });
        EmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected) {
                    FileRead fileRead = new FileRead(file);
                    fileRead.readAllData();
                    MovingAverage movingAverage = new MovingAverage(fileRead);
                    double guessEma = movingAverage.CalculateEma();
                    JOptionPane.showMessageDialog(null, "Üssel Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessEma);
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir dosya seçin.");
                }

            }
        });

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser files = new JFileChooser();
                int returnVal = files.showOpenDialog(null);
                if (!fileSelected) {
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        selectedFile = files.getSelectedFile();
                        textArea1.setText("");
                        JOptionPane.showMessageDialog(null, "Dosya başarıyla eklendi: " + selectedFile.getName());
                        fileSelected = true;
                        selectFileButton.setEnabled(false);
                        file=selectedFile;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Zaten bir dosya seçildi. Sürükleyip bırakma yöntemi kullanılamaz.");
                }
            }
        });


    }
    public void dosya(){

    }
}




