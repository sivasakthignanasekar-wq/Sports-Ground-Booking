package com.wipro.sports.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.wipro.sports.bean.GroundBookingBean;
import com.wipro.sports.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       Administrator admin = new Administrator();
protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
  String operation = request.getParameter("operation");
       try {
            if ("newRecord".equals(operation)) {

                GroundBookingBean bean = new GroundBookingBean();
                bean.setTeamName(request.getParameter("teamName"));
                bean.setGroundName(request.getParameter("groundName"));
                bean.setTimeSlot(request.getParameter("timeSlot"));
                bean.setRemarks(request.getParameter("remarks"));
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date bookingDate=sdf.parse(request.getParameter("bookingDate"));
              bean.setBookingDate(bookingDate);

                String result = admin.addRecord(bean);

                if ("FAIL".equals(result)) 
                {
                    response.sendRedirect("error.html");
                } else 
                {
                    response.sendRedirect("success.html");
                }
            }
            else if ("viewRecord".equals(operation)) {
               String teamName = request.getParameter("teamName");
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
          Date bookingDate=sdf.parse(request.getParameter("bookingDate"));
           GroundBookingBean bean=admin.viewRecord(teamName, bookingDate);
            request.setAttribute("record", bean);

                RequestDispatcher rd=request.getRequestDispatcher("displayGroundBooking.jsp");

                rd.forward(request, response);
            }
            else if ("viewAllRecords".equals(operation)) {

                List<GroundBookingBean> list=admin.viewAllRecords();
                request.setAttribute("allRecords", list);
                RequestDispatcher rd=request.getRequestDispatcher("displayAllGroundBookings.jsp");
              rd.forward(request, response);
            }

        } catch (Exception e) 
       {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
       }
        }
