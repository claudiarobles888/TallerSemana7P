package avengers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AvengerIG {
    private JPanel Ventana;
    private JLabel Titulo;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtMision;
    private JComboBox cbxPeligrosidad;
    private JTextField txtPago;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnLimpiar;
    private JTable ID;
    private JButton btnActualizar;
    private JPanel tabAvengers;
    private JLabel Informe;
    private JTextArea txtAInforme;
    private JButton btnInforme;
    private JButton btnIIndividual;
    private JTextArea txtAListado;

    private AvengersManager manager = new AvengersManager();

    public AvengerIG() {

            btnRegistrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String id = txtID.getText().trim();
                        String nombre = txtNombre.getText().trim();
                        String mision = txtMision.getText().trim();
                        int peligrosidad = Integer.parseInt(cbxPeligrosidad.getSelectedItem().toString());
                        double pagoMensual = Double.parseDouble(txtPago.getText().trim());

                        if (id.isEmpty() || nombre.isEmpty() || mision.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                            return;
                        }

                        if (pagoMensual <= 0) {
                            JOptionPane.showMessageDialog(null, "El pago mensual debe ser mayor a 0");
                            return;
                        }

                        Avengers avenger = new Avengers(id, nombre, mision, peligrosidad, pagoMensual);

                        if (manager.registrarAvenger(avenger)) {
                            JOptionPane.showMessageDialog(null, "Avenger registrado exitosamente");
                            txtID.setText("");
                            txtNombre.setText("");
                            txtMision.setText("");
                            cbxPeligrosidad.setSelectedIndex(0);
                            txtPago.setText("");
                            txtID.requestFocus();

                            txtAListado.setText("");
                            ArrayList<Avengers> avengers = manager.listarAvengers();
                            for (Avengers av : avengers) {
                                txtAListado.append("ID: " + av.getId() + "\n");
                                txtAListado.append("Nombre: " + av.getNombre() + "\n");
                                txtAListado.append("Misión: " + av.getMision() + "\n");
                                txtAListado.append("Peligrosidad: " + av.getNivelPeligrosidad() + "\n");
                                txtAListado.append("Pago Mensual: $" + String.format("%.2f", av.getPagoMensual()) + "\n");
                                txtAListado.append("─────────────────────────────────────\n");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un Avenger con ese ID");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error: Verifique que los datos numéricos sean correctos");
                    }
                }
            });


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtID.getText().trim();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID para buscar");
                    return;
                }

                Avengers avenger = manager.buscarAvenger(id);

                if (avenger != null) {
                    txtNombre.setText(avenger.getNombre());
                    txtMision.setText(avenger.getMision());
                    cbxPeligrosidad.setSelectedItem(avenger.getNivelPeligrosidad());
                    txtPago.setText(String.valueOf(avenger.getPagoMensual()));
                    JOptionPane.showMessageDialog(null, "Avenger encontrado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un Avenger con el ID: " + id);
                }
            }
        });


        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = txtID.getText().trim();
                    String nombre = txtNombre.getText().trim();
                    String mision = txtMision.getText().trim();
                    int peligrosidad = Integer.parseInt(cbxPeligrosidad.getSelectedItem().toString());
                    double pagoMensual = Double.parseDouble(txtPago.getText().trim());

                    if (id.isEmpty() || nombre.isEmpty() || mision.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                        return;
                    }

                    if (manager.modificarAvenger(id, nombre, mision, peligrosidad, pagoMensual)) {
                        JOptionPane.showMessageDialog(null, "Avenger modificado exitosamente");
                        txtID.setText("");
                        txtNombre.setText("");
                        txtMision.setText("");
                        cbxPeligrosidad.setSelectedIndex(0);
                        txtPago.setText("");
                        txtID.requestFocus();

                        txtAListado.setText("");
                        ArrayList<Avengers> avengers = manager.listarAvengers();
                        for (Avengers av : avengers) {
                            txtAListado.append("ID: " + av.getId() + "\n");
                            txtAListado.append("Nombre: " + av.getNombre() + "\n");
                            txtAListado.append("Misión: " + av.getMision() + "\n");
                            txtAListado.append("Peligrosidad: " + av.getNivelPeligrosidad() + "\n");
                            txtAListado.append("Pago Mensual: $" + String.format("%.2f", av.getPagoMensual()) + "\n");
                            txtAListado.append("─────────────────────────────────────\n");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un Avenger con el ID: " + id);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Verifique que los datos numéricos sean correctos");
                }
            }
        });


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtID.setText("");
                txtNombre.setText("");
                txtMision.setText("");
                cbxPeligrosidad.setSelectedIndex(0);
                txtPago.setText("");
                txtID.requestFocus();
            }
        });


        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAListado.setText("");
                ArrayList<Avengers> avengers = manager.listarAvengers();

                if (avengers.isEmpty()) {
                    txtAListado.setText("No hay Avengers registrados.");
                    return;
                }

                for (Avengers avenger : avengers) {
                    txtAListado.append("ID: " + avenger.getId() + "\n");
                    txtAListado.append("Nombre: " + avenger.getNombre() + "\n");
                    txtAListado.append("Misión: " + avenger.getMision() + "\n");
                    txtAListado.append("Peligrosidad: " + avenger.getNivelPeligrosidad() + "\n");
                    txtAListado.append("Pago Mensual: $" + String.format("%.2f", avenger.getPagoMensual()) + "\n");
                    txtAListado.append("─────────────────────────────────────\n");
                }
            }
        });


        btnInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Avengers> avengers = manager.listarAvengers();

                if (avengers.isEmpty()) {
                    txtAInforme.setText("No hay Avengers registrados para generar el informe.");
                    return;
                }

                StringBuilder informe = new StringBuilder();
                informe.append("═══════════════════════════════════════════════════════════════\n");
                informe.append("              INFORME FINANCIERO DE AVENGERS\n");
                informe.append("═══════════════════════════════════════════════════════════════\n\n");

                for (Avengers avenger : avengers) {
                    informe.append("───────────────────────────────────────────────────────────────\n");
                    informe.append(String.format("NOMBRE: %s (ID: %s)\n", avenger.getNombre(), avenger.getId()));
                    informe.append(String.format("MISIÓN: %s\n", avenger.getMision()));
                    informe.append(String.format("NIVEL DE PELIGROSIDAD: %d\n", avenger.getNivelPeligrosidad()));
                    informe.append("───────────────────────────────────────────────────────────────\n");
                    informe.append(String.format("Pago Mensual:              $%,15.2f\n", avenger.getPagoMensual()));
                    informe.append(String.format("Pago Anual:                $%,15.2f\n", avenger.calcularPagoAnual()));
                    informe.append(String.format("Aporte al Fondo (8%% mes):  $%,15.2f\n", avenger.calcularAporteFondo()));
                    informe.append(String.format("Aporte Anual al Fondo:     $%,15.2f\n", avenger.calcularAporteFondo() * 12));
                    informe.append(String.format("Impuesto del Gobierno:     $%,15.2f\n", avenger.calcularImpuesto()));
                    informe.append(String.format("PAGO NETO ANUAL:           $%,15.2f\n", avenger.calcularPagoNeto()));
                    informe.append("───────────────────────────────────────────────────────────────\n\n");
                }
                txtAInforme.setText(informe.toString());
            }
        });


        btnIIndividual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(null, "Ingrese el ID del Avenger:");

                if (id == null || id.trim().isEmpty()) {
                    return;
                }

                Avengers avenger = manager.buscarAvenger(id.trim());

                if (avenger == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró un Avenger con el ID: " + id);
                    return;
                }

                StringBuilder informe = new StringBuilder();
                informe.append("═══════════════════════════════════════════════════════════════\n");
                informe.append("              INFORME FINANCIERO DEL AVENGER\n");
                informe.append("═══════════════════════════════════════════════════════════════\n\n");
                informe.append(String.format("NOMBRE: %s (ID: %s)\n", avenger.getNombre(), avenger.getId()));
                informe.append(String.format("MISIÓN: %s\n", avenger.getMision()));
                informe.append(String.format("NIVEL DE PELIGROSIDAD: %d\n", avenger.getNivelPeligrosidad()));
                informe.append("───────────────────────────────────────────────────────────────\n");
                informe.append(String.format("Pago Mensual:              $%,15.2f\n", avenger.getPagoMensual()));
                informe.append(String.format("Pago Anual:                $%,15.2f\n", avenger.calcularPagoAnual()));
                informe.append(String.format("Aporte al Fondo (8%% mes):  $%,15.2f\n", avenger.calcularAporteFondo()));
                informe.append(String.format("Aporte Anual al Fondo:     $%,15.2f\n", avenger.calcularAporteFondo() * 12));
                informe.append(String.format("Impuesto del Gobierno:     $%,15.2f\n", avenger.calcularImpuesto()));
                informe.append(String.format("PAGO NETO ANUAL:           $%,15.2f\n", avenger.calcularPagoNeto()));
                informe.append("═══════════════════════════════════════════════════════════════\n");

                txtAInforme.setText(informe.toString());
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Gestión de Avengers");
        frame.setContentPane(new AvengerIG().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


