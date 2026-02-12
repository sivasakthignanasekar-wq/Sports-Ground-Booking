package com.wipro.sports.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.wipro.sports.bean.GroundBookingBean;
import com.wipro.sports.util.DBUtil;
public class GroundBookingDAO {
    public String createRecord(GroundBookingBean bean) {

   try (Connection con = DBUtil.getDBConnection()) {
            String seqQuery = "SELECT RECORDS_ID_SEQ.NEXTVAL FROM dual";
            PreparedStatement ps1 = con.prepareStatement(seqQuery);
            ResultSet rs = ps1.executeQuery();
String recordId = null;
            if (rs.next()) {
                recordId = "RB" + rs.getInt(1);
            }
            String insertQuery ="INSERT INTO GROUNDS_BOOKING_TBL " +"(RECORD_ID, TEAM_NAME, GROUND_NAME, BOOKING_DATE, TIME_SLOT, REMARKS) " +"VALUES (?, ?, ?, ?, ?, ?)";

      PreparedStatement ps = con.prepareStatement(insertQuery);
           ps.setString(1, recordId);
            ps.setString(2, bean.getTeamName());
            ps.setString(3, bean.getGroundName());
            ps.setDate(4, new java.sql.Date(bean.getBookingDate().getTime()));
            ps.setString(5, bean.getTimeSlot());
            ps.setString(6, bean.getRemarks());
int rows = ps.executeUpdate();
            if (rows > 0) {
                return recordId;
            }

        } 
   catch (Exception e) {
            e.printStackTrace();
        }

        return "FAIL";
    }
    public List<GroundBookingBean> viewAllRecords() {
    	List<GroundBookingBean> list = new ArrayList<>();
  try (Connection con = DBUtil.getDBConnection()) {
String query = "SELECT * FROM GROUNDS_BOOKING_TBL";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

  while (rs.next()) {
           GroundBookingBean bean = new GroundBookingBean();
                bean.setRecordId(rs.getString("RECORD_ID"));
                bean.setTeamName(rs.getString("TEAM_NAME"));
                bean.setGroundName(rs.getString("GROUND_NAME"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setTimeSlot(rs.getString("TIME_SLOT"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

        } catch (Exception e)
  {
            e.printStackTrace();
        }

        return list;
    }
    public GroundBookingBean viewRecord(String teamName, Date bookingDate) {
         try (Connection con = DBUtil.getDBConnection()) {

            String query ="SELECT * FROM GROUNDS_BOOKING_TBL " +"WHERE TEAM_NAME = ? AND BOOKING_DATE = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, teamName);
            ps.setDate(2, new java.sql.Date(bookingDate.getTime()));

            ResultSet rs = ps.executeQuery();
        if (rs.next()) {
                GroundBookingBean bean = new GroundBookingBean();
                bean.setRecordId(rs.getString("RECORD_ID"));
                bean.setTeamName(rs.getString("TEAM_NAME"));
                bean.setGroundName(rs.getString("GROUND_NAME"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setTimeSlot(rs.getString("TIME_SLOT"));
                bean.setRemarks(rs.getString("REMARKS"));

                return bean;
            }

        } 
         catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
