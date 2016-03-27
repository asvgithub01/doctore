package com.asvfactory.asv.doctore.viewModels;

import com.asvfactory.asv.doctore.model.Constants;
import com.asvfactory.asv.doctore.model.Training;
import com.asvfactory.asv.doctore.model.TrainingTable;
import com.asvfactory.asv.doctore.util.FileHelper;

import java.util.ArrayList;

/**
 * Created by Alberto on 27/03/2016.
 */ /*
Es el conjunto de diferentes entrenamientos
 */
public class TrainingTableVM {

    TrainingTable mModel;

    //region methods
    public void addItem(Training item) {
        if (mModel == null)
            mModel = new TrainingTable();

        if (mModel.getTrainings() == null)
            mModel.setTrainings(new ArrayList<Training>());

        mModel.getTrainings().add(item);
        this.save();

    }

    public void deleteItem(Training trainingItem) {

        //for (Exercise item : trainingItem.getTrainingExercises()) {
        TrainingVM trainingVM = new TrainingVM(trainingItem.getTrainingID());
        trainingVM.delete();//asv esta vaina es xa q borre el ejercicio de las stats

        this.mModel.getTrainings().remove(trainingItem);
    }

    private void save() {
        FileHelper.writeDoctoreFile(mModel, Constants.FILE_ALL_TRAININGS);
    }
    //endregion
}
