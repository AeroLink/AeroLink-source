/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.HR2.ClassFiles;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EdenRamoneda
 */
public class HR2_TrainingReq_Class {

    public SimpleStringProperty tr_id, t_id, dept_name, title, training_title, no_of_participants, employee_code, trainor,
            from_day, to_day, reason, request_status_id, request_status, requested_by, date_requested;

    public HR2_TrainingReq_Class(String tr_id, String t_id, String dept_id, String job_id, String training_title,
            String no_of_participants, String employee_code, String trainor, String from_day, String to_day, String reason,
            String request_status_id, String request_status, String requested_by, String date_requested) {

        this.tr_id = new SimpleStringProperty(tr_id);
        this.t_id = new SimpleStringProperty(t_id);
        this.dept_name = new SimpleStringProperty(dept_id);
        this.title = new SimpleStringProperty(job_id);
        this.training_title = new SimpleStringProperty(training_title);
        this.no_of_participants = new SimpleStringProperty(no_of_participants);
        this.employee_code = new SimpleStringProperty(employee_code);
        this.trainor = new SimpleStringProperty(trainor);
        this.from_day = new SimpleStringProperty(from_day);
        this.to_day = new SimpleStringProperty(to_day);
        this.reason = new SimpleStringProperty(reason);
        this.request_status_id = new SimpleStringProperty(request_status_id);
        this.request_status = new SimpleStringProperty(request_status);
        this.requested_by = new SimpleStringProperty(requested_by);
        this.date_requested = new SimpleStringProperty(date_requested);

    }
}
