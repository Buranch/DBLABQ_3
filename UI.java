/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisness_company;

import java.awt.AWTEventMulticaster;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.Enumeration;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Biruk
 */
public class UI extends javax.swing.JFrame {

    /**
     * Creates new form UI
     */
    private JMenuItem addAssistant = new JMenuItem("Add Assistant");
    private JMenuItem addManager = new JMenuItem("Add Manager");
    private JPopupMenu menuPresidentPopup = new JPopupMenu();
    private JPopupMenu menuManagerPopup = new JPopupMenu();

    Employee boss = new Employee("CEO", 200000);
    DefaultMutableTreeNode currentRoot;
    Employee currentEmployee;

    public UI() {
        initComponents();
        boss = new Employee("CEO", 200000);
        Employee marketVp = new Employee("VP Marketing", 100000);
        Employee prodVP = new Employee("VP Production", 100000);

        // Preparing the Presidents
        boss.add(marketVp);
        boss.add(prodVP);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("CEO");
        addNodes(root, boss);
        menuManagerPopup.add(addAssistant);
        menuPresidentPopup.add(addManager);

        jScrollPane2.setComponentPopupMenu(menuPresidentPopup);
        jScrollPane2.setComponentPopupMenu(menuManagerPopup);

        JTree jTree = new JTree(root);

        addAssistant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "Enter Assistant Name ", TOOL_TIP_TEXT_KEY, HEIGHT);
                String salary = JOptionPane.showInputDialog(null, "Enter Salary ", TOOL_TIP_TEXT_KEY, HEIGHT);
                currentEmployee.add(new Assistance(name, Integer.parseInt(salary)));
                currentRoot.removeAllChildren();
                addNodes(currentRoot, currentEmployee);
                ((DefaultTreeModel) jTree.getModel()).reload(root);
            }
        });

        addManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "Enter Manager Name ", TOOL_TIP_TEXT_KEY, HEIGHT);
                String salary = JOptionPane.showInputDialog(null, "Enter Salary ", TOOL_TIP_TEXT_KEY, HEIGHT);
                currentEmployee.add(new Manager(name, Integer.parseInt(salary)));
                currentRoot.removeAllChildren();
                addNodes(currentRoot, currentEmployee);
                ((DefaultTreeModel) jTree.getModel()).reload(root);
            }
        });

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TreePath selPath = jTree.getPathForLocation(e.getX(), e.getY());
                String selectedTerm = null;
                try {
                    selectedTerm = selPath.getLastPathComponent().toString();

                } catch (Exception ee) {
                    System.out.println("Catched " + ee.getMessage());
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jTree.getClosestRowForLocation(e.getX(), e.getY());
                    jTree.setSelectionRow(row);
                    Employee fnd = boss.getChild(selectedTerm);
                    if(fnd == null){
                        return;
                    }
                    if (fnd.isManager()) {
                        menuManagerPopup.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        if(fnd.isAssistance()){
                            return;
                        }
                        menuPresidentPopup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

            }
        };

        jTree.addMouseListener(ml);
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                currentRoot = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                TreePath path = e.getPath();
                String selectedTerm = path.getLastPathComponent().toString();
                Employee fnd = boss.getChild(selectedTerm);
                currentEmployee = boss.getChild(selectedTerm);
                if (fnd != null) {
                    jLabel1.setText("" + fnd.getSalary());
                } else if (selectedTerm.equals("CEO")) {
                    jLabel1.setText("" + boss.getSalary());
                }
            }
        });
        jScrollPane2.setViewportView(jTree);

    }

    private void addNodes(DefaultMutableTreeNode pnode, Employee emp) {
        DefaultMutableTreeNode node;
        Enumeration e = emp.elements();
        while (e.hasMoreElements()) {
            Employee newEmp = (Employee) e.nextElement();
            node = new DefaultMutableTreeNode(newEmp.getName());
            pnode.add(node);
            addNodes(node, newEmp);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
