package com.wipro.sports.service;
import java.util.Date;
import java.util.List;
import com.wipro.sports.bean.GroundBookingBean;
import com.wipro.sports.dao.GroundBookingDAO;

public class Administrator {
    GroundBookingDAO dao=new GroundBookingDAO();
public String addRecord(GroundBookingBean bean) {
        return dao.createRecord(bean);
    }
public GroundBookingBean viewRecord(String teamName, Date bookingDate) {
        return dao.viewRecord(teamName, bookingDate);
    }
public List <GroundBookingBean> viewAllRecords() {
        return dao.viewAllRecords();
    }
  }
