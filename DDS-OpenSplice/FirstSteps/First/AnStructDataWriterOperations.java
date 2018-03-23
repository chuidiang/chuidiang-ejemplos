package First;

public interface AnStructDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            First.AnStruct instance_data);

    long register_instance_w_timestamp(
            First.AnStruct instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            First.AnStruct instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            First.AnStruct instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            First.AnStruct instance_data, 
            long handle);

    int write_w_timestamp(
            First.AnStruct instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            First.AnStruct instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            First.AnStruct instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            First.AnStruct instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            First.AnStruct instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            First.AnStructHolder key_holder, 
            long handle);
    
    long lookup_instance(
            First.AnStruct instance_data);

}
