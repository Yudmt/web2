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
    
    private final int SITUACAO_ESPERA = 0;
    private final int SITUACAO_ATENDIMENTO = 1;
    private final int SITUACAO_ATENDIDO = 2;
    private final int SITUACAO_CANCELADO = 3;
    
    public static final int ESTADO_RISCO = 0;
    public static final int ESTADO_CRITICO = 1;
    public static final int ESTADO_ESTAVEL = 2;
    
    @Expose private int id;
    @Expose private Paciente paciente;
    @Expose private String anamnese;
    @Expose private String data;
    @Expose private Atendente atendente;
    @Expose private int estado;
    private Medico medico;
    
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
        return anamnese;
    }

    public void setAnamnese(String Anamnese) {
        this.anamnese = Anamnese;
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
    
    public boolean isRisco () {
        return estado == ESTADO_RISCO;
    }
    
    public boolean isCritico () {
        return estado == ESTADO_CRITICO;
    }
    
    public boolean isEstavel () {
        return estado == ESTADO_ESTAVEL;
    }
}
