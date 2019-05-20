package BWV_Package;

import com.ebay.xcelite.annotations.Column;
import com.ebay.xcelite.annotations.Row;

@Row(colsOrder = {"Officer", "TotalActionCount", "TotalVisitCount"})
public class TotalAction{
	
	@Column (name="TotalActionCount")
    private String TotalActionCount;

	@Column (name="Officer")
    private String Officer;
	
	@Column (name="TotalVisitCount")
	private String TotalVisitCount;

    public String getTotalActionCount ()
    {
        return TotalActionCount;
    }

    public void setTotalActionCount (String TotalActionCount)
    {
        this.TotalActionCount = TotalActionCount;
    }

    public String getOfficer ()
    {
        return Officer;
    }

    public void setOfficer (String Officer)
    {
        this.Officer = Officer;
    }
    
    public String getTotalVisitCount ()
    {
        return TotalVisitCount;
    }

    public void setTotalVisitCount (String TotalVisitCount)
    {
        this.TotalVisitCount = TotalVisitCount;
    }

    
    
    

    @Override
    public String toString()
    {
        return "[Officer = "+ Officer +",TotalActionCount ="+TotalActionCount+",TotalVisitCount="+TotalVisitCount+"]";
    }
}
