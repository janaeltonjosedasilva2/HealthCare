package br.saojudas.mobile.healthcare.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CadastroUsuario.class,
        parentColumns = "id",
        childColumns = "idUsuario",
        onDelete = CASCADE),
        indices = {@Index(value = "idUsuario")})
public class Agendamento implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nomeMedicamento;
    private String doseMedicamento;
    private String nomeMedico;
    private int frequenciaMedicamento;
    public String horaPrimeiraDose;
    public int idUsuario;

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Agendamento() {
    }

    public int getFrequenciaMedicamento() {
        return frequenciaMedicamento;
    }

    public void setFrequenciaMedicamento(int frequenciaMedicamento) {
        this.frequenciaMedicamento = frequenciaMedicamento;
    }

    public String getHoraPrimeiraDose() {
        return horaPrimeiraDose;
    }

    public void setHoraPrimeiraDose(String horaPrimeiraDose) {
        this.horaPrimeiraDose = horaPrimeiraDose;
    }

//    public void setHoraPrimeiraDoseString(String horaPrimeiraDose){
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        try {
//            cal.setTime(sdf.parse(horaPrimeiraDose));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getHoraPrimeiraDoseString() {
//        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
//        return formatador.format(horaPrimeiraDose.getTime());
//    }
//
//    public Date getHoraPrimeiraDose() {
//        return horaPrimeiraDose;
//    }
//
//    public String getHoraPrimeiraDoseString() {
//        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
//        return formatador.format(horaPrimeiraDose.getTime());
//    }
//
//    public void setHoraPrimeiraDose(String horaPrimeiraDose) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm");
//
//        try {
//            this.horaPrimeiraDose = (Date) formatter.parse(horaPrimeiraDose);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setDoseMedicamento(String doseMedicamento) {
        this.doseMedicamento = doseMedicamento;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getDoseMedicamento() {
        return doseMedicamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
