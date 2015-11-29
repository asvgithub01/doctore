package com.asvfactory.asv.doctore.model;

import android.transition.Visibility;

import com.asvfactory.asv.doctore.util.FileHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*asv falta:
        * trasladar todo lo q son metodos al ViewModel
        * generar getter/setter exercise Order
        * generar constructor
        * hacer q sea @Bindable, o no y simplemente oneway
*/

//listado con los ficheros de los entrenamientos posibles
public class TrainingTable implements Serializable {
    List<Training> trainings;

    //region getter/setter
    public List<Training> getTrainings() {

        trainings = (List<Training>) FileHelper.readDoctoreFile(Constants.FILE_ALL_TRAININGS);
        if (trainings == null)
            trainings = new ArrayList<>();
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    //endregion

}

