import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormStockPrice extends JFrame implements ActionListener {
    private JButton selectFileButton;
    private JButton SmaButton;
    private JPanel panel1;
    private JButton EmaButton;
    private JButton WmaButton;
    private JTextArea textArea1=new JTextArea(3, 3);
    private boolean fileSelected = false;
    private File selectedFile;
    private FileRead fileRead;

    public FormStockPrice() {
        add(panel1);
        panel1.setSize(800, 400);
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

        DragAndDrop drag = new DragAndDrop(textArea1, this);
        new DropTarget(textArea1,drag);

        SmaButton.addActionListener(this);

        WmaButton.addActionListener(this);

        EmaButton.addActionListener(this);

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
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Zaten bir dosya seçildi. Sürükleyip bırakma yöntemi kullanılamaz.");
                }
            }
        });
    }
    public void setFileRead(FileRead fileRead) {
        this.fileRead=fileRead;
    }
    public void setFile(File file) {
        this.selectedFile = file;
        this.fileSelected = true;
        this.fileRead = new FileRead(file);
    }
    public void disableSelectFileButton() {
        selectFileButton.setEnabled(false);
    }
    public boolean isFileSelected() {
        return fileSelected;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedFile!=null) {
            FileRead fileRead = new FileRead(selectedFile);
            fileRead.readAllData();
            fileRead.checkData();
            MovingAverage movingAverage = new MovingAverage(fileRead);
            if(e.getActionCommand().equals("Üssel Hareketli Ortalama")){
                double guessEma = movingAverage.CalculateEma();
                JOptionPane.showMessageDialog(null, "Üssel Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessEma);
            }
            else if(e.getActionCommand().equals("Ağırlıklı Hareketli Ortalama")){
                double guessWma = movingAverage.CalculateWma();
                JOptionPane.showMessageDialog(null, "Ağırlıklı Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessWma);
            }
            else if(e.getActionCommand().equals("Basit Hareketli Ortalama")){
                double guessSma = movingAverage.CalculateSma();
                JOptionPane.showMessageDialog(null, "Basit Hareketli Ortalamaya Göre Tahmin Fiyatı: " + guessSma);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lütfen bir dosya seçin.");
        }
    }
}




