package RMI;

import java.io.Serializable;

public class TicketSla implements java.io.Serializable {
    private static final long serialVersionUID = 20260517L;

    private String ticketId;
    private String priority;
    private int openedHoursAgo;
    private boolean breached;
    private String action;

    public TicketSla(){};

    public TicketSla(String ticketId, String priority, int openedHoursAgo) {
        this.ticketId = ticketId;
        this.priority = priority;
        this.openedHoursAgo = openedHoursAgo;
    }

    public void check(){
        if(this.openedHoursAgo > 2 && this.priority.equals("CRITICAL")){
            this.breached = true;
            return;
        }
        if(this.openedHoursAgo > 8 && this.priority.equals("HIGH")){
            this.breached = true;
            return;
        }
        if(this.openedHoursAgo > 24 && this.priority.equals("MEDIUM")){
            this.breached = true;
            return;
        }
        if(this.openedHoursAgo > 72 && this.priority.equals("LOW")){
            this.breached = true;
            return;
        }
        this.breached = false;
    }

    public String checkAction(String priority, int openedHoursAgo){
        if (!this.breached) {
            return "MONITOR";
        } else if (priority.equals("CRITICAL") || openedHoursAgo > 96) {
            return "ESCALATE_L2";
        } else {
            return "ESCALATE_L1";
        }
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getOpenedHoursAgo() {
        return openedHoursAgo;
    }

    public void setOpenedHoursAgo(int openedHoursAgo) {
        this.openedHoursAgo = openedHoursAgo;
    }

    public boolean isBreached() {
        return breached;
    }

    public void setBreached(boolean breached) {
        this.breached = breached;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
