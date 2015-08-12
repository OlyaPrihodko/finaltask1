package com.epam.prihodko.finaltask.customTags;

import com.epam.prihodko.finaltask.exception.ProjectException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllListTag extends TagSupport {

    private int size;
    public void setSize(String size){
        this.size=Integer.parseInt(size);
    }
    @Override
    public int doStartTag() throws JspTagException {
        try{
            JspWriter out = pageContext.getOut();
            out.write("<table border=\"1\">");
           // out.write("<thead><tr><th>"+listname+"</th></tr></thead>");
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
}
