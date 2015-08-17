package com.epam.prihodko.finaltask.customTags;

import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.domain.*;
import com.epam.prihodko.finaltask.exception.ProjectException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AllListTag extends TagSupport {
private final static String LOCALE = "com.epam.prihodko.finaltask/localization.locale";
    private MapBean map;
    private String lang;
    private String user;

    public void setLang(String lang){
            this.lang=lang;
        }
    public void setMap(MapBean map){
        this.map=map;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getLang(){return lang;}
    public MapBean getMap(){
    return map;
    }
    public String getUser() {
        return user;
    }
    @Override
    public int doStartTag() throws JspTagException {
        String language = getLang().substring(0,2);
        String country = getLang().substring(3,5);
        Locale locale = new Locale(language,country);
        ResourceBundle resourceBundle= ResourceBundle.getBundle(LOCALE,locale);
        int size = new Integer(map.getSize());
        try{
            JspWriter out = pageContext.getOut();
            Object first = map.getElement();
            if(Integer.parseInt(map.getSize())==0){
                if(getUser().equals(ResponseParameterName.USER)){
                    String noOrders = resourceBundle.getString("locale.message.Message9");
                    out.write("<h4>"+noOrders+"</h4>");
                }
                //???????????????????????
                if(getUser().equals(ResponseParameterName.ADMIN)&&(first instanceof Apartment)){
                    String noOrders = resourceBundle.getString("locale.message.Message16");
                    out.write("<h4>"+noOrders+"</h4>");
                }
                if(getUser().equals(ResponseParameterName.ADMIN)){
                    String noOrders = resourceBundle.getString("locale.message.Message13");
                    out.write("<h4>"+noOrders+"</h4>");
                }
            }
            else {
            out.write("<table class=\"table table-bordered table-striped\">");

            if(first instanceof Order){
                String id = resourceBundle.getString("locale.message.Id");
                String apClass = resourceBundle.getString("locale.message.ApartmentClass");
                String roomNumber = resourceBundle.getString("locale.message.RoomNumber");
                String couchette = resourceBundle.getString("locale.message.Couchette");
                String datein = resourceBundle.getString("locale.message.Datein");
                String dateout = resourceBundle.getString("locale.message.Dateout");
                String status = resourceBundle.getString("locale.message.Status");
                String action = resourceBundle.getString("locale.message.Action");
                out.write("<thead class=\"text-center\"><tr>" +
                        "<th>"+id+"</th>" +
                        "<th>"+apClass+"</th>" +
                        "<th>"+roomNumber+"</th>"+
                        "<th>"+couchette+"</th>"+
                        "<th>"+datein+"</th>"+
                        "<th>"+dateout+"</th>" +
                        "<th>"+status+"</th>"+
                        "<th>"+action+"</th>"+
                        "</tr></thead>");

                out.write("<tbody>");
                for(int i=0;i<size;i++){
                    out.write("<tr>"+map.getElementToString());
                    String addValueToOrderId = " document.getElementById('"+ResponseParameterName.ORDER_ID+"').value='"+map.getId()+"'";

                    if(getUser().equals(ResponseParameterName.USER)){
                        out.write("<td>" +
                                "<div class=\"btn-group btn-group-vertical\">" +
                                "<button  type=\"submit\" class=\"btn btn-labeled btn-primary btn-mini\"\n" +
                                "onclick=\"document.getElementById('command').value='go-to-change-order-page';" +
                                addValueToOrderId+ ";\">\n" +
                                "<span class=\"btn-label\" ><i class=\"fa fa-edit\"></i>\n" +
                                "</span> \n" +
                                "</button>");

                        out.write("<button  type=\"submit\" class=\"btn btn-labeled btn-danger btn-mini\"\n" +
                                "onclick=\"document.getElementById('command').value='remove-order';" +
                                addValueToOrderId+";\">\n" +
                                "<span class=\"btn-label\" ><i class=\"glyphicon glyphicon-remove\"></i>\n" +
                                "</span> \n" +
                                "</button>" +
                                "</div>" +
                                "</td>" +
                                "</tr>");
                    }
                    else{
                        if(getUser().equals(ResponseParameterName.ADMIN)){
                            out.write("<td>" +
                                    "<div class=\"btn-group btn-group-vertical\">" +
                                    "<button  type=\"submit\" class=\"btn btn-labeled btn-primary btn-mini\"\n" +
                                    "onclick=\"document.getElementById('command').value='find-apartment';" +
                                    addValueToOrderId+ ";\">\n" +
                                    "<span class=\"btn-label\" ><i class=\"glyphicon glyphicon-search\"></i>\n" +
                                    "</span> \n" +
                                    "</button>");

                            out.write("<button  type=\"submit\" class=\"btn btn-labeled btn-danger btn-mini\"\n" +
                                    "onclick=\"document.getElementById('command').value='remove-order';" +
                                    addValueToOrderId+";\">\n" +
                                    "<span class=\"btn-label\" ><i class=\"glyphicon glyphicon-remove\"></i>\n" +
                                    "</span> \n" +
                                    "</button>" +
                                    "</div>" +
                                    "</td>" +
                                    "</tr>");
                        }
                    }
                }
            }
                if(first instanceof Apartment){
                    String id = resourceBundle.getString("locale.message.Id");
                    String price = resourceBundle.getString("locale.message.Price");
                    String couchette = resourceBundle.getString("locale.message.Couchette");
                    String roomNumber = resourceBundle.getString("locale.message.RoomNumber");
                    String status = resourceBundle.getString("locale.message.Status");
                    String apClass = resourceBundle.getString("locale.message.ApartmentClass");
                    String action = resourceBundle.getString("locale.message.Action");
                    String choose = resourceBundle.getString("locale.message.Choose");
                    out.write("<thead class=\"text-center\"><tr>" +
                            "<th>"+id+"</th>" +
                            "<th>"+price+"</th>" +
                            "<th>"+couchette+"</th>"+
                            "<th>"+roomNumber+"</th>"+
                            "<th>"+status+"</th>"+
                            "<th>"+apClass+"</th>" +
                            "<th>"+action+"</th>"+
                            "</tr></thead>");

                    out.write("<tbody>");
                    for(int i=0;i<size;i++){
                        out.write("<tr>"+map.getElementToString());
                        String addValueToApartmentId = " document.getElementById('"+ResponseParameterName.APARTMENT_ID+"').value='"+map.getId()+"'";

                        out.write("<td>" +
                                "<div class=\"btn-group btn-group-vertical\">" +
                                "<button  type=\"submit\" class=\"btn btn-labeled btn-success btn-small\"\n" +
                                "onclick=\"document.getElementById('command').value='to-reserve-apartment';" +
                                addValueToApartmentId+ ";\">\n" +
                                "<span class=\"btn-label\" ><i class=\"fa fa-check\"></i>\n" +
                                "</span> "+choose+"\n" +
                                "</button>"+
                                "</div>" +
                                "</td>" +
                                "</tr>");

                        /*out.write("<button  type=\"submit\" class=\"btn btn-labeled btn-danger btn-mini\"\n" +
                                "onclick=\"document.getElementById('command').value='remove-order';" +
                                addValueToOrderId+";\">\n" +
                                "<span class=\"btn-label\" ><i class=\"glyphicon glyphicon-remove\"></i>\n" +
                                "</span> \n" +
                                "</button>" +
                                "</div>" +
                                "</td>" +
                                "</tr>");*/


                    }
                }
                if(first instanceof Check){
                    String id = resourceBundle.getString("locale.message.Id");
                    String price = resourceBundle.getString("locale.message.Price");
                    String apartmentId = resourceBundle.getString("locale.message.ApartmentId");
                    String orderId = resourceBundle.getString("locale.message.OrderId");
                    out.write("<thead class=\"text-center\"><tr>" +
                            "<th>"+id+"</th>" +
                            "<th>"+price+"</th>" +
                            "<th>"+apartmentId+"</th>"+
                            "<th>"+orderId+"</th>"+
                            "</tr></thead>");

                    out.write("<tbody>");
                    for(int i=0;i<size;i++){
                        out.write("<tr>"+map.getElementToString()+"</tr>");
                    }
                }


            out.write("</tbody>");
            out.write("</table>");
            }
        }
        catch (IOException e) {
        throw new JspTagException(e.getMessage());
    }
        return SKIP_BODY;
    }


}
