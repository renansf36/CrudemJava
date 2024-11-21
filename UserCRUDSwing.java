import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserCRUDSwing extends JFrame {
    private List<User> users = new ArrayList<>();
    private int idCounter = 1;

    private DefaultTableModel tableModel;
    private JTable userTable;
    private JTextField nameField;
    private JTextField emailField;

    public UserCRUDSwing() {
        setTitle("CRUD de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        // Tabela
        String[] columnNames = {"ID", "Nome", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 20, 550, 200);
        add(scrollPane);

        // Campos de entrada
        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setBounds(20, 240, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 240, 200, 20);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 270, 80, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 270, 200, 20);
        add(emailField);

        // Botões
        JButton createButton = new JButton("Criar");
        createButton.setBounds(320, 240, 100, 30);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
        add(createButton);

        JButton updateButton = new JButton("Atualizar");
        updateButton.setBounds(430, 240, 100, 30);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        add(updateButton);

        JButton deleteButton = new JButton("Deletar");
        deleteButton.setBounds(320, 280, 100, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        add(deleteButton);

        JButton clearButton = new JButton("Limpar");
        clearButton.setBounds(430, 280, 100, 30);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(clearButton);

        setVisible(true);
    }

    private void createUser() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        User user = new User(idCounter++, name, email);
        users.add(user);
        tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getEmail()});
        clearFields();
        JOptionPane.showMessageDialog(this, "Usuário criado com sucesso.");
    }

    private void updateUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para atualizar.");
            return;
        }

        String name = nameField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        User user = findUserById(userId);

        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(email, selectedRow, 2);
            clearFields();
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso.");
        }
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para deletar.");
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        User user = findUserById(userId);

        if (user != null) {
            users.remove(user);
            tableModel.removeRow(selectedRow);
            clearFields();
            JOptionPane.showMessageDialog(this, "Usuário deletado com sucesso.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        userTable.clearSelection();
    }

    private User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new UserCRUDSwing();
    }
}
