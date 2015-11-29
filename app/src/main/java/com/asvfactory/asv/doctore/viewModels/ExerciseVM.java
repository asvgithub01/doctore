package com.asvfactory.asv.doctore.viewModels;

import com.asvfactory.asv.doctore.model.Constants;
import com.asvfactory.asv.doctore.model.Exceptions;
import com.asvfactory.asv.doctore.model.Exercise;
import com.asvfactory.asv.doctore.model.Training;
import com.asvfactory.asv.doctore.model.TrainingStats;
import com.asvfactory.asv.doctore.model.TrainingTable;
import com.asvfactory.asv.doctore.util.FileHelper;

import java.util.ArrayList;

/**
 * Created by Alberto on 29/11/2015.
 * asv falta ver como impedir que se cree el vm si el modelo es null
 */
public class ExerciseVM {
    Exercise mModel;

    public ExerciseVM(Exercise model) {
        if(model!=null)
        this.mModel = model;
       /* else
           throw Exceptions.exceptionVMMNULL;
           */
    }

    //region methods
    //tiempo de ejercicio neto*repeticiones
    public long effortExercise1() {
        return (this.mModel.getDefaultSecondsExercise() - this.mModel.getDefaultSecondsRelax()) * this.mModel.getDefaultRepetitions();
    }

    //tiempo total / tiempo total relax
    public long effortExercise2() {
        return ((this.mModel.getDefaultSecondsExercise() + this.mModel.getDefaultSecondsRelax()) * this.mModel.getDefaultRepetitions()) / (this.mModel.getDefaultSecondsRelax() * this.mModel.getDefaultRepetitions());
    }

    //custom 1, de momento el q mas convence
    public long effortExercise3() {
        return ((this.mModel.getDefaultSecondsExercise() * 2) / this.mModel.getDefaultSecondsRelax()) + (this.mModel.getDefaultRepetitions() / Constants.MAX_REPETITIONS);
    }
    //endregion
}

public class TrainingVM {
    Training mModel;
    //region Methods
    public void save() {

    }

    public void load() {

    }

    public void addExerciseItem(Exercise exercise) {

    }

    public void addStatsItem(TrainingStats trainingStats) {

    }

    public void deleteItem(String ExerciseID) {
        //borrar de trainingExercises
        //y de trainingStats
    }

    //
    public double totalTrainingEffort(Constants.E_EFFORT effortEnum) {
        double totalReturn = 0;
        for (Exercise item : this.trainingExercises) {
            totalReturn += item.effortExercise3();
        }

        return totalReturn;
    }

    //endregion
}

public class TrainingStatsVM {
    TrainingStats mModel;
    //empty
}

public class TrainingTableVM {

    TrainingTable mModel;
    //region methods
    public void addItem(Training item) {
        if (trainings == null)
            trainings = new ArrayList<>();

        trainings.add(item);
        item.save();
        this.save();

    }

    public void deleteItem(Training trainingItem) {

        for (Exercise item : trainingItem.trainingExercises) {
            trainingItem.deleteItem(item.exerciseID);
        }
        this.trainings.remove(trainingItem);
    }

    private void save() {
        FileHelper.writeDoctoreFile(this, Constants.FILE_ALL_TRAININGS);
    }
    //endregion
}
