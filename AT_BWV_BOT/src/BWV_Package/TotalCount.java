package BWV_Package;

import com.ebay.xcelite.annotations.Column;

public class TotalCount{
	
	@Column (name="Count MessageType")
private String Count_MessageType;
	@Column (name="MessageType")
private String MessageType;

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
return "[MessageType = "+MessageType+",Count_MessageType = "+Count_MessageType+"]";
}
}

