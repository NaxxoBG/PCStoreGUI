import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class ShopServer_View extends JFrame
{
   private final String[] ELEMENTS = {"SSD", "HDD", "RAM", "CPU", "GPU"};
   private final PCPart[] parts = {new SSD(), new HDD() , new RAM(), new CPU(), new GPU()};
   private JComboBox<String> box;
   private JLabel name;
   private JLabel value;
   private JTextField nameField;
   private JTextField valueField;
   private JButton addButton;
   private static DefaultListModel<String> model;
   public static JList<String> list;
   public static List<Object> arrList;
   private JButton removeButton;

   public ShopServer_View () {
      super("ShopServer");
      setResizable(false);
      setLayout(null);

      addButtonListener listener = new addButtonListener();
      removeButtonListener listener2 = new removeButtonListener();

      box = new JComboBox<String>(ELEMENTS);
      box.setSelectedIndex(0);
      box.setBounds(5, 10, 432, 30);
      add(box);

      name = new JLabel("Name: ");
      name.setBounds(5,72,268,30);
      add(name);

      nameField = new JTextField(10);
      nameField.setBounds(70,72,367,30);
      nameField.setToolTipText("Write the name of the product");
      add(nameField);

      value = new JLabel("Value: ");
      value.setBounds(5, 144, 268, 30);
      add(value);

      valueField = new JTextField(10);
      valueField.setBounds(70,144,367,30);
      valueField.setToolTipText("Set the value of the product");
      add(valueField);

      addButton = new JButton("Add");
      addButton.addActionListener(listener);
      addButton.setBounds(5, 200, 432,30);
      add(addButton);

      model = new DefaultListModel<String>();
      list = new JList<String>(model);
      list.setBounds(5, 240, 432, 200);
      //JScrollPane scroll = new JScrollPane(list);
      //scroll.add(list);
      //add(scroll);
      arrList = Arrays.asList(model.toArray());
      add(list);

      removeButton = new JButton("Remove");
      removeButton.addActionListener(listener2);
      removeButton.setBounds(5,450,432,30);
      add(removeButton);

      pack();
   }

   class addButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (nameField.getText().length() < 1 && valueField.getText().length() < 1) {
            JOptionPane.showMessageDialog(value, "Please enter a name and a product value", "Warning", JOptionPane.WARNING_MESSAGE);
         } else if (nameField.getText().length() < 1) {
            JOptionPane.showMessageDialog(name, "Please enter a product name", "Warning", JOptionPane.WARNING_MESSAGE);
         } else if (valueField.getText().length() < 1) {
            JOptionPane.showMessageDialog(value, "Please enter a product value", "Warning", JOptionPane.WARNING_MESSAGE);
         } else {
            try {
               Object obj = parts[box.getSelectedIndex()];
               ((PCPart) obj).setName(nameField.getText());
               ((PCPart) obj).setValue(Double.parseDouble(valueField.getText()));
               model.addElement(obj.toString());
            }
            catch (NumberFormatException e2) {
               JOptionPane.showMessageDialog(value, "Please enter a correct product value", "Error", JOptionPane.ERROR_MESSAGE);
            }
         }
      }
   }

   class removeButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (list.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(value, "Either the list is empty or you have not selected an item", "Error", JOptionPane.ERROR_MESSAGE);
         } else {
            model.remove(list.getSelectedIndex());
            Subscriber_View.modelSub.clear();
         }
      }
   }

   public static class Subscriber_View extends JFrame
   {
      JPanel panel;
      JCheckBox checkSSD;
      String idSSD = "deselected";
      JCheckBox checkHDD;
      String idHDD = "deselected";
      JCheckBox checkRAM;
      String idRAM = "deselected";
      JCheckBox checkCPU;
      String idCPU = "deselected";
      JCheckBox checkGPU;
      String idGPU = "deselected";
      JList<String> listSub;
      static DefaultListModel<String> modelSub;
      JButton update;

      public Subscriber_View() {
         super("Subscriber");
         setResizable(false);
         GridLayout grid = new GridLayout(1,5);
         grid.setHgap(40);
         panel = new JPanel(grid);
         panel.setBounds(5,5, 420, 40);
         setLayout(null);

         updateListener listener = new updateListener();

         checkSSD = new JCheckBox("SSD");
         checkSSD.setSelected(false);
         checkSSD.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                  idSSD = "selected";
               } else {
                  idSSD = "deselected";
               }
               System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
               modelSub.clear();
            }
         });

         checkHDD = new JCheckBox("HDD");
         checkHDD.setSelected(false);
         checkHDD.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                  idHDD = "selected";
               } else {
                  idHDD = "deselected";
               }
               System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
               modelSub.clear();
            }
         });

         checkRAM = new JCheckBox("RAM");
         checkRAM.setSelected(false);
         checkRAM.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                  idRAM = "selected";
               } else {
                  idRAM = "deselected";
               }
               System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
               modelSub.clear();
            }
         });

         checkCPU = new JCheckBox("CPU");
         checkCPU.setSelected(false);
         checkCPU.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                  idCPU = "selected";
               } else {
                  idCPU = "deselected";
               }
               System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
               modelSub.clear();
            }
         });

         checkGPU = new JCheckBox("GPU");
         checkGPU.setSelected(false);
         checkGPU.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
               if (e.getStateChange() == ItemEvent.SELECTED) {
                  idGPU = "selected";
               } else {
                  idGPU = "deselected";
               }
               System.out.println(e.getStateChange() == ItemEvent.SELECTED ? "SELECTED" : "DESELECTED");
               modelSub.clear();
            }
         });

         panel.add(checkSSD);
         panel.add(checkHDD);
         panel.add(checkRAM);
         panel.add(checkCPU);
         panel.add(checkGPU);

         add(panel);
         Border raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
         panel.setBorder(raisedEtched);

         modelSub = new DefaultListModel<String>();
         listSub = new JList<String>(modelSub);
         listSub.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         listSub.setVisibleRowCount(-1);
         listSub.setBounds(5, 50, 420, 300);
         listSub.setLayoutOrientation(JList.VERTICAL);
         add(listSub);

         update = new JButton("Update");
         update.setBounds(5, 360, 420, 40);
         update.addActionListener(listener);
         add(update);
      }

      class updateListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
            if (Arrays.equals(model.toArray(), modelSub.toArray())) {
               System.out.println("DO NOTHING");
            } else {
               for (Object element : model.toArray()) {
                  if (idSSD.equals("selected") && ((String) element).substring(0, 3).equals("SSD")) {
                     modelSub.addElement((String) element);
                  } else if (idHDD.equals("selected") && ((String) element).substring(0, 3).equals("HDD")) {
                     modelSub.addElement((String) element);
                  } else if (idRAM.equals("selected") && ((String) element).substring(0, 3).equals("RAM")) {
                     modelSub.addElement((String) element);
                  } else if (idCPU.equals("selected") && ((String) element).substring(0, 3).equals("CPU")) {
                     modelSub.addElement((String) element);
                  } else if (idGPU.equals("selected") && ((String) element).substring(0, 3).equals("GPU")) {
                     modelSub.addElement((String) element);
                  }
               }
            }
         }
      }
   }

   public static void main(String[] args) {
      ShopServer_View shop = new ShopServer_View();
      shop.setVisible(true);
      shop.setDefaultCloseOperation(EXIT_ON_CLOSE);
      shop.setSize(450,550);
      shop.setLocation(435,240);
      Subscriber_View sub = new Subscriber_View();
      sub.setDefaultCloseOperation(EXIT_ON_CLOSE);
      sub.setSize(435, 445);
      sub.setVisible(true);
      sub.setLocation(935,240);
   }
}