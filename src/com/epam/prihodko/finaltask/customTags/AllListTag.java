package com.epam.prihodko.finaltask.customTags;

import com.epam.prihodko.finaltask.controller.ResponseParameterName;
import com.epam.prihodko.finaltask.domain.MapBean;
import com.epam.prihodko.finaltask.domain.Order;
import com.epam.prihodko.finaltask.domain.SetBean;
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
//private final static String CHANGE_TYPE_YES = "yes";
  //  private final static String CHANGE_TYPE_NO = "no";

    private MapBean map;
    private String lang;
    //private String change;

    public void setLang(String lang){
            this.lang=lang;
        }
    public void setMap(MapBean map){
        this.map=map;
    }
    /*public void setChange(String change) {
        this.change = change;
    }*/

    public String getLang(){return lang;}
    public MapBean getMap(){
    return map;
    }
    /*public String getChange() {
        return change;
    }*/

    @Override
    public int doStartTag() throws JspTagException {
        String language = getLang().substring(0,2);
        String country = getLang().substring(3,5);
        Locale locale = new Locale(language,country);
        ResourceBundle resourceBundle= ResourceBundle.getBundle(LOCALE,locale);
        int size = new Integer(map.getSize());
        try{
            JspWriter out = pageContext.getOut();
            if(Integer.parseInt(map.getSize())==0){
                String noOrders = resourceBundle.getString("locale.message.Message9");
            out.write("<h4>"+noOrders+"</h4>");
            }
            else {
            out.write("<table class=\"table table-bordered table-striped\">");
            Object first = map.getElement();

            if(first instanceof Order){
                String id = resourceBundle.getString("locale.message.Id");
                String apClass = resourceBundle.getString("locale.message.OrderApartmentClass");
                String roomNumber = resourceBundle.getString("locale.message.OrderRoomNumber");
                String couchette = resourceBundle.getString("locale.message.OrderCouchette");
                String datein = resourceBundle.getString("locale.message.OrderDatein");
                String dateout = resourceBundle.getString("locale.message.OrderDateout");
                String status = resourceBundle.getString("locale.message.OrderStatus");
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
            }
            out.write("<tbody>");
                for(int i=0;i<size;i++){
                    out.write("<tr>"+map.getElementToString());
                    //String a=map.getId()+"";
                    //String setToSessionOrderId="<c:set scope=\"session\" value=\""+map.getId()+"\" var=\""+
                       //     ResponseParameterName.ORDER_ID+"\"/>";
                    String f = " document.getElementById('"+ResponseParameterName.ORDER_ID+"').value='"+map.getId()+"'";

                    out.write("<td>" +
                            "<div class=\"btn-group btn-group-vertical\">" +
                            "<button  type=\"submit\" class=\"btn btn-labeled btn-primary btn-mini\"\n" +
                            "onclick=\"document.getElementById('command').value='go-to-change-order-page';" +
                            f + ";\">\n" +
                            "<span class=\"btn-label\" ><i class=\"fa fa-edit\"></i>\n" +
                            "</span> \n" +
                            "</button>" +

                            "<button  type=\"submit\" class=\"btn btn-labeled btn-danger btn-mini\"\n" +
                            "onclick=\"document.getElementById('command').value='remove-order';" +
                            f+";\">\n" +
                            "<span class=\"btn-label\" ><i class=\"glyphicon glyphicon-remove\"></i>\n" +
                            "</span> \n" +
                            "</button>" +
                            "</div>" +
                            "</td>" +
                            "</tr>");
                }
            out.write("</tbody>");
              /*  String setToSessionOrderId="<c:set scope=\"session\" value=\""+map.getId()+"\" var=\""+
                        ResponseParameterName.ORDER_ID+"\"/>";
                        */

            out.write("</table>");
            }
        }
        catch (IOException e) {
        throw new JspTagException(e.getMessage());
    }
        return SKIP_BODY;
    }


}


/*
*
*  private int size;
    public void setSize(String size){
        this.size=Integer.parseInt(size);
    }
    @Override
    public int doStartTag() throws JspTagException {
        try{
            JspWriter out = pageContext.getOut();
            out.write("<table class=\"table table-bordered table-striped\">");
          //  out.write("<thead><tr><th>"+listname+"</th></tr></thead>");
            out.write("<tbody><tr><th>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
    return EVAL_BODY_INCLUDE;
    }
    @Override
    public int doAfterBody()throws JspTagException{
        if(size>1){
            size=size-1;
            try{
                pageContext.getOut().write("</td></tr><tr><td>");
            }
            catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        }
        else{
        return SKIP_BODY;
        }
    }
    @Override
    public int doEndTag() throws JspTagException{
        try{
            pageContext.getOut().write("</td></tr></tbody></table>");
        }
        catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;//EVAL_PAGE;
    }
* */
