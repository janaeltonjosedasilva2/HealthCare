package br.saojudas.mobile.healthcare.model;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Agendamento implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String nomeMedicamento;
    private String doseMedicamento;
    private String nomeMedico;
    private int frequenciaMedicamento;
    public String horaPrimeiraDose;

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
