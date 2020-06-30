/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gantt;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author KevinB
 */
public class Cola {

    protected Node Cabecera;

    public Cola() {
        Cabecera = null;
    }

    public void insert(int llegada, int rafaga, String nom) {
        Node tmp = new Node(llegada, rafaga, nom);
        int l = longitud();
        if (l > 0) {
            Node aux1 = Cabecera;
            while (aux1.next != null) {
                aux1 = aux1.next;
            }
            aux1.setNext(tmp);
        } else {
            Cabecera = tmp;
        }

    }
    public void extraer(int x) {
        while (x > 0) {
            int l = longitud();
            if (l > 1) {
                Cabecera=Cabecera.next;
            }
            x--;
        }

    }

    public int longitud() {
        int cont = 0;
        Node tmp = Cabecera;
        while (tmp != null) {
            cont = cont + 1;
            tmp = tmp.getNext();
        }
        return cont;
    }

    public String imprimir() {
        String clientes = "";

        if (!isEmpty()) {
            Node tmp = Cabecera;
            while (tmp != null) {

                tmp = tmp.getNext();
            }
        }

        return clientes;
    }

    public boolean isEmpty() {
        if (Cabecera == null) {
            return true;
        } else {
            return false;
        }
    }
}
