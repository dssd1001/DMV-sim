import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class DMV extends JFrame {

    Container c;
    ArrayList<Person> PDatabase = new ArrayList<Person>();
    ArrayList<License> LDatabase = new ArrayList<License>();
    HeapOfLicenses heapL = new HeapOfLicenses();
    URL url1 = DMV.class.getResource("Resources/Person.txt");
    URL url2 = DMV.class.getResource("Resources/License.txt");
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    Font norm = new Font("verdana", Font.PLAIN, 14);
    Color tan = new Color(240, 240, 240);

    public void readFromTextfile() throws ParseException {
        try {
            File file = new File(url1.getPath());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String s = "";
            while ((s = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, "|");
                Person e = new Person(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
                PDatabase.add(e);
            }
            if (!PDatabase.isEmpty()) {
                File nfile = new File(url2.getPath());
                BufferedReader br2 = new BufferedReader(new FileReader(nfile));

                String s2 = "";
                int i = 0;
                while ((s2 = br2.readLine()) != null) {
                    StringTokenizer st2 = new StringTokenizer(s2, "|");
                    String a = st2.nextToken();
                    char b = st2.nextToken().charAt(0);
                    Date c = format.parse(st2.nextToken());
                    Date d = format.parse(st2.nextToken());
                    int e = Integer.parseInt(st2.nextToken());
                    boolean f = Boolean.valueOf(st2.nextToken());
                    boolean g = Boolean.valueOf(st2.nextToken());
                    String h = st2.nextToken();
                    License l = new License(PDatabase.get(i), a, b, c, d, e, f, g, h);
                    i++;
                    LDatabase.add(l);
                }
            }
        } catch (IOException ioe) {
        }
    }

    public void writeToTextfile() {
        try {
            File file = new File(url2.getPath());
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

            for (int i = 0; i < LDatabase.size(); i++)
                bw.write(LDatabase.get(i).toString2() + "\n");
            bw.close();

            File nfile = new File(url1.getPath());
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(nfile, false));
            for (int i = 0; i < PDatabase.size(); i++)
                bw2.write(PDatabase.get(i).toString2() + "\n");
            bw2.close();
        } catch (Exception e) {

        }
    }

    public void initiate() {
        try {
            readFromTextfile();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JMenuBar menuBar;
        JMenu menu1, menu2;
        c = getContentPane();
        c.setBackground(tan);
        c.setForeground(Color.black);
        c.setLayout(null);
        setBounds(0, 0, 1000, 500);

        menuBar = new JMenuBar();
        menuBar.setBackground(tan);

        menu1 = new JMenu("Return to DMV Home");
        menu1.setBackground(tan);
        menu2 = new JMenu("Exit");
        menu2.setBackground(tan);

        setJMenuBar(menuBar);

        menuBar.add(menu1);
        menuBar.add(menu2);

        MouseListener mouseListener1 = new MouseAdapter() {
            public void mouseClicked(MouseEvent h) {
                writeToTextfile();
                System.exit(0);
            }
        };
        menu2.addMouseListener(mouseListener1);

        MouseListener mouseListener2 = new MouseAdapter() {
            public void mouseClicked(MouseEvent h) {
                DMV();
            }
        };
        menu1.addMouseListener(mouseListener2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        DMV();
    }

    public void DMV() {
        getContentPane().removeAll();
        getContentPane().repaint();

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(DMV.class.getResource("Resources/dmvLogo.png")));
        label.setBounds(150, 70, 700, 200);

        JButton btnMasterAdministrator = new JButton("Master Administrator");
        btnMasterAdministrator.setBounds(384, 332, 200, 29);

        JButton btnDmvStaff = new JButton("DMV Staff");
        btnDmvStaff.setBounds(690, 332, 117, 29);

        c.add(label);
        c.add(btnMasterAdministrator);
        c.add(btnDmvStaff);

        btnMasterAdministrator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministratorLogin();
            }
        });
        btnDmvStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BrowseLicenses();
            }
        });
    }

    public void AdministratorLogin() {
        getContentPane().removeAll();
        getContentPane().repaint();
        JLabel iD, pass;
        JTextField id, pw;
        JButton bbutt, butt1;

        JLabel title = new JLabel("Administrator Login");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        title.setBounds(150, 50, 600, 150);
        c.add(title);

        iD = new JLabel("Enter ID:");
        pass = new JLabel("Enter Password:");

        id = new JTextField();
        pw = new JTextField();

        iD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        iD.setBounds(350, 200, 200, 30);
        pass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pass.setBounds(350, 300, 200, 30);
        id.setBounds(650, 200, 200, 30);
        pw.setBounds(650, 300, 200, 30);

        c.add(iD);
        c.add(pass);
        c.add(id);
        c.add(pw);

        bbutt = new JButton("<-Back");
        bbutt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DMV();
            }
        });
        bbutt.setBounds(0, 0, 100, 30);
        c.add(bbutt);

        butt1 = new JButton("Go");
        butt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id.getText().equals("") || pw.getText().equals("")) {
                    Toolkit.getDefaultToolkit().beep();
                    id.requestFocusInWindow();
                    id.selectAll();
                } else {
                    if (!id.getText().equals("admin")) {
                        Toolkit.getDefaultToolkit().beep();
                        id.requestFocusInWindow();
                        id.selectAll();
                    } else if (pw.getText().equals("admin")) {
                        AdminBrowseLicenses();
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        id.requestFocusInWindow();
                        id.selectAll();
                    }
                }
            }
        });
        butt1.setBounds(800, 400, 100, 30);
        c.add(butt1);
    }

    public void BrowseLicenses() {
        getContentPane().removeAll();
        getContentPane().repaint();

        sortLicenses("name");

        c.add(new playground(1));
    }

    public void AdminBrowseLicenses() {
        getContentPane().removeAll();
        getContentPane().repaint();

        sortLicenses("name");

        c.add(new playground(2));
    }

    public void sortLicenses(String category) {
        for (License l : LDatabase) {
            heapL.add(l, category);
        }

        for (int i = 0; i < LDatabase.size(); i++) {
            License l = heapL.removeRoot(category);
            LDatabase.set(i, l);
            PDatabase.set(i, l.Person);
        }

    }

    public class playground extends JPanel implements ListSelectionListener {

        DefaultListModel listModel;
        DefaultComboBoxModel cbModel;
        JComboBox comboBox;
        JScrollPane listScrollPane;
        JList list;
        JLabel lblLCNum, lblExpDate, lblLast, lblFirst, lblAddress, lblDate21, lblRstr, lblAbc, lblMf, lblHgt, lblHClr, lblEClr, lblLb, lblPicture, lblSuspended, lblRevoked, lblTrInfPts;
        JTextArea textArea;
        JButton btnSave;

        public playground(int accessBy) {
            setLayout(null);
            setBounds(0, 0, 1000, 500);
            setBackground(new Color(240, 240, 240));

            cbModel = new DefaultComboBoxModel();
            comboBox = new JComboBox(cbModel);
            listModel = new DefaultListModel();
            list = new JList();
            listScrollPane = new JScrollPane();

            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);

            comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            comboBox.setEditable(true);
            comboBox.setBounds(50, 36, 150, 27);
            AutoCompletion.enable(comboBox);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int index = comboBox.getSelectedIndex();
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            });

            lblSuspended = new JLabel("");
            lblSuspended.setIcon(new ImageIcon(DMV.class.getResource("Resources/SUSPENDED.png")));
            lblSuspended.setBounds(300, 150, 400, 160);
            lblSuspended.setVisible(false);
            add(lblSuspended);

            lblRevoked = new JLabel("");
            lblRevoked.setHorizontalAlignment(SwingConstants.CENTER);
            lblRevoked.setIcon(new ImageIcon(DMV.class.getResource("Resources/REVOKED.png")));
            lblRevoked.setBounds(300, 120, 400, 160);
            lblRevoked.setVisible(false);
            add(lblRevoked);
            add(comboBox);


            JComboBox sortCBox = new JComboBox(new DefaultComboBoxModel(new String[]{"Name", "Lic Type", "Gender", "Hair Cl", "Eye Cl"}));
            sortCBox.setToolTipText("Sort By (Select a method):");
            sortCBox.setSelectedIndex(0);
            sortCBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            sortCBox.setEditable(false);
            sortCBox.setBounds(50, 6, 150, 27);
            sortCBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selected = sortCBox.getSelectedIndex();
                    switch (selected) {
                        case 0:
                            sortLicenses("name");
                            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);
                            comboBox.repaint();
                            listScrollPane.repaint();
                            updateLabels();
                            break;
                        case 1:
                            sortLicenses("ltype");
                            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);
                            comboBox.repaint();
                            listScrollPane.repaint();
                            updateLabels();
                            break;
                        case 2:
                            sortLicenses("gender");
                            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);
                            listScrollPane.repaint();
                            comboBox.repaint();
                            updateLabels();
                            break;
                        case 3:
                            sortLicenses("hairc");
                            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);
                            listScrollPane.repaint();
                            comboBox.repaint();
                            updateLabels();
                            break;
                        case 4:
                            sortLicenses("eyec");
                            setUpListModel(listModel, list, listScrollPane, cbModel, comboBox);
                            listScrollPane.repaint();
                            comboBox.repaint();
                            updateLabels();
                            break;
                    }
                }
            });
            add(sortCBox);
            add(comboBox);


            list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setVisibleRowCount(-1);
            list.addListSelectionListener(this);

            listScrollPane.setBounds(50, 75, 150, 300);
            add(listScrollPane);

            JPanel panel = new JPanel();
            panel.setBounds(240, 96, 510, 249);
            panel.setOpaque(false);
            add(panel);
            panel.setLayout(null);

            lblLCNum = new JLabel("#########");
            lblLCNum.setFont(new Font("Lucida Grande", Font.BOLD, 20));
            lblLCNum.setForeground(new Color(165, 42, 42));
            lblLCNum.setBounds(210, 6, 130, 26);
            panel.add(lblLCNum);

            lblExpDate = new JLabel("00/00/0000");
            lblExpDate.setFont(new Font("Lucida Grande", Font.BOLD, 15));
            lblExpDate.setForeground(new Color(165, 42, 42));
            lblExpDate.setBounds(220, 44, 120, 20);
            panel.add(lblExpDate);

            lblLast = new JLabel("Last");
            lblLast.setFont(new Font("Lucida Grande", Font.BOLD, 14));
            lblLast.setBounds(210, 70, 110, 16);
            panel.add(lblLast);

            lblFirst = new JLabel("First");
            lblFirst.setFont(new Font("Lucida Grande", Font.BOLD, 14));
            lblFirst.setBounds(210, 90, 110, 16);
            panel.add(lblFirst);

            lblAddress = new JLabel("<html>" + "1111 1ST STREET LOS ANGELES, CA 90000" + "</html>");
            lblAddress.setVerticalAlignment(SwingConstants.TOP);
            lblAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
            lblAddress.setBounds(185, 110, 150, 30);
            panel.add(lblAddress);

            lblDate21 = new JLabel("00/00/0000");
            lblDate21.setForeground(new Color(165, 42, 42));
            lblDate21.setFont(new Font("Lucida Grande", Font.BOLD, 15));
            lblDate21.setBounds(220, 140, 120, 20);
            panel.add(lblDate21);

            lblRstr = new JLabel("NONE");
            lblRstr.setBounds(225, 160, 61, 16);
            panel.add(lblRstr);

            lblAbc = new JLabel("A/B/C");
            lblAbc.setFont(new Font("Lucida Grande", Font.BOLD, 14));
            lblAbc.setBounds(427, 32, 61, 16);
            panel.add(lblAbc);

            lblMf = new JLabel("M/F");
            lblMf.setBounds(270, 205, 50, 16);
            panel.add(lblMf);

            lblHgt = new JLabel("H'-GT\"");
            lblHgt.setBounds(270, 221, 50, 16);
            panel.add(lblHgt);

            lblHClr = new JLabel("CLR");
            lblHClr.setBounds(360, 205, 50, 16);
            panel.add(lblHClr);

            lblEClr = new JLabel("CLR");
            lblEClr.setBounds(446, 205, 50, 16);
            panel.add(lblEClr);

            lblLb = new JLabel("000 lb");
            lblLb.setBounds(360, 221, 50, 16);
            panel.add(lblLb);

            lblPicture = new JLabel("");
            lblPicture.setIcon(new ImageIcon(DMV.class.getResource("Resources/CatPict.jpg")));
            lblPicture.setBounds(20, 0, 155, 210);
            panel.add(lblPicture);

            JLabel lblTrafficInfractionPts = new JLabel("INFRACTION PTS:");
            lblTrafficInfractionPts.setForeground(new Color(0, 0, 128));
            lblTrafficInfractionPts.setBounds(360, 136, 110, 16);
            panel.add(lblTrafficInfractionPts);

            lblTrInfPts = new JLabel("0");
            lblTrInfPts.setBounds(474, 136, 30, 16);
            panel.add(lblTrInfPts);

            JLabel label = new JLabel("");
            label.setIcon(new ImageIcon(DMV.class.getResource("Resources/dl.png")));
            label.setBounds(240, 36, 520, 339);
            add(label);

            JLabel lblNotes = new JLabel("Notes:");
            lblNotes.setFont(new Font("Times New Roman", Font.BOLD, 16));
            lblNotes.setBounds(800, 36, 61, 16);
            add(lblNotes);

            textArea = new JTextArea();
            textArea.setLineWrap(true);
            textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            textArea.setBounds(800, 64, 150, 311);
            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    btnSave.setEnabled(true);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    btnSave.setEnabled(true);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    btnSave.setEnabled(true);
                }
            });
            add(textArea);

            if (accessBy == 2) {
                JButton btnRemove = new JButton("Remove");
                btnRemove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int index = list.getSelectedIndex();
                        listModel.remove(index);
                        cbModel.removeElementAt(index);
                        PDatabase.remove(index);
                        LDatabase.remove(index);

                        int size = listModel.getSize();

                        if (size == 0) {
                            btnRemove.setEnabled(false);
                        } else {
                            if (index == listModel.getSize()) {
                                index--;
                            }
                            list.setSelectedIndex(index);
                            list.ensureIndexIsVisible(index);
                        }
                    }
                });
                btnRemove.setFont(new Font("Times New Roman", Font.PLAIN, 13));
                btnRemove.setBounds(50, 387, 70, 29);
                add(btnRemove);

                JButton btnAdd = new JButton("Add");
                btnAdd.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addEditLicenseDialogue dialog = new addEditLicenseDialogue(-1);
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                    }
                });
                btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 13));
                btnAdd.setBounds(150, 386, 50, 29);
                add(btnAdd);

                JButton btnEdit = new JButton("Edit...");
                btnEdit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addEditLicenseDialogue dialog = new addEditLicenseDialogue(list.getSelectedIndex());
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                    }
                });
                btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 13));
                btnEdit.setBounds(643, 387, 117, 29);
                add(btnEdit);
            }

            btnSave = new JButton("Save");
            btnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String s = textArea.getText();
                    LDatabase.get(list.getSelectedIndex()).Notes = s;
                    btnSave.setEnabled(false);
                }
            });
            btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 13));
            btnSave.setBounds(880, 386, 70, 29);
            btnSave.setEnabled(false);
            add(btnSave);
        }

        private void setUpListModel(DefaultListModel lm, JList l, JScrollPane sp, DefaultComboBoxModel cbm, JComboBox cb) {
            lm.clear();
            for (int i = 0; i < PDatabase.size(); i++) {
                lm.addElement(PDatabase.get(i).toString());
            }
            l.setModel(lm);
            l.setSelectedIndex(0);
            sp.setViewportView(l);

            cbm.removeAllElements();
            for (int i = 0; i < LDatabase.size(); i++) {
                cbm.addElement(LDatabase.get(i).Person.toString());
            }
            cb.setModel(cbm);
            cb.setSelectedIndex(0);
        }

        public void updateLabels() {
            int index = list.getSelectedIndex();
            Person p = PDatabase.get(index);
            License l = LDatabase.get(index);
            //lblPicture.setIcon(new ImageIcon(DMV.class.getResource("Resources/People/" + p.toString3() + ".jpeg")));
            lblLCNum.setText(l.DLNumber);
            lblExpDate.setText(format.format(l.ExpirationDate));
            lblLast.setText(p.lname);
            lblFirst.setText(p.fname);
            lblAddress.setText("<html>" + p.address + "</html>");
            lblDate21.setText(format.format(l.DateWhen21));
            lblRstr.setText(p.Restrictions);
            lblAbc.setText(String.valueOf(l.LicenseType));
            lblMf.setText(p.Gender);
            lblHgt.setText(p.Height);
            lblHClr.setText(p.HairColor);
            lblEClr.setText(p.EyesColor);
            lblLb.setText(p.Weight);
            lblTrInfPts.setText(String.valueOf(l.TViolationPoints));
            lblSuspended.setVisible(l.LicenseSuspended);
            lblRevoked.setVisible(l.LicenseRevoked);
            textArea.setText(l.Notes);
            btnSave.setEnabled(false);
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            // TODO Auto-generated method stub
            if (e.getValueIsAdjusting() == false) {
                if (list.getSelectedIndex() >= 0) {
                    cbModel.setSelectedItem(cbModel.getElementAt(list.getSelectedIndex()));
                    updateLabels();
                }
            }
        }
    }

    public class addEditLicenseDialogue extends JDialog {

        private final JPanel contentPanel = new JPanel();
        JCheckBox checkBox1, checkBox2;
        private JTextField textField, txtField;
        private JTextField txtMmddyyyy;
        private JTextField textField_1;
        private JTextField txtMf;
        private JTextField textField_2;
        private JTextField textField_3;
        private JTextField textField_4;
        private JTextField textField_5;
        private JTextField textField_6;
        private JTextField textField_7;
        private JTextField txtAbc;
        private JTextField textField_8;
        private JTextField textField_9;
        private JTextField textField_10;

        public addEditLicenseDialogue(int AddEdit) {
            setBounds(100, 100, 700, 400);
            getContentPane().setLayout(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            getContentPane().add(contentPanel, BorderLayout.CENTER);
            contentPanel.setLayout(null);
            {
                JLabel lblPersonalInformation = new JLabel("Personal Information");
                lblPersonalInformation.setBounds(9, 10, 131, 16);
                contentPanel.add(lblPersonalInformation);
            }
            {
                JLabel lblName = new JLabel("Last:");
                lblName.setBounds(187, 36, 40, 16);
                contentPanel.add(lblName);
            }
            {
                textField = new JTextField();
                textField.setBounds(231, 31, 104, 26);
                contentPanel.add(textField);
                textField.setColumns(10);
            }
            {
                JLabel lblFName = new JLabel("First:");
                lblFName.setBounds(371, 36, 31, 16);
                contentPanel.add(lblFName);
            }
            {
                txtField = new JTextField();
                txtField.setBounds(406, 31, 104, 26);
                contentPanel.add(txtField);
                txtField.setColumns(10);
            }
            {
                JLabel lblDob = new JLabel("DOB:");
                lblDob.setBounds(555, 36, 31, 16);
                contentPanel.add(lblDob);
            }
            {
                txtMmddyyyy = new JTextField();
                txtMmddyyyy.setBounds(590, 31, 104, 26);
                txtMmddyyyy.setText("MM/dd/yyyy");
                contentPanel.add(txtMmddyyyy);
                txtMmddyyyy.setColumns(10);
            }
            {
                JLabel lblGender = new JLabel("Gender:");
                lblGender.setBounds(545, 98, 48, 16);
                contentPanel.add(lblGender);
            }
            {
                txtMf = new JTextField();
                txtMf.setBounds(597, 93, 99, 26);
                txtMf.setText("M/F");
                contentPanel.add(txtMf);
                txtMf.setColumns(10);
            }
            {
                JLabel lblAddress = new JLabel("Address:");
                lblAddress.setBounds(172, 67, 55, 16);
                contentPanel.add(lblAddress);
            }
            {
                textField_1 = new JTextField();
                textField_1.setBounds(231, 62, 366, 26);
                contentPanel.add(textField_1);
                textField_1.setColumns(10);
            }
            {
                JLabel lblHairColor = new JLabel("Hair Color:");
                lblHairColor.setBounds(159, 98, 68, 16);
                contentPanel.add(lblHairColor);
            }
            {
                textField_2 = new JTextField();
                textField_2.setBounds(231, 93, 104, 26);
                contentPanel.add(textField_2);
                textField_2.setColumns(10);
            }
            {
                JLabel lblEyeColor = new JLabel("Eye Color:");
                lblEyeColor.setBounds(339, 98, 63, 16);
                contentPanel.add(lblEyeColor);
            }
            {
                textField_3 = new JTextField();
                textField_3.setBounds(406, 93, 104, 26);
                contentPanel.add(textField_3);
                textField_3.setColumns(10);
            }
            {
                JLabel lblHeight = new JLabel("Height:");
                lblHeight.setBounds(181, 129, 46, 16);
                contentPanel.add(lblHeight);
            }
            {
                textField_4 = new JTextField();
                textField_4.setBounds(231, 124, 104, 26);
                contentPanel.add(textField_4);
                textField_4.setColumns(10);
            }
            {
                JLabel lblWeight = new JLabel("Weight:");
                lblWeight.setBounds(355, 129, 47, 16);
                contentPanel.add(lblWeight);
            }
            {
                textField_5 = new JTextField();
                textField_5.setBounds(406, 124, 104, 26);
                contentPanel.add(textField_5);
                textField_5.setColumns(10);
            }
            {
                JLabel lblRestrictions = new JLabel("Restrictions:");
                lblRestrictions.setBounds(514, 129, 79, 16);
                contentPanel.add(lblRestrictions);
            }
            {
                textField_6 = new JTextField();
                textField_6.setBounds(597, 124, 99, 26);
                contentPanel.add(textField_6);
                textField_6.setColumns(10);
            }
            {
                JLabel lblLicenseData = new JLabel("License Data");
                lblLicenseData.setBounds(9, 155, 131, 16);
                contentPanel.add(lblLicenseData);
            }
            {
                JLabel lblDl = new JLabel("DL#:");
                lblDl.setBounds(198, 181, 29, 16);
                contentPanel.add(lblDl);
            }
            {
                textField_7 = new JTextField();
                textField_7.setBounds(231, 176, 104, 26);
                contentPanel.add(textField_7);
                textField_7.setColumns(10);
            }
            {
                JLabel lblType = new JLabel("Type:");
                lblType.setBounds(368, 181, 34, 16);
                contentPanel.add(lblType);
            }
            {
                txtAbc = new JTextField();
                txtAbc.setBounds(406, 176, 104, 26);
                txtAbc.setText("A/B/C");
                contentPanel.add(txtAbc);
                txtAbc.setColumns(10);
            }
            {
                JLabel lblDateExpires = new JLabel("Date Expires:");
                lblDateExpires.setBounds(144, 212, 83, 16);
                contentPanel.add(lblDateExpires);
            }
            {
                textField_8 = new JTextField();
                textField_8.setBounds(231, 207, 104, 26);
                contentPanel.add(textField_8);
                textField_8.setColumns(10);
            }
            {
                JLabel lblDate = new JLabel("Date 21:");
                lblDate.setBounds(349, 212, 53, 16);
                contentPanel.add(lblDate);
            }
            {
                textField_9 = new JTextField();
                textField_9.setBounds(406, 207, 104, 26);
                contentPanel.add(textField_9);
                textField_9.setColumns(10);
            }
            {
                JLabel lblTrafficViolationPoints = new JLabel("Traffic Violation Points:");
                lblTrafficViolationPoints.setBounds(144, 243, 258, 16);
                contentPanel.add(lblTrafficViolationPoints);
            }
            {
                textField_10 = new JTextField();
                textField_10.setBounds(406, 238, 104, 26);
                contentPanel.add(textField_10);
                textField_10.setColumns(10);
            }
            {
                JLabel lblSuspended = new JLabel("Suspended?:");
                lblSuspended.setBounds(144, 272, 83, 16);
                contentPanel.add(lblSuspended);
            }
            {
                checkBox1 = new JCheckBox("");
                checkBox1.setBounds(231, 269, 104, 23);
                contentPanel.add(checkBox1);
            }
            {
                JLabel lblRevoked = new JLabel("Revoked?:");
                lblRevoked.setBounds(339, 272, 63, 16);
                contentPanel.add(lblRevoked);
            }
            {
                checkBox2 = new JCheckBox("");
                checkBox2.setBounds(406, 269, 104, 23);
                contentPanel.add(checkBox2);
            }
            if (AddEdit != -1) {
                Person p = PDatabase.get(AddEdit);
                License l = LDatabase.get(AddEdit);
                txtField.setText(p.fname);
                textField.setText(p.lname);
                textField_1.setText(p.address);
                txtMmddyyyy.setText(p.DOB);
                textField_6.setText(p.Restrictions);
                txtMf.setText(p.Gender);
                textField_2.setText(p.HairColor);
                textField_3.setText(p.EyesColor);
                textField_4.setText(p.Height);
                textField_5.setText(p.Weight);

                textField_7.setText(l.DLNumber);
                txtAbc.setText(String.valueOf(l.LicenseType));
                textField_8.setText(format.format(l.ExpirationDate));
                textField_9.setText(format.format(l.DateWhen21));
                textField_10.setText(String.valueOf(l.TViolationPoints));
                checkBox1.setSelected(l.LicenseSuspended);
                checkBox2.setSelected(l.LicenseRevoked);
            }
            {
                JPanel buttonPane = new JPanel();
                buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
                getContentPane().add(buttonPane, BorderLayout.SOUTH);
                {
                    JButton okButton = new JButton("OK");
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (AddEdit == -1) {
                                if (alreadyExists()) {
                                    Toolkit.getDefaultToolkit().beep();
                                    textField.requestFocusInWindow();
                                    textField.selectAll();
                                } else {
                                    PDatabase.add(nPerson());

                                    LDatabase.add(nLicense());
                                    dispose();
                                    AdminBrowseLicenses();
                                }
                            } else {
                                int index = AddEdit;
                                PDatabase.remove(index);
                                PDatabase.add(index, nPerson());
                                LDatabase.remove(index);
                                LDatabase.add(index, nLicense());
                                dispose();
                                AdminBrowseLicenses();
                            }
                        }

                        private Person nPerson() {
                            Person p = new Person(txtField.getText(), textField.getText(), textField_1.getText(), txtMmddyyyy.getText(), textField_6.getText(), txtMf.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText());
                            return p;
                        }

                        private License nLicense() {
                            String a = textField_7.getText();
                            char b = txtAbc.getText().charAt(0);
                            Date c = null, d = null;
                            try {
                                c = format.parse(textField_8.getText());
                                d = format.parse(textField_9.getText());
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            int i = Integer.parseInt(textField_10.getText());
                            boolean f = checkBox1.isSelected();
                            boolean g = checkBox2.isSelected();
                            String h = " ";
                            License l = new License(nPerson(), a, b, c, d, i, f, g, h);
                            return l;
                        }

                        private Boolean alreadyExists() {
                            String fName = txtField.getText();
                            String lName = textField.getText();
                            for (int i = 0; i < PDatabase.size(); i++) {
                                if (lName.equalsIgnoreCase(PDatabase.get(i).lname))
                                    if (fName.equalsIgnoreCase(PDatabase.get(i).fname))
                                        return true;
                            }
                            return false;
                        }
                    });
                    okButton.setActionCommand("OK");
                    if (AddEdit != -1)
                        okButton.setText("Save");
                    buttonPane.add(okButton);
                    getRootPane().setDefaultButton(okButton);
                }
                {
                    JButton cancelButton = new JButton("Cancel");
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    cancelButton.setActionCommand("Cancel");
                    buttonPane.add(cancelButton);
                }
            }
        }

    }
}
