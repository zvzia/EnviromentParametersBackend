package pw.stud.envparam;


import pw.stud.envparam.model.SurroundingConditions;
import pw.stud.envparam.model.Sensor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DbMock {
    private final ArrayList<Sensor> sensorsList;
    private final ArrayList<SurroundingConditions> paramRecords;

    public DbMock() {
        sensorsList = new ArrayList<>();
        sensorsList.add(new Sensor(1, "Salon"));
        sensorsList.add(new Sensor(2, "Sypialnia"));
        sensorsList.add(new Sensor(3, "Kuchnia"));

        paramRecords = new ArrayList<>();
        paramRecords.add(new SurroundingConditions(1, 21.0F, 49, 1, getDate(2023, 0, 1, 12, 30)));
        paramRecords.add(new SurroundingConditions(2, 21.5F, 47, 1, getDate(2023, 0, 2, 12, 30 )));
        paramRecords.add(new SurroundingConditions(3, 21.4F, 52, 1, getDate(2023, 0, 3, 12, 30 )));
        paramRecords.add(new SurroundingConditions(4, 21.6F, 53, 1, getDate(2023, 0, 4, 12, 30 )));
        paramRecords.add(new SurroundingConditions(5, 21.2F, 51, 1, getDate(2023, 0, 5, 12, 30 )));

        paramRecords.add(new SurroundingConditions(8, 21.0F, 49, 1, getDate(2023, 2, 1, 12, 30)));
        paramRecords.add(new SurroundingConditions(9, 21.3F, 48,1, getDate(2023, 2, 2, 12, 30)));
        paramRecords.add(new SurroundingConditions(10, 21.2F, 51, 1, getDate(2023, 2, 3, 12, 30)));

        paramRecords.add(new SurroundingConditions(6, 21.3F, 59, 2, getDate(2023, 0, 5, 12, 30 )));
        paramRecords.add(new SurroundingConditions(7, 21.1F, 42,3, getDate(2023, 0, 5, 12, 30 )));
    }

    public ArrayList<Sensor> getSensorsList() {
        return sensorsList;
    }

    public Sensor getSensorById(int id){
        for (Sensor sensor : sensorsList) {
            if (sensor.getId() == id){
                return sensor;
            }
        }
        return null;
    }

    public ArrayList<SurroundingConditions> getParamRecords() {
        return paramRecords;
    }

    public ArrayList<SurroundingConditions> getLastRecords(){
        ArrayList<SurroundingConditions> lastRecords = new ArrayList<>();
        lastRecords.add(new SurroundingConditions(5, 21.2F, 51, 1, getDate(2023, 0, 5, 12, 30 )));
        lastRecords.add(new SurroundingConditions(6, 21.3F, 59, 2, getDate(2023, 0, 5, 12, 30 )));
        lastRecords.add(new SurroundingConditions(7, 21.1F, 42, 3, getDate(2023, 0, 5, 12, 30 )));

        return lastRecords;
    }

    public String getLastUpdateDateString(){
        Date lastDate = new Date(2023, 1, 5, 12, 30 );
        String dateString = String.valueOf(lastDate.getHours()) + ":" +  String.valueOf(lastDate.getMinutes()) + " " + String.valueOf(lastDate.getDate()) + "." + String.valueOf(lastDate.getMonth()) + "." + String.valueOf(lastDate.getYear());
        return dateString;
    }

    public ArrayList<SurroundingConditions> getTemperaturesForSensor(int id){
        ArrayList<SurroundingConditions> sensorTempRecords = new ArrayList<>();

        for (SurroundingConditions record: paramRecords) {
            if(record.getSensorId() == id){
                sensorTempRecords.add(record);
            }
        }
        return sensorTempRecords;
    }

    public ArrayList<SurroundingConditions> getRecordsForSensorInRange(int id, Date start, Date end){
        ArrayList<SurroundingConditions> sensorRecords = new ArrayList<>();

        for (SurroundingConditions record: paramRecords) {
            if(record.getSensorId() == id && record.getDate().after(start) && record.getDate().before(end)){
                sensorRecords.add(record);
            }
        }
        return sensorRecords;
    }

    private static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
