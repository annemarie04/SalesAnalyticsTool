import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GUI implements ActionListener {
    private JFrame jFrame;
    private JButton jButton;
    private JPanel jPanel;
    private JLabel jLabel;
    static Color buttonColor = new Color(242, 216, 216);
    static Color backgroundColor = new Color(92, 137, 132);
    static Color textColor = new Color(84, 91, 119);
    private Service myService;
    CSVService csv = new CSVService();

    public GUI(Service service, CSVService csv ) throws IOException {
        this.myService = service;

        jFrame = new JFrame();
        jPanel = new JPanel();

        jPanel.setBorder(BorderFactory.createEmptyBorder(600,600,600,600));
        jPanel.setLayout(new GridLayout(0, 1));
        jPanel.setBackground(backgroundColor);

        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sales Analytics Tool");
        jFrame.pack();
        jFrame.setVisible(true);

        jLabel = new JLabel("cute label.");

        JButton button1 = new JButton("1. Add Data");
        button1.addActionListener(e -> openAddDataWindow(myService));
        button1.setBounds(50,40, 500, 50);
        button1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button1.setOpaque(true);
        button1.setBackground(buttonColor);
        button1.setForeground(textColor);

        JButton button2 = new JButton("2. Display Data");
        button2.addActionListener(this);
        button2.setBounds(50,100, 500, 50);
        button2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button2.setOpaque(true);
        button2.setBackground(buttonColor);
        button2.setForeground(textColor);

        JButton button3 = new JButton("3. Generate Reports");
        button3.addActionListener(this);
        button3.setBounds(50,160, 500, 50);
        button3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button3.setOpaque(true);
        button3.setBackground(buttonColor);
        button3.setForeground(textColor);

        JButton button4 = new JButton("4. Display Reports");
        button4.addActionListener(this);
        button4.setBounds(50,220, 500, 50);
        button4.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button4.setOpaque(true);
        button4.setBackground(buttonColor);
        button4.setForeground(textColor);

        JButton button5 = new JButton("5. Generate Analytics");
        button5.addActionListener(this);
        button5.setBounds(50,280, 500, 50);
        button5.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button5.setOpaque(true);
        button5.setBackground(buttonColor);
        button5.setForeground(textColor);

        JButton button6 = new JButton("6. Apply Promotion");
        button6.addActionListener(this);
        button6.setBounds(50,340, 500, 50);
        button6.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button6.setOpaque(true);
        button6.setBackground(buttonColor);
        button6.setForeground(textColor);

        JButton button7 = new JButton("7. Search Product");
        button7.addActionListener(this);
        button7.setBounds(50,400, 500, 50);
        button7.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button7.setOpaque(true);
        button7.setBackground(buttonColor);
        button7.setForeground(textColor);

        JButton button8 = new JButton("8. Search Customer");
        button8.addActionListener(this);
        button8.setBounds(50,460, 500, 50);
        button8.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button8.setOpaque(true);
        button8.setBackground(buttonColor);
        button8.setForeground(textColor);

        JButton button9 = new JButton("9. Delete Data");
        button9.addActionListener(this);
        button9.setBounds(600,40, 500, 50);
        button9.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button9.setOpaque(true);
        button9.setBackground(buttonColor);
        button9.setForeground(textColor);

        JButton button10 = new JButton("10. Load from Database");
        button10.addActionListener(this);
        button10.setBounds(600,100, 500, 50);
        button10.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button10.setOpaque(true);
        button10.setBackground(buttonColor);
        button10.setForeground(textColor);

        JButton button11 = new JButton("11. Update Customer Information");
        button11.addActionListener(this);
        button11.setBounds(600,160, 500, 50);
        button11.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button11.setOpaque(true);
        button11.setBackground(buttonColor);
        button11.setForeground(textColor);

        JButton button12 = new JButton("12. Update Transaction Information");
        button12.addActionListener(this);
        button12.setBounds(600,220, 500, 50);
        button12.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button12.setOpaque(true);
        button12.setBackground(buttonColor);
        button12.setForeground(textColor);

        JButton button13 = new JButton("13. Add Reports to DB");
        button13.addActionListener(this);
        button13.setBounds(600,280, 500, 50);
        button13.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button13.setOpaque(true);
        button13.setBackground(buttonColor);
        button13.setForeground(textColor);

        JButton button14 = new JButton("14. Display Reports from DB");
        button14.addActionListener(this);
        button14.setBounds(600,340, 500, 50);
        button14.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button14.setOpaque(true);
        button14.setBackground(buttonColor);
        button14.setForeground(textColor);

        JButton button15 = new JButton("15. Delete Report from DB");
        button15.addActionListener(this);
        button15.setBounds(600,400, 500, 50);
        button15.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button15.setOpaque(true);
        button15.setBackground(buttonColor);
        button15.setForeground(textColor);

        JButton button16 = new JButton("16. Update Report Content");
        button16.addActionListener(this);
        button16.setBounds(600,460, 500, 50);
        button16.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button16.setOpaque(true);
        button16.setBackground(buttonColor);
        button16.setForeground(textColor);

        jFrame.add(button1);
        jFrame.add(button2);
        jFrame.add(button3);
        jFrame.add(button4);
        jFrame.add(button5);
        jFrame.add(button6);
        jFrame.add(button7);
        jFrame.add(button8);
        jFrame.add(button9);
        jFrame.add(button10);
        jFrame.add(button11);
        jFrame.add(button12);
        jFrame.add(button13);
        jFrame.add(button14);
        jFrame.add(button15);
        jFrame.add(button16);
        jFrame.add(jLabel);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cute button clicked!");
    }

    public void openAddDataWindow(Service service) {
        JFrame jFrame2 = new JFrame();
        JPanel jPanel2 = new JPanel();
        JLabel jLabel2 = new JLabel("cute label.");

        jPanel2.setBorder(BorderFactory.createEmptyBorder(600,600,600,600));
        jPanel2.setLayout(new GridLayout(0, 1));
        jPanel2.setBackground(backgroundColor);

        jFrame2.add(jPanel2, BorderLayout.CENTER);
        jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame2.setTitle("Add Data");
        jFrame2.pack();
        jFrame2.setVisible(true);

        JButton button1 = new JButton("1.1. Add Products.");
        //button1.addActionListener(service.addProducts() );
        button1.setBounds(50,40, 500, 50);
        button1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button1.setOpaque(true);
        button1.setBackground(buttonColor);
        button1.setForeground(textColor);

        JButton button2 = new JButton("1.2. Add Customers");
        //button2.addActionListener(service.addProducts());
        button2.setBounds(50,100, 500, 50);
        button2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button2.setOpaque(true);
        button2.setBackground(buttonColor);
        button2.setForeground(textColor);
//
        JButton button3 = new JButton("1.3. Add SalesTransactions.");
        //button3.addActionListener(this);
        button3.setBounds(50,160, 500, 50);
        button3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button3.setOpaque(true);
        button3.setBackground(buttonColor);
        button3.setForeground(textColor);

        JButton button4 = new JButton("1.4. Add Regions");
        //button4.addActionListener(this);
        button4.setBounds(50,220, 500, 50);
        button4.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button4.setOpaque(true);
        button4.setBackground(buttonColor);
        button4.setForeground(textColor);

        JButton button5 = new JButton("1.5. Add Products, Customers, SalesTransactions, Regions.");
        //button5.addActionListener(this);
        button5.setBounds(50,280, 500, 50);
        button5.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        button5.setOpaque(true);
        button5.setBackground(buttonColor);
        button5.setForeground(textColor);

        jFrame2.add(button1);
        jFrame2.add(button2);
        jFrame2.add(button3);
        jFrame2.add(button4);
        jFrame2.add(button5);
        jFrame2.add(jLabel2);

    }
}
