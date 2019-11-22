package Windows;

import buildings.Buildings;
import buildings.Floors.FlatFloor;
import buildings.Floors.OfficeFloor;
import buildings.Spaces.Flat;
import buildings.Spaces.Office;
import buildings.dwelling.Dwelling;
import buildings.dwelling.OfficeBuilding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class MenuWindow {
    private static final int HEIGHT = 300;
    private static final int WIDTH = 500;

    private JFrame frame;
    private JPanel jPanel;

    public MenuWindow(){
        frame = getFrame();
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        frame.add(jPanel);
        JButton dwellingButton = new JButton("Жилое здание");
        JButton officeButton = new JButton("Офисное здание");
        JRadioButton radioButton1 = new JRadioButton("first");
        radioButton1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {

                try
                {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame.getContentPane());
                    jPanel.revalidate();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        JRadioButton radioButton2 = new JRadioButton("second");
        radioButton2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {

                try
                {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame.getContentPane());
                    jPanel.revalidate();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });
        JRadioButton radioButton3 = new JRadioButton("third");
        radioButton3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {

                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame.getContentPane());
                    jPanel.revalidate();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        jPanel.add(radioButton1);
        jPanel.add(radioButton2);
        jPanel.add(radioButton3);
        dwellingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = getFile();
                Dwelling dwelling = null;
                if (file != null) {
                    try {

                        dwelling = (Dwelling) Buildings.readBuilding(new FileReader(file), Flat.class, FlatFloor.class,Dwelling.class);
                        frame.dispose();
                        BuildingReview buildingReview = new BuildingReview(dwelling);
                    } catch (Exception e1){
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(frame,"Файл не содержит здания");
                    }
                }
            }
        });
        officeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = getFile();
                if(file!=null) {
                    OfficeBuilding officeBuilding = null;
                    try {
                        officeBuilding = (OfficeBuilding) Buildings.readBuilding(new FileReader(file), OfficeBuilding.class, OfficeFloor.class, Office.class);
                        frame.dispose();
                        BuildingReview buildingReview = new BuildingReview(officeBuilding);
                    } catch (Exception e1){
                        JOptionPane.showMessageDialog(frame,"Файл не содержит здания");
                    }
                }
            }
        });

        jPanel.add(dwellingButton);
        jPanel.add(officeButton);

    }

    private static JFrame getFrame(){
        JFrame fr = new JFrame();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        fr.setBounds(dimension.width/2-WIDTH/2,dimension.height/2-HEIGHT/2,WIDTH,HEIGHT);
        fr.setTitle("App");
        return fr;
    }

    public File getFile(){
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выбор файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(jPanel);
        file = fileChooser.getSelectedFile();

        return file;
    }
}
