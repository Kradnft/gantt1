/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gantt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class GUI implements ActionListener{
    
    JLabel lbTitulo;
    JTable tbInfo, tbGant;
    JButton btIniciar, btProcesoAleatorio;
    DefaultTableModel modelTbInfo, modelTbGant;
    JScrollPane spTablaInfo;
    
    String[] nombres = {"Matias Roca", "Julen Miguel", "Iluminada Gracia", "Felisa Montesinos", "Óscar Collado", "Ian Solana", "Serafin Mari", "Encarnacion del Moral", "Sebastiana Lin"};
    
    public JPanel Titulo() {

        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 0, 1280, 50);

        Border borderPanel = new TitledBorder(new EtchedBorder());
        Panel.setBorder(borderPanel);
        Panel.setBackground(new java.awt.Color(204, 166, 166));
        
        lbTitulo = new JLabel("Simulador de procesos FIFO",SwingConstants.CENTER);
        lbTitulo.setBounds(0, 0, 1280, 50);
        lbTitulo.setVisible(true);
        lbTitulo.setFont(new java.awt.Font("Cambria", 0, 29));
        Panel.add(lbTitulo);

        return Panel;
    }
    
    public JPanel Tabla() {
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 50, 1280, 300);
        Panel.setFont(new java.awt.Font("Cambria", 2, 11));
        //Panel.setBackground(new java.awt.Color(204, 0, 166));
        
        modelTbInfo = new DefaultTableModel();
        tbInfo = new JTable();
        tbInfo.setModel(modelTbInfo);
        
        modelTbInfo.addColumn("Proceso");
        modelTbInfo.addColumn("T. Llegada");
        modelTbInfo.addColumn("Rafaga");
        modelTbInfo.addColumn("T. Comienzo");
        modelTbInfo.addColumn("T. Final");
        modelTbInfo.addColumn("T. Retorno");
        modelTbInfo.addColumn("T. Espera");
        modelTbInfo.addRow(new Object[]{"Proceso", "T. Llegada", "Rafaga", "T. Comienzo", "T. Final", "T. Retorno", "T. Espera"});
        
        tbInfo.getTableHeader().setReorderingAllowed(false);
        tbInfo.setBounds(0, 0, 1280, 280);
        tbInfo.setVisible(true);
        
        tbInfo.setPreferredScrollableViewportSize(new Dimension(450,63));
        tbInfo.setFillsViewportHeight(true);
        
        Panel.add(tbInfo);
       
        return Panel;
    }
    
    public JPanel Gant(){
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 340, 1280, 280);
        Panel.setFont(new java.awt.Font("Cambria", 2, 11));
        Panel.setBackground(new java.awt.Color(204, 166, 166));

        modelTbGant = new DefaultTableModel();
        tbGant = new JTable();
        tbGant.setModel(modelTbGant);
        
        Object[] data = new Object[30];
        for (int i=0;i<30;i++){
            modelTbGant.addColumn(i);
            data[i] = i;
        }
                   
        modelTbGant.addRow(data);
        
        tbGant.getTableHeader().setReorderingAllowed(false);
        tbGant.setBounds(0, 10, 1280, 280);
        tbGant.setVisible(true);
        
        tbGant.setPreferredScrollableViewportSize(new Dimension(450,63));
        tbGant.setFillsViewportHeight(true);
        
        Panel.add(tbGant);
        
        return Panel;
    }
    
    public JPanel Botonera(){
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 620, 1280, 90);
        Panel.setFont(new java.awt.Font("Cambria", 2, 11));
        Panel.setBackground(new java.awt.Color(204, 166, 0));
        
        btIniciar = new JButton("Iniciar simulación");
        btIniciar.setBounds(200, 10, 200, 45);
        btIniciar.setVisible(true);
        btIniciar.addActionListener(this);
        Panel.add(btIniciar);
        Panel.setBackground(new java.awt.Color(198, 198, 198));
        
        btProcesoAleatorio = new JButton("Insertar proceso aleatorio");
        btProcesoAleatorio.setBounds(850, 10, 200, 45);
        btProcesoAleatorio.setVisible(true);
        btProcesoAleatorio.addActionListener(this);
        Panel.add(btProcesoAleatorio);
        Panel.setBackground(new java.awt.Color(198, 198, 198));

        return Panel;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btIniciar) {
            System.out.println("Boton iniciar");
            Random aleatorio = new Random(System.currentTimeMillis()); 
            aleatorio.setSeed(System.currentTimeMillis());

            boolean disponible = true;
            int tiempo = 0;
            Cola clientes = new Cola();
            int rafaga = aleatorio.nextInt(8) + 3;
            int pNombre = aleatorio.nextInt(9);
            
            clientes.insert(tiempo, rafaga, nombres[pNombre]);
            
            while (clientes.longitud()!=0 && tiempo<30){
            aleatorio.setSeed(System.currentTimeMillis());
                try {
                    Thread.sleep(2000); //Pausara 2 segundos
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            clientes.Cabecera.comienzo=tiempo;
            Node personaActual = clientes.Cabecera;
            System.out.println("Cliente que sera atendido:"+personaActual.nombre );
            System.out.println("Numero de rafaga:"+personaActual.rafaga);
            int auxiliar =personaActual.rafaga;
            while (auxiliar>0){
                try {
                    Thread.sleep(2000); //Pausara 2 segundos
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Estamos en el "+tiempo);
                System.out.println("Se esta atendiendo a "+personaActual.nombre);
                tiempo++;
                auxiliar--;
                int x = aleatorio.nextInt(6);
                if (x==4){
                    System.out.println("///////");
                    System.out.println("Llego un nuevo cliente");
                    int prafa = aleatorio.nextInt(4)+2;
                    int pombre = aleatorio.nextInt(9);
                    System.out.println("Rafagas: "+prafa+" nombre: "+nombres[pombre] );
                    clientes.insert(tiempo-1, prafa, nombres[pombre]);
                    System.out.println("///////////");
                }
                
            }
            System.out.println("------------------------");
            System.out.println("Resumen de: " + personaActual.nombre);
            System.out.println("Llegada en: " + personaActual.llegada);
            System.out.println("Rafaga de: "+ personaActual.rafaga);
            System.out.println("Comenzo a las: " + personaActual.comienzo);
            personaActual.fin=personaActual.rafaga+personaActual.comienzo;
            System.out.println("Tiempo final: "+ personaActual.fin);
            personaActual.retorno=personaActual.fin-personaActual.llegada;
            System.out.println("Tiempo de retorno: "+ personaActual.retorno);
            personaActual.espera= personaActual.retorno-personaActual.rafaga;
            System.out.println("Salio en: " + tiempo);
            System.out.println("------------------------");
            System.out.println("(Pausa incomoda para leer el resumen)");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            clientes.extraer(1);
            
            
        }
            
        }
    }
    
}
