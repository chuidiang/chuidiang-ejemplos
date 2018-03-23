package First;

import org.opensplice.dds.dcps.Utilities;

public final class AnStructDataReaderViewHelper
{

    public static First.AnStructDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataReaderView) {
            return (First.AnStructDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static First.AnStructDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof First.AnStructDataReaderView) {
            return (First.AnStructDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
