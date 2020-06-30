/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gantt;

/**
 *
 * @author KevinB
 */
public class Node {

    public int rafaga = 0;
    public int llegada = 0;
    public int comienzo = 0;
    public int fin = 0;
    public int retorno = 0;
    public int espera = 0;
    public String nombre;
    public Node next;

    public Node(int llegada, int raf, String nom) {
        rafaga = raf;
        next = null;
        nombre = nom;
        this.llegada = llegada;
    }

    public int getRafaga() {
        return rafaga;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public int getComienzo() {
        return comienzo;
    }

    public void setComienzo(int comienzo) {
        this.comienzo = comienzo;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
