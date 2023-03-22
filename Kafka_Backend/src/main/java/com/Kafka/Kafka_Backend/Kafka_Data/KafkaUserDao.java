package com.Kafka.Kafka_Backend.Kafka_Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tbl_UserDao")
public class KafkaUserDao {
    @Id
    private String id;
    private String Cust_Name;
    private String Message_Data;
    private String Time;

    public KafkaUserDao(String cust_id, String cust_Name, String message_Data, String time) {
        id = cust_id;
        Cust_Name = cust_Name;
        Message_Data = message_Data;
        Time = time;
    }

    public KafkaUserDao() {

    }

    public String getCust_id() {
        return id;
    }

    public void setCust_id(String cust_id) {
        id = cust_id;
    }

    public String getCust_Name() {
        return Cust_Name;
    }

    public void setCust_Name(String cust_Name) {
        Cust_Name = cust_Name;
    }

    public String getMessage_Data() {
        return Message_Data;
    }

    public void setMessage_Data(String message_Data) {
        Message_Data = message_Data;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "KafkaUserDao{" +
                "Cust_id='" + id + '\'' +
                ", Cust_Name='" + Cust_Name + '\'' +
                ", Message_Data='" + Message_Data + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
