/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kinds;

import com.google.gson.annotations.Expose;

/**
 *
 * @author jonasrg
 */
public class Agendamento {
    /*
    
    Paciente
    Anamnese (texto com os sintomas do paciente)
    Data/Hora que o agendamento foi feito
    atendente que fez o agendamento
    
    Falta implementar a SITUAÇÃO que pode assumir os seguintes estados
    - espera
    - em atendimento *
    - atendido *
    - cancelado *
    * deve-se armazenar o médico responsável
    
    O ESTADO DO PACIENTE que pode assumir os seguintes estados
    - 0 risco de vida
    - 1 estado crítico
    - 2 estável
    
    */
    
    @Expose private int id;
    @Expose private Paciente paciente;
    @Expose private String Anamnese;
    @Expose private String data;
    @Expose private Atendente atendente;
    @Expose private int estado;
    
    private String json;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getAnamnese() {
        return Anamnese;
    }

    public void setAnamnese(String Anamnese) {
        this.Anamnese = Anamnese;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
