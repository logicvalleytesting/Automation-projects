package BWV_Package;

import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.annotations.Row;

@Row(colsOrder = {"OfficerId", "MessageType", "Count_MessageType"})
public class TotalCountOfficerWise{
	
	@Column (name="OfficerId")
private String OfficerId;
	@Column (name="Count_MessageType")
private String Count_MessageType;
	@Column (name="MessageType")
private String MessageType;

public String getOfficerId ()
{
return OfficerId;
}

public void setOfficerId (String OfficerId)
{
this.OfficerId = OfficerId;
}

public String getCount_MessageType ()
{
return Count_MessageType;
}

public void setCount_MessageType (String Count_MessageType)
{
this.Count_MessageType = Count_MessageType;
}

public String getMessageType ()
{
return MessageType;
}

public void setMessageType (String MessageType)
{
this.MessageType = MessageType;
}

@Override
public String toString()
{
return "[OfficerId = "+OfficerId+", MessageType = "+MessageType+", Count_MessageType = "+Count_MessageType+"]";
}
}


