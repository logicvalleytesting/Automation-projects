package BWV_Package;

import java.util.BitSet;

import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.annotations.Row;



@Row(colsOrder = {"OfficerId", "Casenumber", "MessageType", "IsRecordSuccess","RecordedFileName","RecordFailReason","DownloadedFileName","DateActioned"})

public class All_records{
	
	@Column (name="OfficerId")
    private String OfficerId;
	
	@Column (name="Casenumber")
    private String Casenumber;

	@Column (name="MessageType")
    private String MessageType;
	
	@Column (name="IsRecordSuccess")
    private int  IsRecordSuccess ;
	

	@Column (name="RecordedFileName")
    private String RecordedFileName="null";
	
	@Column (name="RecordFailReason")
    private String RecordFailReason = "null";
	
	@Column (name="DownloadedFileName")
    private String DownloadedFileName ="null";

	@Column (name="DateActioned")
	private String DateActioned;

    public String getOfficerId ()
    {
        return OfficerId;
    }

    public void setOfficerId (String OfficerId)
    {
        this.OfficerId = OfficerId;
    }

    public String getRecordedFileName ()
    {
        return RecordedFileName;
    }

    public void setRecordedFileName (String RecordedFileName)
    {
        this.RecordedFileName = RecordedFileName;
    }

    public String getDateActioned ()
    {
        return DateActioned;
    }

    public void setDateActioned (String DateActioned)
    {
        this.DateActioned = DateActioned;
    }

    public String getCasenumber ()
    {
        return Casenumber;
    }

    public void setCasenumber (String Casenumber)
    {
        this.Casenumber = Casenumber;
    }

    public String getDownloadedFileName ()
    {
        return DownloadedFileName;
    }

    public void setDownloadedFileName (String DownloadedFileName)
    {
        this.DownloadedFileName = DownloadedFileName;
    }

    public String getRecordFailReason ()
    {
        return RecordFailReason;
    }

    public void setRecordFailReason (String RecordFailReason)
    {
        this.RecordFailReason = RecordFailReason;
    }

    public String getMessageType ()
    {
        return MessageType;
    }

    public void setMessageType (String MessageType)
    {
        this.MessageType = MessageType;
    }

    public int getIsRecordSuccess ()
    {
        return IsRecordSuccess;
    }

    public void setIsRecordSuccess (int IsRecordSuccess)
    {
        this.IsRecordSuccess = IsRecordSuccess;
    }

    @Override
    public String toString()
    {
      return "[OfficerId = "+OfficerId+",Casenumber = "+Casenumber+",MessageType = "+MessageType+",IsRecordSuccess = "+IsRecordSuccess+" ,RecordedFileName = "+RecordedFileName+",RecordFailReason = "+RecordFailReason+", DownloadedFileName = "+DownloadedFileName+", DateActioned = "+DateActioned+"]";
        
      
    }
}
			