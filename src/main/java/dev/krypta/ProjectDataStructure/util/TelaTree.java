package dev.krypta.ProjectDataStructure.util;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaTree extends JFrame {
    private TreeNode treeNode;
    private JTextArea outputArea;
    private JTextField nomeItemField;
    private JTextField nomeFormulaField;
    private JTextField quantidadeField;
    private JTextField valorField;
    private JTextField idFormulaField;
    private JTextField buscarItemField;
    private JTextField deletarItemField;
    private JTextArea listaItensArea;
    private DefaultListModel<String> itensListModel;
    private JList<String> itensList;
    private List<ItemFormula> itensTemporarios;

    public TelaTree() {
        itensTemporarios = new ArrayList<>();
        initializeComponents();
        setupLayout();
        setupActions();
    }

    private void initializeComponents() {
        setTitle("Gerenciador de Árvore de Fórmulas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);

        nomeItemField = new JTextField(15);
        nomeFormulaField = new JTextField(15);
        quantidadeField = new JTextField(15);
        valorField = new JTextField(15);
        idFormulaField = new JTextField(15);
        buscarItemField = new JTextField(15);
        deletarItemField = new JTextField(15);
        listaItensArea = new JTextArea(10, 30);
        listaItensArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));

        itensListModel = new DefaultListModel<>();
        itensList = new JList<>(itensListModel);
        itensList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Painel principal com duas colunas
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Painel esquerdo - Entrada de dados
        JPanel leftPanel = createInputPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Painel central - Área de saída e lista
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Painel direito - Operações
        JPanel rightPanel = createOperationsPanel();
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Barra de status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Status: Pronto"));
        add(statusPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Adicionar Item à Lista"));

        // Campos de entrada
        panel.add(createLabeledField("Nome do Item:", nomeItemField));
        panel.add(Box.createVerticalStrut(5));
        panel.add(createLabeledField("Nome da Fórmula Pai:", nomeFormulaField));
        panel.add(Box.createVerticalStrut(5));
        panel.add(createLabeledField("Quantidade:", quantidadeField));
        panel.add(Box.createVerticalStrut(5));
        panel.add(createLabeledField("Valor:", valorField));
        panel.add(Box.createVerticalStrut(5));
        panel.add(createLabeledField("ID Fórmula:", idFormulaField));
        panel.add(Box.createVerticalStrut(10));

        // Botões
        JButton adicionarBtn = new JButton("Adicionar Item");
        adicionarBtn.addActionListener(e -> adicionarItem());
        panel.add(adicionarBtn);

        panel.add(Box.createVerticalStrut(5));

        JButton limparBtn = new JButton("Limpar Lista");
        limparBtn.addActionListener(e -> limparLista());
        panel.add(limparBtn);

        panel.add(Box.createVerticalStrut(10));

        // Lista de itens adicionados
        JLabel listaLabel = new JLabel("Itens Adicionados:");
        panel.add(listaLabel);

        JScrollPane scrollLista = new JScrollPane(itensList);
        scrollLista.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollLista);

        panel.add(Box.createVerticalStrut(10));

        // Botão criar árvore
        JButton criarArvoreBtn = new JButton("Criar Árvore");
        criarArvoreBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        criarArvoreBtn.setBackground(new Color(76, 175, 80));
        criarArvoreBtn.setForeground(Color.WHITE);
        criarArvoreBtn.addActionListener(e -> criarArvore());
        panel.add(criarArvoreBtn);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Visualização da Árvore"));

        JScrollPane scrollOutput = new JScrollPane(outputArea);
        scrollOutput.setPreferredSize(new Dimension(500, 600));
        panel.add(scrollOutput, BorderLayout.CENTER);

        // Botões de visualização
        JPanel viewPanel = new JPanel(new FlowLayout());
        JButton mostrarArvoreBtn = new JButton("Mostrar Árvore");
        mostrarArvoreBtn.addActionListener(e -> mostrarArvore());
        viewPanel.add(mostrarArvoreBtn);

        JButton calcularTotalBtn = new JButton("Calcular Total");
        calcularTotalBtn.addActionListener(e -> calcularTotal());
        viewPanel.add(calcularTotalBtn);

        panel.add(viewPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOperationsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new TitledBorder("Operações"));

        // Buscar nó
        JPanel buscarPanel = new JPanel(new BorderLayout());
        buscarPanel.setBorder(new TitledBorder("Buscar Nó"));
        buscarPanel.add(createLabeledField("Nome do Item:", buscarItemField), BorderLayout.NORTH);
        JButton buscarBtn = new JButton("Buscar");
        buscarBtn.addActionListener(e -> buscarNode());
        buscarPanel.add(buscarBtn, BorderLayout.CENTER);
        panel.add(buscarPanel);

        panel.add(Box.createVerticalStrut(10));

        // Deletar nó
        JPanel deletarPanel = new JPanel(new BorderLayout());
        deletarPanel.setBorder(new TitledBorder("Deletar Nó"));
        deletarPanel.add(createLabeledField("Nome do Item:", deletarItemField), BorderLayout.NORTH);
        JButton deletarBtn = new JButton("Deletar");
        deletarBtn.setBackground(new Color(244, 67, 54));
        deletarBtn.setForeground(Color.WHITE);
        deletarBtn.addActionListener(e -> deletarNode());
        deletarPanel.add(deletarBtn, BorderLayout.CENTER);
        panel.add(deletarPanel);

        panel.add(Box.createVerticalStrut(10));

        // Atualizar nó
        JPanel atualizarPanel = new JPanel();
        atualizarPanel.setLayout(new BoxLayout(atualizarPanel, BoxLayout.Y_AXIS));
        atualizarPanel.setBorder(new TitledBorder("Atualizar Nó"));

        JTextField atualizarNomeItemField = new JTextField(15);
        JTextField atualizarQuantidadeField = new JTextField(15);
        JTextField atualizarValorField = new JTextField(15);

        atualizarPanel.add(createLabeledField("Nome do Item:", atualizarNomeItemField));
        atualizarPanel.add(Box.createVerticalStrut(5));
        atualizarPanel.add(createLabeledField("Nova Quantidade:", atualizarQuantidadeField));
        atualizarPanel.add(Box.createVerticalStrut(5));
        atualizarPanel.add(createLabeledField("Novo Valor:", atualizarValorField));
        atualizarPanel.add(Box.createVerticalStrut(10));

        JButton atualizarBtn = new JButton("Atualizar");
        atualizarBtn.setBackground(new Color(255, 152, 0));
        atualizarBtn.setForeground(Color.WHITE);
        atualizarBtn.addActionListener(e -> atualizarNode(
            atualizarNomeItemField.getText(),
            atualizarQuantidadeField.getText(),
            atualizarValorField.getText()
        ));
        atualizarPanel.add(atualizarBtn);

        panel.add(atualizarPanel);

        panel.add(Box.createVerticalStrut(10));

        // Botão limpar árvore
        JButton limparArvoreBtn = new JButton("Limpar Árvore");
        limparArvoreBtn.addActionListener(e -> limparArvore());
        panel.add(limparArvoreBtn);

        return panel;
    }

    private JPanel createLabeledField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void setupActions() {
        // Enter nos campos numéricos
        quantidadeField.addActionListener(e -> adicionarItem());
        valorField.addActionListener(e -> adicionarItem());
    }

    private void adicionarItem() {
        try {
            String nomeItem = nomeItemField.getText().trim();
            String nomeFormula = nomeFormulaField.getText().trim();
            String quantidadeStr = quantidadeField.getText().trim();
            String valorStr = valorField.getText().trim();
            String idFormula = idFormulaField.getText().trim();

            if (nomeItem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome do item é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Integer quantidade = quantidadeStr.isEmpty() ? 1 : Integer.parseInt(quantidadeStr);
            Double valor = valorStr.isEmpty() ? 0.0 : Double.parseDouble(valorStr);
            String nomeFormulaFinal = nomeFormula.isEmpty() ? null : nomeFormula;

            ItemFormula item = new ItemFormula(nomeItem, idFormula, nomeFormulaFinal, quantidade, valor);
            itensTemporarios.add(item);
            atualizarLista();

            // Limpar campos
            nomeItemField.setText("");
            nomeFormulaField.setText("");
            quantidadeField.setText("");
            valorField.setText("");
            idFormulaField.setText("");
            nomeItemField.requestFocus();

            appendOutput("Item adicionado: " + nomeItem + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao converter número: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarLista() {
        itensListModel.clear();
        for (ItemFormula item : itensTemporarios) {
            String display = String.format("%s (Pai: %s, Qtd: %d, Valor: %.2f)",
                item.getNomeItem(),
                item.getNomeFormula() == null ? "RAIZ" : item.getNomeFormula(),
                item.getQuantidade(),
                item.getValor());
            itensListModel.addElement(display);
        }
    }

    private void limparLista() {
        itensTemporarios.clear();
        atualizarLista();
        appendOutput("Lista de itens limpa.\n");
    }

    private void criarArvore() {
        if (itensTemporarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione pelo menos um item antes de criar a árvore!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            treeNode = TreeNode.buildTree(itensTemporarios);
            appendOutput("\n=== Árvore criada com sucesso! ===\n");
            mostrarArvore();
            calcularTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar árvore: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            appendOutput("Erro: " + e.getMessage() + "\n");
        }
    }

    private void mostrarArvore() {
        if (treeNode == null || treeNode.getRaiz() == null) {
            appendOutput("Árvore está vazia.\n");
            return;
        }

        outputArea.setText("");
        appendOutput("=== Estrutura da Árvore ===\n\n");
        String treeString = treeToString(treeNode.getRaiz(), 0);
        appendOutput(treeString);
        appendOutput("\n");
    }

    private String treeToString(TreeNode.Node node, int level) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }

        ItemFormula item = node.getItemFormula();
        sb.append(String.format("%s└─ %s\n", indent.toString(), item.getNomeItem()));
        sb.append(String.format("%s   Qtd: %d | Valor: R$ %.2f\n", indent.toString(), item.getQuantidade(), item.getValor()));

        for (TreeNode.Node child : node.getItemFormulaList()) {
            sb.append(treeToString(child, level + 1));
        }

        return sb.toString();
    }

    private void calcularTotal() {
        if (treeNode == null || treeNode.getRaiz() == null) {
            appendOutput("Árvore está vazia. Crie uma árvore primeiro.\n");
            return;
        }

        Double total = TreeNode.calcularTotal(treeNode);
        appendOutput(String.format("\n=== Valor Total: R$ %.2f ===\n\n", total));
    }

    private void buscarNode() {
        String nomeItem = buscarItemField.getText().trim();
        if (nomeItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do item a ser buscado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (treeNode == null || treeNode.getRaiz() == null) {
            appendOutput("Árvore está vazia. Crie uma árvore primeiro.\n");
            return;
        }

        TreeNode.Node node = TreeNode.findNode(treeNode.getRaiz(), nomeItem);
        if (node != null) {
            ItemFormula item = node.getItemFormula();
            appendOutput(String.format("\n=== Nó encontrado ===\n"));
            appendOutput(String.format("Nome: %s\n", item.getNomeItem()));
            appendOutput(String.format("Quantidade: %d\n", item.getQuantidade()));
            appendOutput(String.format("Valor: R$ %.2f\n", item.getValor()));
            appendOutput(String.format("Nome Fórmula: %s\n", item.getNomeFormula() == null ? "RAIZ" : item.getNomeFormula()));
            appendOutput(String.format("ID Fórmula: %s\n", item.getIdFormula() == null ? "N/A" : item.getIdFormula()));
            appendOutput(String.format("Número de filhos: %d\n\n", node.getItemFormulaList().size()));
        } else {
            appendOutput(String.format("Nó '%s' não encontrado na árvore.\n\n", nomeItem));
            JOptionPane.showMessageDialog(this, "Nó não encontrado!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deletarNode() {
        String nomeItem = deletarItemField.getText().trim();
        if (nomeItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do item a ser deletado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (treeNode == null || treeNode.getRaiz() == null) {
            appendOutput("Árvore está vazia. Crie uma árvore primeiro.\n");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja deletar o nó '" + nomeItem + "'?\n" +
            "Todos os filhos também serão removidos!",
            "Confirmação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            boolean deletado = TreeNode.deleteNode(treeNode, nomeItem);
            if (deletado) {
                appendOutput(String.format("Nó '%s' deletado com sucesso!\n\n", nomeItem));
                mostrarArvore();
                calcularTotal();
            } else {
                appendOutput(String.format("Nó '%s' não encontrado para deleção.\n\n", nomeItem));
                JOptionPane.showMessageDialog(this, "Nó não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            deletarItemField.setText("");
        }
    }

    private void atualizarNode(String nomeItem, String quantidadeStr, String valorStr) {
        if (nomeItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do item a ser atualizado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (treeNode == null || treeNode.getRaiz() == null) {
            appendOutput("Árvore está vazia. Crie uma árvore primeiro.\n");
            return;
        }

        try {
            ItemFormula itemUpdate = new ItemFormula();
            if (!quantidadeStr.isEmpty()) {
                itemUpdate.setQuantidade(Integer.parseInt(quantidadeStr));
            }
            if (!valorStr.isEmpty()) {
                itemUpdate.setValor(Double.parseDouble(valorStr));
            }

            boolean atualizado = TreeNode.updateNode(treeNode, nomeItem, itemUpdate);
            if (atualizado) {
                appendOutput(String.format("Nó '%s' atualizado com sucesso!\n\n", nomeItem));
                mostrarArvore();
                calcularTotal();
            } else {
                appendOutput(String.format("Nó '%s' não encontrado para atualização.\n\n", nomeItem));
                JOptionPane.showMessageDialog(this, "Nó não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao converter número: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparArvore() {
        treeNode = null;
        outputArea.setText("");
        appendOutput("Árvore limpa. Crie uma nova árvore.\n");
    }

    private void appendOutput(String text) {
        outputArea.append(text);
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TelaTree tela = new TelaTree();
            tela.setVisible(true);
        });
    }
}
