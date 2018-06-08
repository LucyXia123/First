/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsinghuadtv.www.entity;

/**
 *
 * @author liwenbo
 */
@SuppressWarnings("serial")
public class ActivityMaterials
{
    private int participantId;
    private String title;
    private String content;
    private String usernumber;

    public int getParticipantId()
    {
        return participantId;
    }

    public void setParticipantId(int participantId)
    {
        this.participantId = participantId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getUsernumber()
    {
        return usernumber;
    }

    public void setUsernumber(String usernumber)
    {
        this.usernumber = usernumber;
    }
    
    
}
