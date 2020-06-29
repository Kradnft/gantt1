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
    int personaActual;
    
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
            
            //Se genera un numero aleatorio
            Random aleatorio = new Random(System.currentTimeMillis()); 
            aleatorio.setSeed(System.currentTimeMillis());
            
            //Se inicializa el tiempo en cero
            int tiempo = 0;
            
            //Se crea una cola de clientes
            Cola clientes = new Cola();
            
            //Se le asigna una rafaga y un nombre aleatorio al primer cliente
            int rafaga = aleatorio.nextInt(8) + 3;
            int pNombre = aleatorio.nextInt(9);
            
            //Se inserta el primer cliente a la cola
            clientes.insert(tiempo, rafaga, nombres[pNombre]);
          
            //Mientras que haya clientes y el tiempo sea menor a 30
            while (clientes.longitud() != 0) {
                //Se generan los objetos de las tablas de info. y gantt para el cliente actual
                Object[] dataAuxInfo = new Object[7];
                Object[] dataGantt = new Object[30];
                
                //Se genera una nueva semilla aleatoria
                aleatorio.setSeed(System.currentTimeMillis());
                
                //Se guarda el tiempo de comienzo en el tiempo actual
                clientes.Cabecera.comienzo = tiempo;
                
                //Se setea la persona actual como la cabeza de la cola
                Node personaActual = clientes.Cabecera;
                
                //Se muestra que cliente será atendido y se suben sus primeros datos a la tabla de informacion
                System.out.println("Cliente que sera atendido:" + personaActual.nombre);
                dataAuxInfo[0] = personaActual.nombre;
                dataAuxInfo[1] = tiempo;
                dataAuxInfo[2] = personaActual.rafaga;
                dataAuxInfo[3] = personaActual.comienzo;               
                System.out.println("Numero de rafaga:" + personaActual.rafaga);
                
                
                //Se itera sobre el cliente actual hasta terminar su rafaga
                while (tiempo < personaActual.llegada + personaActual.rafaga) {
                    
                    //Se muestra la informacion
                    System.out.println("");
                    System.out.println("Estamos en el tiempo: " + tiempo);
                    System.out.println("Se esta atendiendo a " + personaActual.nombre);
                    
                    //En el tiempo actual se llena  esa unidad en el modelo de gantt
                    dataGantt[tiempo] = tiempo;
                    
                    //Llegada de un nuevo cliente de manera aleatoria en cada unidad de tiempo
                    int x = aleatorio.nextInt(2);
                    System.out.println("");
                    System.out.println("¿Llega un nuevo cliente?: (0 = No, 1 = Si) " + x);
                    System.out.println("");
                    if (x == 1) {
                        //Cabecera del mensaje
                        System.out.println("///////");
                        System.out.println("Llego un nuevo cliente");
                        
                        //Se le asigna una rafaga y un nombre aleatorio
                        int nuevoClientRagafa = aleatorio.nextInt(4) + 2;
                        int nuevoClientNombre = aleatorio.nextInt(9);
                        
                        //Se muestra la informacion del nuevo cliente
                        System.out.println("Nombre del nuevo cliente: " + nombres[nuevoClientNombre]);
                        System.out.println("Rafaga del nuevo cliente: " + nuevoClientRagafa);
                        
                        //Se inserta el nuevo cliente en la cola
                        clientes.insert(aleatorio.nextInt(30), nuevoClientRagafa, nombres[nuevoClientNombre]);
                        System.out.println("///////////");
                        System.out.println("");
                    }
                    
                    //Se aumenta el tiempo
                    tiempo++;
                    
                    System.out.println("Impresion de los clientes: " + clientes.imprimir());
                }
                
                personaActual.fin = tiempo;
                personaActual.retorno = personaActual.fin - personaActual.llegada;
                personaActual.espera = personaActual.retorno - personaActual.rafaga;
                
                dataAuxInfo[4] = personaActual.fin;
                dataAuxInfo[5] = personaActual.retorno;
                dataAuxInfo[6] = personaActual.espera;
                
                modelTbInfo.addRow(dataAuxInfo);
                modelTbGant.addRow(dataGantt);
                
                System.out.println("------------------------");
                System.out.println("Resumen de: " + personaActual.nombre);
                System.out.println("Llegada en: " + personaActual.llegada);
                System.out.println("Rafaga de: " + personaActual.rafaga);
                System.out.println("Comenzo a las: " + personaActual.comienzo);
                personaActual.fin = personaActual.rafaga + personaActual.comienzo;
                System.out.println("Tiempo final: " + personaActual.fin);
                personaActual.retorno = personaActual.fin - personaActual.llegada;
                System.out.println("Tiempo de retorno: " + personaActual.retorno);
                personaActual.espera = personaActual.retorno - personaActual.rafaga;
                System.out.println("Salio en: " + tiempo);
                System.out.println("------------------------");
                System.out.println("(Pausa incomoda para leer el resumen)");
                /*try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                clientes.extraer(1);

            }

        }
    }
    
}
