package First;

import org.opensplice.dds.dcps.Utilities;

public final class AnStructDataWriterHelper
{

    public static First.AnStructDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataWriter) {
            return (First.AnStructDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static First.AnStructDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataWriter) {
            return (First.AnStructDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
