package Windows;

import buildings.dwelling.Dwelling;
import buildings.dwelling.OfficeBuilding;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicBorders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildingReview {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 1000;

    private JFrame frame;
    private JPanel panel;
    private JPanel buildingPanel;
    private Building building;
    private Floor currentFloor;
    private int numbFloor;
    private Space currentSpace;
    private int numbSpace;
    private String type;
    private Label labelBuilding;
    private Label labelFloor;
    private Label labelSpace;
    private JPanel[] floorsPanels;


    public BuildingReview(Building b){
        building = b;
        if(building instanceof Dwelling){
            type = "Dwelling";

        }
        if(building instanceof OfficeBuilding){
            type = "OfficeBuilding";

        }
        frame = getFrame();
        frame.setTitle(type);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));


        init();
        createMaket();
        setText(0,0);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void init(){
        currentFloor = building.getFloor(0);
        currentSpace = building.getSpace(0);
    }
    private static JFrame getFrame(){
        JFrame fr = new JFrame();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        fr.setBounds(dimension.width/2-WIDTH/2,dimension.height/2-HEIGHT/2,WIDTH,HEIGHT);
        fr.setTitle("BuildingReview");
        return fr;
    }
    private void createMaket(){
        floorsPanels = new JPanel[building.getNumOfFloors()];
        int numspace = 0;
        for(int i = 0;i<building.getNumOfFloors();i++){
            int f = i;
            int n1 = numspace;
            Floor floor = building.getFloor(i);
            floorsPanels[i] = new JPanel();
            floorsPanels[i].addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(final MouseEvent e)
                {
                    currentFloor = floor;

                    setText(f,n1);
                }
            });

            for (int j = 0; j < floor.getNumOfSpaces(); j++)
            {
                int n = numspace;
                Space space = floor.getSpace(j);
                JButton button = new JButton(numspace+space.getClass().getName());
                button.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(final ActionEvent e)
                    {
                        currentSpace = space;

                        setText(f,n);
                    }

                });
                numspace++;
                floorsPanels[i].add(button);
            }
            panel.add(floorsPanels[i]);
        }
    }
    private void setText(int f,int s){
        numbSpace = s;
        numbFloor = f;
        if(labelBuilding==null)labelBuilding = new Label();
        labelBuilding.setBackground(Color.WHITE);
        labelBuilding.setText(type+" Кол-во этажей:"+building.getNumOfFloors()+" Площадь:"+building.getSquare());
        panel.add(labelBuilding);

        if(labelFloor==null)labelFloor = new Label();
        labelFloor.setBackground(Color.WHITE);
        labelFloor.setText("Номер этажа: "+f+" Кол-во помещений: "+currentFloor.getNumOfSpaces()+" Площадь: "+currentFloor.getAllSquare());
        panel.add(labelFloor);

        if(labelSpace==null)labelSpace = new Label();
        labelSpace.setBackground(Color.WHITE);
        labelSpace.setText("Номер: "+s+" Кол-во комнат: "+currentSpace.getNumOfRooms()+" Площадь: "+currentSpace.getSquare());
        panel.add(labelSpace);
        panel.revalidate();
    }
}
