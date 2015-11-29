package com.asvfactory.asv.doctore.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alberto on 27/11/2015.
 * asv falta:
 * trasladar todo lo q son metodos al ViewModel
 * generar getter/setter exercise Order
 * generar constructor
 * hacer q sea @Bindable, o no y simplemente oneway
 */

//para guardar cuantas veces y que ejercicio se ha realizado
// tb se guarda el ejercicio realizado real(en el momento en el que se cumplen las repeticiones realizadas)
//se guarda el id del exercise base y tb se guarda el exercise si difiere en algo del base
// cuando se ha realizado el Training
public class TrainingStats implements Serializable {
    Date trainingDate;
    Exercise exerciseDone;//si es null es xq es igual que el base y si tiene algo es que cuando se cargó se modificó alguno de los campos
    String baseExerciseID;

    //region getter/setter
    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Exercise getExerciseDone() {
        return exerciseDone;
    }

    public void setExerciseDone(Exercise exerciseDone) {
        this.exerciseDone = exerciseDone;
    }

    public String getBaseExerciseID() {
        return baseExerciseID;
    }

    public void setBaseExerciseID(String baseExerciseID) {
        this.baseExerciseID = baseExerciseID;
    }
    //endregion
}
