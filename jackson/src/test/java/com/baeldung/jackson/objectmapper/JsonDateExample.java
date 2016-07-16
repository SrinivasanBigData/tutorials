package com.baeldung.jackson.objectmapper;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.jackson.objectmapper.dto.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDateExample extends Example {

    protected final Logger Logger = LoggerFactory.getLogger(getClass());

    public JsonDateExample() {
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    class Request {
        Car car;
        Date datePurchased;
        public Car getCar() {
            return car;
        }

        public void setCar(final Car car) {
            this.car = car;
        }

        public Date getDatePurchased() {
            return datePurchased;
        }

        public void setDatePurchased(final Date datePurchased) {
            this.datePurchased = datePurchased;
        }
    }

    @Override
    public void testExample() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Car car = new Car("yellow", "renault");
        final Request request = new Request();
        request.setCar(car);
        request.setDatePurchased(new Date());
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        final String carAsString = objectMapper.writeValueAsString(request);
        assertNotNull(carAsString);
        assertThat(carAsString, containsString("datePurchased"));
    }
}
