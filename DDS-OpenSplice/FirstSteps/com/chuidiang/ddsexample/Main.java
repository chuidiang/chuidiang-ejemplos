package com.chuidiang.ddsexample;

import DDS.*;
import First.*;

public class Main {

    private final String partitionName = new String("AnStructPartition");
    private DomainParticipantFactory domainParticipantFactory;
    private DomainParticipant participant;
    private AnStructTypeSupport anStructTS;
    private String anStructTypeName;
    private TopicQosHolder topicQosHolder;
    private Topic anStructTopic;
    private PublisherQosHolder publisherQosHolder;
    private Publisher publisher;
    private DataWriter parentWriter;
    private AnStructDataWriter talker;
    private SubscriberQosHolder subQos;
    private Subscriber chatSubscriber;
    private DataReader datareader;
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


        startWriting();
        return;
    }

    private void startWriting() throws InterruptedException {
        AnStruct data = new AnStruct();
        data.id = 1;
        data.aText = "text" + data.id;

        while (true) {
            talker.write(data, HANDLE_NIL.value);
            data.id++;
            data.aText = "text" + data.id;
            Thread.sleep(1000);
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


                    ws._wait(conditions, duration);
                    System.out.println("Fin wait " + conditions.value.length);
                    int status = reader.take(
                            seqHolder,
                            infoSeq,
                            LENGTH_UNLIMITED.value,
                            ANY_SAMPLE_STATE.value,
                            ANY_VIEW_STATE.value,
                            ALIVE_INSTANCE_STATE.value);


                    if (null != seqHolder.value) {
                        for (int i = 0; i < seqHolder.value.length; i++) {
                            System.out.println(seqHolder.value[i].id);
                            System.out.println(seqHolder.value[i].aText);
                            System.out.println("-----------");
                        }
                    }


                }
            }
        }.start();
    }

    private void createReader() {
        datareader = chatSubscriber.create_datareader(
                anStructTopic,
                DATAREADER_QOS_USE_TOPIC_QOS.value,
                null,
                STATUS_MASK_NONE.value);
        reader = AnStructDataReaderHelper.narrow(datareader);
    }

    private void createSubscriber() {
        int status;
        subQos = new SubscriberQosHolder();
        status = participant.get_default_subscriber_qos(subQos);
        subQos.value.partition.name = new String[1];
        subQos.value.partition.name[0] = partitionName;

        chatSubscriber = participant.create_subscriber(
                subQos.value, null, STATUS_MASK_NONE.value);
        chatSubscriber.set_default_datareader_qos(new DataReaderQos());
    }

    private void createWriter() {
        DataWriterQosHolder dataWriterQosHolder = new DataWriterQosHolder();
        publisher.get_default_datawriter_qos(dataWriterQosHolder);
        publisher.copy_from_topic_qos(dataWriterQosHolder, topicQosHolder.value);
        dataWriterQosHolder.value.writer_data_lifecycle.autodispose_unregistered_instances = false;

        parentWriter = publisher.create_datawriter(
                anStructTopic,
                dataWriterQosHolder.value,
                null,
                STATUS_MASK_NONE.value);

        talker = AnStructDataWriterHelper.narrow(parentWriter);
    }

    private void createPublisher() {

        publisherQosHolder = new PublisherQosHolder();
        participant.get_default_publisher_qos(publisherQosHolder);
        publisherQosHolder.value.partition.name = new String[1];
        publisherQosHolder.value.partition.name[0] = partitionName;

        publisher = participant.create_publisher(
                publisherQosHolder.value, null, STATUS_MASK_NONE.value);
        System.out.println("registrado publisher");
    }

    private void createTopic() {

        topicQosHolder = new TopicQosHolder();
        participant.get_default_topic_qos(topicQosHolder);
        topicQosHolder.value.reliability.kind =
                ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
        topicQosHolder.value.durability.kind = DurabilityQosPolicyKind.TRANSIENT_DURABILITY_QOS;

        anStructTopic = participant.create_topic(
                "Struct_1",
                anStructTypeName,
                topicQosHolder.value,
                null,
                STATUS_MASK_NONE.value);

        System.out.println("registrado topic");
    }

    private void registerType() {
        anStructTS = new AnStructTypeSupport();
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
