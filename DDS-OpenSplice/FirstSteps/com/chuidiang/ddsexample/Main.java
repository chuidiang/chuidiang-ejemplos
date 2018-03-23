package com.chuidiang.ddsexample;

import DDS.*;
import First.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }

    private DomainParticipantFactory dpf;
    private DomainParticipant participant;

    public void run() throws InterruptedException {


        dpf = DomainParticipantFactory.get_instance ();

        int domain = DOMAIN_ID_DEFAULT.value;

        participant =  dpf.create_participant(
                domain, PARTICIPANT_QOS_DEFAULT.value, null, STATUS_MASK_NONE.value
               );
        System.out.println("Creado domain participant");

        AnStructTypeSupport anStructTS = new AnStructTypeSupport();
        String anStructTypeName = anStructTS.get_type_name();
        int status = anStructTS.register_type(participant, anStructTypeName);
        System.out.println("registrado tipo");


//        int status = -1;
//        participant.get_default_topic_qos(topicQos);
//        topicQos.value.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
//        topicQos.value.durability.kind = DurabilityQosPolicyKind.TRANSIENT_DURABILITY_QOS;
//        status = participant.set_default_topic_qos(topicQos.value);
//        ErrorHandler.checkStatus(status,
//                "DomainParticipant.set_default_topic_qos");
//        topic = participant.create_topic(topicName, typeName, topicQos.value,
//                null, STATUS_MASK_NONE.value);
//        ErrorHandler.checkHandle(topic, "DomainParticipant.create_topic");




        TopicQosHolder reliableTopicQos = new TopicQosHolder();
        status = participant.get_default_topic_qos(reliableTopicQos);
        reliableTopicQos.value.reliability.kind =
                ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
        reliableTopicQos.value.durability.kind = DurabilityQosPolicyKind.TRANSIENT_DURABILITY_QOS;
        status = participant.set_default_topic_qos(reliableTopicQos.value);
        System.out.println("registrado reliable QoS");

        Topic anStructTopic = participant.create_topic(
                 "Struct_1",
                 anStructTypeName,
                 reliableTopicQos.value,
                 null,
                 STATUS_MASK_NONE.value);

//        int status = participant.get_default_publisher_qos(pubQos);
//        ErrorHandler.checkStatus(status,
//                "DomainParticipant.get_default_publisher_qos");
//
//        pubQos.value.partition.name = new String[1];
//        pubQos.value.partition.name[0] = partitionName;
//        publisher = participant.create_publisher(pubQos.value, null,
//                STATUS_MASK_NONE.value);
//        ErrorHandler.checkHandle(publisher,
//                "DomainParticipant.create_publisher");



        PublisherQosHolder publisherQosHolder = new PublisherQosHolder();
        status = participant.get_default_publisher_qos(publisherQosHolder);
        publisherQosHolder.value.partition.name = new String[1];
        String partitionName = new String("AnStructPartition");
        publisherQosHolder.value.partition.name[0] = partitionName;

        Publisher chatPublisher = participant.create_publisher(
                publisherQosHolder.value, null, STATUS_MASK_NONE.value);
        System.out.println("registrado partition");


//        publisher.get_default_datawriter_qos(WQosH);
//        publisher.copy_from_topic_qos(WQosH, topicQos.value);
//        WQosH.value.writer_data_lifecycle.autodispose_unregistered_instances = false;
//        writer = publisher.create_datawriter(topic, WQosH.value, null,STATUS_MASK_NONE.value);
//        ErrorHandler.checkHandle(writer, "Publisher.create_datawriter");
        DataWriterQosHolder wQoSHolder = new DataWriterQosHolder();
        chatPublisher.get_default_datawriter_qos(wQoSHolder);
        chatPublisher.copy_from_topic_qos(wQoSHolder, reliableTopicQos.value);
        wQoSHolder.value.writer_data_lifecycle.autodispose_unregistered_instances = false;

        DataWriter parentWriter = chatPublisher.create_datawriter(
                anStructTopic,
                wQoSHolder.value,
                null,
                STATUS_MASK_NONE.value);

        AnStructDataWriter talker = AnStructDataWriterHelper.narrow(parentWriter);


        SubscriberQosHolder subQos = new SubscriberQosHolder();
        status = participant.get_default_subscriber_qos (subQos);
        subQos.value.partition.name = new String[1];
        subQos.value.partition.name[0] = partitionName;

        Subscriber chatSubscriber = participant.create_subscriber(
                subQos.value, null, STATUS_MASK_NONE.value);
        chatSubscriber.set_default_datareader_qos(new DataReaderQos());

        DataReader parentReader = chatSubscriber.create_datareader(
                anStructTopic,
                DATAREADER_QOS_USE_TOPIC_QOS.value,
                null,
                STATUS_MASK_NONE.value);
        AnStructDataReader reader = AnStructDataReaderHelper.narrow(parentReader);

//        AnStructDataReaderImpl reader = new AnStructDataReaderImpl(anStructTS);
        AnStructDataReaderHelper.narrow(reader);

        new Thread(){
            public void run(){

                StatusCondition statuscondition = reader.get_statuscondition();
                statuscondition.set_enabled_statuses(DATA_AVAILABLE_STATUS.value);

                WaitSet ws = new WaitSet();
                ws.attach_condition(statuscondition);

                Duration_t duration = new Duration_t();
                duration.sec=10000;

                ConditionSeqHolder conditions = new ConditionSeqHolder();

                while (true) {
                    AnStructSeqHolder seqHolder = new AnStructSeqHolder();
                    SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();


                        ws._wait(conditions,duration);
                    System.out.println("Fin wait "+conditions.value.length);
                        int status = reader.take(
                                seqHolder,
                                infoSeq,
                                LENGTH_UNLIMITED.value,
                                ANY_SAMPLE_STATE.value,
                                ANY_VIEW_STATE.value,
                                ALIVE_INSTANCE_STATE.value);


                        if (null!=seqHolder.value) {
                            for (int i = 0; i < seqHolder.value.length; i++) {
                                System.out.println(seqHolder.value[i].id);
                                System.out.println(seqHolder.value[i].aText);
                                System.out.println("-----------");
                            }
                        }


                }
            }
        }.start();

//        AnStructDataWriterImpl talker = new AnStructDataWriterImpl(anStructTS);

        AnStruct data = new AnStruct();
        data.id=1;
        data.aText="text"+data.id;
//        talker.register_instance(data);
        while (true){
            talker.write(data,HANDLE_NIL.value);
            data.id++;
            data.aText="text"+data.id;
            Thread.sleep(1000);
            System.out.println("va");
        }
    }
}
