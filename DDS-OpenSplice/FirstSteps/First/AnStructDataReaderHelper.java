package First;

import org.opensplice.dds.dcps.Utilities;

public final class AnStructDataReaderHelper
{

    public static First.AnStructDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataReader) {
            return (First.AnStructDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static First.AnStructDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataReader) {
            return (First.AnStructDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
