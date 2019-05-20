package BWV_Package;

import com.ebay.xcelite.annotations.Column;


public class TotalVisit{
	
	@Column (name="TotalVisitCount")
private String TotalVisitCount;
	@Column (name="Officer")
private String Officer;

public String getTotalVisitCount ()
{
return TotalVisitCount;
}

public void setTotalVisitCount (String TotalVisitCount)
{
this.TotalVisitCount = TotalVisitCount;
}

public String getOfficer ()
{
return Officer;
}

public void setOfficer (String Officer)
{
this.Officer = Officer;
}

@Override
public String toString()
{
return "[TotalVisitCount = "+TotalVisitCount+", Officer = "+Officer+"]";
}
}