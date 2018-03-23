package First;

import org.opensplice.dds.dcps.Utilities;

public final class AnStructTypeSupportHelper
{

    public static First.AnStructTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructTypeSupport) {
            return (First.AnStructTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static First.AnStructTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructTypeSupport) {
            return (First.AnStructTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
