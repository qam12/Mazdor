package com.example.maju.mazdor;

/**
 * Created by qamber.haider on 4/30/2018.
 */

public class Job_class {
    public String job_title;
    public String job_rate;
    public String job_location;
    public String job_description;
    public String job_id;

    public Job_class() {
    }

    public Job_class(String job_title, String job_rate, String job_location, String job_description, String job_id) {
        this.job_title = job_title;
        this.job_rate = job_rate;
        this.job_location = job_location;
        this.job_description = job_description;
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_rate() {
        return job_rate;
    }

    public void setJob_rate(String job_rate) {
        this.job_rate = job_rate;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
}