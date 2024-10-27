package org.example.utils.marshallers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {

    private  static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy,MM,dd HH:mm:ss");
        }
    };


    @Override
    public Date unmarshal(String s) throws Exception {
        return new Date(dateFormat.get().parse(s).getTime());
    }

    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.get().format(date);
    }
}
