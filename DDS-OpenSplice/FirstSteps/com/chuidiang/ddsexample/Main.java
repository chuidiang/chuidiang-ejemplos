package com.chuidiang.ddsexample;

import DDS.*;
import First.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String TOPIC_NAME = "Struct_1";
    private final String partitionName = new String("AnStructPartition");

    private DomainParticipantFactory domainParticipantFactory;
    private DomainParticipant participant;

    private String anStructTypeName;
    private Topic anStructTopic;

    private Publisher publisher;
    private AnStructDataWriter anStructDataWriter;

    private Subscriber subscriber;
    private AnStructDataReader reader;

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }


    public void run() throws InterruptedException {


        createDomainParticipantFactory();
        createParticipant();
        registerType();
        createTopic();


        createPublisher();
        createWriter();

        createSubscriber();
        createReader();


        startReading();


//        startWriting();
        startWritingWithRegistration();
        return;
    }

    private void startWriting() throws InterruptedException {
        AnStruct data = new AnStruct();
        int counter=1;
        data.id = counter;
        data.aText = "text" + data.id;

        while (true) {
            anStructDataWriter.write(data, HANDLE_NIL.value);
            data.id = counter%5;
            counter++;
            data.aText = "text" + counter;
            Thread.sleep(500);
            System.out.println("va");
        }
    }

    private void startWritingWithRegistration() throws InterruptedException {
        Map<Integer, Long> handles = new HashMap<>();

        for (int i=0;i<5;i++) {
            AnStruct data = new AnStruct();
            data.id=i;
            data.aText="text"+i;
            long handle = anStructDataWriter.register_instance(data);
            handles.put(data.id, handle);
        }

        AnStruct data = new AnStruct();
        int counter=0;
        data.id=counter;
        data.aText="text"+counter;
        while (true) {
            long handle = handles.get(data.id);
            anStructDataWriter.write(data, handle);
            data.id = counter%5;
            counter++;
            data.aText = "text" + counter;
            Thread.sleep(500);
            System.out.println("va");
        }
    }

    private void startReading() {
        new Thread() {
            public void run() {

                StatusCondition statuscondition = reader.get_statuscondition();
                statuscondition.set_enabled_statuses(DATA_AVAILABLE_STATUS.value);

                WaitSet ws = new WaitSet();
                ws.attach_condition(statuscondition);

                Duration_t duration = new Duration_t();
                duration.sec = 10000;

                ConditionSeqHolder conditions = new ConditionSeqHolder();

                while (true) {
                    AnStructSeqHolder seqHolder = new AnStructSeqHolder();
                    SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();


                    int waitStatus = ws._wait(conditions, duration);
                    if (waitStatus == RETCODE_OK.value) {

                        System.out.println("Fin wait " + conditions.value.length);
                        reader.take(
                                seqHolder,
                                infoSeq,
                                LENGTH_UNLIMITED.value,
//                                ANY_SAMPLE_STATE.value,
                                DDS.NOT_READ_SAMPLE_STATE.value,
//                                DDS.NEW_VIEW_STATE.value,
                                ANY_VIEW_STATE.value,
                                ALIVE_INSTANCE_STATE.value);


                        if (null != seqHolder.value) {
                            for (int i = 0; i < seqHolder.value.length; i++) {
                                System.out.println(seqHolder.value[i].id);
                                System.out.println(seqHolder.value[i].aText);

                            }
                            System.out.println("-----------");
                        }
                    }


                }
            }
        }.start();
    }

    private void createReader() {
        DataReader datareader = subscriber.create_datareader(
                anStructTopic,
                DATAREADER_QOS_USE_TOPIC_QOS.value,
                null,
                STATUS_MASK_NONE.value);

        reader = AnStructDataReaderHelper.narrow(datareader);
    }

    private void createSubscriber() {
        SubscriberQosHolder subQos = new SubscriberQosHolder();
        participant.get_default_subscriber_qos(subQos);
        subQos.value.partition.name = new String[1];
        subQos.value.partition.name[0] = partitionName;

        subscriber = participant.create_subscriber(
                subQos.value, null, STATUS_MASK_NONE.value);
    }

    private void createWriter() {

        DataWriter dataWriter = publisher.create_datawriter(
                anStructTopic,
                DATAWRITER_QOS_USE_TOPIC_QOS.value,
                null,
                STATUS_MASK_NONE.value);

        anStructDataWriter = AnStructDataWriterHelper.narrow(dataWriter);
    }

    private void createPublisher() {

        PublisherQosHolder publisherQosHolder = new PublisherQosHolder();
        participant.get_default_publisher_qos(publisherQosHolder);
        publisherQosHolder.value.partition.name = new String[1];
        publisherQosHolder.value.partition.name[0] = partitionName;

        publisher = participant.create_publisher(
                publisherQosHolder.value, null, STATUS_MASK_NONE.value);
        System.out.println("registrado publisher");
    }

    private void createTopic() {

        TopicQosHolder topicQosHolder = new TopicQosHolder();
        participant.get_default_topic_qos(topicQosHolder);
        topicQosHolder.value.reliability.kind =
                ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
        topicQosHolder.value.durability.kind = DurabilityQosPolicyKind.TRANSIENT_DURABILITY_QOS;

        anStructTopic = participant.create_topic(
                TOPIC_NAME,
                anStructTypeName,
                topicQosHolder.value,
                null,
                STATUS_MASK_NONE.value);

        System.out.println("registrado topic");
    }

    private void registerType() {
        AnStructTypeSupport anStructTS = new AnStructTypeSupport();
        anStructTypeName = anStructTS.get_type_name();
        anStructTS.register_type(participant, anStructTypeName);
        System.out.println("registrado tipo");
    }

    private void createParticipant() {
        int domain = DOMAIN_ID_DEFAULT.value;

        participant = domainParticipantFactory.create_participant(
                domain, PARTICIPANT_QOS_DEFAULT.value, null, STATUS_MASK_NONE.value
        );
        System.out.println("Creado domain participant");
    }

    private void createDomainParticipantFactory() {
        domainParticipantFactory = DomainParticipantFactory.get_instance();
    }
}
